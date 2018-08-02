package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshScrollView psv;
    private ListView listView;
    private ViewPager viewPager;
    private LinearLayout linpoint;
    private int operType=1;
    private int pageIndex=1;

    private String myurl="http://365jia.cn/news/api3/365jia/news/headline?page="+pageIndex;

    private  List<ResultData.DataBeanX.DataBean> datas=new ArrayList<>();
    private MyTask myTask;
    private List<String> imgUrls=new ArrayList<>();

    private List<ImageView> lists=new ArrayList<>();
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int cIndex=viewPager.getCurrentItem();
            cIndex++;
            viewPager.setCurrentItem(cIndex);

            //改变指示器
            setSelectedPoint(cIndex%lists.size());


            sendEmptyMessageDelayed(0,1000);
        }
    };
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        psv = findViewById(R.id.psv);
        listView = findViewById(R.id.lv);
        viewPager = findViewById(R.id.vp);
        linpoint = findViewById(R.id.lin_point);

        initPsv();

        //请求网络数据
        requestNetData();
    }

    private void requestNetData() {
        new MyTask().execute(myurl);

    }



    class MyTask extends AsyncTask<String, Void, String> {
        //在后台执行的任务
        @Override
        protected String doInBackground(String... strings) {//String... 可变长的字符串  string类型的数组，长度不固定
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();
                String s = zhuanhuan(inputStream);
                return s;

            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }
        //在doInBackground之后，调用的方法，，，更新UI的操作，相当于handlerMessage中的功能
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
    //解析+展示
            Gson gson = new Gson();

            ResultData resultData = gson.fromJson(s, ResultData.class);
            List<ResultData.DataBeanX.DataBean> data = resultData.getData().getData();

//展示轮播图
            showPics(data);

            if(operType==1){
                datas.clear();
            }

            datas.addAll(data);
            setAdapter();
            psv.onRefreshComplete();




        }
        //在doInBackground之前执行的方法，一般用于提示用户
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "正在进行网络请求，请稍后。。。。", Toast.LENGTH_SHORT).show();
        }

    }



    private void showPics(List<ResultData.DataBeanX.DataBean> data) {
        imgUrls.clear();
        imgUrls.add("http://365jia.cn/uploads/"+data.get(0).getPics().get(0));
        imgUrls.add("http://365jia.cn/uploads/"+data.get(1).getPics().get(0));
        imgUrls.add("http://365jia.cn/uploads/"+data.get(2).getPics().get(0));
        MyVpAdapter adapter = new MyVpAdapter(imgUrls, MainActivity.this);

        viewPager.setAdapter(adapter);
        initPoint();
        
        mHandler.sendEmptyMessageDelayed(0,1000);
    }


    private void setAdapter() {
        if (adapter==null){
            adapter = new ListViewAdapter(datas,MainActivity.this);
        }
    }

    //设置选中的小圆点
    private  void setSelectedPoint(int pageIndex){
        for (int i = 0; i <lists.size() ; i++) {
            if(i==pageIndex){
//                lists.get(i).setImageResource(R.drawable.point_selected);
            }else {
//                lists.get(i).setImageResource(R.drawable.point_unselected);
            }

        }

    }
    private void initPoint() {
        for (int i = 0; i < imgUrls.size(); i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,10,0);
            if (i==0){
                imageView.setImageResource(R.drawable.);
            }
        }
    }

    /**
     * 将流转换成String
     */
    private String zhuanhuan(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        String str;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            while ((str=reader.readLine())!=null){
                builder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }{
    }

    private void initPsv() {
    }
}
