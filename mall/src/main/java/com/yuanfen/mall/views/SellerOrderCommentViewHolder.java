package com.yuanfen.mall.views;

import android.content.Context;
import android.view.ViewGroup;

import com.yuanfen.mall.adapter.SellerOrderBaseAdapter;
import com.yuanfen.mall.adapter.SellerOrderReceiveAdapter;

/**
 * 卖家 订单列表 已签收，待评价
 */
public class SellerOrderCommentViewHolder extends AbsSellerOrderViewHolder {

    public SellerOrderCommentViewHolder(Context context, ViewGroup parentView) {
        super(context, parentView);
    }

    @Override
    public String getOrderType() {
        return "wait_evaluate";
    }

    @Override
    public SellerOrderBaseAdapter getSellerOrderAdapter() {
        return new SellerOrderReceiveAdapter(mContext);
    }

}
