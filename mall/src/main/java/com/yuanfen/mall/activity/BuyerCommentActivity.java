package com.yuanfen.mall.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import  androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import  androidx.recyclerview.widget.RecyclerView;;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuanfen.common.Constants;
import com.yuanfen.common.activity.AbsActivity;
import com.yuanfen.common.activity.ChooseVideoActivity;
import com.yuanfen.common.custom.ItemDecoration;
import com.yuanfen.common.custom.RatingBar;
import com.yuanfen.common.glide.ImgLoader;
import com.yuanfen.common.http.HttpCallback;
import com.yuanfen.common.interfaces.ActivityResultCallback;
import com.yuanfen.common.interfaces.CommonCallback;
import com.yuanfen.common.interfaces.ImageResultCallback;
import com.yuanfen.common.interfaces.OnItemClickListener;
import com.yuanfen.common.interfaces.PermissionCallback;
import com.yuanfen.common.upload.UploadBean;
import com.yuanfen.common.upload.UploadCallback;
import com.yuanfen.common.upload.UploadStrategy;
import com.yuanfen.common.upload.UploadUtil;
import com.yuanfen.common.utils.ActivityResultUtil;
import com.yuanfen.common.utils.DialogUitl;
import com.yuanfen.common.utils.FloatWindowHelper;
import com.yuanfen.common.utils.MediaUtil;
import com.yuanfen.common.utils.PermissionUtil;
import com.yuanfen.common.utils.StringUtil;
import com.yuanfen.common.utils.ToastUtil;
import com.yuanfen.common.utils.WordUtil;
import com.yuanfen.mall.R;
import com.yuanfen.mall.adapter.AddGoodsCommentAdapter;
import com.yuanfen.mall.bean.AddGoodsCommentImageBean;
import com.yuanfen.mall.http.MallHttpConsts;
import com.yuanfen.mall.http.MallHttpUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 买家 发表商品评论
 */
public class BuyerCommentActivity extends AbsActivity implements View.OnClickListener {

    public static void forward(Context context, String orderId) {
        Intent intent = new Intent(context, BuyerCommentActivity.class);
        intent.putExtra(Constants.MALL_ORDER_ID, orderId);
        ((Activity) context).startActivityForResult(intent, 0);
    }

    private String mOrderId;
    private ImageView mGoodsThumb;
    private TextView mGoodsName;
    private TextView mGoodsSpecName;
    private EditText mEditText;
    private RatingBar mGoodsStar;//商品星数
    private RatingBar mSendStar;//送货服务星数
    private RatingBar mServiceStar;//服务态度星数
    private RecyclerView mRecyclerView;
    private CheckBox mCheckBox;
    private TextView mPubStatus;//公开状态
    private String mPubString;
    private String mUnPubString;
    private int mPubColor;
    private int mUnPubColor;
    private String mContentVal;
    private AddGoodsCommentAdapter mAdapter;
    private int mTargetPosition;
    private Runnable mPremissionVideoCallback;//选择视频
    private Dialog mLoading;
    private ImageResultCallback mImageResultCallback = new ImageResultCallback() {
        @Override
        public void beforeCamera() {

        }

        @Override
        public void onSuccess(File file) {
            if (file != null && file.exists()) {
                if (mAdapter != null) {
                    mAdapter.setImageFile(mTargetPosition, file);
                }
            } else {
                ToastUtil.show(R.string.file_not_exist);
            }
        }


        @Override
        public void onFailure() {
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buyer_comment;
    }

    @Override
    protected void main() {
        mOrderId = getIntent().getStringExtra(Constants.MALL_ORDER_ID);
        mGoodsThumb = findViewById(R.id.goods_thumb);
        mGoodsName = findViewById(R.id.goods_name);
        mGoodsSpecName = findViewById(R.id.goods_spec_name);
        mEditText = findViewById(R.id.edit);
        mGoodsStar = findViewById(R.id.rating_bar);
        mSendStar = findViewById(R.id.rating_bar_send);
        mServiceStar = findViewById(R.id.rating_bar_service);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false));
        ItemDecoration decoration = new ItemDecoration(mContext, 0x00000000, 15, 0);
        decoration.setOnlySetItemOffsetsButNoDraw(true);
        mRecyclerView.addItemDecoration(decoration);
        mPubString = WordUtil.getString(R.string.mall_238);
        mUnPubString = WordUtil.getString(R.string.mall_244);
        mPubColor = ContextCompat.getColor(mContext, R.color.global);
        mUnPubColor = ContextCompat.getColor(mContext, R.color.gray3);
        mPubStatus = findViewById(R.id.pub_status);
        mCheckBox = findViewById(R.id.checkbox);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mPubStatus != null) {
                    if (isChecked) {
                        mPubStatus.setText(mPubString);
                        mPubStatus.setTextColor(mPubColor);
                    } else {
                        mPubStatus.setText(mUnPubString);
                        mPubStatus.setTextColor(mUnPubColor);
                    }
                }
            }
        });

        findViewById(R.id.btn_publish).setOnClickListener(this);


        mAdapter = new AddGoodsCommentAdapter(mContext);
        mAdapter.setOnItemClickListener(new OnItemClickListener<AddGoodsCommentImageBean>() {
            @Override
            public void onItemClick(AddGoodsCommentImageBean bean, int position) {
                mTargetPosition = position;
                if (position == mAdapter.getItemCount() - 1) {
                    chooseVideo();
                } else {
                    chooseImage();
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        MallHttpUtil.getBuyerOrderDetail(mOrderId, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                if (code == 0 && info.length > 0) {
                    JSONObject obj = JSON.parseObject(info[0]);
                    JSONObject orderInfo = obj.getJSONObject("order_info");
                    if (mGoodsThumb != null) {
                        ImgLoader.display(mContext, orderInfo.getString("spec_thumb_format"), mGoodsThumb);
                    }
                    if (mGoodsName != null) {
                        mGoodsName.setText(orderInfo.getString("goods_name"));
                    }
                    if (mGoodsSpecName != null) {
                        mGoodsSpecName.setText(StringUtil.contact(orderInfo.getString("spec_name"), "x", orderInfo.getString("nums")));
                    }
                }
            }
        });
    }


    /**
     * 选择图片
     */
    private void chooseImage() {
        DialogUitl.showStringArrayDialog(mContext, new Integer[]{R.string.alumb, R.string.camera}, new DialogUitl.StringArrayDialogCallback() {
            @Override
            public void onItemClick(String text, int tag) {
                if (tag == R.string.camera) {
                    MediaUtil.getImageByCamera(BuyerCommentActivity.this, false, mImageResultCallback);
                } else if (tag == R.string.alumb) {
                    MediaUtil.getImageByAlumb(BuyerCommentActivity.this, false, mImageResultCallback);
                }
            }
        });
    }

    /**
     * 选择视频
     */
    private void chooseVideo() {
        if (!FloatWindowHelper.checkVoice(false)) {
            return;
        }
        PermissionUtil.request(this, new PermissionCallback() {
                    @Override
                    public void onAllGranted() {
                        Intent intent = new Intent(mContext, ChooseVideoActivity.class);
                        ActivityResultUtil.startActivityForResult(BuyerCommentActivity.this, intent, new ActivityResultCallback() {
                            @Override
                            public void onSuccess(Intent intent) {
                                String videoPath = intent.getStringExtra(Constants.VIDEO_PATH);
                                if (!TextUtils.isEmpty(videoPath) && mAdapter != null) {
                                    mAdapter.setImageFile(mAdapter.getItemCount() - 1, new File(videoPath));
                                }
                            }
                        });
                    }
                }, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO);
    }

    private void showLoading() {
        if (mLoading == null) {
            mLoading = DialogUitl.loadingDialog(mContext, WordUtil.getString(R.string.video_pub_ing));
        }
        if (!mLoading.isShowing()) {
            mLoading.show();
        }
    }


    private void hideLoading() {
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_publish) {
            submit();
        }
    }

    public void submit() {
        mContentVal = mEditText.getText().toString().trim();
        if (TextUtils.isEmpty(mContentVal)) {
            ToastUtil.show(R.string.mall_245);
            return;
        }
        uploadFile();
    }

    /**
     * 上传文件
     */
    private void uploadFile() {
        showLoading();
        final List<UploadBean> uploadList = new ArrayList<>();
        File videoFile = mAdapter.getVideoFile();
        if (videoFile != null && videoFile.exists()) {
            UploadBean upVideoBean = new UploadBean(videoFile, UploadBean.VIDEO);
            upVideoBean.setTag(mAdapter);
            uploadList.add(upVideoBean);
            File coverImageFile = createVideoCoverImage(videoFile.getAbsolutePath());
            if (coverImageFile != null && coverImageFile.length() > 0 && coverImageFile.exists()) {
                UploadBean upCoverImgBean = new UploadBean(coverImageFile, UploadBean.IMG);
                upCoverImgBean.setTag(mAdapter);
                uploadList.add(upCoverImgBean);
            }
        }

        List<AddGoodsCommentImageBean> imgList = mAdapter.getList();
        for (int i = 0, size = imgList.size() - 1; i < size; i++) {
            AddGoodsCommentImageBean bean = imgList.get(i);
            File uploadFile = bean.getFile();
            if (uploadFile != null && uploadFile.exists()) {
                UploadBean uploadBean = new UploadBean(uploadFile, UploadBean.IMG);
                uploadBean.setTag(bean);
                uploadList.add(uploadBean);
            }
        }
        if (uploadList.size() > 0) {
            UploadUtil.startUpload(new CommonCallback<UploadStrategy>() {
                @Override
                public void callback(UploadStrategy strategy) {
                    strategy.upload(uploadList, true, new UploadCallback() {

                        @Override
                        public void onFinish(List<UploadBean> list, boolean success) {
                            if (!success) {
                                hideLoading();
                                return;
                            }
                            for (UploadBean bean : list) {
                                Object tag = bean.getTag();
                                if (tag != null) {
                                    if (tag == mAdapter) {
                                        if (bean.getType() == UploadBean.VIDEO) {
                                            mAdapter.setVideoUrl(bean.getRemoteFileName());
                                        } else if (bean.getType() == UploadBean.IMG) {
                                            mAdapter.setVideoImgUrl(bean.getRemoteFileName());
                                        }
                                    } else if (tag instanceof AddGoodsCommentImageBean) {
                                        ((AddGoodsCommentImageBean) tag).setUploadResultUrl(bean.getRemoteFileName());
                                    }
                                }
                            }
                            pubComment();
                        }
                    });
                }
            });
        } else {
            hideLoading();
            pubComment();
        }
    }


    /**
     * 发表评价
     */
    private void pubComment() {
        StringBuilder sb = new StringBuilder();
        List<AddGoodsCommentImageBean> imgList = mAdapter.getList();
        for (int i = 0, size = imgList.size() - 1; i < size; i++) {
            String uploadResultUrl = imgList.get(i).getUploadResultUrl();
            if (!TextUtils.isEmpty(uploadResultUrl)) {
                sb.append(uploadResultUrl);
                sb.append(",");
            }
        }
        String thumbs = sb.toString();
        if (thumbs.length() > 1) {
            thumbs = thumbs.substring(0, thumbs.length() - 1);
        }
        MallHttpUtil.buyerSetComment(
                mOrderId,
                mContentVal,
                thumbs,
                mAdapter.getVideoUrl(),
                mAdapter.getVideoImgUrl(),
                mCheckBox.isChecked() ? "0" : "1",
                mGoodsStar.getFillCount(),
                mSendStar.getFillCount(),
                mServiceStar.getFillCount(),
                new HttpCallback() {
                    @Override
                    public void onSuccess(int code, String msg, String[] info) {
                        if (code == 0) {
                            setResult(RESULT_OK);
                            finish();
                        }
                        ToastUtil.show(msg);
                    }

                    @Override
                    public void onFinish() {
                        hideLoading();
                    }
                }
        );
    }


    /**
     * 生成视频封面图
     */
    private File createVideoCoverImage(String videoPath) {
        MediaMetadataRetriever mmr = null;
        Bitmap bitmap = null;
        try {
            mmr = new MediaMetadataRetriever();
            mmr.setDataSource(videoPath);
            bitmap = mmr.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST);
        } catch (Exception e) {
            bitmap = null;
            e.printStackTrace();
        } finally {
            if (mmr != null) {
                mmr.release();
            }
        }
        if (bitmap == null) {
            return null;
        }
        String coverImagePath = videoPath.replace(".mp4", ".jpg");
        File imageFile = new File(coverImagePath);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (bitmap != null) {
            bitmap.recycle();
        }
        return imageFile;

    }


    @Override
    protected void onDestroy() {
        MallHttpUtil.cancel(MallHttpConsts.GET_BUYER_ORDER_DETAIL);
        MallHttpUtil.cancel(MallHttpConsts.BUYER_SET_COMMENT);
        UploadUtil.cancelUpload();
        hideLoading();
        super.onDestroy();
    }


}
