package com.source.kevin.fingertrans.utils;

import android.content.Context;
import android.os.Environment;

import com.source.kevin.fingertrans.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * the file io util
 */
public class FileUtils {


    public static String readSettingList(Context context) {
        StringBuilder sb = new StringBuilder();

        if (SettingPreference.getInstance().isFirstLaunch()) {
            try {
                InputStream is = context.getResources().openRawResource(R.raw.settings);
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
        SettingPreference.getInstance().save(SettingPreference.SP_SETTING_LIST, SettingPreference.FILE_SETTING_LIST, json);
    }


}
