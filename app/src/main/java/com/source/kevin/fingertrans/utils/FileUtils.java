package com.source.kevin.fingertrans.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.source.kevin.fingertrans.FingerApp;
import com.source.kevin.fingertrans.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Locale;

/**
 * the file io util
 */
public class FileUtils {


    public static String readSettingList(Context context) {
        StringBuilder sb = new StringBuilder();
        if (SettingPreference.getInstance().isFirstLaunch()) {
            int setting = R.raw.settings;
            if (FingerApp.getLanguage().equals("zh"))
                setting = R.raw.settings_zh;
            SettingPreference.getInstance().save(SettingPreference.SP_APP_MANAGE, FingerApp.get().getPackageName(), true);
            try {
                InputStream is = context.getResources().openRawResource(setting);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            saveUserSetting(sb.toString());
        }
        return SettingPreference.getInstance().getSettingList(sb.toString());
    }

    public static void saveUserSetting(String json) {
        String sp = SettingPreference.getInstance().getSPSettingList();
        SettingPreference.getInstance().save(sp, SettingPreference.FILE_SETTING_LIST, json);
    }


}
