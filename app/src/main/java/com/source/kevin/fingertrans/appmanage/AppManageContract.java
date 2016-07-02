package com.source.kevin.fingertrans.appmanage;

import com.source.kevin.fingertrans.data.AppInfo;
import com.source.kevin.fingertrans.mvp.BasePresenter;
import com.source.kevin.fingertrans.mvp.BaseView;

import java.util.ArrayList;

/**
 * the appManage Contract
 */
public class AppManageContract {

    public interface View extends BaseView<Presenter> {
        void initAppList(ArrayList<AppInfo> appInfos);
    }


    public interface Presenter extends BasePresenter {
        void getInstalledApp();
        void saveChange(AppInfo info, boolean isChecked);
    }

}
