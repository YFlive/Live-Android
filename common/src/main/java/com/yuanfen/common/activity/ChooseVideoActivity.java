package com.yuanfen.common.activity;

import android.content.Intent;
import android.os.Bundle;
//2import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.GridLayoutManager;
import  androidx.recyclerview.widget.RecyclerView;;
import android.view.View;

import com.yuanfen.common.Constants;
import com.yuanfen.common.R;
import com.yuanfen.common.adapter.ChooseVideoAdapter;
import com.yuanfen.common.bean.ChooseImageBean;
import com.yuanfen.common.bean.ChooseVideoBean;
import com.yuanfen.common.custom.ItemDecoration;
import com.yuanfen.common.dialog.VideoPreviewDialog;
import com.yuanfen.common.interfaces.CommonCallback;
import com.yuanfen.common.interfaces.VideoResultCallback;
import com.yuanfen.common.utils.ChooseVideoUtil;
import com.yuanfen.common.utils.MediaUtil;
import com.yuanfen.common.utils.WordUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 选择视频
 */
public class ChooseVideoActivity extends AbsActivity implements ChooseVideoAdapter.ActionListener {

    private RecyclerView mRecyclerView;
    private ChooseVideoAdapter mAdapter;
    private ChooseVideoUtil mChooseVideoUtil;
    private boolean mUseCamera;//是否使用拍照
    private boolean mUsePreview;//是否使用预览

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_video;
    }


    @Override
    protected void main() {
        Intent intent = getIntent();
        mUseCamera = intent.getBooleanExtra(Constants.USE_CAMERA, true);
        mUsePreview = intent.getBooleanExtra(Constants.USE_PREVIEW, true);
        setTitle(WordUtil.getString(R.string.video_local));
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false));
        ItemDecoration decoration = new ItemDecoration(mContext, 0x00000000, 5, 5);
        decoration.setOnlySetItemOffsetsButNoDraw(true);
        mRecyclerView.addItemDecoration(decoration);
        mChooseVideoUtil = new ChooseVideoUtil();
        mChooseVideoUtil.getLocalVideoList(new CommonCallback<List<ChooseVideoBean>>() {
            @Override
            public void callback(List<ChooseVideoBean> videoList) {
                if (mContext != null && mRecyclerView != null) {
                    List<ChooseVideoBean> list = new ArrayList<>();
                    if (mUseCamera) {
                        list.add(new ChooseVideoBean(ChooseImageBean.CAMERA));
                    }
                    if (videoList != null && videoList.size() > 0) {
                        list.addAll(videoList);
                    } else {
                        View noData = findViewById(R.id.no_data);
                        if (noData != null && noData.getVisibility() != View.VISIBLE) {
                            noData.setVisibility(View.VISIBLE);
                        }
                    }
                    mAdapter = new ChooseVideoAdapter(mContext, list);
                    mAdapter.setActionListener(ChooseVideoActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
        });
    }

    @Override
    public void onCameraClick() {
        MediaUtil.startVideoRecord(this, new VideoResultCallback() {
            @Override
            public void onSuccess(File file, long duration) {
                Intent intent = new Intent();
                intent.putExtra(Constants.VIDEO_PATH, file.getAbsolutePath());
                intent.putExtra(Constants.VIDEO_DURATION, duration);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public void onVideoItemClick(ChooseVideoBean bean) {
        if (mUsePreview) {
            VideoPreviewDialog dialog = new VideoPreviewDialog();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.VIDEO_PATH, bean.getVideoFile().getAbsolutePath());
            bundle.putLong(Constants.VIDEO_DURATION, bean.getDuration());
            dialog.setArguments(bundle);
            dialog.show(getSupportFragmentManager(), "VideoPreviewDialog");
        } else {
            useVideo(bean.getVideoFile().getAbsolutePath(), bean.getDuration());
        }
    }

    /**
     * 使用视频
     */
    public void useVideo(String videoPath, long duration) {
        Intent intent = new Intent();
        intent.putExtra(Constants.VIDEO_PATH, videoPath);
        intent.putExtra(Constants.VIDEO_DURATION, duration);
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        if (mChooseVideoUtil != null) {
            mChooseVideoUtil.release();
        }
        mChooseVideoUtil = null;
        super.onDestroy();
    }

}
