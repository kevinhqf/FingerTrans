package com.source.kevin.fingertrans.appmanage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.source.kevin.fingertrans.R;
import com.source.kevin.fingertrans.data.AppInfo;
import com.source.kevin.fingertrans.view.OnItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * the adapter that control the app list
 */
public class AppManageAdapter extends RecyclerView.Adapter<AppManageAdapter.AppViewHolder> {

    private Context mContext;
    private ArrayList<AppInfo> appInfo;
    private OnItemClickListener<AppInfo> itemClickListener;

    public AppManageAdapter(Context context, ArrayList<AppInfo> appInfo) {
        this.mContext = context;
        this.appInfo = appInfo;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_app, null));

    }


    @Override
    public void onBindViewHolder(final AppViewHolder holder, int position) {


        final AppInfo info = this.appInfo.get(position);
        holder.setContent(info);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = !info.isChecked();
                info.setChecked(isChecked);
                holder.sb_switcher.setCheckedImmediately(isChecked);
                if (itemClickListener != null)
                    itemClickListener.onItemClick(info);

            }
        });

    }

    @Override
    public int getItemCount() {
        return appInfo == null ? 0 : appInfo.size();
    }

    class AppViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ia_iv_icon)
        ImageView iv_icon;
        @BindView(R.id.ia_tv_name)
        TextView tv_name;
        @BindView(R.id.ia_sb_switch)
        SwitchButton sb_switcher;

        public AppViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setContent(final AppInfo info) {
            iv_icon.setImageDrawable(info.getIcon());
            tv_name.setText(info.getAppName());
            sb_switcher.setCheckedImmediately(info.isChecked());
            sb_switcher.setClickable(false);
        }
    }


    public void setOnItemClickListener(OnItemClickListener<AppInfo> listener) {
        this.itemClickListener = listener;
    }

}
