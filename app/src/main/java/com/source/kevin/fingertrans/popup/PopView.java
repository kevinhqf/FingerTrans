package com.source.kevin.fingertrans.popup;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.source.kevin.fingertrans.R;
import com.source.kevin.fingertrans.data.TransInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * PopView
 */
public class PopView implements PopupContract.View, View.OnClickListener {

    private WindowManager mWindowManager;
    private Context mContext;
    private OnViewDismissListener mOnViewDismissListener;
    private LinearLayout mPopView;
    @BindView(R.id.pv_tv_query)
    TextView tv_query;
    @BindView(R.id.pv_tv_translate)
    TextView tv_translation;
    @BindView(R.id.pv_tv_explains)
    TextView tv_explain;
    // the flag that is the popView dismiss
    private boolean isDismiss = true;


    public PopView(Context application) {
        mContext = application;
        mWindowManager = (WindowManager) application.getSystemService(Context.WINDOW_SERVICE);

    }


    /**
     * calculate the popup view layout parameters
     *
     * @return the popView layout parameter
     */
    private WindowManager.LayoutParams getPopViewParams() {
        int w = WindowManager.LayoutParams.MATCH_PARENT;
        int h = WindowManager.LayoutParams.WRAP_CONTENT;

        int flags = 0;
        int type;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            type = WindowManager.LayoutParams.TYPE_TOAST;
        } else {
            type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(w, h, type, flags, PixelFormat.TRANSLUCENT);
        layoutParams.gravity = Gravity.TOP;
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        layoutParams.gravity = Gravity.CENTER | Gravity.TOP;
        layoutParams.x = 0;
        layoutParams.y = 0;
        return layoutParams;
    }


    /**
     * remove the popView
     */
    private void removePoppedView() {
        if (isDismiss)return;
        if (mWindowManager != null && mPopView != null) {
            mWindowManager.removeView(mPopView);
        }
        isDismiss = true;
    }

    /**
     * remove the popView and handle the dismiss mission
     */
    private void removePoppedViewAndClear() {
        removePoppedView();
        if (mOnViewDismissListener != null) {
            mOnViewDismissListener.onViewDismiss();
        }
    }





    @Override
    public void onClick(View view) {
        removePoppedViewAndClear();
    }




    @Override
    public void showPopView(TransInfo info) {
        removePoppedView();
        if (mPopView == null) {
            mPopView = (LinearLayout) View.inflate(mContext, R.layout.pop_view, null);
            ButterKnife.bind(this, mPopView);
            mPopView.setOnClickListener(this);
        }

        tv_query.setText(info.getQuery());
        if (info.getExplains() == null) {
            tv_explain.setVisibility(View.GONE);
            tv_translation.setVisibility(View.VISIBLE);
            tv_translation.setText(info.getTranslations());

        } else {
            tv_translation.setVisibility(View.GONE);
            tv_explain.setVisibility(View.VISIBLE);
            tv_explain.setText(info.getExplains());
        }

        mWindowManager.addView(mPopView, getPopViewParams());
        isDismiss = false;
    }

    @Override
    public void setPresenter(PopupContract.Presenter presenter) {
        
    }

    public void setOnViewDismissListener(OnViewDismissListener listener) {
        mOnViewDismissListener = listener;
    }

    /**
     * dismiss listener interface
     */
    public interface OnViewDismissListener {
        void onViewDismiss();
    }
}