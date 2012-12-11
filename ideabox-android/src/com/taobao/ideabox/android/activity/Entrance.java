package com.taobao.ideabox.android.activity;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.taobao.ideabox.R;

/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-9
 * Time: ����4:56
 */
public class Entrance extends Activity{
    private final int IDEA_LIST_REQUEST_CODE = 4;
    private final int ADD_IDEA_REQUEST_CODE=1;
    private TextView notifyMsg; //��Ϣ��ʾ����

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("entrance oncreate()");
        setContentView(R.layout.main);
        //��ø�����Ŧ����ӵ���¼�
        notifyMsg = (TextView)findViewById(R.id.notifyMsg);
        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(mclickListener);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(mclickListener);
        Button button5 = (Button)findViewById(R.id.button5);
    }

    /**
     * ��Ӱ�Ŧ�¼�
     */
    private Button.OnClickListener mclickListener = new Button.OnClickListener(){
        public void onClick(View v){
             switch(v.getId()){
                 case R.id.button1:
                     break;
                 case R.id.button2:
                     break;
                 case R.id.button3:
                     break;
                 case R.id.button4:
                     Intent intent = new Intent(Entrance.this, IdeaList.class);
                     intent.putExtra("main_notify","�������������Ϣ��");
                     startActivityForResult(intent,IDEA_LIST_REQUEST_CODE);
                     break;
                 case R.id.button5:
                     break;
             }
        }
    };

    /**
     * �����˵�
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,1,"�˳�");
        return true;
    }


    /**
     * �˵�����¼�
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 1:
                //ȷ���Ƿ��˳�
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("ȷ���˳�ideabox?");
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setPositiveButton("��������",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                new AlertDialog.Builder(Entrance.this)
                                        .setMessage("��������").create().show();
                                Intent MyIntent = new Intent(Intent.ACTION_MAIN);
                                MyIntent.addCategory(Intent.CATEGORY_HOME);
                                startActivity(MyIntent);
                            }
                        });

                builder.setNegativeButton("ȡ��",

                        new DialogInterface.OnClickListener()

                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                               // Toast.makeText(Entrance.this, "�Ѿ�ȡ���˳�",
                               //         Toast.LENGTH_LONG).show();
                            }

                        });
                builder.create().show();
                //�˳�Ӧ��
                break;
        }
        return false;
    }

    /**
     * �õ��ر�
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IDEA_LIST_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            if(data != null){
                //��ô�IdeaList���صĽ��
                notifyMsg.setText(data.getStringExtra("result_text"));
            }
        }
    }
}