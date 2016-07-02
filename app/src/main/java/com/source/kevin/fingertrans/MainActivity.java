package com.source.kevin.fingertrans;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.source.kevin.fingertrans.appmanage.AppManageActivity;
import com.source.kevin.fingertrans.data.Setting;
import com.source.kevin.fingertrans.setting.SettingAdapter;
import com.source.kevin.fingertrans.setting.SettingContract;
import com.source.kevin.fingertrans.setting.SettingPresenter;
import com.source.kevin.fingertrans.translate.TranslationService;
import com.source.kevin.fingertrans.utils.SettingPreference;
import com.source.kevin.fingertrans.view.DividerDecoration;
import com.source.kevin.fingertrans.view.OnItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SettingContract.View,
        OnItemClickListener<Setting> {


    @BindView(R.id.am_rv_setting)
    RecyclerView rv_setting;

    private SettingPresenter mPresenter;
    private SettingAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        new SettingPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.saveSettingList();
    }

    @Override
    public void initSettingList(ArrayList<Setting> settings) {
        mAdapter = new SettingAdapter(this, settings);
        mAdapter.setOnItemClickListener(this);
        rv_setting.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_setting.setAdapter(mAdapter);
        //rv_setting.addItemDecoration(new DividerDecoration(this, DividerDecoration.VERTICAL_LIST));

    }

    @Override
    public void initService(boolean isStarted) {
        Intent service = new Intent(this, TranslationService.class);
        if (isStarted) {
            startService(service);
        } else {
            stopService(service);

        }
    }

    @Override
    public void setPresenter(SettingContract.Presenter presenter) {
        mPresenter = (SettingPresenter) presenter;
    }

    @Override
    public void onItemClick(Setting s) {
        switch (s.getId()) {
            case SettingPresenter.ID_START_SERVICE:
                initService(s.isChecked());
                break;

            case SettingPresenter.ID_ABOUT:
                break;
            case SettingPresenter.ID_BOOT_START:
                SettingPreference.getInstance().save(SettingPreference.SP_SETTING_LIST
                        , SettingPreference.BOOT_COMPLETE_START, s.isChecked());
                break;
            case SettingPresenter.ID_APP_MANAGE:
                Intent intent = new Intent(this, AppManageActivity.class);
                startActivity(intent);
                break;
        }
    }


}
