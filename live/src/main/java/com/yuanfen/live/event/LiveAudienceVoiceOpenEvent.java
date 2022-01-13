package com.yuanfen.live.event;

import com.yuanfen.live.bean.LiveBean;

public class LiveAudienceVoiceOpenEvent {

    private LiveBean mLiveBean;

    public LiveAudienceVoiceOpenEvent(LiveBean liveBean) {
        mLiveBean = liveBean;
    }

    public LiveBean getLiveBean() {
        return mLiveBean;
    }

    public void setLiveBean(LiveBean liveBean) {
        mLiveBean = liveBean;
    }
}
