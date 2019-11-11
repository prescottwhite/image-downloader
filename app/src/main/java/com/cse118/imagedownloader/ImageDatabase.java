package com.cse118.imagedownloader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.recyclerview.widget.RecyclerView;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class ImageDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "images.db";
    private static final int VERSION = 1;

    public ImageDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static final class ImageTable {
        private static final String TABLE = "images";
        private static final String COL_ID = "_id";
        private static final String COL_TITLE = "title";
        private static final String COL_BLOB = "blob";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ImageTable.TABLE + " (" +
                ImageTable.COL_ID + " integer primary key autoincrement, " +
                ImageTable.COL_TITLE + " text, " +
                ImageTable.COL_BLOB + " BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + ImageTable.TABLE);
        onCreate(db);
    }

    public long addBlob(String title, byte[] blob) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ImageTable.COL_TITLE, title);
        values.put(ImageTable.COL_BLOB, blob);
        long id = db.insert(ImageTable.TABLE, null, values);
        return id;
    }

    public List<Image> getImages() {
        List<Image> images = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "select * from " + ImageTable.TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Image image = new Image();
                image.setId(cursor.getInt(0));
                image.setTitle(cursor.getString(1));
                image.setBlob(cursor.getBlob(2));
                images.add(image);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return images;
    }
}
