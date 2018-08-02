package com.meituan.tianlong.yuekaomoni.mvp.model.shop.jiekou;

import com.meituan.tianlong.yuekaomoni.network.OkCallBack;

import java.util.Map;

public interface AddCart {
    void addCart(String url, Map<String,String> map, OkCallBack okCallBack);
}
