package com.ateam.hospital.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ateam.hospital.Controller.Doctor;
import com.ateam.hospital.Controller.Treatment;

import java.util.ArrayList;
import java.util.List;

/**
 *  Project Hospital
 *  Created by Anchit Gupta on 2019-11-26.
 *  Under the MIT License
 */

public class TreatmentDB extends SQLiteOpenHelper {

    private static final int    DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db";
    private static final String TABLE_DATA = "treatment";

    private static final String KEY_ID     = "treat_id";
    private static final String KEY_NAME   = "treat_name";
    private static final String KEY_TREAT  = "treat_charges";
    private static final String KEY_MED    = "medicine_charges";


    public TreatmentDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TREAT_TABLE =
                "CREATE TABLE " + TABLE_DATA +
                        "("
                        + KEY_ID        + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + KEY_NAME      + "TEXT,"
                        + KEY_TREAT     + "INTEGER,"
                        + KEY_MED       + "INTEGER"
                        +")";
        db.execSQL(CREATE_TREAT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




    public List<Treatment> getAllData() {

        List<Treatment> AccList = new ArrayList<Treatment>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Treatment acc = new Treatment();
                acc.setTreatment_id(Integer.parseInt(cursor.getString(0)));
                acc.setTreatmentname(cursor.getString(1));
                acc.setCharges(cursor.getInt(2));
                acc.setMedicine_charges(cursor.getDouble(3));
                // Adding contact to list
                AccList.add(acc);
            } while (cursor.moveToNext());
        }
        return AccList;
    }

    public long insertData (Treatment treatment){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, treatment.getTreatmentname());
        values.put(KEY_TREAT, treatment.getCharges());
        values.put(KEY_MED, treatment.getMedicine_charges());

        long k = db.insert(TABLE_DATA, null, values);

        return k;
    }

    public boolean deleteData (int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_DATA, KEY_ID + "=" + id, null) > 0;
    }

    //TODO get by particular id
}
