package com.source.kevin.fingertrans.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.source.kevin.fingertrans.FingerApp;

/**
 * the share preference that save the user setting
 */
public class SettingPreference {

    public static final String SP_SETTING_LIST = "setting_list";
    public static final String SP_APP_MANAGE = "app_manage";
    public static final String FILE_SETTING_LIST = "setting_list_file";

    public static final String BOOT_COMPLETE_START = "boot_complete";

    private SettingPreference() {

    }

    public boolean isFirstLaunch() {
        return getSettingList(null) == null;
    }

    public void save(String preference, String key, Boolean value) {
        SharedPreferences.Editor edit = getPreference(preference).edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public void save(String preference, String key, String value) {
        SharedPreferences.Editor edit = getPreference(preference).edit();
        edit.putString(key, value);
        edit.apply();
    }

    public boolean isListened(String packageName) {
        return getPreference(SP_APP_MANAGE).getBoolean(packageName, false);
    }

    public String getSettingList(String defaultSetting) {
        return getPreference(SP_SETTING_LIST).getString(FILE_SETTING_LIST, defaultSetting);
    }

    public boolean isBootCompleteStart() {
        return getPreference(SP_SETTING_LIST).getBoolean(BOOT_COMPLETE_START, false);
    }

    private SharedPreferences getPreference(String preference) {
        return FingerApp.get().getSharedPreferences(preference, Context.MODE_PRIVATE);
    }



    public static SettingPreference getInstance() {
        return SingletonHolder.instance;
    }

    static class SingletonHolder {
        public static final SettingPreference instance = new SettingPreference();
    }


}
