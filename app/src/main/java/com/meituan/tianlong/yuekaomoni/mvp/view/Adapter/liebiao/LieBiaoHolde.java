package com.meituan.tianlong.yuekaomoni.mvp.view.Adapter.liebiao;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meituan.tianlong.yuekaomoni.R;

public class LieBiaoHolde extends XRecyclerView.ViewHolder{
    ImageView imageView;
    TextView title,price;
    public LieBiaoHolde(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        title = itemView.findViewById(R.id.title_item);
        price = itemView.findViewById(R.id.price_item);
    }
}
