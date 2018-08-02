package com.meituan.tianlong.yuekaomoni.mvp.view.Adapter.liebiao;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meituan.tianlong.yuekaomoni.R;
import com.meituan.tianlong.yuekaomoni.mvp.model.liebiao.beans.DataBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public  class LieBiaoAdapter extends XRecyclerView.Adapter<LieBiaoHolde> {
    List<DataBean> data;
    Context context;
    ImageLoader imageLoader;
    DisplayImageOptions options;
    private XRecyclerView xRecyclerView;
    private OnItemClickListener onItemClickListener;

    public LieBiaoAdapter(Context context) {
        this.data = new ArrayList<>();
        this.context = context;
        this.options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        imageLoader = ImageLoader.getInstance();
    }

    public void addData(List<DataBean> list){
        data.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LieBiaoHolde onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view= LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_xlist,viewGroup,false);
        LieBiaoHolde holde = new LieBiaoHolde(view);

        return holde;
    }

    @Override
    public void onBindViewHolder(@NonNull LieBiaoHolde lieBiaoHolde, int i) {
        final DataBean dataBean = data.get(i);

        lieBiaoHolde.title.setText(dataBean.title);
        lieBiaoHolde.price.setText("￥ "+dataBean.price+"");
        String url = dataBean.images.split("\\|")[0];
        imageLoader.displayImage(url,lieBiaoHolde.imageView,options);

        lieBiaoHolde.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(dataBean);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    /**
     * 1.以下是要在适配器中定义的单击事件
     *
     *
     * 定义 RecyclerView 选项单击事件的回调接口
     */
    public interface OnItemClickListener {
        void onItemClick(DataBean dataBean);
    }
    /**
     * 3.在适配器中声名 该接口 并提供setter方法
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 4 和 5  可写可不写
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }
}
