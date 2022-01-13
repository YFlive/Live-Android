# Live-Android
```javascript
├── app程序入口
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           └── java
│               └── com.yuanfen.phonelive
│                   ├── AppContext.java全局Application
│                   ├── activity
│                   │   └── LauncherActivity.java启动页
│                   └── wxapi
│                       ├── WXEntryActivity.java微信登录回调
│                       └── WXPayEntryActivity.java微信支付回调
├── baidu百度语音识别
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           └── java
│               └── com.yuanfen.baidu
│                   └── utils
│                       └── ImAsrUtil.java百度语音
├── beauty美颜
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           └── java
│               └── com.yuanfen.beauty
│                   ├── custom自定义控件
│                   │   ├── ItemDecoration2.java
│                   │   ├── MyRadioButton.java
│                   │   ├── SquareImageView2.java
│                   │   └── TextSeekBar.java
│                   ├── simple 自定义控件
│                   │   ├── SimpleBeautyEffectListener.java
│                   │   ├── SimpleBeautyViewHolder.java
│                   │   ├── SimpleFilterAdapter.java
│                   │   └── SimpleFilterBean.java
│                   ├── ui
│                   │   ├── adapter RecyclerView适配器
│                   │   │   ├── BaseBeautyAdapter.java
│                   │   │   ├── BeautyAdapter.java
│                   │   │   ├── BeautyPagerAdapter.java
│                   │   │   ├── DefaultShapeAdapter.java
│                   │   │   ├── DistortionAdapter.java
│                   │   │   ├── FilterAdapter.java
│                   │   │   ├── QuickBeautyAdapter.java
│                   │   │   ├── ShapeAdapter.java
│                   │   │   ├── SpeciallyAdapter.java
│                   │   │   ├── SpeciallyPagerAdapter.java
│                   │   │   ├── StickerAdapter.java
│                   │   │   ├── StickerPagerAdapter.java
│                   │   │   └── WaterMarkAdapter.java
│                   │   ├── bean实体类
│                   │   │   ├── BeautyBean.java
│                   │   │   ├── FilterBean.java
│                   │   │   ├── QuickBeautyBean.java
│                   │   │   ├── ShapeBean.java
│                   │   │   ├── SpeciallyBean.java
│                   │   │   ├── StickerBeautyBean.java
│                   │   │   ├── StickerCategaryBean.java
│                   │   │   ├── StickerServiceBean.java
│                   │   │   └── WatermarkBean.java
│                   │   ├── enums
│                   │   │   ├── BeautyTypeEnum.java
│                   │   │   ├── DistortionEnum.java
│                   │   │   ├── FilterEnum.java
│                   │   │   ├── QuickBeautyEnum.java
│                   │   │   └── QuickBeautyShapeEnum.java
│                   │   ├── filter
│                   │   │   └── MHFilter.java
│                   │   ├── interfaces
│                   │   │   ├── BeautyEffectListener.java
│                   │   │   ├── DefaultBeautyEffectListener.java
│                   │   │   ├── IBeautyViewHolder.java
│                   │   │   ├── MHBeautyEffectListener.java
│                   │   │   ├── MHCameraClickListener.java
│                   │   │   ├── OnItemClickListener.java
│                   │   │   ├── StickerCanClickListener.java
│                   │   │   └── StickerDataCallBack.java
│                   │   ├── manager
│                   │   │   └── StickerManager.java
│                   │   ├── util
│                   │   │   ├── ClickUtil.java
│                   │   │   └── DensityUtils.java
│                   │   └── views
│                   │       ├── AbsViewHolder.java
│                   │       ├── BadgeRadioButton
│                   │       │   ├── BadgeRadioButton.java
│                   │       │   └── DrawableCenterRadioButton.java
│                   │       ├── BaseBeautyViewHolder.java
│                   │       ├── BeautyDataModel.java
│                   │       ├── BeautyViewHolder.java
│                   │       ├── BeautyViewHolderFactory.java
│                   │       ├── DefaultBeautyViewHolder.java
│                   │       └── custom
│                   │           ├── ContentViewPager.java
│                   │           ├── ItemDecoration.java
│                   │           ├── LTabTextView.java
│                   │           ├── MyRadioButton.java
│                   │           ├── MyView.java
│                   │           ├── ScaleImageButton.java
│                   │           ├── TextSeekBar.java
│                   │           ├── TextSeekBarNew.java
│                   │           └── indicator
│                   │               ├── indicator
│                   │               │   ├── Indicator.java
│                   │               │   ├── slidebar
│                   │               │   │   ├── ColorBar.java
│                   │               │   │   ├── DrawableBar.java
│                   │               │   │   ├── LayoutBar.java
│                   │               │   │   └── ScrollBar.java
│                   │               │   └── transition
│                   │               │       └── OnTransitionTextListener.java
│                   │               └── utils
│                   │                   └── ColorGradient.java
│                   └── views
│                       ├── DefaultBeautyViewHolder.java
│                       └── MHProjectBeautyEffectListener.java
├── common通用模块
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           └── java
│               └── com.yuanfen.common
│                   ├── CommonAppConfig.java通用配置
│                   ├── CommonAppContext.java通用Application
│                   ├── Constants.java常量
│                   ├── HtmlConfig.java网址配置
│                   ├── activity
│                   │   ├── AbsActivity.java抽象Activity
│                   │   ├── ChooseImageActivity.java选择图片
│                   │   ├── ChooseLocationActivity.java选择位置
│                   │   ├── ChooseVideoActivity.java选择视频
│                   │   ├── ErrorActivity.java错误页面
│                   │   └── WebViewActivity.java 网页页面
│                   ├── adapter RecyclerView适配器
│                   │   ├── ChatChargeCoinAdapter.java充值
│                   │   ├── ChatChargePayAdapter.java充值
│                   │   ├── ChooseImageAdapter.java选择图片
│                   │   ├── ChooseLocationAdapter.java选择位置
│                   │   ├── ChooseVideoAdapter.java选择视频
│                   │   ├── ImChatFaceAdapter.java聊天表情
│                   │   ├── ImChatFacePagerAdapter.java聊天表情
│                   │   ├── ImagePreviewAdapter.java图片预览
│                   │   ├── RefreshAdapter.java
│                   │   └── ViewPagerAdapter.java
│                   ├── bean实体类
│                   │   ├── AdBean.java启动页广告
│                   │   ├── ChooseImageBean.java选择图片
│                   │   ├── ChooseVideoBean.java选择视频
│                   │   ├── CoinBean.java充值
│                   │   ├── CoinPayBean.java充值
│                   │   ├── ConfigBean.java通用配置
│                   │   ├── GoodsBean.java直播间商品
│                   │   ├── LevelBean.java等级
│                   │   ├── LiveChatBean.java直播间聊天
│                   │   ├── LiveClassBean.java直播间类型
│                   │   ├── LiveGiftBean.java直播间礼物
│                   │   ├── MeiyanConfig.java美颜配置
│                   │   ├── TxLocationBean.java腾讯定位
│                   │   ├── TxLocationPoiBean.java腾讯定位地址
│                   │   ├── UserBean.java用户
│                   │   ├── UserItemBean.java我的页面item
│                   │   └── VideoClassBean.java视频分类
│                   ├── custom自定义控件
│                   │   ├── ActiveVoiceLayout.java发布动态录音
│                   │   ├── AppBarLayoutBehavior.java
│                   │   ├── BubbleLayout.java聊天气泡
│                   │   ├── CircleProgress.java圆形进度条
│                   │   ├── CoinGiveLayout.java充值赠送
│                   │   ├── CommonRefreshView.java
│                   │   ├── DrawGiftView.java手绘礼物
│                   │   ├── DrawableCheckBox.java
│                   │   ├── DrawableRadioButton.java
│                   │   ├── DrawableRadioButton2.java
│                   │   ├── DrawableTextView.java
│                   │   ├── FixAppBarLayoutBehavior.java
│                   │   ├── HomeIndicatorTitle.java首页指示器
│                   │   ├── ItemDecoration.java
│                   │   ├── ItemSlideHelper.java
│                   │   ├── LineProgress.java水平进度条
│                   │   ├── MyFrameLayout1.java
│                   │   ├── MyFrameLayout2.java
│                   │   ├── MyImageView2.java
│                   │   ├── MyLinearLayout1.java
│                   │   ├── MyLinearLayout2.java
│                   │   ├── MyLinearLayout3.java
│                   │   ├── MyLinearLayout4.java
│                   │   ├── MyLinearLayout5.java
│                   │   ├── MyLinearLayout6.java
│                   │   ├── MyRadioButton.java
│                   │   ├── MyRelativeLayout2.java
│                   │   ├── MyRelativeLayout5.java
│                   │   ├── MyRelativeLayout6.java
│                   │   ├── MyRelativeLayout7.java
│                   │   ├── MyViewPager.java
│                   │   ├── NineGridLayout.java九宫格
│                   │   ├── NineGridLayout2.java九宫格
│                   │   ├── RatingBar.java星级评分
│                   │   ├── RatioImageView.java
│                   │   ├── RatioRoundImageView.java
│                   │   ├── SquareImageView.java方形图片
│                   │   ├── SquareRoundedImageView.java
│                   │   ├── StarCountView.java评分控件
│                   │   ├── TabButton.java
│                   │   ├── TabButtonGroup.java
│                   │   └── VerticalImageSpan.java
│                   ├── dialog 弹窗
│                   │   ├── AbsDialogFragment.java抽象的弹窗
│                   │   ├── ActiveVideoPreviewDialog.java发动态视频预览
│                   │   ├── ChatFaceDialog.java聊天表情
│                   │   ├── ImagePreviewDialog.java图片预览
│                   │   ├── LiveChargeDialogFragment.java直播间充值
│                   │   ├── LiveChargePayDialogFragment.java直播间充值
│                   │   ├── NotCancelableDialog.java 无法取消的弹窗
│                   │   └── VideoPreviewDialog.java 视频预览
│                   ├── event 事件
│                   │   ├── CoinChangeEvent.java 充值事件
│                   │   ├── FollowEvent.java 关注事件
│                   │   ├── LocationEvent.java 位置事件
│                   │   ├── LoginInvalidEvent.java 登录失效事件
│                   │   └── UpdateFieldEvent.java 属性更新事件
│                   ├── fragment
│                   │   └── ProcessFragment.java
│                   ├── glide 图片加载
│                   │   └── ImgLoader.java 图片加载工具
│                   ├── http 网络请求
│                   │   ├── CommonHttpConsts.java 网络请求常量
│                   │   ├── CommonHttpUtil.java 网络请求工具
│                   │   ├── Data.java 数据
│                   │   ├── HttpCallback.java 网络请求回调
│                   │   ├── HttpClient.java 网络请求工具
│                   │   ├── HttpLoggingInterceptor.java
│                   │   └── JsonBean.java
│                   ├── interfaces 接口回调
│                   │   ├── ActivityResultCallback.java
│                   │   ├── CommonCallback.java
│                   │   ├── ImageResultCallback.java
│                   │   ├── KeyBoardHeightChangeListener.java
│                   │   ├── LifeCycleListener.java
│                   │   ├── OnFaceClickListener.java
│                   │   ├── OnItemClickListener.java
│                   │   └── VideoResultCallback.java
│                   ├── mob 三方分享登录
│                   │   ├── LoginData.java
│                   │   ├── MobBean.java
│                   │   ├── MobCallback.java
│                   │   ├── MobConst.java
│                   │   ├── MobLoginUtil.java
│                   │   ├── MobShareUtil.java
│                   │   └── ShareData.java
│                   ├── pay 支付
│                   │   ├── PayCallback.java
│                   │   ├── PayPresenter.java
│                   │   ├── ali 支付宝支付
│                   │   │   ├── AliPayBuilder.java
│                   │   │   ├── Base64.java
│                   │   │   └── SignUtils.java
│                   │   └── wx 微信支付
│                   │       ├── WxApiWrapper.java
│                   │       └── WxPayBuilder.java
│                   │   └── paypal paypal支付
│                   │       ├── PaypalBuilder.java
│                   │       ├── PaypalResultFragment.java
│                   ├── upload 上传
│                   │   ├── UploadBean.java 上传实体类
│                   │   ├── UploadCallback.java 上传回调
│                   │   ├── UploadQnImpl.java 七牛云上传
│                   │   ├── AwsUploadImpl.java 亚马逊存储上传
│                   │   ├── AWSTransferUtil.java 亚马逊存储
│                   │   └── UploadStrategy.java 上传策略
│                   ├── utils 工具类
│                   │   ├── BitmapUtil.java bitmap操作
│                   │   ├── CalculateUtil.java 计算
│                   │   ├── ChooseImageUtil.java 选择图片
│                   │   ├── ChooseVideoUtil.java 选择视频
│                   │   ├── CityUtil.java 选择城市地址
│                   │   ├── ClickUtil.java 点击事件
│                   │   ├── CommonIconUtil.java 通用图标
│                   │   ├── DateFormatUtil.java 日期
│                   │   ├── DecryptUtil.java 解密
│                   │   ├── DialogUitl.java 弹窗
│                   │   ├── DownloadUtil.java 下载
│                   │   ├── DpUtil.java dp转px
│                   │   ├── FaceUtil.java 表情
│                   │   ├── FileUtil.java 文件
│                   │   ├── GifCacheUtil.java gif缓存
│                   │   ├── GlideCatchUtil.java glide缓存
│                   │   ├── IntentHelper.java
│                   │   ├── JsonUtil.java
│                   │   ├── KeyBoardHeightUtil.java 键盘弹出
│                   │   ├── KeyBoardHeightUtil2.java 键盘弹出
│                   │   ├── L.java 日志打印
│                   │   ├── LocationUtil.java 位置
│                   │   ├── LogUtil.java
│                   │   ├── MD5Util.java md5加密
│                   │   ├── ProcessImageUtil.java
│                   │   ├── ProcessResultUtil.java
│                   │   ├── RandomUtil.java 随机数
│                   │   ├── RouteUtil.java 路由
│                   │   ├── ScreenDimenUtil.java 屏幕尺寸
│                   │   ├── SpUtil.java 数据存储
│                   │   ├── StringUtil.java 字符串
│                   │   ├── SystemUtil.java
│                   │   ├── ToastUtil.java Toast弹窗
│                   │   ├── ValidatePhoneUtil.java
│                   │   ├── VersionUtil.java 版本号
│                   │   ├── WordFilterUtil.java 关键词过滤
│                   │   ├── WordUtil.java 获取文字
│                   │   └── XmlUtil.java
│                   └── views
│                       ├── AbsCommonViewHolder.java
│                       ├── AbsLivePageViewHolder.java
│                       └── AbsViewHolder.java
├── game 游戏
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           └── java
│               └── com.yuanfen.game
│                   ├── GameConsts.java
│                   ├── adapter 
│                   │   ├── GameAdapter.java
│                   │   ├── GameNzLsAdapter.java
│                   │   ├── GameNzSfAdapter.java
│                   │   └── GameNzSzAdapter.java
│                   ├── bean
│                   │   ├── BankerBean.java
│                   │   ├── GameNzLsBean.java
│                   │   ├── GameNzSzBean.java
│                   │   └── GameParam.java
│                   ├── custom
│                   │   ├── GameBetCoinView.java
│                   │   ├── GameEbbView.java
│                   │   ├── LuckPanView.java
│                   │   ├── LuckPanWrap.java
│                   │   ├── LuckPanWrap2.java
│                   │   ├── PokerView.java
│                   │   └── ZpBetView.java
│                   ├── dialog
│                   │   ├── GameDialogFragment.java
│                   │   ├── GameNzLsDialogFragment.java
│                   │   ├── GameNzSfDialogFragment.java
│                   │   └── GameNzSzDialogFragment.java
│                   ├── event
│                   │   ├── GameWindowChangedEvent.java
│                   │   └── OpenGameChargeEvent.java
│                   ├── http
│                   │   ├── GameHttpConsts.java
│                   │   └── GameHttpUtil.java
│                   ├── interfaces
│                   │   └── GameActionListener.java
│                   ├── util
│                   │   ├── GameIconUtil.java
│                   │   ├── GamePresenter.java
│                   │   └── GameSoundPool.java
│                   └── views
│                       ├── AbsGameViewHolder.java
│                       ├── GameEbbViewHolder.java
│                       ├── GameHdViewHolder.java
│                       ├── GameNzResultViewHolder.java
│                       ├── GameNzViewHolder.java
│                       ├── GameZjhViewHolder.java  智勇三张游戏
│                       ├── GameZpResultViewHolder.java
│                       └── GameZpViewHolder.java  幸运转盘游戏
├── im 私信聊天
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           └── java
│               └── com.yuanfen.im
│                   ├── activity
│                   │   ├── ChatActivity.java 聊天
│                   │   ├── ChatChooseImageActivity.java 选择图片
│                   │   ├── ChatRoomActivity.java 聊天
│                   │   ├── LocationActivity.java 选择位置
│                   │   └── SystemMessageActivity.java 系统消息
│                   ├── adapter RecyclerView适配器
│                   │   ├── ChatImagePreviewAdapter.java 图片预览
│                   │   ├── ImChatChooseImageAdapter.java 选择图片
│                   │   ├── ImListAdapter.java 会话列表
│                   │   ├── ImRoomAdapter.java 消息列表
│                   │   ├── LocationAdapter.java 选择位置
│                   │   └── SystemMessageAdapter.java 系统消息
│                   ├── bean 实体类
│                   │   ├── ChatChooseImageBean.java 选择图片
│                   │   ├── ImChatImageBean.java 选择图片
│                   │   ├── ImMessageBean.java 消息
│                   │   ├── ImMsgLocationBean.java 选择位置
│                   │   ├── ImUserBean.java 聊天用户
│                   │   ├── SystemMessageBean.java 系统消息
│                   │   └── TimeInfo.java 时间
│                   ├── custom 自定义控件
│                   │   ├── ChatVoiceLayout.java 聊天语音
│                   │   ├── MyImageView.java 聊天图片
│                   │   └── MyRelativeLayout.java
│                   ├── dialog 弹窗
│                   │   ├── ChatImageDialog.java 聊天图片
│                   │   ├── ChatMoreDialog.java 更多弹窗
│                   │   ├── ChatVoiceInputDialog.java 语音输入
│                   │   └── SystemMessageDialogFragment.java 系统消息
│                   ├── event 事件
│                   │   ├── ImLoginEvent.java  IM登录
│                   │   ├── ImOffLineMsgEvent.java IM离线
│                   │   ├── ImRoamMsgEvent.java 漫游消息
│                   │   ├── ImUnReadCountEvent.java 未读消息数
│                   │   ├── ImUserMsgEvent.java 聊天消息
│                   │   └── SystemMsgEvent.java 系统消息
│                   ├── http 网络请求
│                   │   ├── ImHttpConsts.java
│                   │   └── ImHttpUtil.java
│                   ├── interfaces 接口回调
│                   │   ├── ChatRoomActionListener.java
│                   │   ├── ImClient.java
│                   │   └── SendMsgResultCallback.java
│                   ├── receiver 接收器
│                   │   └── JPushReceiver.java 极光推送接收器
│                   ├── utils 工具类
│                   │   ├── ImDateUtil.java 聊天日期
│                   │   ├── ImMessageUtil.java 消息
│                   │   ├── ImPushUtil.java 推送
│                   │   ├── ImTextRender.java 文字渲染
│                   │   ├── ImageUtil.java 图片工具
│                   │   ├── JimMessageUtil.java 极光IM
│                   │   ├── MediaRecordUtil.java 录音
│                   │   └── VoiceMediaPlayerUtil.java 播放录音
│                   └── views
│                       ├── ChatListViewHolder.java
│                       ├── ChatRoomDialogViewHolder.java
│                       ├── ChatRoomViewHolder.java
│                       └── SystemMessageViewHolder.java
├── live 直播
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           └── java
│               └── com.yuanfen.live
│                   ├── LiveConfig.java 直播推流配置
│                   ├── activity 
│                   │   ├── LiveActivity.java 直播间
│                   │   ├── LiveAddImpressActivity.java 添加印象
│                   │   ├── LiveAdminListActivity.java 直播间管理员
│                   │   ├── LiveAnchorActivity.java 主播直播间
│                   │   ├── LiveAudienceActivity.java 观众直播间
│                   │   ├── LiveBlackActivity.java 拉黑列表
│                   │   ├── LiveChooseClassActivity.java 选择直播间类型
│                   │   ├── LiveContributeActivity.java 直播间贡献榜
│                   │   ├── LiveGoodsAddActivity.java 添加直播间商品
│                   │   ├── LiveGuardListActivity.java 直播间守护榜
│                   │   ├── LiveRecordActivity.java 直播记录回放
│                   │   ├── LiveRecordPlayActivity.java 直播记录回放
│                   │   ├── LiveReportActivity.java 直播间举报
│                   │   ├── LiveShutUpActivity.java 直播间禁言
│                   │   ├── RoomManageActivity.java 房间管理
│                   │   └── RoomManageDetailActivity.java 房间管理详情
│                   ├── adapter RecyclerView适配器
│                   │   ├── DailyTaskAdapter.java 日常任务
│                   │   ├── GuardAdapter.java 守护
│                   │   ├── GuardRightAdapter.java 守护权限
│                   │   ├── LiveAdminListAdapter.java 直播间管理员
│                   │   ├── LiveAdminRoomAdapter.java 房间管理
│                   │   ├── LiveBlackAdapter.java 直播间拉黑
│                   │   ├── LiveChatAdapter.java 直播间聊天
│                   │   ├── LiveFunctionAdapter.java 直播间功能列表
│                   │   ├── LiveGiftAdapter.java 礼物
│                   │   ├── LiveGiftCountAdapter.java 礼物数量
│                   │   ├── LiveGiftPagerAdapter.java 礼物
│                   │   ├── LiveGoodsAdapter.java 直播间商品
│                   │   ├── LiveGoodsAddAdapter.java 直播间添加商品
│                   │   ├── LivePkAdapter.java 直播间PK
│                   │   ├── LiveReadyClassAdapter.java 直播间选择类型
│                   │   ├── LiveReadyShareAdapter.java 直播间分享
│                   │   ├── LiveRecordAdapter.java 直播回放
│                   │   ├── LiveReportAdapter.java 直播间举报
│                   │   ├── LiveRoomScrollAdapter.java 直播间上下滑动
│                   │   ├── LiveRoomTypeAdapter.java 房间类型
│                   │   ├── LiveShareAdapter.java 直播分享
│                   │   ├── LiveShopAdapter.java 直播间商品
│                   │   ├── LiveShutUpAdapter.java 直播间禁言
│                   │   ├── LiveTimeChargeAdapter.java 直播间充值
│                   │   ├── LiveUserAdapter.java 直播间观众
│                   │   ├── LuckPanRecordAdapter.java 转盘游戏记录
│                   │   ├── LuckPanWinAdapter.java 转盘中奖
│                   │   ├── RedPackAdapter.java 红包
│                   │   └── RedPackResultAdapter.java 红包记录
│                   ├── bean 实体类
│                   │   ├── BackPackGiftBean.java 背包礼物
│                   │   ├── DailyTaskBean.java 日常任务
│                   │   ├── GlobalGiftBean.java 全局礼物
│                   │   ├── GuardBuyBean.java 购买守护
│                   │   ├── GuardRightBean.java 守护权限
│                   │   ├── GuardUserBean.java 守护用户
│                   │   ├── ImpressBean.java 主播印象
│                   │   ├── LiveAdminRoomBean.java 直播间管理员
│                   │   ├── LiveBean.java 直播间实体类
│                   │   ├── LiveBuyGuardMsgBean.java 购买守护消息
│                   │   ├── LiveChatBean.java 直播间聊天实体类
│                   │   ├── LiveConfigBean.java 直播间配置
│                   │   ├── LiveDanMuBean.java 直播间弹幕
│                   │   ├── LiveEnterRoomBean.java 进入直播间
│                   │   ├── LiveFunctionBean.java 直播间功能
│                   │   ├── LiveGiftPrizePoolWinBean.java 奖池中奖
│                   │   ├── LiveGuardInfo.java 守护信息
│                   │   ├── LiveLuckGiftWinBean.java 幸运礼物中奖
│                   │   ├── LivePkBean.java PK
│                   │   ├── LiveReceiveGiftBean.java 收到礼物
│                   │   ├── LiveRecordBean.java 直播记录
│                   │   ├── LiveReportBean.java 直播间举报
│                   │   ├── LiveRoomTypeBean.java 直播间类型
│                   │   ├── LiveShutUpBean.java 直播禁言
│                   │   ├── LiveTimeChargeBean.java 直播间计时收费
│                   │   ├── LiveUserGiftBean.java 直播间送礼物
│                   │   ├── LuckPanBean.java 幸运转盘
│                   │   ├── RedPackBean.java 红包
│                   │   ├── RedPackResultBean.java 红包结果
│                   │   ├── SearchUserBean.java 搜索用户
│                   │   ├── TiFilter.java
│                   │   ├── TurntableConfigBean.java 转盘配置
│                   │   └── TurntableGiftBean.java 转盘礼物
│                   ├── custom 自定义控件
│                   │   ├── FrameImageView.java
│                   │   ├── GiftMarkView.java 礼物标识
│                   │   ├── GiftPageViewPager.java 礼物ViewPager
│                   │   ├── LiveAudienceRecyclerView.java 观众直播间上下滑动
│                   │   ├── LiveLightView.java 直播间飘心的ImageView
│                   │   ├── MusicProgressTextView.java 背景音乐下载进度
│                   │   ├── MyFrameLayout3.java
│                   │   ├── MyFrameLayout4.java
│                   │   ├── MyImageView.java
│                   │   ├── MyRelativeLayout1.java
│                   │   ├── MyTextView.java
│                   │   ├── MyTextView2.java
│                   │   ├── PkProgressBar.java PK进度条
│                   │   ├── ProgressTextView.java
│                   │   ├── StarView.java
│                   │   └── TopGradual.java
│                   ├── dialog 弹窗
│                   │   ├── DailyTaskDialogFragment.java 每日任务
│                   │   ├── GiftPrizePoolFragment.java 幸运礼物奖池
│                   │   ├── LiveChatListDialogFragment.java 直播间聊天列表
│                   │   ├── LiveChatRoomDialogFragment.java 直播间聊天弹窗
│                   │   ├── LiveFunctionDialogFragment.java 主播直播间功能弹窗
│                   │   ├── LiveGiftDialogFragment.java 直播间礼物弹窗
│                   │   ├── LiveGoodsDialogFragment.java 直播间商品
│                   │   ├── LiveGuardBuyDialogFragment.java 购买守护
│                   │   ├── LiveGuardDialogFragment.java 守护列表
│                   │   ├── LiveInputDialogFragment.java 直播间聊天输入框
│                   │   ├── LiveLinkMicListDialogFragment.java 直播间连麦列表
│                   │   ├── LiveLinkMicPkSearchDialog.java 直播间PK搜索
│                   │   ├── LiveRedPackListDialogFragment.java 直播间红包列表
│                   │   ├── LiveRedPackResultDialogFragment.java 抢红包结果
│                   │   ├── LiveRedPackRobDialogFragment.java 抢红包
│                   │   ├── LiveRedPackSendDialogFragment.java 发红包
│                   │   ├── LiveRoomCheckDialogFragment.java 密码直播间弹窗
│                   │   ├── LiveRoomCheckDialogFragment2.java 密码直播间弹窗
│                   │   ├── LiveRoomTypeDialogFragment.java 直播间类型弹窗
│                   │   ├── LiveShareDialogFragment.java 直播间分享
│                   │   ├── LiveShopDialogFragment.java 直播间商品
│                   │   ├── LiveTimeDialogFragment.java 直播间计时收费弹窗
│                   │   ├── LiveUserDialogFragment.java 直播间用户弹窗
│                   │   ├── LuckPanDialogFragment.java 直播间转盘
│                   │   ├── LuckPanRecordDialogFragment.java 直播间转盘记录
│                   │   ├── LuckPanTipDialogFragment.java 直播间转盘规则
│                   │   └── LuckPanWinDialogFragment.java 直播间转盘结果
│                   ├── event
│                   │   ├── LinkMicTxAccEvent.java
│                   │   ├── LinkMicTxMixStreamEvent.java
│                   │   └── LiveRoomChangeEvent.java
│                   ├── http
│                   │   ├── LiveHttpConsts.java
│                   │   ├── LiveHttpUtil.java
│                   │   └── MusicUrlCallback.java
│                   ├── interfaces
│                   │   ├── ILiveLinkMicViewHolder.java
│                   │   ├── ILivePushViewHolder.java
│                   │   ├── LiveFunctionClickListener.java
│                   │   ├── LivePushListener.java
│                   │   └── RedPackCountDownListener.java
│                   ├── music
│                   │   ├── LiveMusicAdapter.java
│                   │   ├── LiveMusicBean.java
│                   │   ├── LiveMusicDialogFragment.java
│                   │   ├── LiveMusicPlayer.java
│                   │   ├── LrcBean.java
│                   │   ├── LrcParser.java
│                   │   ├── LrcTextView.java
│                   │   └── db
│                   │       ├── MusicDbHelper.java
│                   │       └── MusicDbManager.java
│                   ├── presenter
│                   │   ├── LiveDanmuPresenter.java
│                   │   ├── LiveEnterRoomAnimPresenter.java
│                   │   ├── LiveGiftAnimPresenter.java
│                   │   ├── LiveLightAnimPresenter.java
│                   │   ├── LiveLinkMicAnchorPresenter.java
│                   │   ├── LiveLinkMicPkPresenter.java
│                   │   ├── LiveLinkMicPresenter.java
│                   │   ├── LiveRoomCheckLivePresenter.java
│                   │   ├── LiveRoomCheckLivePresenter2.java
│                   │   └── UserHomeSharePresenter.java
│                   ├── socket 直播间socket
│                   │   ├── GameActionListenerImpl.java
│                   │   ├── SocketChatUtil.java
│                   │   ├── SocketClient.java
│                   │   ├── SocketGameUtil.java
│                   │   ├── SocketLinkMicAnchorUtil.java
│                   │   ├── SocketLinkMicPkUtil.java
│                   │   ├── SocketLinkMicUtil.java
│                   │   ├── SocketMessageListener.java
│                   │   ├── SocketReceiveBean.java
│                   │   └── SocketSendBean.java
│                   ├── utils
│                   │   ├── LiveIconUtil.java
│                   │   ├── LiveStorge.java
│                   │   └── LiveTextRender.java
│                   └── views
│                       ├── AbsLiveGiftViewHolder.java 直播间礼物
│                       ├── AbsLiveLinkMicPlayViewHolder.java
│                       ├── AbsLiveLinkMicPushViewHolder.java
│                       ├── AbsLivePushViewHolder.java
│                       ├── AbsLiveViewHolder.java
│                       ├── AbsUserHomeViewHolder.java
│                       ├── DanmuViewHolder.java 弹幕
│                       ├── LauncherAdViewHolder.java 启动页广告
│                       ├── LiveAddImpressViewHolder.java主播印象
│                       ├── LiveAdminListViewHolder.java 直播间管理员列表
│                       ├── LiveAnchorViewHolder.java 直播直播间
│                       ├── LiveAudienceViewHolder.java 观众直播间
│                       ├── LiveContributeViewHolder.java 直播间贡献榜
│                       ├── LiveEndViewHolder.java 结束直播
│                       ├── LiveGiftDaoViewHolder.java 道具礼物
│                       ├── LiveGiftDrawViewHolder.java 手绘礼物
│                       ├── LiveGiftGiftViewHolder.java 直播间礼物
│                       ├── LiveGiftLuckTopViewHolder.java 幸运礼物
│                       ├── LiveGiftPackageViewHolder.java 背包礼物
│                       ├── LiveGiftPrizePoolViewHolder.java 幸运礼物奖池
│                       ├── LiveGiftViewHolder.java 直播间礼物
│                       ├── LiveGoodsAddViewHolder.java 主播添加商品
│                       ├── LiveLinkMicPkViewHolder.java 连麦PK
│                       ├── LiveLinkMicPlayKsyViewHolder.java 连麦金山播放
│                       ├── LiveLinkMicPlayTxViewHolder.java 连麦腾讯播放
│                       ├── LiveLinkMicPushKsyViewHolder.java 连麦金山推流
│                       ├── LiveLinkMicPushTxViewHolder.java 连麦腾讯推流
│                       ├── LiveMusicViewHolder.java 直播间背景音乐
│                       ├── LiveMyLiveRoomViewHolder.java 我的直播间
│                       ├── LiveMyRoomViewHolder.java 我的房间
│                       ├── LivePlayKsyViewHolder.java直播间金山播放
│                       ├── LivePlayTxViewHolder.java直播间腾讯播放
│                       ├── LivePushKsyViewHolder.java直播间金山推流
│                       ├── LivePushTxViewHolder.java直播间腾讯推流
│                       ├── LiveReadyViewHolder.java 直播间准备
│                       ├── LiveRecordPlayViewHolder.java 直播回放
│                       ├── LiveRecordViewHolder.java 直播回放
│                       ├── LiveRoomBtnViewHolder.java 直播间按钮
│                       ├── LiveRoomPlayViewHolder.java 直播间播放
│                       ├── LiveRoomViewHolder.java 直播间公共逻辑
│                       ├── LiveVoiceAnchorViewHolder.java 语音直播间主播底部
│                       ├── LiveVoiceAudienceViewHolder.java 语音直播间主播底部
│                       ├── LiveVoiceLinkMicViewHolder.java 语音直播间连麦逻辑
│                       ├── LiveVoicePlayTxViewHolder.java 语音直播间播放
│                       ├── LiveVoicePushTxViewHolder.java 语音直播间推流
│                       ├── LiveTitleAnimViewHolder.java 直播间标题动画
│                       ├── LiveWebViewHolder.java 直播间H5
│                       └── LuckLiveGiftViewHolder.java 幸运礼物
├── local.properties
├── main 主要逻辑
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           └── java
│               └── com.yuanfen.main
│                   ├── activity
│                   │   ├── ActiveAllTopicActivity.java 动态所有话题
│                   │   ├── ActiveChooseTopicActivity.java 动态选择话题
│                   │   ├── ActiveDetailActivity.java 动态详情
│                   │   ├── ActivePubActivity.java 发布动态
│                   │   ├── ActiveReportActivity.java 动态举报
│                   │   ├── ActiveSearchTopicActivity.java 动态搜索话题
│                   │   ├── ActiveTopicActivity.java 动态话题
│                   │   ├── ActiveVideoPlayActivity.java 动态播放视频
│                   │   ├── ActiveVideoRecordActivity.java 动态录制视频
│                   │   ├── CancelAccountActivity.java 注销账户
│                   │   ├── CancelConditionActivity.java 注销条件
│                   │   ├── CashActivity.java 提现
│                   │   ├── DailyTaskActivity.java 每日任务
│                   │   ├── EditNameActivity.java 修改昵称
│                   │   ├── EditProfileActivity.java 编辑资料
│                   │   ├── EditSexActivity.java 修改性别
│                   │   ├── EditSignActivity.java 修改签名
│                   │   ├── FansActivity.java 粉丝
│                   │   ├── FindPwdActivity.java 找回密码
│                   │   ├── FollowActivity.java 关注
│                   │   ├── GoodsAddActivity.java 添加商品
│                   │   ├── LiveClassActivity.java 直播分类
│                   │   ├── LiveRecommendActivity.java 直播推荐
│                   │   ├── LiveVoiceRoomActivity.java 语音直播间列表
│                   │   ├── LoginActivity.java 登录
│                   │   ├── LoginInvalidActivity.java 登录失效
│                   │   ├── MainActivity.java 首页
│                   │   ├── MallClassActivity.java 商城分类
│                   │   ├── MallSearchActivity.java 商城搜索
│                   │   ├── ModifyPwdActivity.java 修改密码
│                   │   ├── MyActiveActivity.java 我的动态
│                   │   ├── MyCoinActivity.java 充值
│                   │   ├── MyImpressActivity.java 我的印象
│                   │   ├── MyProfitActivity.java 我的收益
│                   │   ├── MyVideoActivity.java 我的视频
│                   │   ├── RankActivity.java 排行榜
│                   │   ├── RecommendActivity.java 推荐
│                   │   ├── RegisterActivity.java 注册
│                   │   ├── SearchActivity.java 搜索
│                   │   ├── SettingActivity.java 设置
│                   │   ├── ShopActivity.java 商城
│                   │   ├── ShopGoodsActivity.java 商城商品
│                   │   ├── TestActivity.java
│                   │   ├── ThreeDistributActivity.java 三级分销
│                   │   └── UserHomeActivity.java 用户主页
│                   ├── adapter recyclerView适配器
│                   │   ├── ActiveAdapter.java 动态
│                   │   ├── ActiveAllTopicAdapter.java 动态所有话题
│                   │   ├── ActiveChooseTopicAdapter.java 动态选择话题
│                   │   ├── ActiveCommentAdapter.java 动态评论
│                   │   ├── ActiveHotTopicAdapter.java 动态热门话题
│                   │   ├── ActiveImageAdapter.java 动态图片
│                   │   ├── ActiveRecomTopicAdapter.java 动态推荐话题
│                   │   ├── ActiveSearchTopicAdapter.java 动态搜索话题
│                   │   ├── CancelConditionAdapter.java 注销条件
│                   │   ├── CashAccountAdapter.java 注销装好
│                   │   ├── CoinAdapter.java 充值
│                   │   ├── CoinPayAdapter.java 充值
│                   │   ├── LiveShareAdapter.java 直播间分享
│                   │   ├── LoginTypeAdapter.java 登录类型
│                   │   ├── MainHomeFollowAdapter.java 首页直播关注
│                   │   ├── MainHomeLiveAdapter.java 首页直播
│                   │   ├── MainHomeLiveClassAdapter.java 首页直播分类
│                   │   ├── MainHomeLiveRecomAdapter.java 首页直播推荐
│                   │   ├── MainHomeNearAdapter.java 首页附近直播
│                   │   ├── MainHomeVideoAdapter.java 首页视频
│                   │   ├── MainHomeVideoClassAdapter.java 首页视频分类
│                   │   ├── MainListAdapter.java 排行榜
│                   │   ├── MainMallAdapter.java 首页商城
│                   │   ├── MainMallClassAdapter.java 首页商城分类
│                   │   ├── MainMeAdapter.java 我的
│                   │   ├── MallClassAdapter.java 商城分类
│                   │   ├── MallSearchAdapter.java 商城搜索
│                   │   ├── RecommendAdapter.java 推荐
│                   │   ├── SearchAdapter.java 搜索
│                   │   ├── SettingAdapter.java 设置
│                   │   ├── ShopAdapter.java 商城
│                   │   └── VideoHomeAdapter.java 视频
│                   ├── bean 实体类
│                   │   ├── ActiveBean.java 动态
│                   │   ├── ActiveCommentBean.java 动态评论
│                   │   ├── ActiveImageBean.java 动态图片
│                   │   ├── ActiveTopicBean.java 动态话题
│                   │   ├── ActiveUserBean.java 动态用户
│                   │   ├── BannerBean.java 轮播图
│                   │   ├── BonusBean.java 签到奖励
│                   │   ├── CancelConditionBean.java 注销条件
│                   │   ├── CashAccountBean.java 提现账户
│                   │   ├── ListBean.java 直播间
│                   │   ├── RecommendBean.java 推荐
│                   │   ├── SettingBean.java 设置
│                   │   ├── StoreBean.java
│                   │   └── UserHomeConBean.java
│                   ├── custom
│                   │   ├── ActiveLikeImage.java
│                   │   └── BonusItemView.java
│                   ├── dialog
│                   │   ├── ActiveInputDialogFragment.java
│                   │   ├── LoginForbiddenDialogFragment.java
│                   │   ├── LoginTipDialogFragment.java
│                   │   └── MainStartDialogFragment.java
│                   ├── event
│                   │   ├── ActiveCommentEvent.java
│                   │   ├── ActiveDeleteEvent.java
│                   │   ├── ActiveLikeEvent.java
│                   │   └── RegSuccessEvent.java
│                   ├── http
│                   │   ├── MainHttpConsts.java
│                   │   └── MainHttpUtil.java
│                   ├── interfaces
│                   │   ├── AppBarStateListener.java
│                   │   ├── DataLoader.java
│                   │   ├── MainAppBarExpandListener.java
│                   │   ├── MainAppBarLayoutListener.java
│                   │   └── MainStartChooseCallback.java
│                   ├── presenter
│                   │   └── CheckLivePresenter.java
│                   ├── utils
│                   │   ├── AudioRecorderEx.java
│                   │   ├── FilePathUtil.java
│                   │   └── MainIconUtil.java
│                   └── views
│                       ├── AbsMainActiveViewHolder.java 动态
│                       ├── AbsMainHomeChildViewHolder.java 首页
│                       ├── AbsMainHomeParentViewHolder.java 首页
│                       ├── AbsMainListChildViewHolder.java 首页
│                       ├── AbsMainViewHolder.java 首页
│                       ├── ActiveHomeViewHolder.java 动态
│                       ├── ActiveRecordVoiceViewHolder.java 发动态录音
│                       ├── ActiveRecordVoiceViewHolder2.java 发动态录音
│                       ├── BonusViewHolder.java 签到奖励
│                       ├── CashAccountViewHolder.java 提现账户
│                       ├── GoodsAddTaoBaoViewHolder.java 添加商品
│                       ├── GoodsAddXcxViewHolder.java
│                       ├── MainActiveFollowViewHolder.java 动态关注
│                       ├── MainActiveNewestViewHolder.java 动态最新
│                       ├── MainActiveRecommendViewHolder.java 动态推荐
│                       ├── MainActiveViewHolder.java 动态
│                       ├── MainHomeFollowViewHolder.java 首页关注
│                       ├── MainHomeLiveViewHolder.java 首页直播
│                       ├── MainHomeNearViewHolder.java 首页最近
│                       ├── MainHomeVideoViewHolder.java 首页视频
│                       ├── MainHomeViewHolder.java 首页
│                       ├── MainListContributeViewHolder.java 贡献榜
│                       ├── MainListProfitViewHolder.java 收益榜
│                       ├── MainListViewHolder.java 排行榜
│                       ├── MainMallViewHolder.java 首页商城
│                       ├── MainMeViewHolder.java 我的
│                       ├── UserHomeDetailViewHolder.java 个人主页详情
│                       ├── UserHomeViewHolder.java 个人主页
│                       ├── UserHomeViewHolder2.java
│                       └── VideoHomeViewHolder.java 个人主页视频
├── mall 商城
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           └── java
│               └── com.yuanfen.mall
│                   ├── activity
│                   │   ├── BuyerAccountActivity.java 买家商户
│                   │   ├── BuyerActivity.java 买家
│                   │   ├── BuyerAddressActivity.java 买家地址
│                   │   ├── BuyerAddressEditActivity.java 买家编辑地址
│                   │   ├── BuyerCashActivity.java 买家提现
│                   │   ├── BuyerCommentActivity.java 买家评论
│                   │   ├── BuyerCommentAppendActivity.java 买家追评
│                   │   ├── BuyerOrderActivity.java 买家订单
│                   │   ├── BuyerOrderDetailActivity.java 买家订单详情
│                   │   ├── BuyerRefundApplyActivity.java 买家退款申请
│                   │   ├── BuyerRefundDetailActivity.java 买家退款详情
│                   │   ├── BuyerRefundOfficialActivity.java 买家退款
│                   │   ├── ChooseGoodsClassActivity.java 选择商品
│                   │   ├── GoodsAddOutSideActivity.java 添加外部商品
│                   │   ├── GoodsCommentActivity.java 商品评论
│                   │   ├── GoodsDetailActivity.java 商品详情
│                   │   ├── GoodsEditSpecActivity.java 商品规格
│                   │   ├── GoodsMakeOrderActivity.java 创建订单
│                   │   ├── GoodsOutSideDetailActivity.java 外部商品详情
│                   │   ├── GoodsRecordActivity.java 商品记录
│                   │   ├── GoodsSearchActivity.java 商品搜索
│                   │   ├── ManageClassActivity.java 设置经营类目
│                   │   ├── OrderMessageActivity.java 订单消息
│                   │   ├── PayContentActivity1.java 付费内容
│                   │   ├── PayContentActivity2.java 付费内容
│                   │   ├── PayContentClassActivity.java 付费内容类型
│                   │   ├── PayContentDetailActivity.java 付费内容详情
│                   │   ├── PayContentPubActivity.java 发布付费内容
│                   │   ├── SellerAccountActivity.java 卖家账户
│                   │   ├── SellerActivity.java 卖家页面
│                   │   ├── SellerAddGoodsActivity.java 卖家添加商品
│                   │   ├── SellerAddressActivity.java 卖家地址
│                   │   ├── SellerAddressEditActivity.java 卖家编辑地址
│                   │   ├── SellerCashActivity.java 卖家提现
│                   │   ├── SellerManageGoodsActivity.java 卖家商品管理
│                   │   ├── SellerOrderActivity.java 卖家订单
│                   │   ├── SellerOrderDetailActivity.java 卖家订单详情
│                   │   ├── SellerRefundDetailActivity.java 卖家退款详情
│                   │   ├── SellerRejectRefundActivity.java 卖家拒绝退款
│                   │   ├── SellerSendActivity.java 卖家发货
│                   │   ├── ShopApplyActivity.java 店铺申请
│                   │   ├── ShopApplyBondActivity.java 店铺申请开店保证金
│                   │   ├── ShopApplyResultActivity.java 店铺申请结果
│                   │   ├── ShopCertActivity.java 店铺认证
│                   │   ├── ShopDetailActivity.java 店铺详情
│                   │   └── ShopHomeActivity.java 店铺主页
│                   ├── adapter recyclerView适配器
│                   │   ├── AddGoodsCommentAdapter.java
│                   │   ├── AddGoodsDetailAdapter.java
│                   │   ├── AddGoodsSpecAdapter.java
│                   │   ├── AddGoodsTitleAdapter.java
│                   │   ├── BuyerAddressAdapter.java
│                   │   ├── BuyerOrderAllAdapter.java
│                   │   ├── BuyerOrderBaseAdapter.java
│                   │   ├── BuyerOrderCommentAdapter.java
│                   │   ├── BuyerOrderPayAdapter.java
│                   │   ├── BuyerOrderReceiveAdapter.java
│                   │   ├── BuyerOrderRefundAdapter.java
│                   │   ├── BuyerOrderSendAdapter.java
│                   │   ├── BuyerRefundRecordAdapter.java
│                   │   ├── GoodsChooseSpecAdapter.java
│                   │   ├── GoodsClassLeftAdapter.java
│                   │   ├── GoodsClassRightAdapter.java
│                   │   ├── GoodsCommentAdapter.java
│                   │   ├── GoodsCommentTypeAdapter.java
│                   │   ├── GoodsDetailAdapter.java
│                   │   ├── GoodsEditSpecAdapter.java
│                   │   ├── GoodsPayAdapter.java
│                   │   ├── GoodsRecordAdapter.java
│                   │   ├── GoodsTitleAdapter.java
│                   │   ├── ManageClassAdapter.java
│                   │   ├── OrderMessageAdapter.java
│                   │   ├── PayBuyAdapter.java
│                   │   ├── PayContentClassAdapter.java
│                   │   ├── PayContentMulAdapter.java
│                   │   ├── PayContentVideoPlayAdapter.java
│                   │   ├── PayPubAdapter.java
│                   │   ├── SearchGoodsAdapter.java
│                   │   ├── SearchPayAdapter.java
│                   │   ├── SellerOrderAllAdapter.java
│                   │   ├── SellerOrderAllRefundAdapter.java
│                   │   ├── SellerOrderBaseAdapter.java
│                   │   ├── SellerOrderFinishAdapter.java
│                   │   ├── SellerOrderPayAdapter.java
│                   │   ├── SellerOrderReceiveAdapter.java
│                   │   ├── SellerOrderRefundAdapter.java
│                   │   ├── SellerOrderSendAdapter.java
│                   │   ├── SellerShenHeAdapter.java
│                   │   ├── SellerWuliuAdapter.java
│                   │   ├── SellerXiaJiaAdapter.java
│                   │   ├── SellerZaiShouAdapter.java
│                   │   └── ShopHomeAdapter.java
│                   ├── bean 实体类
│                   │   ├── AddGoodsCommentImageBean.java
│                   │   ├── AddGoodsImageBean.java
│                   │   ├── AddGoodsSpecBean.java
│                   │   ├── BuyerAddressBean.java
│                   │   ├── BuyerOrderBean.java
│                   │   ├── BuyerRefundRecordBean.java
│                   │   ├── GoodsChooseSpecBean.java
│                   │   ├── GoodsClassBean.java
│                   │   ├── GoodsClassTitleBean.java
│                   │   ├── GoodsCommentBean.java
│                   │   ├── GoodsCommentTypeBean.java
│                   │   ├── GoodsHomeClassBean.java
│                   │   ├── GoodsManageBean.java
│                   │   ├── GoodsPayBean.java
│                   │   ├── GoodsRecordBean.java
│                   │   ├── GoodsRecordItemBean.java
│                   │   ├── GoodsRecordTitleBean.java
│                   │   ├── GoodsSimpleBean.java
│                   │   ├── GoodsSpecBean.java
│                   │   ├── ManageClassBean.java
│                   │   ├── OrderMsgBean.java
│                   │   ├── PayContentBean.java
│                   │   ├── PayContentBuyBean.java
│                   │   ├── PayContentClassBean.java
│                   │   ├── PayContentVideoBean.java
│                   │   ├── PayContentVideoPlayBean.java
│                   │   ├── RefundReasonBean.java
│                   │   ├── RejectRefundBean.java
│                   │   ├── SearchGoodsBean.java
│                   │   ├── SearchPayBean.java
│                   │   ├── SellerOrderBean.java
│                   │   └── WuliuBean.java
│                   ├── dialog
│                   │   ├── BuyerRefundReasonDialogFragment.java
│                   │   ├── GoodsCertDialogFragment.java
│                   │   ├── GoodsPayDialogFragment.java
│                   │   ├── GoodsSpecDialogFragment.java
│                   │   ├── OfficialRefundReasonDialogFragment.java
│                   │   ├── PayCommentDialogFragment.java
│                   │   ├── PayContentPayDialogFragment.java
│                   │   ├── PayContentResultDialogFragment.java
│                   │   ├── PayContentTipDialogFragment.java
│                   │   └── SellerRejectReasonDialogFragment.java
│                   ├── http
│                   │   ├── MallHttpConsts.java
│                   │   └── MallHttpUtil.java
│                   └── views
│                       ├── AbsBuyerOrderViewHolder.java 买家订单
│                       ├── AbsSellerOrderViewHolder.java 卖家订单
│                       ├── BuyerOrderAllViewHolder.java 买家全部订单
│                       ├── BuyerOrderCommentViewHolder.java 买家待评论订单
│                       ├── BuyerOrderPayViewHolder.java 买家待支付订单
│                       ├── BuyerOrderReceiveViewHolder.java 买家待收货订单
│                       ├── BuyerOrderRefundViewHolder.java 买家退款中订单
│                       ├── BuyerOrderSendViewHolder.java 买家待发货订单
│                       ├── PayBuyViewHolder.java 付费内容我购买的
│                       ├── PayContentChooseVideoViewHolder.java 付费内容选择视频
│                       ├── PayContentMulViewHolder.java 付费内容多个视频
│                       ├── PayContentSingleViewHolder.java 付费内容单个视频
│                       ├── PayPubViewHolder.java 付费内容发布
│                       ├── SearchGoodsViewHolder.java 搜索商品
│                       ├── SearchPayViewHolder.java 搜索付费内容
│                       ├── SellerOrderAllRefundViewHolder.java 卖家全部退款订单
│                       ├── SellerOrderAllViewHolder.java 卖家全部订单
│                       ├── SellerOrderClosedViewHolder.java 卖家已关闭订单
│                       ├── SellerOrderCommentViewHolder.java 卖家带评论订单
│                       ├── SellerOrderFinishViewHolder.java 卖家已完成订单
│                       ├── SellerOrderPayViewHolder.java 卖家待支付订单
│                       ├── SellerOrderReceiveViewHolder.java 卖家待收货订单
│                       ├── SellerOrderRefundViewHolder.java 卖家退款订单
│                       ├── SellerOrderSendViewHolder.java 卖家待收货订单
│                       ├── SellerShenHeViewHolder.java 卖家审核商品
│                       ├── SellerXiaJiaViewHolder.java 卖家下架商品
│                       └── SellerZaiShouViewHolder.java 卖家在售商品
├── video 短视频
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           └── java
│               └── com.yuanfen.video
│                   ├── activity
│                   │   ├── AbsVideoCommentActivity.java 视频评论
│                   │   ├── AbsVideoPlayActivity.java 视频播放
│                   │   ├── VideoChooseActivity.java 选择视频
│                   │   ├── VideoChooseClassActivity.java 选择视频分类
│                   │   ├── VideoEditActivity.java 视频编辑
│                   │   ├── VideoGoodsAddActivity.java 视频添加商品
│                   │   ├── VideoPlayActivity.java 视频播放
│                   │   ├── VideoPublishActivity.java 视频发布
│                   │   ├── VideoRecordActivity.java 视频录制
│                   │   └── VideoReportActivity.java 视频举报
│                   ├── adapter recyclerView适配器
│                   │   ├── MusicAdapter.java 视频背景音乐
│                   │   ├── MusicClassAdapter.java 视频音乐分类
│                   │   ├── VideoChooseAdapter.java 视频选择
│                   │   ├── VideoChooseClassAdapter.java 视频选择分类
│                   │   ├── VideoCommentAdapter.java 视频评论
│                   │   ├── VideoGoodsAddAdapter.java 视频添加商品
│                   │   ├── VideoPubShareAdapter.java 视频发布分享
│                   │   ├── VideoReportAdapter.java 视频举报
│                   │   ├── VideoScrollAdapter.java 视频上下滑动
│                   │   └── VideoShareAdapter.java 视频分享
│                   ├── bean 实体类
│                   │   ├── MusicBean.java
│                   │   ├── MusicClassBean.java
│                   │   ├── VideoBean.java
│                   │   ├── VideoChooseBean.java
│                   │   ├── VideoCommentBean.java
│                   │   └── VideoReportBean.java
│                   ├── custom
│                   │   ├── ColorfulProgress.java
│                   │   ├── NumberProgressBar.java
│                   │   ├── RangeSlider.java
│                   │   ├── RangeSliderViewContainer.java
│                   │   ├── RecordProgressView.java
│                   │   ├── SliderViewContainer.java
│                   │   ├── ThumbView.java
│                   │   ├── ThumbnailAdapter.java
│                   │   ├── VideoLoadingBar.java
│                   │   ├── VideoProgressController.java
│                   │   ├── VideoProgressView.java
│                   │   ├── VideoRecordBtnView.java
│                   │   └── ViewTouchProcess.java
│                   ├── dialog
│                   │   ├── VideoGoodsDialogFragment.java
│                   │   ├── VideoInputDialogFragment.java
│                   │   ├── VideoMusicClassDialog.java
│                   │   └── VideoShareDialogFragment.java
│                   ├── event
│                   │   ├── VideoCommentEvent.java
│                   │   ├── VideoDeleteEvent.java
│                   │   ├── VideoLikeEvent.java
│                   │   ├── VideoScrollPageEvent.java
│                   │   └── VideoShareEvent.java
│                   ├── http
│                   │   ├── VideoHttpConsts.java
│                   │   └── VideoHttpUtil.java
│                   ├── interfaces
│                   │   ├── VideoMusicActionListener.java
│                   │   └── VideoScrollDataHelper.java
│                   ├── utils
│                   │   ├── MusicMediaPlayerUtil.java
│                   │   ├── VideoIconUtil.java
│                   │   ├── VideoLocalUtil.java
│                   │   ├── VideoStorge.java
│                   │   └── VideoTextRender.java
│                   └── views
│                       ├── VideoCommentViewHolder.java 视频评论
│                       ├── VideoEditCutViewHolder.java 视频裁剪
│                       ├── VideoEditFilterViewHolder.java 视频滤镜
│                       ├── VideoEditMusicViewHolder.java 视频背景音乐
│                       ├── VideoMusicChildViewHolder.java 视频背景音乐
│                       ├── VideoMusicCollectViewHolder.java 视频收藏音乐
│                       ├── VideoMusicHotViewHolder.java 视频热门音乐
│                       ├── VideoMusicSearchViewHolder.java 视频音乐搜索
│                       ├── VideoMusicViewHolder.java 视频音乐
│                       ├── VideoPlayViewHolder.java 视频播放
│                       ├── VideoPlayWrapViewHolder.java 视频播放
│                       ├── VideoProcessViewHolder.java 视频处理进度
│                       └── VideoScrollViewHolder.java 视频上下滑动

```
