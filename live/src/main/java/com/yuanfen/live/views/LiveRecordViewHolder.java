package com.yuanfen.live.views;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuanfen.common.CommonAppConfig;
import com.yuanfen.common.adapter.RefreshAdapter;
import com.yuanfen.common.bean.UserBean;
import com.yuanfen.common.custom.CommonRefreshView;
import com.yuanfen.common.http.HttpCallback;
import com.yuanfen.common.interfaces.OnItemClickListener;
import com.yuanfen.common.utils.FloatWindowHelper;
import com.yuanfen.common.utils.L;
import com.yuanfen.common.utils.ToastUtil;
import com.yuanfen.live.R;
import com.yuanfen.live.activity.LiveRecordPlayActivity;
import com.yuanfen.live.adapter.LiveRecordAdapter;
import com.yuanfen.live.bean.LiveRecordBean;
import com.yuanfen.live.http.LiveHttpConsts;
import com.yuanfen.live.http.LiveHttpUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cxf on 2018/10/18.
 */

public class LiveRecordViewHolder extends AbsUserHomeViewHolder implements OnItemClickListener<LiveRecordBean> {

    private CommonRefreshView mRefreshView;
    private LiveRecordAdapter mAdapter;
    private ActionListener mActionListener;
    private String mToUid;

    public LiveRecordViewHolder(Context context, ViewGroup parentView, String toUid) {
        super(context, parentView, toUid);
    }

    @Override
    protected void processArguments(Object... args) {
        if (args.length > 0) {
            mToUid = (String) args[0];
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_live_record;
    }

    @Override
    public void init() {
        if (TextUtils.isEmpty(mToUid)) {
            return;
        }
        mRefreshView = (CommonRefreshView) mContentView;
        mRefreshView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        if (mToUid.equals(CommonAppConfig.getInstance().getUid())) {
            mRefreshView.setEmptyLayoutId(R.layout.view_no_data_live_record);
        } else {
            mRefreshView.setEmptyLayoutId(R.layout.view_no_data_live_record_2);
        }
        mRefreshView.setDataHelper(new CommonRefreshView.DataHelper<LiveRecordBean>() {
            @Override
            public RefreshAdapter<LiveRecordBean> getAdapter() {
                if (mAdapter == null) {
                    mAdapter = new LiveRecordAdapter(mContext);
                    mAdapter.setOnItemClickListener(LiveRecordViewHolder.this);
                }
                return mAdapter;
            }

            @Override
            public void loadData(int p, HttpCallback callback) {
                LiveHttpUtil.getLiveRecord(mToUid, p, callback);
            }

            @Override
            public List<LiveRecordBean> processData(String[] info) {
                return JSON.parseArray(Arrays.toString(info), LiveRecordBean.class);
            }

            @Override
            public void onRefreshSuccess(List<LiveRecordBean> list, int listCount) {

            }

            @Override
            public void onRefreshFailure() {

            }

            @Override
            public void onLoadMoreSuccess(List<LiveRecordBean> loadItemList, int loadItemCount) {

            }

            @Override
            public void onLoadMoreFailure() {

            }
        });
    }

    @Override
    public void loadData() {
        if (isFirstLoadData()) {
            mRefreshView.initData();
        }
    }

    /**
     * 获取直播回放的url并跳转
     */
    private void fowardLiveRecord(String recordId) {
        if (!FloatWindowHelper.checkVoice(true)) {
            return;
        }
        LiveHttpUtil.getAliCdnRecord(recordId, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                if (code == 0) {
                    JSONObject object = JSON.parseObject(info[0]);
                    String url = object.getString("url");
                    L.e("直播回放的url--->" + url);
                    if (mActionListener != null) {
                        UserBean userBean = mActionListener.getUserBean();
                        if (userBean != null) {
                            LiveRecordPlayActivity.forward(mContext, url, userBean);
                        }
                    }
                } else {
                    ToastUtil.show(msg);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LiveHttpUtil.cancel(LiveHttpConsts.GET_LIVE_RECORD);
        mActionListener = null;
    }

    public void setActionListener(ActionListener actionListener) {
        mActionListener = actionListener;
    }

    @Override
    public void onItemClick(LiveRecordBean bean, int position) {
        fowardLiveRecord(bean.getId());
    }

    public interface ActionListener {
        UserBean getUserBean();
    }
}
