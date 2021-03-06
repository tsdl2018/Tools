package com.gh.tools.photo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.gh.tools.R;


/**
 * @author: gh
 * @description:
 * @date: 2016/9/9 10:00.
 */
public class SelectPhotoPop extends PopupWindow implements View.OnClickListener {

    private Activity mActivity;
    private PhotoPresenter mPresenter;

    public SelectPhotoPop(Context context, PhotoPresenter presenter) {
        this.mPresenter = presenter;
        mActivity = (Activity)context;
        View view = LayoutInflater.from(context).inflate(R.layout.pop_select_photo, null);
        this.setContentView(view);

        view.findViewById(R.id.id_tv_camera).setOnClickListener(this);
        view.findViewById(R.id.id_tv_photo).setOnClickListener(this);
        view.findViewById(R.id.id_tv_cancle).setOnClickListener(this);

        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_tv_camera:
                //跳转到调用系统相机
                mPresenter.toCamera();
                dismiss();
                break;
            case R.id.id_tv_photo:
                //跳转到调用系统图库
                mPresenter.toAlbum();
                dismiss();
                break;
            case R.id.id_tv_cancle:
                dismiss();
                break;
        }
    }
}
