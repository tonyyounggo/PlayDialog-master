package com.example.nikeru8.playdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    //private TextView m_tv_message;
    //private int mChoice;
    private TextView m_tv_message;
    private int mChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init();
        init();

    }

   //OptionMenu，ActionBar上面三個點點
   @Override
   public boolean onCreateOptionsMenu(Menu menu){
       menu.add(0,1,Menu.NONE,"第一次發起攻擊");
       menu.add(0,2,Menu.NONE,"第二次場地掃蕩");
       menu.add(0,3,Menu.NONE,"還來");
       menu.add(0,4,Menu.NONE,"有完沒完啊.....");
       return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item){
       switch (item.getItemId()){
           case 1:
               Toast.makeText(MainActivity.this, "勝利", Toast.LENGTH_SHORT).show();
               break;
           case 2:
               Toast.makeText(MainActivity.this,"清除", Toast.LENGTH_SHORT).show();
               break;
           case 3:
               Toast.makeText(MainActivity.this,"再進攻", Toast.LENGTH_SHORT).show();
               break;
           case 4:
               Toast.makeText(MainActivity.this,"累了",Toast.LENGTH_SHORT).show();
               break;
       }
            return true;
   }


   //Button 1 先建立內容   //Button1 implments DialogInterface.OnClickListener 必須要override的方法
   private void init(){ m_tv_message=(TextView)findViewById(R.id.message);}
    public void 對話框(View viw){
        new AlertDialog.Builder(this)
                .setMessage("路上車好塞啊！")
                .setPositiveButton("紅綠燈造成的",this)
                .setNegativeButton("有車禍在前面",this)
                .show();

    }
    //在建立動作 作為複寫＠Override
    @Override
    public void onClick(DialogInterface dialogInterface,int wich){
        m_tv_message.setText("原來如此");
    }




    //Button2 clickAlertDialogYesNo
    //因為MainActivity介面的DialogInterface.OnClickListener在Button1已經使用過了
    //所以在Button2我們在下面自己寫了一個方法
    //並寫入clcikAlertDialogYesNo

    public void 對話框02(View view){
        AlertDialogYesNoListener listener=new AlertDialogYesNoListener();
        new AlertDialog.Builder(this).setMessage("發生啥事").setPositiveButton("前面有車禍",listener)
                .setNegativeButton("快繞過去",listener).show();}

    private class AlertDialogYesNoListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialogInterface, int wich){
            switch (wich){
                case DialogInterface.BUTTON_POSITIVE:m_tv_message.setText("來去看熱鬧");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:m_tv_message.setText("別去惹事");
                    break;
            }
        }
    }




   /* //Button3使用內部類別，直接把DialogInterface.OnClickListener這方法寫在內部
    //new DialogInterface.OnClickListener就是我們在Button2的時候寫在外面的方法
*/


   public void 對話框03(View view){
       new AlertDialog.Builder(this).setMessage("好事在誰頭上發生")
               .setPositiveButton("我",new DialogInterface.OnClickListener(){
                   @Override //Override 第一次 顯示在TextView上
                   public void onClick(DialogInterface dialogInterface, int wich){
                       m_tv_message.setText("Me");
                   }
               })
               .setNegativeButton("你", new DialogInterface.OnClickListener(){
                   @Override //Override 第二次 顯示在TextView上
                   public void onClick(DialogInterface dialogInterface,int wich){
                       m_tv_message.setText("You");
                   }
               })
               .setNeutralButton("他",new DialogInterface.OnClickListener(){
                   @Override //Override 第三次 顯示在TextView上
                   public void onClick(DialogInterface dialogInterface,int wich){
                       m_tv_message.setText("Him");
                   }
               }).show();}





    //按下button4
    //建造陣列=把string.xml內的response的string-array內的東西丟入

    public void Button4(View view){
        final String[]response02=getResources().getStringArray(R.array.response02);
        new AlertDialog.Builder(this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar)
                //這邊必須用setTitle，不能像上面三個Button一樣使用setMessage
                .setTitle("科學小飛俠成員")
                //setItems的用法
                .setItems(response02,new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int wich){
                        m_tv_message.setText(response02[wich]);
                    }
                }).show();

    }



//Button5
    public void multiPicks(View view){
        final String[]response02=getResources().getStringArray(R.array.response02);
        final boolean[] selected=new boolean[response02.length];//記錄每個選項的勾選狀態
        new AlertDialog.Builder(this)
                .setTitle("誰要出動")
                .setMultiChoiceItems(response02,selected,new DialogInterface.OnMultiChoiceClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int wich,boolean ischecked){
                        selected[wich]=ischecked;
                    }
                }).setPositiveButton("出動",new DialogInterface.OnClickListener(){
                    @Override
            public void onClick(DialogInterface dialogInterface, int wich){
                        StringBuilder result=new StringBuilder();
                        for (int i=0; i<selected.length; i++){
                            if (selected[i]){
                                result.append(response02[i]).append("\n");//指定插入內容
                            }

                        }m_tv_message.setText(result);

                    }
        })
                .setNegativeButton("待命",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int wich) {
                        m_tv_message.setText("修整再出發");
                    }


                }).show();

    }





    public void pickOne(View view){
        final String[] response=getResources().getStringArray(R.array.response02);
        new AlertDialog.Builder(this)
                .setTitle("哪一個要上")
                .setSingleChoiceItems(response,0,new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int wich){
                        mChoice=wich;
                    }
                })
                .setPositiveButton("決定是你了", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface,int wich){
                        m_tv_message.setText(response[mChoice]);
                    }

                })
                .setNegativeButton("取消出擊", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int wich ){
                        m_tv_message.setText("機動待命");
                    }

                }).show();


    }
}






















