package com.taobao.ideabox.android.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.taobao.ideabox.HelloEntrance;
import com.taobao.ideabox.R;
import com.taobao.ideabox.android.common.MyAdapter;

/**
 * User: john
 * Date: 12-12-10
 * Time: ����10:29
 */
public class MyFileManager extends ListActivity {

    private List<String> items = null;
    private List<String> paths = null;
    private String rootPath = "/";
    private String curPath = "/";
    private String filePath = "/";
    private TextView mPath;
    private final static String TAG = "bb";
    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fileselect);
        mPath = (TextView) findViewById(R.id.mPath);
        Button buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent data = new Intent(MyFileManager.this, HelloEntrance.class);
                Bundle bundle = new Bundle();
                bundle.putString("file", mPath.getText().toString());
                data.putExtras(bundle);
                setResult(2, data);
                finish();
            }
        });
        Button buttonCancle = (Button) findViewById(R.id.buttonCancle);
        buttonCancle.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        getFileDir(rootPath);
    }
    private void getFileDir(String filePath) {
        mPath.setText(filePath);
        items = new ArrayList<String>();
        paths = new ArrayList<String>();
        File f = new File(filePath);
        File[] files = f.listFiles();
        if (!filePath.equals(rootPath)) {
            items.add("b1");
            paths.add(rootPath);
            items.add("b2");
            paths.add(f.getParent());
        }
        if(files != null){
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                items.add(file.getName());
                paths.add(file.getPath());
            }
        }
        setListAdapter(new MyAdapter(this, items, paths));
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        File file = new File(paths.get(position));
        if (file.isDirectory()) {
            curPath = paths.get(position);
            getFileDir(paths.get(position));
        } else {
            if(curPath.equals(rootPath)){
                filePath = curPath + file.getName();
            } else {
                filePath = curPath + "/" + file.getName();
            }

            mPath.setText(filePath);
        }
    }

}
