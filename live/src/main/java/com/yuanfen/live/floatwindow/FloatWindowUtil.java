package com.yuanfen.live.floatwindow;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuanfen.common.CommonAppContext;
import com.yuanfen.common.glide.ImgLoader;
import com.yuanfen.common.utils.DpUtil;
import com.yuanfen.common.utils.FloatWindowHelper;
import com.yuanfen.common.utils.L;
import com.yuanfen.common.utils.ScreenDimenUtil;
import com.yuanfen.common.utils.StringUtil;
import com.yuanfen.common.utils.ToastUtil;
import com.yuanfen.live.R;
import com.yuanfen.live.bean.LiveBean;
import com.yuanfen.live.event.LiveAudienceVoiceOpenEvent;
import com.yuanfen.live.views.LiveVoicePlayUtil;

import org.greenrobot.eventbus.EventBus;


public class FloatWindowUtil implements FloatWindowHelper.ActionListener {

    private static final String TAG = "float";
    private static FloatWindowUtil sInstance;

    private FloatWindow mFloatWindow;
    private LiveBean mLiveBean;

    private FloatWindowUtil() {
        FloatWindowHelper.setActionListener(this);
    }

    public static FloatWindowUtil getInstance() {
        if (sInstance == null) {
            synchronized (FloatWindowUtil.class) {
                if (sInstance == null) {
                    sInstance = new FloatWindowUtil();
                }
            }
        }
        return sInstance;
    }

    public FloatWindowUtil setLiveBean(LiveBean liveBean) {
        mLiveBean = liveBean;
        return this;
    }

    public void requestPermission() {
        FloatWindowPermission.getInstance().requestPermission();
    }


    /**
     * 显示悬浮窗
     */
    public void show() {
        L.e(TAG, "----show---->");
        View v = LayoutInflater.from(CommonAppContext.getInstance()).inflate(R.layout.view_voice_float_window, null);
        if (mLiveBean != null) {
            ImageView avatar = v.findViewById(R.id.avatar);
            ImgLoader.displayAvatar(CommonAppContext.getInstance(), mLiveBean.getThumb(), avatar);
            TextView name = v.findViewById(R.id.name);
            name.setText(mLiveBean.getUserNiceName());
            TextView idVal = v.findViewById(R.id.id_val);
            idVal.setText(StringUtil.contact("ID:", mLiveBean.getUid()));
        }
        v.findViewById(R.id.btn_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLiveBean != null) {
                    EventBus.getDefault().post(new LiveAudienceVoiceOpenEvent(mLiveBean));
                }
                dismiss();
            }
        });
        v.measure(0, 0);
        ScreenDimenUtil screenDimenUtil = ScreenDimenUtil.getInstance();
        int screenW = screenDimenUtil.getScreenWdith();
        int screenH = screenDimenUtil.getScreenHeight();
        int w = v.getMeasuredWidth();
        int h = DpUtil.dp2px(40);
        int x = screenW - w;
        int y = screenH - h - DpUtil.dp2px(150);
        FloatWindow window = new FloatWindow();
        window.setWidth(w);
        window.setHeight(h);
        window.setX(x);
        window.setY(y);

        window.setView(v);
        boolean res = window.show();
        if (res) {
            mFloatWindow = window;
        }

    }


    /**
     * 是否可以播放声音
     */
    @Override
    public boolean checkVoice(boolean enterLive) {
        if (mFloatWindow != null && mLiveBean != null) {
            if (enterLive) {
                LiveVoicePlayUtil.getInstance().setKeepAlive(false);
                LiveVoicePlayUtil.getInstance().release();
                dismiss();
                return true;
            } else {
                ToastUtil.show(R.string.a_059);
                return false;
            }
        }
        return true;
    }

    /**
     * 设置隐藏和显示
     */
    @Override
    public void setFloatWindowVisible(boolean visible) {
        if (mFloatWindow != null) {
            mFloatWindow.setVisible(visible);
        }
    }

    /**
     * 隐藏悬浮窗
     */
    public void dismiss() {
        L.e(TAG, "----dismiss---->");
        if (mFloatWindow != null) {
            mFloatWindow.dismiss();
        }
        mFloatWindow = null;
        mLiveBean = null;
    }


    public void release() {
        LiveVoicePlayUtil.getInstance().setKeepAlive(false);
        LiveVoicePlayUtil.getInstance().release();
        dismiss();
    }

}
