package com.jianghw.music;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.database.SQLException;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jianghw.music.db.MusicDBHelper;

/**
 * <b>@Description:</b>TODO<br/>
 * <b>@Author:</b>jianghw<br/>
 * <b>@Since:</b>2016/4/27<br/>
 */
public class MuiceApplication extends Application {

    public static Context applicationContext;
    private Handler applicationHandler;
    private MusicDBHelper DB_HELPER;

    public static Point displaySize = new Point();//显示大小
    public static float density = 1;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationContext = getApplicationContext();
        applicationHandler = new Handler(applicationContext.getMainLooper());

        /**
         * Data base initialize
         */
        initDB();
        /*
         * Display Density Calculation so that Application not problem with All resolution.
		 */
        checkDisplaySize();
        density = applicationContext.getResources().getDisplayMetrics().density;
        /**
         *
         */
        Fresco.initialize(applicationContext);
    }

    private void checkDisplaySize() {
        try {
            WindowManager manager = (WindowManager) applicationContext.getSystemService(Context.WINDOW_SERVICE);
            if (manager != null) {
                Display display = manager.getDefaultDisplay();
                if (display != null) {
                    if (android.os.Build.VERSION.SDK_INT < 13) {
                        displaySize.set(display.getWidth(), display.getHeight());
                    } else {
                        display.getSize(displaySize);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void initDB() {
        if (DB_HELPER == null) {
            DB_HELPER = new MusicDBHelper(applicationContext);
        }

        try {
            DB_HELPER.getWritableDatabase();
            DB_HELPER.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 手动调用
     */
    private void closeDB() {
        try {
            DB_HELPER.onCloseDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
