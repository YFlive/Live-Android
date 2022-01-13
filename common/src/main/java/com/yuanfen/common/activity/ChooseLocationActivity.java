package com.yuanfen.common.activity;

import android.content.Intent;
//import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuanfen.common.CommonAppConfig;
import com.yuanfen.common.Constants;
import com.yuanfen.common.R;
import com.yuanfen.common.adapter.ChooseLocationAdapter;
import com.yuanfen.common.adapter.RefreshAdapter;
import com.yuanfen.common.bean.TxLocationPoiBean;
import com.yuanfen.common.custom.CommonRefreshView;
import com.yuanfen.common.http.CommonHttpConsts;
import com.yuanfen.common.http.CommonHttpUtil;
import com.yuanfen.common.http.HttpCallback;
import com.yuanfen.common.interfaces.OnItemClickListener;
import com.yuanfen.common.utils.WordUtil;

import java.util.List;

/**
 * 选择位置地址
 */
public class ChooseLocationActivity extends AbsActivity implements OnItemClickListener<TxLocationPoiBean> {

    private CommonRefreshView mRefreshView;
    private ChooseLocationAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_location;
    }

    @Override
    protected void main() {
        setTitle(WordUtil.getString(R.string.location_1));
        mRefreshView = findViewById(R.id.refreshView);
        mRefreshView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRefreshView.setDataHelper(new CommonRefreshView.DataHelper<TxLocationPoiBean>() {
            @Override
            public RefreshAdapter<TxLocationPoiBean> getAdapter() {
                if (mAdapter == null) {
                    mAdapter = new ChooseLocationAdapter(mContext);
                    mAdapter.setOnItemClickListener(ChooseLocationActivity.this);
                }
                return mAdapter;
            }

            @Override
            public void loadData(int p, HttpCallback callback) {
                double lng = CommonAppConfig.getInstance().getLng();
                double lat = CommonAppConfig.getInstance().getLat();
                if (lng == 0 || lat == 0) {
                    return;
                }
                CommonHttpUtil.getAddressInfoByTxLocaitonSdk(lng, lat, 1, p, CommonHttpConsts.GET_MAP_INFO, callback);
            }

            @Override
            public List<TxLocationPoiBean> processData(String[] info) {
                JSONObject obj = JSON.parseObject(info[0]);
                if (obj != null) {
                    return JSON.parseArray(obj.getString("pois"), TxLocationPoiBean.class);
                }
                return null;
            }

            @Override
            public void onRefreshSuccess(List<TxLocationPoiBean> list, int listCount) {

            }

            @Override
            public void onRefreshFailure() {

            }

            @Override
            public void onLoadMoreSuccess(List<TxLocationPoiBean> loadItemList, int loadItemCount) {

            }

            @Override
            public void onLoadMoreFailure() {

            }
        });
        mRefreshView.initData();
    }


    @Override
    public void onItemClick(TxLocationPoiBean bean, int position) {
        Intent intent = new Intent();
        intent.putExtra(Constants.CHOOSE_LOCATION, bean != null ? bean.getTitle() : "");
        setResult(RESULT_OK, intent);
        finish();
    }
}
