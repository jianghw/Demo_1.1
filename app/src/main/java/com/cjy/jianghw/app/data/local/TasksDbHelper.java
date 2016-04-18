package com.cjy.jianghw.app.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * <b>@Description:</b>TODO<br/>
 * <b>@Author:</b>Administrator<br/>
 * <b>@Since:</b>2016/4/18 0018<br/>
 */
public class TasksDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Tasks.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    public static final String TABLE_NAME = "task";
    public static final String COLUMN_NAME_ENTRY_ID = "entryID";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_COMPLETED = "completed";
    private static final java.lang.String SQL_CREATE_ENTRIES = "CREATE TABLE"+ TABLE_NAME + " (" +
            BaseColumns._ID + TEXT_TYPE + " PRIMARY KEY," +
            COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_COMPLETED + BOOLEAN_TYPE +
            " )";

    public TasksDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
