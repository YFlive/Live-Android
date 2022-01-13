package com.yuanfen.main.activity;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

import com.yuanfen.common.Constants;
import com.yuanfen.common.activity.AbsActivity;
import com.yuanfen.main.R;
import com.yuanfen.main.views.MainListViewHolder;

/**
 * 排行榜
 */
public class RankActivity extends AbsActivity {

    public static void forward(Context context, int position) {
        Intent intent = new Intent(context, RankActivity.class);
        intent.putExtra(Constants.LIVE_POSITION, position);
        context.startActivity(intent);
    }

    @Override
    protected boolean isStatusBarWhite() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rank;
    }

    @Override
    protected void main() {
        int position = getIntent().getIntExtra(Constants.LIVE_POSITION, 0);
        MainListViewHolder viewHolder = new MainListViewHolder(mContext, (ViewGroup) findViewById(R.id.container));
        viewHolder.addToParent();
        viewHolder.subscribeActivityLifeCycle();
        viewHolder.loadData(position);
    }
}
