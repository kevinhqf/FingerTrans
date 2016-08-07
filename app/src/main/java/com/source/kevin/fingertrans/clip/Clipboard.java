package com.source.kevin.fingertrans.clip;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;

import com.source.kevin.fingertrans.FingerApp;

import java.util.ArrayList;
import java.util.List;

/**
 * the clipboard
 */
public class Clipboard {

    private ClipboardManager mClipboard;
    private List<ClipboardManager.OnPrimaryClipChangedListener> listeners = new ArrayList<>();

    private Clipboard() {
        this.mClipboard = (ClipboardManager) FingerApp.get().getSystemService(Context.CLIPBOARD_SERVICE);
    }

    /**
     * 获取粘贴板的文本数据
     * @return
     */
    public String getText() {
        return mClipboard.getPrimaryClip().getItemAt(0).coerceToText(FingerApp.get()).toString();
    }

    /**
     * 判断是否有监听对象
     *
     * @return
     */
    public boolean isStartListened() {
        return listeners.size() != 0;
    }

    /**
     * 设置监听
     *
     * @param listener
     */
    public void setPrimaryClipChangedListener(ClipboardManager.OnPrimaryClipChangedListener listener) {
        if (listener == null) {
            removeAllListener();
            return;
        }
        mClipboard.addPrimaryClipChangedListener(listener);
        listeners.add(listener);
    }

    /**
     * 移除所有监听
     */
    public void removeAllListener() {
        for (ClipboardManager.OnPrimaryClipChangedListener l : listeners)
            mClipboard.removePrimaryClipChangedListener(l);
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static Clipboard getInstance() {
        return SingletonHolder.instance;
    }

    static class SingletonHolder {
        public static final Clipboard instance = new Clipboard();
    }
}
