package com.taobao.ideabox;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.taobao.ideabox.android.server.ServerConnection;
import com.taobao.ideabox.android.server.UrlConstants;

public class HelloEntrance extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView tv = (TextView)findViewById(R.id.textView1);
        String result = ServerConnection.query(UrlConstants.QUERY_IDEAS);
        tv.setText(result);
    }
}
