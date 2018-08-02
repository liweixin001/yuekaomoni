package com.meituan.tianlong.yuekaomoni.mvp.view.shop;

import com.meituan.tianlong.yuekaomoni.mvp.model.shop.beans.ShopData;

public interface ShopView {
        void showCart(ShopData data);

        void showError(String error);
}
