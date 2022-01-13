package com.yuanfen.live.activity;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

import com.yuanfen.common.Constants;
import com.yuanfen.common.activity.AbsActivity;
import com.yuanfen.common.bean.UserBean;
import com.yuanfen.common.utils.WordUtil;
import com.yuanfen.live.R;
import com.yuanfen.live.views.LiveRecordViewHolder;

/**
 * Created by cxf on 2018/9/30.
 */

public class LiveRecordActivity extends AbsActivity {

    public static void forward(Context context, UserBean userBean) {
        if (userBean == null) {
            return;
        }
        Intent intent = new Intent(context, LiveRecordActivity.class);
        intent.putExtra(Constants.USER_BEAN, userBean);
        context.startActivity(intent);
    }

    private UserBean mUserBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_live_record;
    }

    @Override
    protected void main() {
        setTitle(WordUtil.getString(R.string.live_record));
        mUserBean = getIntent().getParcelableExtra(Constants.USER_BEAN);
        if (mUserBean == null) {
            return;
        }
        LiveRecordViewHolder liveRecordViewHolder = new LiveRecordViewHolder(mContext, (ViewGroup) findViewById(R.id.container),mUserBean.getId());
        liveRecordViewHolder.setActionListener(new LiveRecordViewHolder.ActionListener() {
            @Override
            public UserBean getUserBean() {
                return mUserBean;
            }
        });
        liveRecordViewHolder.addToParent();
        liveRecordViewHolder.subscribeActivityLifeCycle();
        liveRecordViewHolder.loadData();
    }
}
