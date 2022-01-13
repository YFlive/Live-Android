package com.yuanfen.mall.views;

import android.content.Context;
import android.view.ViewGroup;

import com.yuanfen.mall.activity.SellerSendActivity;
import com.yuanfen.mall.adapter.SellerOrderBaseAdapter;
import com.yuanfen.mall.adapter.SellerOrderSendAdapter;
import com.yuanfen.mall.bean.SellerOrderBean;

/**
 * 卖家 订单列表 待发货
 */
public class SellerOrderSendViewHolder extends AbsSellerOrderViewHolder {

    public SellerOrderSendViewHolder(Context context, ViewGroup parentView) {
        super(context, parentView);
    }

    @Override
    public String getOrderType() {
        return "wait_shipment";
    }

    @Override
    public SellerOrderBaseAdapter getSellerOrderAdapter() {
        return new SellerOrderSendAdapter(mContext);
    }

    /**
     * 点击item
     */
    @Override
    public void onItemClick(SellerOrderBean bean) {
        SellerSendActivity.forward(mContext, bean.getId());
    }

}
