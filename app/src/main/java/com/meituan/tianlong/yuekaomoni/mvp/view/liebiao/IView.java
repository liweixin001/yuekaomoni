package com.meituan.tianlong.yuekaomoni.mvp.view.liebiao;

import com.meituan.tianlong.yuekaomoni.BasePresent;
import com.meituan.tianlong.yuekaomoni.mvp.model.liebiao.beans.DataBean;

import java.util.List;

public interface IView{
        void ShowData(List<DataBean> data);
}
