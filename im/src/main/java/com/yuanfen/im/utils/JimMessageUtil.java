package com.yuanfen.im.utils;

import android.content.Context;
import android.text.TextUtils;

import com.yuanfen.common.CommonAppConfig;
import com.yuanfen.common.CommonAppContext;
import com.yuanfen.common.Constants;
import com.yuanfen.common.interfaces.CommonCallback;
import com.yuanfen.common.utils.L;
import com.yuanfen.common.utils.SpUtil;
import com.yuanfen.common.utils.ToastUtil;
import com.yuanfen.common.utils.WordUtil;
import com.yuanfen.im.R;
import com.yuanfen.im.bean.ImMessageBean;
import com.yuanfen.im.bean.ImMsgLocationBean;
import com.yuanfen.im.bean.ImUserBean;
import com.yuanfen.im.event.ImMessagePromptEvent;
import com.yuanfen.im.event.ImOffLineMsgEvent;
import com.yuanfen.im.event.ImRoamMsgEvent;
import com.yuanfen.im.event.ImUnReadCountEvent;
import com.yuanfen.im.event.ImUserMsgEvent;
import com.yuanfen.im.interfaces.ImClient;
import com.yuanfen.im.interfaces.SendMsgResultCallback;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.DownloadCompletionCallback;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.LocationContent;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.enums.MessageStatus;
import cn.jpush.im.android.api.event.ConversationRefreshEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.MessageRetractEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.android.api.options.MessageSendingOptions;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by cxf on 2017/8/10.
 * ??????IM????????????????????????
 */

public class JimMessageUtil implements ImClient {

    private static final String TAG = "??????IM";
    private static final String PWD_SUFFIX = "PUSH";//????????????IM???????????????????????????id+"PUSH"?????????????????????
    //????????????uid??????????????????????????????
    public static final String PREFIX = "";
    private Map<String, Long> mMap;
    //???????????????????????????????????????
    private MessageSendingOptions mOptions;
    private SimpleDateFormat mSimpleDateFormat;
    private String mImageString;
    private String mVoiceString;
    private String mLocationString;
    private BasicCallback mSendCompleteCallback;
    private SendMsgResultCallback mSendMsgResultCallback;
    private BasicCallback mHasReadCallback;
    private Runnable mHasReadRunable;
    private Comparator<String> mUidComparator;


    public JimMessageUtil() {
        mMap = new HashMap<>();
        mOptions = new MessageSendingOptions();
        mOptions.setShowNotification(false);//??????????????????????????????????????????????????????????????????????????????????????????
        mSimpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        mImageString = WordUtil.getString(R.string.im_type_image);
        mVoiceString = WordUtil.getString(R.string.im_type_voide);
        mLocationString = WordUtil.getString(R.string.im_type_location);
        mUidComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (Constants.MALL_IM_ADMIN.equals(o1)) {
                    return -1;
                } else if (Constants.MALL_IM_ADMIN.equals(o2)) {
                    return 1;
                }
                return 0;
            }
        };
    }


    public void init() {
        //??????????????????
        JMessageClient.init(CommonAppContext.getInstance(), true);
        JMessageClient.setNotificationFlag(JMessageClient.FLAG_NOTIFY_DISABLE);//??????????????????????????????
    }

    /**
     * ???app????????????uid??????IM?????????uid
     */
    private String getImUid(String uid) {
        if (Constants.MALL_IM_ADMIN.equals(uid)) {
            return uid;
        }
        if (!TextUtils.isEmpty(PREFIX) && !uid.startsWith(PREFIX)) {
            return PREFIX + uid;
        }
        return uid;
    }

    /**
     * ???IM?????????uid??????app?????????uid
     */
    private String getAppUid(String from) {
        if (Constants.MALL_IM_ADMIN.equals(from)) {
            return from;
        }
        if (!TextUtils.isEmpty(from) && from.length() > PREFIX.length()) {
            return from.substring(PREFIX.length());
        }
        return "";
    }

    /**
     * ????????????IM conversation ??????App?????????uid
     */
    private String getAppUid(Conversation conversation) {
        if (conversation == null) {
            return "";
        }
        Object targetInfo = conversation.getTargetInfo();
        if (targetInfo == null) {
            return "";
        }
        if (targetInfo instanceof UserInfo) {
            String userName = ((UserInfo) targetInfo).getUserName();
            return getAppUid(userName);
        }
        return "";
    }


    /**
     * ????????????IM???????????? ??????App?????????uid
     */
    private String getAppUid(Message msg) {
        if (msg == null) {
            return "";
        }
        UserInfo userInfo = msg.getFromUser();
        if (userInfo == null) {
            return "";
        }
        String userName = userInfo.getUserName();
        return getAppUid(userName);
    }


    /**
     * ????????????IM
     */
    public void loginImClient(String uid) {
//        if (SpUtil.getInstance().getBooleanValue(SpUtil.IM_LOGIN)) {
//            L.e(TAG, "??????IM???????????????");
//            JMessageClient.registerEventReceiver(JimMessageUtil.this);
//            CommonAppConfig.getInstance().setLoginIM(true);
//            //EventBus.getDefault().post(new ImLoginEvent(true));
//            refreshAllUnReadMsgCount();
//            return;
//        }
        final String imUid = getImUid(uid);
        JMessageClient.login(imUid, imUid + PWD_SUFFIX, new BasicCallback() {
            @Override
            public void gotResult(int code, String msg) {
                L.e(TAG, "??????????????????---gotResult--->code: " + code + " msg: " + msg);
                if (code == 801003) {//???????????????
                    L.e(TAG, "???????????????????????????");
                    registerAndLoginJMessage(imUid);
                } else if (code == 0) {
                    L.e(TAG, "??????IM????????????");
                    SpUtil.getInstance().setBooleanValue(SpUtil.IM_LOGIN, true);
                    JMessageClient.registerEventReceiver(JimMessageUtil.this);
                    CommonAppConfig.getInstance().setLoginIM(true);
                    //EventBus.getDefault().post(new ImLoginEvent(true));
                    refreshAllUnReadMsgCount();
                }
            }
        });

    }

    //?????????????????????IM
    private void registerAndLoginJMessage(final String uid) {
        JMessageClient.register(uid, uid + PWD_SUFFIX, new BasicCallback() {

            @Override
            public void gotResult(int code, String msg) {
                L.e(TAG, "??????????????????---gotResult--->code: " + code + " msg: " + msg);
                if (code == 0) {
                    L.e(TAG, "??????IM????????????");
                    loginImClient(uid);
                }
            }
        });
    }

    /**
     * ????????????IM
     */
    public void logoutImClient() {
        JMessageClient.unRegisterEventReceiver(JimMessageUtil.this);
        JMessageClient.logout();
        //EventBus.getDefault().post(new ImLoginEvent(false));
        CommonAppConfig.getInstance().setLoginIM(false);
        L.e(TAG, "??????IM??????");
    }


    /**
     * ???????????????????????????uid?????????uid???????????????
     */
    public String getConversationUids() {
        List<Conversation> conversationList = JMessageClient.getConversationList();
        if (conversationList != null) {
            List<String> uidList = new ArrayList<>();
            for (Conversation conversation : conversationList) {
                Object targetInfo = conversation.getTargetInfo();
                if (targetInfo == null || !(targetInfo instanceof UserInfo)) {
                    continue;
                }
                List<Message> messages = conversation.getAllMessage();
                if (messages == null || messages.size() == 0) {
                    String userName = ((UserInfo) targetInfo).getUserName();
                    JMessageClient.deleteSingleConversation(userName);
                    continue;
                }
                String from = getAppUid(conversation);
                if (!TextUtils.isEmpty(from)) {
                    uidList.add(from);
                }
            }
            if (uidList.size() > 0) {
                Collections.sort(uidList, mUidComparator);
                StringBuilder sb = new StringBuilder();
                for (String s : uidList) {
                    sb.append(s);
                    sb.append(",");
                }
                String uids = sb.toString();
                if (uids.endsWith(",")) {
                    uids = uids.substring(0, uids.length() - 1);
                }
                return uids;
            }
        }
        return "";
    }

    /**
     * ??????????????????????????????????????????
     */
    public List<ImUserBean> getLastMsgInfoList(List<ImUserBean> list) {
        if (list == null) {
            return null;
        }
        for (ImUserBean bean : list) {
            Conversation conversation = JMessageClient.getSingleConversation(getImUid(bean.getId()));
            if (conversation != null) {
                bean.setHasConversation(true);
                Message msg = conversation.getLatestMessage();
                if (msg != null) {
                    bean.setLastTime(getMessageTimeString(msg));
                    bean.setUnReadCount(conversation.getUnReadMsgCnt());
                    bean.setMsgType(getMessageType(msg));
                    bean.setLastMessage(getMessageString(msg));
                    bean.setLastMsgId(msg.getId());
                }
            } else {
                bean.setHasConversation(false);
            }
        }
        return list;
    }

    /**
     * ??????????????????
     */
    private List<ImMessageBean> getChatMessageList(String toUid) {
        List<ImMessageBean> result = new ArrayList<>();
        Conversation conversation = JMessageClient.getSingleConversation(getImUid(toUid));
        if (conversation == null) {
            return result;
        }
        List<Message> msgList = conversation.getAllMessage();
        if (msgList == null) {
            return result;
        }
        int size = msgList.size();
        if (size < 20) {
            Message latestMessage = conversation.getLatestMessage();
            if (latestMessage == null) {
                return result;
            }
            List<Message> list = conversation.getMessagesFromNewest(latestMessage.getId(), 20 - size);
            if (list == null) {
                return result;
            }
            list.addAll(msgList);
            msgList = list;
        }
        String uid = CommonAppConfig.getInstance().getUid();
        for (Message msg : msgList) {
            String from = getAppUid(msg);
            if (TextUtils.isEmpty(from)) {
                continue;
            }
            int type = getMessageType(msg);
            if (!TextUtils.isEmpty(from) && type != 0) {
                boolean self = from.equals(uid);
                ImMessageBean bean = new ImMessageBean(from, msg, type, self);
                if (self && msg.getServerMessageId() == 0 || msg.getStatus() == MessageStatus.send_fail) {
                    bean.setSendFail(true);
                }
                result.add(bean);
            }
        }
        return result;
    }

    /**
     * ??????????????????
     */
    @Override
    public void getChatMessageList(String toUid, CommonCallback<List<ImMessageBean>> callback) {
        if (callback != null) {
            callback.callback(getChatMessageList(toUid));
        }
    }


    /**
     * ????????????????????????????????????
     */
    public int getUnReadMsgCount(String uid) {
        Conversation conversation = JMessageClient.getSingleConversation(getImUid(uid));
        if (conversation != null) {
            return conversation.getUnReadMsgCnt();
        }
        return 0;
    }

    /**
     * ?????????????????????????????????
     */
    public void refreshAllUnReadMsgCount() {
        EventBus.getDefault().post(new ImUnReadCountEvent(getAllUnReadMsgCount()));
    }

    /**
     * ?????????????????????????????????
     */
    public String getAllUnReadMsgCount() {
        int unReadCount = JMessageClient.getAllUnReadMsgCount();
        L.e(TAG, "??????????????????----->" + unReadCount);
        String res = "";
        if (unReadCount > 99) {
            res = "99+";
        } else {
            if (unReadCount < 0) {
                unReadCount = 0;
            }
            res = String.valueOf(unReadCount);
        }
        return res;
    }

    /**
     * ????????????????????????????????????
     *
     * @param toUid ??????uid
     */
    public void markAllMessagesAsRead(String toUid, boolean needRefresh) {
        if (!TextUtils.isEmpty(toUid)) {
            Conversation conversation = JMessageClient.getSingleConversation(getImUid(toUid));
            if (conversation != null) {
                conversation.resetUnreadCount();
                if (needRefresh) {
                    refreshAllUnReadMsgCount();
                }
            }
        }
    }

    /**
     * ???????????????????????????  ?????????????????????
     */
    public void markAllConversationAsRead() {
        List<Conversation> list = JMessageClient.getConversationList();
        if (list == null) {
            return;
        }
        for (Conversation conversation : list) {
            conversation.resetUnreadCount();
        }
        EventBus.getDefault().post(new ImUnReadCountEvent("0"));
    }


    /**
     * ????????????
     * ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * SDK ??????????????????????????????????????????????????????????????????????????????????????????
     */
//    public void onEvent(CommandNotificationEvent e){
//        L.e("onCommandNotificationEvent-------->"+e.getMsg());
//    }

    /**
     * ???????????? ??????????????????????????????
     */
    public void onEvent(MessageEvent event) {
        //????????????
        Message msg = event.getMessage();
        if (msg == null) {
            return;
        }
        String uid = getAppUid(msg);
        if (TextUtils.isEmpty(uid)) {
            return;
        }
        int type = getMessageType(msg);
        if (type == 0) {
            return;
        }
        boolean canShow = true;
        Long lastTime = mMap.get(uid);
        long curTime = System.currentTimeMillis();
        if (lastTime != null) {
            if (curTime - lastTime < 1000) {
                //?????????????????????????????????????????????????????????1??????????????????????????????
                msg.setHaveRead(null);//???????????????
                canShow = false;
            } else {
                mMap.put(uid, curTime);
            }
        } else {
            //??????sMap????????????????????????????????????????????????????????????????????????????????????
            mMap.put(uid, curTime);
        }
        if (canShow) {
            L.e(TAG, "????????????--->");
            EventBus.getDefault().post(new ImMessageBean(uid, msg, type, false));
            ImUserMsgEvent imUserMsgEvent = new ImUserMsgEvent();
            imUserMsgEvent.setUid(uid);
            imUserMsgEvent.setLastMessage(getMessageString(msg));
            imUserMsgEvent.setUnReadCount(getUnReadMsgCount(uid));
            imUserMsgEvent.setLastTime(getMessageTimeString(msg));
            imUserMsgEvent.setLastMsgId(msg.getId());
            EventBus.getDefault().post(imUserMsgEvent);
            refreshAllUnReadMsgCount();
        }
    }

    /**
     * ??????????????????
     */
    public void onEvent(OfflineMessageEvent event) {
        String from = getAppUid(event.getConversation());
        L.e(TAG, "?????????????????????-------->?????????" + from);
        if (!TextUtils.isEmpty(from) && !from.equals(CommonAppConfig.getInstance().getUid())) {
            List<Message> list = event.getOfflineMessageList();
            if (list != null && list.size() > 0) {
                ImUserBean bean = new ImUserBean();
                bean.setId(from);
                Message message = list.get(list.size() - 1);
                bean.setLastTime(getMessageTimeString(message));
                bean.setUnReadCount(list.size());
                bean.setMsgType(getMessageType(message));
                bean.setLastMessage(getMessageString(message));
                bean.setLastMsgId(message.getId());
                EventBus.getDefault().post(new ImOffLineMsgEvent(bean));
                refreshAllUnReadMsgCount();
            }
        }
    }

    /**
     * ?????????????????????
     */
    private int getMessageType(Message msg) {
        int type = 0;
        if (msg == null) {
            return type;
        }
        MessageContent content = msg.getContent();
        if (content == null) {
            return type;
        }
        switch (content.getContentType()) {
            case text://??????
                type = ImMessageBean.TYPE_TEXT;
                break;
            case image://??????
                type = ImMessageBean.TYPE_IMAGE;
                break;
            case voice://??????
                type = ImMessageBean.TYPE_VOICE;
                break;
            case location://??????
                type = ImMessageBean.TYPE_LOCATION;
                break;
            case prompt://????????????
                type = ImMessageBean.TYPE_PROMPT;
                break;
        }
        return type;
    }


    /**
     * ??????????????????
     */
    public void onEvent(ConversationRefreshEvent event) {
        Conversation conversation = event.getConversation();
        String from = getAppUid(conversation);
        L.e(TAG, "?????????????????????-------->?????????" + from);
        if (!TextUtils.isEmpty(from) && !from.equals(CommonAppConfig.getInstance().getUid())) {
            Message message = conversation.getLatestMessage();
            ImUserBean bean = new ImUserBean();
            bean.setId(from);
            bean.setLastTime(getMessageTimeString(message));
            bean.setUnReadCount(conversation.getUnReadMsgCnt());
            bean.setMsgType(getMessageType(message));
            bean.setLastMessage(getMessageString(message));
            bean.setLastMsgId(message.getId());
            EventBus.getDefault().post(new ImRoamMsgEvent(bean));
            refreshAllUnReadMsgCount();
        }
    }


    /**
     * ??????????????????
     */
    public void onEvent(MessageRetractEvent event) {
        Conversation conversation = event.getConversation();
        String from = getAppUid(conversation);
        L.e(TAG, "??????????????????-------->?????????" + from);
        if (!TextUtils.isEmpty(from) && !from.equals(CommonAppConfig.getInstance().getUid())) {
            Message message = event.getRetractedMessage();
            EventBus.getDefault().post(new ImMessagePromptEvent(from, message.getId(), false));
        }
    }

    /**
     * ??????????????????????????????
     */
    private String getMessageString(Message message) {
        String result = "";
        MessageContent content = message.getContent();
        if (content == null) {
            return result;
        }
        switch (content.getContentType()) {
            case text://??????
                result = ((TextContent) content).getText();
                break;
            case image://??????
                result = mImageString;
                break;
            case voice://??????
                result = mVoiceString;
                break;
            case location://??????
                result = mLocationString;
                break;
            case prompt://????????????
                String from = getAppUid(message);
                if (!TextUtils.isEmpty(from) && from.equals(CommonAppConfig.getInstance().getUid())) {
                    result = WordUtil.getString(R.string.chat_msg_prompt_0);
                } else {
                    result = WordUtil.getString(R.string.chat_msg_prompt_1);
                }
                break;

        }
        return result;
    }

    private String getMessageTimeString(Message message) {
        return mSimpleDateFormat.format(new Date(message.getCreateTime()));
    }

    private String getMessageTimeString(long time) {
        return mSimpleDateFormat.format(new Date(time));
    }

    /**
     * ??????????????????
     *
     * @param toUid
     * @param content
     * @return
     */
    public ImMessageBean createTextMessage(String toUid, String content) {
        Message message = JMessageClient.createSingleTextMessage(PREFIX + toUid, content);
        if (message == null) {
            return null;
        }
        return new ImMessageBean(CommonAppConfig.getInstance().getUid(), message, ImMessageBean.TYPE_TEXT, true);
    }

    /**
     * ??????????????????
     *
     * @param toUid ?????????id
     * @param path  ????????????
     * @return
     */
    public ImMessageBean createImageMessage(String toUid, String path) {
        CommonAppConfig config = CommonAppConfig.getInstance();
        String appKey = config.getJPushAppKey();
        try {
            Message message = JMessageClient.createSingleImageMessage(PREFIX + toUid, appKey, new File(path));
            return new ImMessageBean(config.getUid(), message, ImMessageBean.TYPE_IMAGE, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ??????????????????
     *
     * @param thumbnail ??????????????????
     */
    @Override
    public void displayImageFile(final Context context, final ImMessageBean bean, final CommonCallback<File> commonCallback, boolean thumbnail) {
        if (bean != null && commonCallback != null) {
            Message msg = bean.getRawMessage();
            if (msg != null) {
                MessageContent messageContent = msg.getContent();
                if (messageContent instanceof ImageContent) {
                    final ImageContent imageContent = (ImageContent) messageContent;
                    String localPath = thumbnail ? imageContent.getLocalThumbnailPath() : imageContent.getLocalPath();
                    if (!TextUtils.isEmpty(localPath)) {
                        File file = new File(localPath);
                        if (file.exists()) {
                            commonCallback.callback(file);
                            return;
                        }
                    }
                    if (thumbnail) {
                        imageContent.downloadThumbnailImage(msg, new DownloadCompletionCallback() {
                            @Override
                            public void onComplete(int i, String s, File file) {
                                commonCallback.callback(file);
                            }
                        });
                    } else {
                        imageContent.downloadOriginImage(msg, new DownloadCompletionCallback() {
                            @Override
                            public void onComplete(int i, String s, File file) {
                                commonCallback.callback(file);
                            }
                        });
                    }
                }
            }
        }
    }

    /**
     * ??????????????????
     */
    public void getVoiceFile(final ImMessageBean bean, final CommonCallback<File> commonCallback) {
        if (bean != null && commonCallback != null) {
            Message msg = bean.getRawMessage();
            if (msg != null) {
                MessageContent messageContent = msg.getContent();
                if (messageContent instanceof VoiceContent) {
                    final VoiceContent voiceContent = (VoiceContent) messageContent;
                    String localPath = voiceContent.getLocalPath();
                    if (!TextUtils.isEmpty(localPath)) {
                        File file = new File(localPath);
                        if (file.exists()) {
                            commonCallback.callback(file);
                            return;
                        }
                    }
                    voiceContent.downloadVoiceFile(msg, new DownloadCompletionCallback() {
                        @Override
                        public void onComplete(int i, String s, File file) {
                            if (file != null && file.exists()) {
                                commonCallback.callback(file);
                            }
                        }
                    });
                }
            }
        }
    }

    /**
     * ??????????????????
     *
     * @param toUid
     * @param lat     ??????
     * @param lng     ??????
     * @param scale   ????????????
     * @param address ??????????????????
     * @return
     */
    public ImMessageBean createLocationMessage(String toUid, double lat, double lng, int scale, String address) {
        String appKey = CommonAppConfig.getInstance().getJPushAppKey();
        Message message = JMessageClient.createSingleLocationMessage(PREFIX + toUid, appKey, lat, lng, scale, address);
        return new ImMessageBean(CommonAppConfig.getInstance().getUid(), message, ImMessageBean.TYPE_LOCATION, true);
    }

    /**
     * ??????????????????
     *
     * @param toUid
     * @param voiceFile ????????????
     * @param duration  ????????????
     * @return
     */
    public ImMessageBean createVoiceMessage(String toUid, File voiceFile, long duration) {
        String appKey = CommonAppConfig.getInstance().getJPushAppKey();
        try {
            Message message = JMessageClient.createSingleVoiceMessage(PREFIX + toUid, appKey, voiceFile, (int) (duration / 1000));
            if (message != null) {
                return new ImMessageBean(CommonAppConfig.getInstance().getUid(), message, ImMessageBean.TYPE_VOICE, true);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * ????????????
     */
    public void sendMessage(String toUid, ImMessageBean bean, SendMsgResultCallback callback) {
        Message msg = bean.getRawMessage();
        if (msg == null) {
            return;
        }
        mSendMsgResultCallback = callback;
        if (mSendCompleteCallback == null) {
            mSendCompleteCallback = new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    if (mSendMsgResultCallback != null) {
                        mSendMsgResultCallback.onSendFinish(i == 0);
                    }
                    mSendMsgResultCallback = null;
                }
            };
        }
        msg.setOnSendCompleteCallback(mSendCompleteCallback);
        JMessageClient.sendMessage(msg, mOptions);
    }


    /**
     * ????????????
     *
     * @param toUid
     * @param bean
     */
    @Override
    public void removeMessage(String toUid, ImMessageBean bean) {
        if (!TextUtils.isEmpty(toUid) && bean != null) {
            Message message = bean.getRawMessage();
            if (message != null) {
                Conversation conversation = JMessageClient.getSingleConversation(getImUid(toUid));
                if (conversation != null) {
                    conversation.deleteMessage(message.getId());
                }
            }
        }
    }

    /**
     * ???????????????????????????????????????????????????????????????
     */
    public void removeAllMessage(String toUid) {
        if (!TextUtils.isEmpty(toUid)) {
            Conversation conversation = JMessageClient.getSingleConversation(getImUid(toUid));
            if (conversation != null) {
                conversation.deleteAllMessage();
            }
        }
    }

    /**
     * ??????????????????
     */
    public void removeConversation(String uid) {
        JMessageClient.deleteSingleConversation(getImUid(uid));
        refreshAllUnReadMsgCount();
    }

    /**
     * ????????????????????????
     */
    public void removeAllConversation() {
        List<Conversation> list = JMessageClient.getConversationList();
        for (Conversation conversation : list) {
            Object targetInfo = conversation.getTargetInfo();
            JMessageClient.deleteSingleConversation(((UserInfo) targetInfo).getUserName());
        }
    }


    /**
     * ???????????????????????????????????????
     */
    public void refreshLastMessage(String uid, ImMessageBean bean) {
        if (TextUtils.isEmpty(uid)) {
            return;
        }
        if (bean == null) {
            return;
        }
        Message msg = bean.getRawMessage();
        if (msg == null) {
            return;
        }
        ImUserMsgEvent imUserMsgEvent = new ImUserMsgEvent();
        imUserMsgEvent.setUid(uid);
        imUserMsgEvent.setLastMessage(getMessageString(msg));
        imUserMsgEvent.setUnReadCount(getUnReadMsgCount(uid));
        imUserMsgEvent.setLastTime(getMessageTimeString(msg));
        imUserMsgEvent.setLastMsgId(msg.getId());
        EventBus.getDefault().post(imUserMsgEvent);
    }

    @Override
    public void setVoiceMsgHasRead(ImMessageBean bean, Runnable runnable) {
        if (bean == null || runnable == null) {
            return;
        }
        mHasReadRunable = runnable;
        if (mHasReadCallback == null) {
            mHasReadCallback = new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    if (mHasReadRunable != null) {
                        mHasReadRunable.run();
                    }
                }
            };
        }
        Message message = bean.getRawMessage();
        if (message != null) {
            message.setHaveRead(mHasReadCallback);
        }
    }

    @Override
    public String getMessageText(ImMessageBean bean) {
        if (bean != null) {
            Message message = bean.getRawMessage();
            if (message != null) {
                MessageContent msgContent = message.getContent();
                if (msgContent != null && msgContent instanceof TextContent) {
                    return ((TextContent) msgContent).getText();
                }
            }
        }
        return "";
    }

    @Override
    public ImMsgLocationBean getMessageLocation(ImMessageBean bean) {
        if (bean != null) {
            Message message = bean.getRawMessage();
            if (message != null) {
                MessageContent msgContent = message.getContent();
                if (msgContent != null && msgContent instanceof LocationContent) {
                    LocationContent locationContent = (LocationContent) msgContent;
                    return new ImMsgLocationBean(locationContent.getAddress(),
                            locationContent.getScale().intValue(),
                            locationContent.getLatitude().doubleValue(),
                            locationContent.getLongitude().doubleValue());
                }
            }
        }
        return null;
    }


    /**
     * ????????????
     */
    @Override
    public void retractMessage(final String toUid, final Message message) {
        if (!TextUtils.isEmpty(toUid) && message != null) {
            Conversation conversation = JMessageClient.getSingleConversation(getImUid(toUid));
            if (conversation != null) {
                conversation.retractMessage(message, new BasicCallback() {

                    @Override
                    public void gotResult(int code, String msg) {
                        L.e(TAG, "????????????----code--> " + code + " ----msg--> " + msg);
                        if (code == 0) {
                            EventBus.getDefault().post(new ImMessagePromptEvent(toUid, message.getId(), true));
                        } else if (code == 855001) {//??????????????????
                            ToastUtil.show(R.string.chat_msg_prompt_2);
                        } else {
                            ToastUtil.show(R.string.chat_msg_prompt_3);
                        }
                    }
                });
            }
        }
    }
}
