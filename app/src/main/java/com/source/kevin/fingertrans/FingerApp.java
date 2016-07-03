package com.source.kevin.fingertrans;

import android.app.Application;
import android.content.Context;

import java.util.Locale;

/**
 * Created by kevin on 6/28/16.
 */
public class FingerApp extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static FingerApp get(){
        return (FingerApp) mContext.getApplicationContext();
    }


    public static String getLanguage(){
        return Locale.getDefault().getLanguage();
    }
}
