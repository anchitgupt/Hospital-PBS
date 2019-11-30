package com.ateam.hospital.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ateam.hospital.Controller.Doctor;
import com.ateam.hospital.Controller.Room;
import com.ateam.hospital.Controller.Treatment;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-26.
 * Under the MIT License
 */
public class RoomDB extends SQLiteOpenHelper {
    private static final int    DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db";
    private static final String TABLE_DATA = "room";

    private static final String KEY_ID = "room_id";
    private static final String KEY_WARD = "ward";
    private static final String KEY_BED = "bed";
    private static final String KEY_STATUS = "status";


    public RoomDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_ROOM_TABLE =
                "CREATE TABLE " + TABLE_DATA +
                        "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + KEY_WARD     +"TEXT,"
                        + KEY_BED     + "TEXT,"
                        + KEY_STATUS     + "BOOLEAN"
                        +")";
        db.execSQL(CREATE_ROOM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public List<Room> getAllData() {

        List<Room> AccList = new ArrayList<Room>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Room acc = new Room();
                acc.setRoom_id(Integer.parseInt(cursor.getString(0)));
                acc.setWard(cursor.getString(1));
                acc.setBedno(cursor.getString(2));
                acc.setStatus(cursor.getInt(3)>0);

                // Adding contact to list
                AccList.add(acc);
            } while (cursor.moveToNext());
        }
        return AccList;
    }

    public long insertData (Room room){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_BED, room.getBedno());
        values.put(KEY_WARD, room.getWard());
        values.put(KEY_STATUS, room.getStatus());



        long k = db.insert(TABLE_DATA, null, values);

        return k;
    }

    public boolean deleteData (int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_DATA, KEY_ID + "=" + id, null) > 0;
    }

    //TODO get by particular id

    public Room getData(int id) {

        String selectQuery = "SELECT  * FROM " + TABLE_DATA + "WHERE room_id =" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Room acc = new Room();
        if (cursor.moveToFirst()) {
            do {
                acc.setRoom_id(Integer.parseInt(cursor.getString(0)));
                acc.setWard(cursor.getString(1));
                acc.setBedno(cursor.getString(2));
                acc.setStatus(cursor.getInt(3)>0);
            } while (cursor.moveToNext());
        }
        return acc;
    }
}
