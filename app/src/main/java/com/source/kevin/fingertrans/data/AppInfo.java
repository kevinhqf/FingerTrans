package com.source.kevin.fingertrans.data;

import android.graphics.drawable.Drawable;

/**
 * the installed app info
 */
public class AppInfo implements Comparable<AppInfo> {
    String packageName;
    String appName;
    boolean isChecked;
    Drawable icon;

    public AppInfo(String packageName, String appName, Drawable icon, boolean isChecked) {
        this.packageName = packageName;
        this.appName = appName;
        this.icon = icon;
        this.isChecked = isChecked;
    }




    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    @Override
    public int compareTo(AppInfo appInfo) {
        if (isChecked) return -1;
        if (appInfo.isChecked) return 1;
        return 0;
    }
}
