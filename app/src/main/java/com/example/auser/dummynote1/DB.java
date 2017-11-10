package com.example.auser.dummynote1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by auser on 2017/11/7.
 */

public class DB {
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_TABLE = "notes";
    private static final String NEW_DATABASE_TABLE = "notes2";
    private static final String DATABASE_CREATE =
            "CREATE TABLE IF NOT EXISTS "+NEW_DATABASE_TABLE+"(_id INTEGER PRIMARY KEY,note TEXT NOT NULL,created INTEGER);";


    private static class DatabaseHelper extends SQLiteOpenHelper {
        Context mCtx;
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            mCtx =context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
            Toast.makeText(mCtx,"資料表更新完畢",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE);
            onCreate(db);
        }
    }

    private Context mCtx = null;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DB(Context ctx) {
        this.mCtx = ctx;
    }

    public DB open() throws SQLException {
        dbHelper = new DatabaseHelper(mCtx);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }
    //查詢全部資料表
    public Cursor getAll(){
        return db.rawQuery("SELECT * FROM "+NEW_DATABASE_TABLE+"",null);

    }
}
