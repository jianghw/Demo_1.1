package com.jianghw.music.data.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jianghw.music.data.IDataSource;
import com.jianghw.music.data.MusicTask;
import com.jianghw.music.db.MostTableHelper;
import com.jianghw.music.db.MusicDBHelper;
import com.jianghw.music.xutil.NullExUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>Administrator<br/>
 * @Since: </b>2016/4/29 0029<br/>
 * @See {@link}
 * @Github {@https:}
 */
public class LocalDataDB implements IDataSource {

    private final SQLiteDatabase mDb;
    private final MusicDBHelper mDbHelper;

    private LocalDataDB(@NonNull Context context) {
        NullExUtils.checkNotNull(context);
        mDbHelper = new MusicDBHelper(context);
        mDb = mDbHelper.getWritableDatabase();
    }

    private static LocalDataDB INSTANCE=null;
    public static LocalDataDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataDB(context);
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public List<MusicTask> getTasks() {
        List<MusicTask> tasks = new ArrayList<>();

        try {
            String[] projection = {
                    MostTableHelper.ID,
                    MostTableHelper.TITLE,
                    MostTableHelper.DISPLAY_NAME,
                    MostTableHelper.DURATION
            };

            Cursor c = mDb.query(
                    MostTableHelper.TABLE_NAME, projection, null, null, null, null, null);

            if (c != null && c.getCount() > 0) {
                while (c.moveToNext()) {
                    String itemId = c.getString(c.getColumnIndexOrThrow(MostTableHelper.ID));
                    String title = c.getString(c.getColumnIndexOrThrow(MostTableHelper.TITLE));
                    String description = c.getString(c.getColumnIndexOrThrow(MostTableHelper.DISPLAY_NAME));
                    boolean completed = c.getInt(c.getColumnIndexOrThrow(MostTableHelper.DURATION)) == 1;
                    MusicTask task = new MusicTask(title, description, itemId, completed);
                    tasks.add(task);
                }
            }
            if (c != null) c.close();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
