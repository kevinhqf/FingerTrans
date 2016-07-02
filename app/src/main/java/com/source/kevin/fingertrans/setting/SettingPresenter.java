package com.source.kevin.fingertrans.setting;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.source.kevin.fingertrans.FingerApp;
import com.source.kevin.fingertrans.clip.Clipboard;
import com.source.kevin.fingertrans.data.Setting;
import com.source.kevin.fingertrans.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * the setting presenter
 */
public class SettingPresenter implements SettingContract.Presenter {
    SettingContract.View mView;
    public static final int ID_START_SERVICE = 0;
    public static final int ID_APP_MANAGE = 1;
    public static final int ID_BOOT_START = 2;
    public static final int ID_ABOUT = 3;

    public static final int SETTING_NORMAL = 0;
    public static final int SETTING_MORE = 1;
    public static final int SETTING_SWITCHER = 2;
    ArrayList<Setting> settings;

    public SettingPresenter(SettingContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getSettingList() {
        Gson gson = new Gson();
        String json = FileUtils.readSettingList(FingerApp.get());
        settings = gson.fromJson(json, new TypeToken<List<Setting>>() {
        }.getType());
        mView.initSettingList(settings);
    }

//    @Override
//    public void changeSetting(int position, boolean isCheck) {
//        settings.get(position).setChecked(isCheck);
//    }

    @Override
    public void saveSettingList() {
        Gson gson = new Gson();
        String json = gson.toJson(settings);
        FileUtils.saveUserSetting(json);
    }


    @Override
    public void start() {
        getSettingList();
        if (!Clipboard.getInstance().isStartListened())
            mView.initService(settings.get(0).isChecked());
    }
}
