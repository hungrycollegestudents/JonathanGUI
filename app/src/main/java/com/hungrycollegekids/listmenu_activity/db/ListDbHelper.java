package com.hungrycollegekids.listmenu_activity.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jonathan on 2/22/18.
 */

public class ListDbHelper extends SQLiteOpenHelper {
    public ListDbHelper(Context context) {
        super(context, ListMenuContract.DB_NAME, null, ListMenuContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + ListMenuContract.TaskEntry.TABLE + " ( " +
                ListMenuContract.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ListMenuContract.TaskEntry.COL_LIST_TITLE + " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ListMenuContract.TaskEntry.TABLE);
        onCreate(db);
    }

}
