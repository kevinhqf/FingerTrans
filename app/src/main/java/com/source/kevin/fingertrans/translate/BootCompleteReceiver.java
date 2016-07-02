package com.source.kevin.fingertrans.translate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.source.kevin.fingertrans.utils.SettingPreference;

/**
 * boot complete receiver
 */
public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (SettingPreference.getInstance().isBootCompleteStart()) {
            Intent i = new Intent(context, TranslationService.class);
            context.startService(i);
        }
    }
}
