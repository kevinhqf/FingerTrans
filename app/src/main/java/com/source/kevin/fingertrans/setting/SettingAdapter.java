package com.source.kevin.fingertrans.setting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.source.kevin.fingertrans.R;
import com.source.kevin.fingertrans.data.Setting;
import com.source.kevin.fingertrans.view.OnItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * setting menu adapter
 */
public class SettingAdapter extends RecyclerView.Adapter<SettingVH> {

    private Context mContext;
    private ArrayList<Setting> mSettings;
    private OnItemClickListener<Setting> onItemClickListener;

    public SettingAdapter(Context context, ArrayList<Setting> settings) {
        this.mContext = context;
        this.mSettings = settings;
    }

    @Override
    public SettingVH onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case SettingPresenter.SETTING_MORE:
                return new SettingVH(LayoutInflater.from(mContext).inflate(R.layout.item_setting_more, null));
            case SettingPresenter.SETTING_SWITCHER:
                return new SwitcherHolder(LayoutInflater.from(mContext).inflate(R.layout.item_setting_switch, null));
            case SettingPresenter.SETTING_NORMAL:
                return new SettingVH(LayoutInflater.from(mContext).inflate(R.layout.item_setting_normal, null));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return mSettings.get(position).getType();

    }

    @Override
    public void onBindViewHolder(final SettingVH holder, int position) {
        final Setting s = mSettings.get(position);
        holder.setTextContent(s);
        if (holder instanceof SwitcherHolder) {
            SwitcherHolder sh = (SwitcherHolder) holder;
            sh.sb_switcher.setCheckedImmediately(s.isChecked());
            sh.sb_switcher.setClickable(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder instanceof SwitcherHolder) {
                    SwitcherHolder sh = (SwitcherHolder) holder;
                    boolean isCheck = !s.isChecked();
                    s.setChecked(isCheck);
                    sh.sb_switcher.setCheckedImmediately(isCheck);
                }


                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(s);
            }
        });
    }



    @Override
    public int getItemCount() {
        return mSettings == null ? 0 : mSettings.size();
    }


    public void setOnItemClickListener(OnItemClickListener<Setting> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    class SwitcherHolder extends SettingVH {

        @BindView(R.id.iss_sb_switch)
        SwitchButton sb_switcher;

        public SwitcherHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
