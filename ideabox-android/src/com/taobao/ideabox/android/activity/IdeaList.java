package com.taobao.ideabox.android.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.taobao.ideabox.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-9
 * Time: 下午4:33
 * 显示Idea列表的Activity
 */
public class IdeaList extends ListActivity {
    private ListView listView;
    private List<Map<String, Object>> list;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleAdapter adapter = new SimpleAdapter(this,getData(), R.layout.idea_list,
                new String[]{"title","info","userImage","audioImage","imageDesc"},
                new int[]{R.id.title,R.id.description,R.id.userImage,R.id.audioImage,R.id.imageDesc});
        setListAdapter(adapter);
        System.out.println("ideaList onCreate()");

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            returnToMain();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void returnToMain(){
        Intent i = new Intent();
        i.putExtra("result_text","IdeaList已展示完毕，返回至主界面！");
        setResult(Activity.RESULT_OK,i);
        finish();
    }

    /**
     * 创建菜单
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,1,"返回主界面");
        return true;
    }

    /**
     * 菜单点击事件
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 1:
                returnToMain();
                break;
        }
        return false;
    }

    private List<Map<String,Object>> getData(){
        list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "G1");
        map.put("info", "google 1");
        map.put("userImage", R.drawable.people);
        map.put("audioImage", R.drawable.voicerecorder);
        map.put("imageDesc", R.drawable.albums);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G2");
        map.put("info", "google 2");
        map.put("userImage", R.drawable.people);
        map.put("audioImage", R.drawable.voicerecorder);
        map.put("imageDesc", R.drawable.albums);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G3");
        map.put("info", "google 3");
        map.put("userImage", R.drawable.people);
        map.put("audioImage", R.drawable.voicerecorder);
        map.put("imageDesc", R.drawable.albums);
        list.add(map);
        return list;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String msg = "你点击了"+list.get(position).get("title")+"点子！";
        Toast.makeText(getApplicationContext(),msg,5).show();
    }
}