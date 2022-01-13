package com.yuanfen.main.dialog;

import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.yuanfen.common.dialog.AbsDialogFragment;
import com.yuanfen.common.utils.DpUtil;
import com.yuanfen.main.R;

public class AuthExampleDialogFragment extends AbsDialogFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.dialog_auth_example;
    }

    @Override
    protected int getDialogStyle() {
        return R.style.dialog;
    }

    @Override
    protected boolean canCancel() {
        return true;
    }

    @Override
    protected void setWindowAttributes(Window window) {
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = DpUtil.dp2px(300);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }
}
