package com.yuanfen.phonelive;

import android.text.TextUtils;

import com.meihu.beautylibrary.MHSDK;
import com.mob.MobSDK;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.live.TXLiveBase;
import com.umeng.commonsdk.UMConfigure;
import com.yuanfen.common.CommonAppConfig;
import com.yuanfen.common.CommonAppContext;
import com.yuanfen.common.utils.DecryptUtil;
import com.yuanfen.common.utils.L;
import com.yuanfen.im.utils.ImMessageUtil;
import com.yuanfen.im.utils.ImPushUtil;


/**
 * Created by cxf on 2017/8/3.
 */

public class AppContext extends CommonAppContext {

    private boolean mBeautyInited;

    @Override
    public void onCreate() {
        super.onCreate();
        //腾讯云直播鉴权url
        String liveLicenceUrl = "https://license.vod2.myqcloud.com/license/v2/1309213665_1/v_cube.license";
        //腾讯云直播鉴权key
        String liveKey = "5d78e1c8c513bd3d0d896f059d55b18f";
        //腾讯云视频鉴权url
        String ugcLicenceUrl = "https://license.vod2.myqcloud.com/license/v2/1309140641_1/v_cube.license";
        //腾讯云视频鉴权key
        String ugcKey = "5d78e1c8c513bd3d0d896f059d55b18f";
        TXLiveBase.getInstance().setLicence(this, liveLicenceUrl, liveKey, ugcLicenceUrl, ugcKey);
        L.setDeBug(BuildConfig.DEBUG);
        //初始化腾讯bugly
        CrashReport.initCrashReport(this);
        CrashReport.setAppVersion(this, CommonAppConfig.getInstance().getVersion());
        //初始化ShareSdk
        MobSDK.init(this, "351fddd9f902d", "b2653d2771497c371e55e29f284c3db3");
        //初始化极光推送
        ImPushUtil.getInstance().init(this);
        //初始化极光IM
        ImMessageUtil.getInstance().init();
        //初始化友盟统计
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);

//        if (!LeakCanary.isInAnalyzerProcess(this)) {
//            LeakCanary.install(this);
//        }
//        PLShortVideoEnv.init(this);
    }

    /**
     * 初始化美狐
     */
    public void initBeautySdk(String beautyKey) {
        if (!TextUtils.isEmpty(beautyKey)) {
            if (!mBeautyInited) {
                mBeautyInited = true;
                if (CommonAppConfig.isYunBaoApp()) {
                    beautyKey = DecryptUtil.decrypt(beautyKey);
                }
                MHSDK.init(this, beautyKey);
                CommonAppConfig.getInstance().setMhBeautyEnable(true);
                L.e("美狐初始化------->" + beautyKey);
            }
        } else {
            CommonAppConfig.getInstance().setMhBeautyEnable(false);
        }
    }

}
