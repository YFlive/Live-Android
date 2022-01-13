package com.yuanfen.mall.activity;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.GridLayoutManager;

import com.alibaba.fastjson.JSON;
import com.yuanfen.common.activity.AbsActivity;
import com.yuanfen.common.adapter.RefreshAdapter;
import com.yuanfen.common.custom.CommonRefreshView;
import com.yuanfen.common.custom.ItemDecoration;
import com.yuanfen.common.http.HttpCallback;
import com.yuanfen.common.utils.WordUtil;
import com.yuanfen.mall.R;
import com.yuanfen.mall.adapter.SellerAddPlatAdapter;
import com.yuanfen.mall.bean.GoodsPlatBean;
import com.yuanfen.mall.event.SetPlatGoodsEvent;
import com.yuanfen.mall.http.MallHttpConsts;
import com.yuanfen.mall.http.MallHttpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;

/**
 * 卖家添加平台商品
 */
public class SellerAddPlatActivity extends AbsActivity {

    public static void forward(Context context) {
        context.startActivity(new Intent(context, SellerAddPlatActivity.class));
    }

    private CommonRefreshView mRefreshView;
    private SellerAddPlatAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_seller_add_plat;
    }

    @Override
    protected void main() {
        setTitle(WordUtil.getString(R.string.mall_406));
        mRefreshView = findViewById(R.id.refreshView);
        mRefreshView.setEmptyLayoutId(R.layout.view_no_data_goods_plat);
        mRefreshView.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        ItemDecoration decoration = new ItemDecoration(mContext, 0x00000000, 10, 0);
        decoration.setOnlySetItemOffsetsButNoDraw(true);
        mRefreshView.setItemDecoration(decoration);
        mRefreshView.setDataHelper(new CommonRefreshView.DataHelper<GoodsPlatBean>() {
            @Override
            public RefreshAdapter<GoodsPlatBean> getAdapter() {
                if (mAdapter == null) {
                    mAdapter = new SellerAddPlatAdapter(mContext);
                }
                return mAdapter;
            }

            @Override
            public void loadData(int p, HttpCallback callback) {
                MallHttpUtil.getAddPlatGoods(p, callback);
            }

            @Override
            public List<GoodsPlatBean> processData(String[] info) {
                return JSON.parseArray(Arrays.toString(info), GoodsPlatBean.class);
            }

            @Override
            public void onRefreshSuccess(List<GoodsPlatBean> list, int listCount) {
            }

            @Override
            public void onRefreshFailure() {
                ((ShopHomeActivity) mContext).finishRefresh();
            }

            @Override
            public void onLoadMoreSuccess(List<GoodsPlatBean> loadItemList, int loadItemCount) {

            }

            @Override
            public void onLoadMoreFailure() {

            }
        });
        EventBus.getDefault().register(this);
        mRefreshView.initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSetPlatGoodsEvent(SetPlatGoodsEvent e) {
        if (mAdapter != null) {
            mAdapter.goodsStatusChanged(e.getGoodsId(), e.getStatus());
        }
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        MallHttpUtil.cancel(MallHttpConsts.GET_ADD_PLAT_GOODS);
        MallHttpUtil.cancel(MallHttpConsts.SET_PLAT_GOODS);
        super.onDestroy();
    }
}
