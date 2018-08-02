package com.meituan.tianlong.yuekaomoni.mvp.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SqliteDao {
    private MySql mySql;
    private SQLiteDatabase db;

    public SqliteDao(Context context) {
        mySql = new MySql(context);
        db = mySql.getReadableDatabase();
    }

    public void add(String keywords) {
        ContentValues values = new ContentValues();
        values.put("keywords", keywords);
        db.insert("search", null, values);
    }

    public List<String> select() {
        Cursor cursor = db.rawQuery("select * from search", null);

        List<String> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            String keywords = cursor.getString(cursor.getColumnIndex("keywords"));
            list.add(keywords);
        }

        return list;
    }
}