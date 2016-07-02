package com.source.kevin.fingertrans.translate;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.source.kevin.fingertrans.FingerApp;
import com.source.kevin.fingertrans.clip.Clipboard;
import com.source.kevin.fingertrans.popup.PopPresenter;
import com.source.kevin.fingertrans.popup.PopView;
import com.source.kevin.fingertrans.utils.AppUtils;
import com.source.kevin.fingertrans.utils.SettingPreference;

/**
 * the background translation service
 */
public class TranslationService extends Service implements ClipboardManager.OnPrimaryClipChangedListener {

    Clipboard clipboard;
    PopView popView;
    PopPresenter presenter;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (clipboard == null)
            clipboard = Clipboard.getInstance();
        clipboard.setPrimaryClipChangedListener(this);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clipboard.setPrimaryClipChangedListener(null);
    }

    @Override
    public void onPrimaryClipChanged() {
        if (!AppUtils.ForegroundAppIsListened())
            return;

        if (popView == null) {
            popView = new PopView(FingerApp.get());
            presenter = new PopPresenter(popView);
        }
        if (presenter != null) {
            presenter.translate(clipboard.getText());
        }
    }
}
