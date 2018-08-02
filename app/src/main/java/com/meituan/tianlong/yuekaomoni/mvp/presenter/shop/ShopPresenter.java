package com.meituan.tianlong.yuekaomoni.mvp.presenter.shop;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.meituan.tianlong.yuekaomoni.ShowShopActivity;
import com.meituan.tianlong.yuekaomoni.mvp.model.shop.beans.ShopData;
import com.meituan.tianlong.yuekaomoni.mvp.model.shop.fangfa.AddCartTask;
import com.meituan.tianlong.yuekaomoni.mvp.view.shop.ShopView;
import com.meituan.tianlong.yuekaomoni.network.OkCallBack;
import com.meituan.tianlong.yuekaomoni.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class ShopPresenter implements IShopPresenter{

    //注入model
    AddCartTask task;
    //注入view
    ShopView view;

    public ShopPresenter(ShowShopActivity  activity) {
        task = new AddCartTask();
        this.view = activity;
    }

    @Override
    public void getCart(String pid) {


        Map<String,String> map = new HashMap<>();

        map.put("uid","4243");
        map.put("pid",pid);
        task.addCart(Constants.ADD_CARTS, map, new OkCallBack() {
            @Override
            public void success(String json) {
                Gson gson = new Gson();
                ShopData shopData = gson.fromJson(json, ShopData.class);
                if (TextUtils.equals(shopData.code,"0")){
                    view.showCart(shopData);
                }else {
                    view.showError(shopData.msg);
                }
            }

            @Override
            public void failed(String json) {

            }
        });
    }

    @Override
    public void Destory() {

    }
}
