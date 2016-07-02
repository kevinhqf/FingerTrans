package com.source.kevin.fingertrans.setting;

import com.source.kevin.fingertrans.data.Setting;
import com.source.kevin.fingertrans.mvp.BasePresenter;
import com.source.kevin.fingertrans.mvp.BaseView;

import java.util.ArrayList;


/**
 * the SettingContract
 */
public class SettingContract {
    public interface View extends BaseView<Presenter>{
        void initSettingList(ArrayList<Setting> settings);
        void initService(boolean isStarted);
    }

    public interface Presenter extends BasePresenter{
        void getSettingList();
        //void changeSetting(int position,boolean isCheck);
        void saveSettingList();
    }
}
