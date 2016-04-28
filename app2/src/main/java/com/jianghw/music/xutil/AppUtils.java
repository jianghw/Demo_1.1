package com.jianghw.music.xutil;

import com.jianghw.music.MuiceApplication;
import com.jianghw.music.R;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>jianghw<br>
 * @Since: </b>2016/4/28<br>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public class AppUtils {
    public static void settingCurrentTheme(int theme) {
        switch (theme) {
            case 1:
                MuiceApplication.applicationContext.setTheme(R.style.AppTheme);
                break;
        }
    }
}
