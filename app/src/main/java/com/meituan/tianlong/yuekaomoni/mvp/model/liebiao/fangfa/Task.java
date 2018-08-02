package com.meituan.tianlong.yuekaomoni.mvp.model.liebiao.fangfa;

import com.meituan.tianlong.yuekaomoni.mvp.model.liebiao.jiekou.ITask;
import com.meituan.tianlong.yuekaomoni.network.Ok;
import com.meituan.tianlong.yuekaomoni.network.OkCallBack;

public class Task implements ITask{

    @Override
    public void getRecyclerView(String url, OkCallBack okCallBack) {
        Ok.getOk().doGet(url,okCallBack);
    }
}
