package com.taobao.ideabox.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.taobao.ideabox.R;
import com.taobao.ideabox.android.server.ServerConnection;
import com.taobao.ideabox.android.server.UrlConstants;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class IdeaCreateActivity extends Activity {

    public static final int FILE_RESULT_CODE = 1;
    private TextView textView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Ïê¼ûStrictModeÎÄµµ
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idea_create);

        final Button button_save = (Button) findViewById(R.id.button_save);
        final Button button_share = (Button) findViewById(R.id.button_share);
        final Button button_pic = (Button) findViewById(R.id.button_pic);
        final EditText text_idea = (EditText) findViewById(R.id.text_idea);

        textView = (TextView) findViewById(R.id.text_pic) ;

        button_save.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String fileUrl = textView.getText().toString();
                Map<String, String> maps = new HashMap<String, String>();
                maps.put("tags", text_idea.getText().toString());
                maps.put("status", 0 + "");        //save
                String id = "";
                try{
                    String str = ServerConnection.sendPost(UrlConstants.URL_POST_IDEA_ADD, maps);
                    JSONObject obj = new JSONObject(str);
                    id = obj.get("ideaId").toString();
                } catch (Exception e){
                    return;
                }
                if("".equals(id)){
                    return;
                }
                //upload file
                ServerConnection.uploadFile(UrlConstants.URL_POST_IMG + "?id=" + id , fileUrl);
                Intent intent = new Intent(IdeaCreateActivity.this, DoneActivity.class);
                startActivity(intent);
            }
        });

        button_share.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String fileUrl = textView.getText().toString();
                Map<String, String> maps = new HashMap<String, String>();
                maps.put("tags", text_idea.getText().toString());
                maps.put("status", 1 + "");     //share
                String id = "";
                try{
                    String str = ServerConnection.sendPost(UrlConstants.URL_POST_IDEA_ADD, maps);
                    JSONObject obj = new JSONObject(str);
                    id = obj.get("ideaId").toString();
                } catch (Exception e){
                    return;
                }
                if("".equals(id)){
                    return;
                }
                ServerConnection.uploadFile(UrlConstants.URL_POST_IMG + "?id=" + id , fileUrl);
                Intent intent = new Intent(IdeaCreateActivity.this, DoneActivity.class);
                startActivity(intent);
            }
        });

        button_pic.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(IdeaCreateActivity.this,MyFileManager.class);
                startActivityForResult(intent, FILE_RESULT_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(FILE_RESULT_CODE == requestCode){
            Bundle bundle = null;
            if(data!=null&&(bundle=data.getExtras())!=null){
                textView.setText(bundle.getString("file"));
            }
        }
    }
}
