package com.source.kevin.fingertrans.setting;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.source.kevin.fingertrans.R;
import com.source.kevin.fingertrans.data.Setting;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * the abstract view holder
 */
public class SettingVH extends RecyclerView.ViewHolder{
    @BindView(R.id.is_tv_detail)
    TextView tv_detail;
    @BindView(R.id.is_tv_title)
    TextView tv_title;

    public SettingVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

    }
    public void setTextContent(Setting s){

        tv_detail.setText(s.getDetail());
        tv_title.setText(s.getTitle());

    }
}
