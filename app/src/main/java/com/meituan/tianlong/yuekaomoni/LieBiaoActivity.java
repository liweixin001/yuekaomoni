package com.meituan.tianlong.yuekaomoni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meituan.tianlong.yuekaomoni.mvp.model.liebiao.beans.DataBean;
import com.meituan.tianlong.yuekaomoni.mvp.model.liebiao.beans.LieBiaoBean;
import com.meituan.tianlong.yuekaomoni.mvp.presenter.liebiao.Presenter;
import com.meituan.tianlong.yuekaomoni.mvp.view.Adapter.liebiao.LieBiaoAdapter;
import com.meituan.tianlong.yuekaomoni.mvp.view.liebiao.IView;
import com.meituan.tianlong.yuekaomoni.network.Ok;
import com.meituan.tianlong.yuekaomoni.network.OkCallBack;
import com.meituan.tianlong.yuekaomoni.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class LieBiaoActivity extends AppCompatActivity implements IView {
    private XRecyclerView xRecyclerView;
    private EditText editText;
    private LieBiaoAdapter lieBiaoAdapter;
    //注入p层
    Presenter presenter;

    int page = 0 ;
    int sort = 0;
    String keywords;
    List<DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lie_biao);

        //初始化
        initview();

        presenter = new Presenter(this);

        keywords = getIntent().getStringExtra("keywords");

        presenter.getData(page,keywords,sort);
    }

    private void initview() {
        xRecyclerView = findViewById(R.id.xrecycler);
        xRecyclerView.setLoadingMoreEnabled(true);//加载更多
        xRecyclerView.setPullRefreshEnabled(true);//下拉刷新
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 0 ;
                getData(page,keywords,sort);
            }
            @Override
            public void onLoadMore() {
                page++;
                getData(page,keywords,sort);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(manager);
        //分割线
        xRecyclerView.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));



        //适配器
        lieBiaoAdapter = new LieBiaoAdapter(LieBiaoActivity.this);
        xRecyclerView.setAdapter(lieBiaoAdapter);
        //单击事件   适配器里的单击事件
        lieBiaoAdapter.setOnItemClickListener(new LieBiaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataBean dataBean) {
                Intent intent = new Intent(LieBiaoActivity.this,ShowShopActivity.class);
                int pid = dataBean.pid;
                String images = dataBean.images;
                intent.putExtra("images",images);
                intent.putExtra("pid",pid+"");
                startActivity(intent);
            }
        });
    }

    @Override
    public void ShowData(List<DataBean> data) {
            lieBiaoAdapter.addData(data);
    }


    //获取数据
    private void getData(int page, String keywords, int sort) {
        String url = Constants.HOST_URL;
        Ok.getOk().doGet(url + "searchProducts?keywords=" + keywords + "&page=" + page + "&sort=" + sort, new OkCallBack() {
            @Override
            public void success(String json) {
                Gson gson = new Gson();
                LieBiaoBean lieBiaoBean = gson.fromJson(json, LieBiaoBean.class);

                lieBiaoAdapter.addData(lieBiaoBean.data);
                xRecyclerView.refreshComplete();
                xRecyclerView.loadMoreComplete();
            }

            @Override
            public void failed(String json) {
                xRecyclerView.refreshComplete();
                xRecyclerView.loadMoreComplete();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.Destory();
    }
}
