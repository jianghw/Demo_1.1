package com.jianghw.music.manager;

import android.app.NotificationManager;
import android.util.Log;
import android.util.SparseArray;

import com.jianghw.music.MusicApplication;
import com.jianghw.music.bean.DelayedPostBean;

import java.util.ArrayList;


/**
 * @Description: </b>TODO<br/>
 * @Author: </b>Administrator<br/>
 * @Since: </b>2016/4/29 0029<br/>
 * @See {@link}
 * @Github {@https:}
 */
public class MusicNotificationManager {
    private static int totalEvents = 1;//总事件
    public static final int receivedNewMessage = totalEvents++;
    public static final int updateInterface = totalEvents++;
    public static final int audioProgressDidChanged = totalEvents++;
    public static final int audioDidReset = totalEvents++;
    public static final int audioPlayStateChanged = totalEvents++;
    public static final int screenshotTook = totalEvents++;

    public static final int albumsDidLoaded = totalEvents++;
    public static final int audioDidSent = totalEvents++;
    public static final int audioDidStarted = totalEvents++;
    public static final int audioRouteChanged = totalEvents++;//音频路径改变
    public static final int newaudioloaded = totalEvents++;

    private int broadcasting = 0;//广播

    private boolean animationInProgress;//动画是否在前进
    public static boolean DEBUG_VERSION = false;//调试版
    private static volatile MusicNotificationManager Instance = null;

    private ArrayList<DelayedPostBean> mDelayedPostBeanArrayList = new ArrayList<>(10);
    private SparseArray<ArrayList<Object>> observers = new SparseArray<>();
    private SparseArray<ArrayList<Object>> removeAfterBroadcast = new SparseArray<>();
    private SparseArray<ArrayList<Object>> addAfterBroadcast = new SparseArray<>();

    /**
     * 通知中心委托 接口
     */
    public interface NotificationCenterDelegate {
        void onReceivedNotification(int id, Object... args);
        void newSongLoaded(Object... args);
    }

    public static MusicNotificationManager getInstance() {
        if (Instance == null) {
            synchronized (NotificationManager.class) {
                if (Instance == null) {
                    Instance = new MusicNotificationManager();
                }
            }
        }
        return Instance;
    }


    public void postNotificationName(int audioRouteChanged, Object... args) {
        boolean allowDuringAnimation = false;
        postNotificationNameInternal(audioRouteChanged, allowDuringAnimation, args);
    }

    private void postNotificationNameInternal(int audioRouteChanged,
                                              boolean allowDuringAnimation, Object[] args) {
        if (DEBUG_VERSION) {
            if (Thread.currentThread() != MusicApplication.applicationHandler.getLooper().getThread()) {
                throw new RuntimeException("postNotificationName allowed only from MAIN thread");
            }
        }
        if (!allowDuringAnimation && animationInProgress) {
            DelayedPostBean delayedPostBean = new DelayedPostBean(audioRouteChanged, args);
            mDelayedPostBeanArrayList.add(delayedPostBean);
            if (DEBUG_VERSION) {
                Log.e("TAG", "delay post notification " + audioRouteChanged + " with args count = " + args.length);
            }
            return;
        }
        broadcasting++;
        ArrayList<Object> objects = observers.get(audioRouteChanged);
        if (objects != null && !objects.isEmpty()) {
            for (int i = 0; i < objects.size(); i++) {
                Object obj = objects.get(i);
                ((NotificationCenterDelegate) obj).onReceivedNotification(audioRouteChanged, args);
            }
        }
        broadcasting--;
        if (broadcasting == 0) {
            if (removeAfterBroadcast.size() != 0) {
                for (int a = 0; a < removeAfterBroadcast.size(); a++) {
                    int key = removeAfterBroadcast.keyAt(a);
                    ArrayList<Object> arrayList = removeAfterBroadcast.get(key);
                    for (int b = 0; b < arrayList.size(); b++) {
//                        removeObserver(arrayList.get(b), key);
                    }
                }
                removeAfterBroadcast.clear();
            }
            if (addAfterBroadcast.size() != 0) {
                for (int a = 0; a < addAfterBroadcast.size(); a++) {
                    int key = addAfterBroadcast.keyAt(a);
                    ArrayList<Object> arrayList = addAfterBroadcast.get(key);
                    for (int b = 0; b < arrayList.size(); b++) {
//                        addObserver(arrayList.get(b), key);
                    }
                }
                addAfterBroadcast.clear();
            }
        }
    }
}
