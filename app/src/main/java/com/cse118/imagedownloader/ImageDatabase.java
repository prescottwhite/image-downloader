package com.cse118.imagedownloader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;

public class ImageDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "images.db";
    private static final int VERSION = 1;

    public ImageDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static final class ImageTable {
        private static final String TABLE = "images";
        private static final String COL_TITLE = "title";
        private static final String COL_BLOB = "blob";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ImageTable.TABLE + " (" +
                ImageTable.COL_TITLE + " text, " +
                ImageTable.COL_BLOB + " Blob)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + ImageTable.TABLE);
    }
}
