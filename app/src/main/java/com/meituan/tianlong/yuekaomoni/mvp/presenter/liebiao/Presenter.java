package com.meituan.tianlong.yuekaomoni.mvp.presenter.liebiao;

import com.google.gson.Gson;
import com.meituan.tianlong.yuekaomoni.LieBiaoActivity;
import com.meituan.tianlong.yuekaomoni.mvp.model.liebiao.beans.LieBiaoBean;
import com.meituan.tianlong.yuekaomoni.mvp.model.liebiao.fangfa.Task;
import com.meituan.tianlong.yuekaomoni.mvp.view.liebiao.IView;
import com.meituan.tianlong.yuekaomoni.network.OkCallBack;

public class Presenter extends OkCallBack implements IPresenter{
    //注入model
    Task task;

    //注入view层
    IView view;

    public Presenter(LieBiaoActivity activity) {
        task = new Task();
        view = activity;
    }

    @Override
    public void getData(int page, String keywords, int sort) {

        task.getRecyclerView("https://www.zhaoapi.cn/product/searchProducts?keywords="+"手机"+"&page="+0+"&sort="+0,this);

    }

    @Override
    public void success(String json) {
        Gson gson = new Gson();
        LieBiaoBean lieBiaoBean = gson.fromJson(json, LieBiaoBean.class);
        view.ShowData(lieBiaoBean.data);
    }

    @Override
    public void failed(String json) {

    }

    @Override
    public void Destory() {
        view = null;
    }
}
