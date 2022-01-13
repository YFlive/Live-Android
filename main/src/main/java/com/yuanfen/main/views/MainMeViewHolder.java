package com.yuanfen.main.views;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.GridLayoutManager;
import  androidx.recyclerview.widget.RecyclerView;;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanfen.common.CommonAppConfig;
import com.yuanfen.common.HtmlConfig;
import com.yuanfen.common.activity.WebViewActivity;
import com.yuanfen.common.bean.LevelBean;
import com.yuanfen.common.bean.UserBean;
import com.yuanfen.common.bean.UserItemBean;
import com.yuanfen.common.glide.ImgLoader;
import com.yuanfen.common.interfaces.CommonCallback;
import com.yuanfen.common.interfaces.OnItemClickListener;
import com.yuanfen.common.utils.CommonIconUtil;
import com.yuanfen.common.utils.RouteUtil;
import com.yuanfen.common.utils.SpUtil;
import com.yuanfen.common.utils.StringUtil;
import com.yuanfen.im.activity.ChatActivity;
import com.yuanfen.live.activity.LiveRecordActivity;
import com.yuanfen.live.activity.RoomManageActivity;
import com.yuanfen.main.R;
import com.yuanfen.main.activity.DailyTaskActivity;
import com.yuanfen.main.activity.EditProfileActivity;
import com.yuanfen.main.activity.FamilyActivity;
import com.yuanfen.main.activity.FansActivity;
import com.yuanfen.main.activity.FollowActivity;
import com.yuanfen.main.activity.MyActiveActivity;
import com.yuanfen.main.activity.MyProfitActivity;
import com.yuanfen.main.activity.MyVideoActivity;
import com.yuanfen.main.activity.SettingActivity;
import com.yuanfen.main.activity.ThreeDistributActivity;
import com.yuanfen.main.adapter.MainMeAdapter;
import com.yuanfen.main.http.MainHttpConsts;
import com.yuanfen.main.http.MainHttpUtil;
import com.yuanfen.mall.activity.BuyerActivity;
import com.yuanfen.mall.activity.GoodsCollectActivity;
import com.yuanfen.mall.activity.PayContentActivity1;
import com.yuanfen.mall.activity.PayContentActivity2;
import com.yuanfen.mall.activity.SellerActivity;

import java.util.List;

/**
 * Created by cxf on 2018/9/22.
 * 我的
 */

public class MainMeViewHolder extends AbsMainViewHolder implements OnItemClickListener<UserItemBean>, View.OnClickListener {

    //    private AppBarLayout mAppBarLayout;
    private ImageView mAvatar;
    private TextView mName;
    private ImageView mSex;
    private ImageView mLevelAnchor;
    private ImageView mLevel;
    private TextView mID;
    private TextView mFollowNum;
    private TextView mFansNum;
    private TextView mCollectNum;
    private boolean mPaused;
    private RecyclerView mRecyclerView1;
    private RecyclerView mRecyclerView2;
    private TextView mTitle1;
    private TextView mTitle2;
    private MainMeAdapter mAdapter1;
    private MainMeAdapter mAdapter2;

    public MainMeViewHolder(Context context, ViewGroup parentView) {
        super(context, parentView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_main_me;
    }

    @Override
    public void init() {
        setStatusHeight();
        final View titleWrap = findViewById(R.id.title_wrap);
        titleWrap.post(new Runnable() {
            @Override
            public void run() {
                View fl_top = findViewById(R.id.fl_top);
                if (fl_top != null) {
                    int height = fl_top.getHeight();
                    ViewGroup.LayoutParams lp = titleWrap.getLayoutParams();
                    lp.height = height;
                    titleWrap.requestLayout();
                }
            }
        });
        mAvatar = (ImageView) findViewById(R.id.avatar);
        mName = (TextView) findViewById(R.id.name);
        mSex = (ImageView) findViewById(R.id.sex);
        mLevelAnchor = (ImageView) findViewById(R.id.level_anchor);
        mLevel = (ImageView) findViewById(R.id.level);
        mID = (TextView) findViewById(R.id.id_val);
        mFollowNum = findViewById(R.id.follow_num);
        mFansNum = findViewById(R.id.fans_num);
        mCollectNum = findViewById(R.id.collect_num);
        findViewById(R.id.btn_follow).setOnClickListener(this);
        findViewById(R.id.btn_fans).setOnClickListener(this);
        findViewById(R.id.btn_collect).setOnClickListener(this);
        findViewById(R.id.btn_edit).setOnClickListener(this);
        findViewById(R.id.btn_msg).setOnClickListener(this);
        findViewById(R.id.btn_wallet).setOnClickListener(this);
        findViewById(R.id.btn_detail).setOnClickListener(this);
        findViewById(R.id.btn_shop).setOnClickListener(this);
        mTitle1 = findViewById(R.id.title1);
        mTitle2 = findViewById(R.id.title2);
        mRecyclerView1 = findViewById(R.id.recyclerView1);
        mRecyclerView1.setLayoutManager(new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false));
        mRecyclerView2 = findViewById(R.id.recyclerView2);
        mRecyclerView2.setLayoutManager(new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false));
    }

    @Override
    public void onPause() {
        super.onPause();
        mPaused = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isShowed() && mPaused) {
            loadData();
        }
        mPaused = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainHttpUtil.cancel(MainHttpConsts.GET_BASE_INFO);
    }

    @Override
    public void loadData() {
        if (isFirstLoadData()) {
            showData();
        }
        MainHttpUtil.getBaseInfo(mCallback);
    }

    private CommonCallback<UserBean> mCallback = new CommonCallback<UserBean>() {
        @Override
        public void callback(UserBean bean) {
            showData();
        }
    };

    private void showData() {
        String userBeanJson = SpUtil.getInstance().getStringValue(SpUtil.USER_INFO);
        if (TextUtils.isEmpty(userBeanJson)) {
            return;
        }
        JSONObject obj = JSON.parseObject(userBeanJson);
        UserBean u = JSON.toJavaObject(obj, UserBean.class);
        ImgLoader.displayAvatar(mContext, u.getAvatar(), mAvatar);
        mName.setText(u.getUserNiceName());
        mSex.setImageResource(CommonIconUtil.getSexIcon(u.getSex()));
        CommonAppConfig appConfig = CommonAppConfig.getInstance();
        LevelBean anchorLevelBean = appConfig.getAnchorLevel(u.getLevelAnchor());
        if (anchorLevelBean != null) {
            ImgLoader.display(mContext, anchorLevelBean.getThumb(), mLevelAnchor);
        }
        LevelBean levelBean = appConfig.getLevel(u.getLevel());
        if (levelBean != null) {
            ImgLoader.display(mContext, levelBean.getThumb(), mLevel);
        }
        mID.setText(u.getLiangNameTip());
        mFollowNum.setText(StringUtil.toWan(u.getFollows()));
        mFansNum.setText(StringUtil.toWan(u.getFans()));
        mCollectNum.setText(StringUtil.toWan(obj.getIntValue("goods_collect_nums")));
        JSONArray arr = obj.getJSONArray("list");
        JSONObject obj1 = arr.getJSONObject(0);
        mTitle1.setText(obj1.getString("title"));
        List<UserItemBean> list1 = JSON.parseArray(obj1.getString("list"), UserItemBean.class);
        if (mAdapter1 == null) {
            mAdapter1 = new MainMeAdapter(mContext, list1);
            mAdapter1.setOnItemClickListener(this);
            mRecyclerView1.setAdapter(mAdapter1);
        } else {
            mAdapter1.setList(list1);
        }
        JSONObject obj2 = arr.getJSONObject(1);
        mTitle2.setText(obj2.getString("title"));
        List<UserItemBean> list2 = JSON.parseArray(obj2.getString("list"), UserItemBean.class);
        if (mAdapter2 == null) {
            mAdapter2 = new MainMeAdapter(mContext, list2);
            mAdapter2.setOnItemClickListener(this);
            mRecyclerView2.setAdapter(mAdapter2);
        } else {
            mAdapter2.setList(list2);
        }

    }


    @Override
    public void onItemClick(UserItemBean bean, int position) {
        if (bean.getId() == 22) {//我的小店
            forwardMall();
            return;
        } else if (bean.getId() == 24) {//付费内容
            forwardPayContent();
            return;
        }
        String url = bean.getHref();
        if (TextUtils.isEmpty(url)) {
            switch (bean.getId()) {
                case 1:
                    forwardProfit();
                    break;
                case 2:
                    forwardCoin();
                    break;
                case 13:
                    forwardSetting();
                    break;
                case 19:
                    forwardMyVideo();
                    break;
                case 20:
                    forwardRoomManage();
                    break;
                case 23://我的动态
                    mContext.startActivity(new Intent(mContext, MyActiveActivity.class));
                    break;
                case 25://每日任务
                    mContext.startActivity(new Intent(mContext, DailyTaskActivity.class));
                    break;
                case 26://我的收藏
                    mContext.startActivity(new Intent(mContext, GoodsCollectActivity.class));
                    break;
            }
        } else {
            if (!url.contains("?")) {
                url = StringUtil.contact(url, "?");
            }
            if (bean.getId() == 8) {//三级分销
                ThreeDistributActivity.forward(mContext, bean.getName(), url);

            } else if (bean.getId() == 6) {//家族中心
                FamilyActivity.forward(mContext, url);
            } else {
                WebViewActivity.forward(mContext, url);
            }
        }
    }

    /**
     * 我的小店 商城
     */
    private void forwardMall() {
        UserBean u = CommonAppConfig.getInstance().getUserBean();
        if (u != null) {
            if (u.getIsOpenShop() == 0) {
                BuyerActivity.forward(mContext);
            } else {
                SellerActivity.forward(mContext);
            }
        }

    }


    /**
     * 付费内容
     */
    private void forwardPayContent() {
        UserBean u = CommonAppConfig.getInstance().getUserBean();
        if (u != null) {
            if (u.getIsOpenPayContent() == 0) {
                PayContentActivity1.forward(mContext);
            } else {
                PayContentActivity2.forward(mContext);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_edit) {
            RouteUtil.forwardUserHome(mContext, CommonAppConfig.getInstance().getUid());
        } else if (i == R.id.btn_follow) {
            forwardFollow();

        } else if (i == R.id.btn_fans) {
            forwardFans();

        } else if (i == R.id.btn_collect) {
            mContext.startActivity(new Intent(mContext, GoodsCollectActivity.class));

        } else if (i == R.id.btn_msg) {
            ChatActivity.forward(mContext);
        } else if (i == R.id.btn_wallet) {
            RouteUtil.forwardMyCoin(mContext);
        } else if (i == R.id.btn_detail) {
            WebViewActivity.forward(mContext, HtmlConfig.DETAIL);
        } else if (i == R.id.btn_shop) {
            WebViewActivity.forward(mContext, HtmlConfig.SHOP);
        }
    }

    /**
     * 编辑个人资料
     */
    private void forwardEditProfile() {
        mContext.startActivity(new Intent(mContext, EditProfileActivity.class));
    }

    /**
     * 我的关注
     */
    private void forwardFollow() {
        FollowActivity.forward(mContext, CommonAppConfig.getInstance().getUid());
    }

    /**
     * 我的粉丝
     */
    private void forwardFans() {
        FansActivity.forward(mContext, CommonAppConfig.getInstance().getUid());
    }

    /**
     * 直播记录
     */
    private void forwardLiveRecord() {
        LiveRecordActivity.forward(mContext, CommonAppConfig.getInstance().getUserBean());
    }

    /**
     * 我的收益
     */
    private void forwardProfit() {
        mContext.startActivity(new Intent(mContext, MyProfitActivity.class));
    }

    /**
     * 我的钻石
     */
    private void forwardCoin() {
        RouteUtil.forwardMyCoin(mContext);
    }

    /**
     * 设置
     */
    private void forwardSetting() {
        mContext.startActivity(new Intent(mContext, SettingActivity.class));
    }

    /**
     * 我的视频
     */
    private void forwardMyVideo() {
        mContext.startActivity(new Intent(mContext, MyVideoActivity.class));
    }

    /**
     * 房间管理
     */
    private void forwardRoomManage() {
        mContext.startActivity(new Intent(mContext, RoomManageActivity.class));
    }


}
