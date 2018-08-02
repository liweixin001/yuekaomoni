package com.meituan.tianlong.yuekaomoni.mvp.presenter.liebiao;

import com.meituan.tianlong.yuekaomoni.BasePresent;

public interface IPresenter extends BasePresent{
    public void getData(int page,String keywords,int sort);
}
