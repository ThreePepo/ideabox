package com.taobao.ideabox.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.taobao.ideabox.R;

/**
 * User: john
 * Date: 12-12-4
 * Time: 上午12:09
 * To change this template use File | Settings | File Templates.
 */
public class DoneActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.done);

        Button button = (Button) findViewById(R.id.btn_ok);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }

}
