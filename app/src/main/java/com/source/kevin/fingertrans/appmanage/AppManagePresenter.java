package com.source.kevin.fingertrans.appmanage;

import com.source.kevin.fingertrans.data.AppInfo;
import com.source.kevin.fingertrans.setting.SettingPresenter;
import com.source.kevin.fingertrans.utils.AppUtils;
import com.source.kevin.fingertrans.utils.SettingPreference;

import java.util.ArrayList;

/**
 * the appManage presenter
 */
public class AppManagePresenter implements AppManageContract.Presenter {

    AppManageContract.View mView;
    ArrayList<AppInfo> apps;

    public AppManagePresenter(AppManageContract.View view) {
        this.mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void getInstalledApp() {
        apps = AppUtils.getInstalledApps();
        mView.initAppList(apps);
    }

    @Override
    public void saveChange(AppInfo info,boolean isChecked) {
        SettingPreference.getInstance().save(SettingPreference.SP_APP_MANAGE,info.getPackageName(),isChecked);
    }


    @Override
    public void start() {
        getInstalledApp();
    }
}
