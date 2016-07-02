package com.source.kevin.fingertrans.appmanage;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.source.kevin.fingertrans.R;
import com.source.kevin.fingertrans.data.AppInfo;
import com.source.kevin.fingertrans.view.DividerDecoration;
import com.source.kevin.fingertrans.view.OnItemClickListener;

import java.io.BufferedWriter;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * the apps list activity
 */
public class AppManageActivity extends AppCompatActivity implements AppManageContract.View, OnItemClickListener<AppInfo> {
    @BindView(R.id.aa_rv_apps)
    RecyclerView rv_apps;
    private AppManageAdapter mAdapter;
    private AppManagePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_appmanage);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mPresenter = new AppManagePresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @OnClick(R.id.aa_iv_back)
    public void back(View v) {
        onBackPressed();
    }

    @Override
    public void initAppList(ArrayList<AppInfo> appInfos) {
        mAdapter = new AppManageAdapter(this, appInfos);
        mAdapter.setOnItemClickListener(this);
        rv_apps.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_apps.setAdapter(mAdapter);
        rv_apps.addItemDecoration(new DividerDecoration(this, DividerDecoration.VERTICAL_LIST));

    }

    @Override
    public void setPresenter(AppManageContract.Presenter presenter) {
        this.mPresenter = (AppManagePresenter) presenter;
    }

    @Override
    public void onItemClick(AppInfo appInfo) {
        mPresenter.saveChange(appInfo, appInfo.isChecked());
    }
}
