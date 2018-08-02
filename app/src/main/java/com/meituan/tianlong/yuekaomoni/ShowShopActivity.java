package com.meituan.tianlong.yuekaomoni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.meituan.tianlong.yuekaomoni.mvp.model.shop.beans.ShopData;
import com.meituan.tianlong.yuekaomoni.mvp.presenter.shop.ShopPresenter;
import com.meituan.tianlong.yuekaomoni.mvp.view.Adapter.shop.GlideImageLoader;
import com.meituan.tianlong.yuekaomoni.mvp.view.shop.ShopView;
import com.meituan.tianlong.yuekaomoni.network.Ok;
import com.meituan.tianlong.yuekaomoni.network.OkCallBack;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowShopActivity extends AppCompatActivity implements ShopView{
    private Banner banner;
    private Button button;
    public String pid;
    private List<String> list = new ArrayList<>();
    private ShopPresenter shopPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shop);
        initview();

        shopPresenter= new ShopPresenter(this);

    }

    private void initview() {
        banner = findViewById(R.id.banner);
        button = findViewById(R.id.gouwu);

        banner.setImageLoader(new GlideImageLoader());
        String images = getIntent().getStringExtra("images");
        pid = getIntent().getStringExtra("pid");



        Log.i("TAG",pid+"");
        String split[] = images.split("\\|");

        for (int i = 0; i <split.length ; i++) {
            list.add(split[i]);
        }
        Toast.makeText(this,list+"",Toast.LENGTH_SHORT).show();
        banner.setImages(list);

        banner.start();



        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                shopPresenter.getCart(pid);
                Intent intent = new Intent(ShowShopActivity.this,GouWuActivity.class);
                startActivity(intent);
            }
        });
    }

    //展示
    @Override
    public void showCart(ShopData data) {
        Toast.makeText(this,data.msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shopPresenter.Destory();
    }
}
