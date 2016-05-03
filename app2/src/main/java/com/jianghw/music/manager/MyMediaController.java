package com.jianghw.music.manager;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.PowerManager;

import com.jianghw.music.MusicApplication;
import com.jianghw.music.bean.SongDetailBean;
import com.jianghw.music.service.MusicPlayerService;

import java.util.Timer;

/**
 * @Description: </b>媒体控制类<br/>
 * @Author: </b>jianghw<br>
 * @Since: </b>2016/4/28<br>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public class MyMediaController implements SensorEventListener {
    public int id = -1;
    public int type = 0;
    public String path = "";
    public int currentPlaylistNum;//当前播放的位置号码

    private MediaPlayer mMediaPlayer = null;//媒体播放器
    private AudioTrack mAudioTrack = null;//声道 音轨

    private static volatile MyMediaController INSTANCE = null;

    private boolean useFrontSpeaker;//用户前端扬声器
    private boolean isIgnoreProximity;//是否忽略距离 传感器用
    private SensorManager sensorManager;//传感器管理器
    private Sensor proximitySensor;//近距传感器
    private PowerManager.WakeLock proximityWakeLock;//电源锁

    private final Object progressTimerSync = new Object();//定义同步对象锁
    private final Object playerSongDetailSync = new Object();//定义同步对象锁
    private Timer progressTimer = null;

    private boolean isPaused = true;//暂停


    public static MyMediaController getInstance() {
        MyMediaController mediaController = INSTANCE;
        if (mediaController == null) {
            synchronized (MyMediaController.class) {
                mediaController = INSTANCE;
                if (mediaController == null) {
                    INSTANCE = mediaController = new MyMediaController();
                }
            }
        }
        return mediaController;
    }

    /**
     * 当有新的指令过来时
     *
     * @param notify
     * @param stopService true暂停播放服务
     */
    public void cleanUpPlayer(boolean notify, boolean stopService) {
        saveLastPreferences();
        notifyAndStopPlay(notify, stopService);
    }

    private void saveLastPreferences() {
        MusicPreferences.saveLastSong(getPlayingSongDetail());
        MusicPreferences.saveLastSongListType(type);
        MusicPreferences.saveLastAlbID(id);
        MusicPreferences.saveLastPosition(currentPlaylistNum);
        MusicPreferences.saveLastPath(path);
    }

    public SongDetailBean getPlayingSongDetail() {
        return MusicPreferences.songDetailBean;
    }

    private void notifyAndStopPlay(boolean notify, boolean stopService) {
        pauseMusicAudio(getPlayingSongDetail());
        stopProximitySensor();
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();

            mMediaPlayer.stop();

            mMediaPlayer.release();
        } else if (mAudioTrack != null) {
            synchronized (playerSongDetailSync) {
                mAudioTrack.pause();
                mAudioTrack.flush();

                mAudioTrack.release();
                mAudioTrack = null;
            }
        }
        stopProgressTimer();
        //标记暂停
        isPaused = true;
        if (stopService) {
            Intent intent = new Intent(
                    MusicApplication.applicationContext, MusicPlayerService.class);
            MusicApplication.applicationContext.stopService(intent);
        }
    }

    private boolean pauseMusicAudio(SongDetailBean songDetailBean) {
        //停近距离传感器
        stopProximitySensor();
        if (mAudioTrack == null && mMediaPlayer == null
                || songDetailBean == null || MusicPreferences.songDetailBean == null
                || MusicPreferences.songDetailBean != null
                && MusicPreferences.songDetailBean.getId() != songDetailBean.getId()) {
            return false;
        }
        stopProgressTimer();
     /*   try {
            if (mMediaPlayer != null) {
                mMediaPlayer.pause();
            } else if (mAudioTrack != null) {
                mAudioTrack.pause();
            }
            isPaused = true;
            NotificationManager.getInstance().postNotificationName(
                    NotificationManager.audioPlayStateChanged, MusicPreferences.songDetailBean.getId()))
            ;
        } catch (Exception e) {
            e.printStackTrace();
            isPaused = true;
            return false;
        }*/
        return true;
    }

    private void stopProximitySensor() {
        if (isIgnoreProximity) {//传感器默认 false
            return;
        }
        try {
            useFrontSpeaker = false;
            MusicNotificationManager.getInstance().postNotificationName(
                    MusicNotificationManager.audioRouteChanged, useFrontSpeaker);
            //注销传感器监听
            if (sensorManager != null && proximitySensor != null) {
                sensorManager.unregisterListener(this);
            }
            //如果锁仍然持有，释放锁
            if (proximityWakeLock != null && proximityWakeLock.isHeld()) {
                proximityWakeLock.release();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 传感器事件监听器
     *
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void stopProgressTimer() {
        synchronized (progressTimerSync) {
            if (progressTimer != null) {
                try {
                    progressTimer.cancel();
                    progressTimer = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isAudioPaused() {
        return isPaused;
    }

    public boolean playAudio(SongDetailBean songDetailBean) {
        if (songDetailBean == null) return false;
        if ((mAudioTrack != null || mMediaPlayer != null)
                && MusicPreferences.songDetailBean != null
                && songDetailBean.getId() == MusicPreferences.songDetailBean.getId()) {

        }
        return true;
    }
}
