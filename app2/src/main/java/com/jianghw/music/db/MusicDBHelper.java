package com.jianghw.music.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jianghw.music.R;

/**
 * <b>@Description:</b>TODO<br/>
 * <b>@Author:</b>jianghw<br/>
 * <b>@Since:</b>2016/4/27<br/>
 */
public class MusicDBHelper extends SQLiteOpenHelper {
    private final Context mContext;

    private String DB_PATH = "";
    private int DATABASE_VERSION = 1;
    private String DATABASE_NAME = "";
    private SQLiteDatabase db;

    public MusicDBHelper(Context context) {
        super(context, context.getResources().getString(R.string.DataBaseName), null,
                Integer.parseInt(context.getResources().getString(R.string.DataBaseName_Version)));

        this.mContext = context;
        DATABASE_NAME = context.getResources().getString(R.string.DataBaseName);
        DATABASE_VERSION = Integer.parseInt(context.getResources()
                .getString(R.string.DataBaseName_Version));
        DB_PATH = context.getDatabasePath(DATABASE_NAME).getPath();

        context.openOrCreateDatabase(DATABASE_NAME, SQLiteDatabase.OPEN_READWRITE, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlForCreateMostPlay());
        db.execSQL(sqlForCreateFavoritePlay());
    }

    private String sqlForCreateMostPlay() {
        String sql = "CREATE TABLE" + MostTableHelper.TABLE_NAME + "("
                + MostTableHelper.ID + "INTEGER NOT NULL PRIMARY KEY,"
                + MostTableHelper.ALBUM_ID + "INTEGER NOT NULL,"//专辑
                + MostTableHelper.TITLE + "TEXT NOT NULL,"//标题
                + MostTableHelper.DISPLAY_NAME + "TEXT NOT NULL,"//显示
                + MostTableHelper.DURATION + "TEXT NOT NULL,"//持续时间
                + MostTableHelper.PATH + "TEXT NOT NULL,"//路径
                + MostTableHelper.AUDIO_PROGRESS + "TEXT NOT NULL,"//音频的进展
                + MostTableHelper.AUDIO_PROGRESS_SEC + "INTEGER NOT NULL,"//音频的进展 秒
                + MostTableHelper.LAST_PLAY_TIME + "TEXT NOT NULL,"//最后的播放时间
                + MostTableHelper.PLAY_COUNT + "TEXT NOT NULL);";//播放次数

        return sql;
    }

    private String sqlForCreateFavoritePlay() {
        String sql = "CREATE TABLE " + FavoriteTableHelper.TABLE_NAME + " ("
                + FavoriteTableHelper.ID + " INTEGER NOT NULL PRIMARY KEY,"
                + FavoriteTableHelper.ALBUM_ID + " INTEGER NOT NULL,"
                + FavoriteTableHelper.ARTIST + " TEXT NOT NULL,"//艺术家
                + FavoriteTableHelper.TITLE + " TEXT NOT NULL,"
                + FavoriteTableHelper.DISPLAY_NAME + " TEXT NOT NULL,"
                + FavoriteTableHelper.DURATION + " TEXT NOT NULL,"
                + FavoriteTableHelper.PATH + " TEXT NOT NULL,"
                + FavoriteTableHelper.AUDIO_PROGRESS + " TEXT NOT NULL,"
                + FavoriteTableHelper.AUDIO_PROGRESS_SEC + " INTEGER NOT NULL,"
                + FavoriteTableHelper.LAST_PLAY_TIME + " TEXT NOT NULL,"
                + FavoriteTableHelper.IS_FAVORITE + " INTEGER NOT NULL);";//人收藏夹
        return sql;
    }

    /**
     * 更新数据库
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS" + MostTableHelper.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS" + FavoriteTableHelper.TABLE_NAME);
            onCreate(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void openDataBase() throws SQLException {
        try {
            db = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 手动调用
     */
    public void onCloseDB() {
        if (getDB() != null) getDB().close();
        super.close();
    }

    private SQLiteDatabase getDB() {
        return db;
    }
}
