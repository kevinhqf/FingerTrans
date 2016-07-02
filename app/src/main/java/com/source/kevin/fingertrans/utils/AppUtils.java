package com.source.kevin.fingertrans.utils;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

import com.source.kevin.fingertrans.FingerApp;
import com.source.kevin.fingertrans.data.AppInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * the util to get the installed applications info
 */
public class AppUtils {

    public static ArrayList<AppInfo> getInstalledApps() {
        ArrayList<AppInfo> appInfo = new ArrayList<>();
        PackageManager packageManager = FingerApp.get().getPackageManager();
        List<PackageInfo> packages = packageManager.getInstalledPackages(0);
        String name = FingerApp.get().getPackageName();
        for (PackageInfo p : packages) {
            if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0 && !p.packageName.equals(name)) {

                String appName = p.applicationInfo.loadLabel(packageManager).toString();

                String packageName = p.packageName;

                Drawable icon = p.applicationInfo.loadIcon(packageManager);
                boolean isCheck = SettingPreference.getInstance().isListened(packageName);
                appInfo.add(new AppInfo(packageName, appName, icon, isCheck));
            }
        }
        Collections.sort(appInfo);


        return appInfo;
    }


    public static boolean ForegroundAppIsListened() {

        // api16 ~ api21
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ActivityManager am = (ActivityManager) FingerApp.get().getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            String packageName = componentInfo.getPackageName();
            Log.e("name", packageName);
            return SettingPreference.getInstance().isListened(packageName);

        } //after api21
        else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            String s = queryUsageStats();
            Log.e("name", s);
        }


        return true;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    public static String queryUsageStats() {
        String packageName=null;
        UsageStatsManager usm = (UsageStatsManager) FingerApp.get().getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();
        List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,
                time - 1000 * 1000, time);
        if (appList != null && appList.size() > 0) {
            SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
            for (UsageStats usageStats : appList) {
                mySortedMap.put(usageStats.getLastTimeUsed(),
                        usageStats);
            }
            if (!mySortedMap.isEmpty()) {
                 packageName = mySortedMap.get(
                        mySortedMap.lastKey()).getPackageName();
            }
        }
        return packageName;
    }


}
