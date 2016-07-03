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
 * MIT License

 Copyright (c) [2016] [Kevin Ho]

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */




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
