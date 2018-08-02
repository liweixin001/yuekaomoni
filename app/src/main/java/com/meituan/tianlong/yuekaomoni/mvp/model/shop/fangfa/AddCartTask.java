package com.meituan.tianlong.yuekaomoni.mvp.model.shop.fangfa;

import com.meituan.tianlong.yuekaomoni.mvp.model.shop.jiekou.AddCart;
import com.meituan.tianlong.yuekaomoni.network.Ok;
import com.meituan.tianlong.yuekaomoni.network.OkCallBack;

import java.util.Map;

public class AddCartTask implements AddCart{
    @Override
    public void addCart(String url, Map<String, String> map, OkCallBack okCallBack) {
        Ok.getOk().post(url,map,okCallBack);
    }
}
