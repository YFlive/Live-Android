package com.yuanfen.mall.views;

import android.app.Dialog;
import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.yuanfen.common.adapter.RefreshAdapter;
import com.yuanfen.common.custom.CommonRefreshView;
import com.yuanfen.common.http.HttpCallback;
import com.yuanfen.common.utils.DialogUitl;
import com.yuanfen.common.utils.ToastUtil;
import com.yuanfen.common.utils.WordUtil;
import com.yuanfen.common.views.AbsCommonViewHolder;
import com.yuanfen.mall.R;
import com.yuanfen.mall.activity.GoodsDetailActivity;
import com.yuanfen.mall.activity.GoodsEditSpecActivity;
import com.yuanfen.mall.activity.SellerManageGoodsActivity;
import com.yuanfen.mall.adapter.SellerZaiShouAdapter;
import com.yuanfen.mall.bean.GoodsManageBean;
import com.yuanfen.mall.http.MallHttpUtil;

import java.util.Arrays;
import java.util.List;

public class SellerZaiShouViewHolder extends AbsCommonViewHolder implements SellerZaiShouAdapter.ActionListener {

    private CommonRefreshView mRefreshView;
    private SellerZaiShouAdapter mAdapter;

    public SellerZaiShouViewHolder(Context context, ViewGroup parentView) {
        super(context, parentView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_seller_manage_goods;
    }

    @Override
    public void init() {
        mRefreshView = findViewById(R.id.refreshView);
        mRefreshView.setEmptyLayoutId(R.layout.view_no_data_goods_seller);
        mRefreshView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRefreshView.setDataHelper(new CommonRefreshView.DataHelper<GoodsManageBean>() {
            @Override
            public RefreshAdapter<GoodsManageBean> getAdapter() {
                if (mAdapter == null) {
                    mAdapter = new SellerZaiShouAdapter(mContext);
                    mAdapter.setActionListener(SellerZaiShouViewHolder.this);
                }
                return mAdapter;
            }

            @Override
            public void loadData(int p, HttpCallback callback) {
                MallHttpUtil.getManageGoodsList("onsale", p, callback);
            }

            @Override
            public List<GoodsManageBean> processData(String[] info) {
                return JSON.parseArray(Arrays.toString(info), GoodsManageBean.class);
            }

            @Override
            public void onRefreshSuccess(List<GoodsManageBean> list, int listCount) {

            }

            @Override
            public void onRefreshFailure() {

            }

            @Override
            public void onLoadMoreSuccess(List<GoodsManageBean> loadItemList, int loadItemCount) {

            }

            @Override
            public void onLoadMoreFailure() {

            }
        });
    }

    @Override
    public void loadData() {
        if (mRefreshView != null) {
            mRefreshView.initData();
        }
    }


    @Override
    public void onItemClick(GoodsManageBean bean) {
        GoodsDetailActivity.forward(mContext, bean.getId(), bean.getType());
    }

    @Override
    public void onPriceNumClick(GoodsManageBean bean) {
        GoodsEditSpecActivity.forward(mContext, bean.getId());
    }

    @Override
    public void onXiaJiaClick(final GoodsManageBean bean) {
        new DialogUitl.Builder(mContext)
                .setContent(WordUtil.getString(R.string.mall_381))
                .setCancelable(true)
                .setBackgroundDimEnabled(true)
                .setClickCallback(new DialogUitl.SimpleCallback() {
                    @Override
                    public void onConfirmClick(Dialog dialog, String content) {
                        MallHttpUtil.goodsUpStatus(bean.getId(), -1, new HttpCallback() {
                            @Override
                            public void onSuccess(int code, String msg, String[] info) {
                                if (code == 0) {
                                    if (mRefreshView != null) {
                                        mRefreshView.initData();
                                    }
                                    ((SellerManageGoodsActivity) mContext).getGoodsNum();
                                } else {
                                    ToastUtil.show(msg);
                                }
                            }
                        });
                    }
                })
                .build()
                .show();

    }
}