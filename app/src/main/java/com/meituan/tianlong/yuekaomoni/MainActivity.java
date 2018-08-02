package com.meituan.tianlong.yuekaomoni;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fynn.fluidlayout.FluidLayout;
import com.meituan.tianlong.yuekaomoni.mvp.model.dao.MySql;
import com.meituan.tianlong.yuekaomoni.mvp.model.dao.SqliteDao;
import com.meituan.tianlong.yuekaomoni.mvp.view.Adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private FluidLayout fluidLayout;
    private ListView listView;
    private SqliteDao dao;
    private List<String> list;
    private MainAdapter adapter;
    private String mNAME[] = {
            "考拉三周年人气热销榜",
            "电动牙刷",
            "尤妮佳",
            "豆豆鞋",
            "沐浴露",
            "日东红茶",
            "榴莲",
            "电动牙刷",
            "雅诗莱黛",
            "豆豆鞋"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initview();
    }

    private void initview() {
        editText = findViewById(R.id.souzhou);
        button = findViewById(R.id.sousuo);
        fluidLayout = findViewById(R.id.liushi);
        listView = findViewById(R.id.list_ite);
        //数据库
        dao = new SqliteDao(this);
        list = new ArrayList<>();
        list  = dao.select();
        adapter = new MainAdapter(this,list);
        listView.setAdapter(adapter);

        //搜索跳转
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keywords = editText.getText().toString();
                dao.add(keywords);
                list = dao.select();
                adapter = new MainAdapter(MainActivity.this,list);
                listView.setAdapter(adapter);
                //跳转传值
                Intent intent = new Intent(MainActivity.this,LieBiaoActivity.class);
                intent.putExtra("keywords",keywords);
                startActivity(intent);
            }
        });



        //流式布局
        for (int i = 0; i <mNAME.length ; i++) {
            FluidLayout.LayoutParams params =
                    new FluidLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
            params.setMargins(12,12,12,12);
            TextView textView= new TextView(this);
            textView.setText(mNAME[i]);
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundResource(R.drawable.flow_yangshi);

            fluidLayout.addView(textView,params);
        }

    }
}
