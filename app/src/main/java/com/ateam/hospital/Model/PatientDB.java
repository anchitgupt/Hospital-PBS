package com.ateam.hospital.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ateam.hospital.Controller.Doctor;
import com.ateam.hospital.Controller.PatientDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-26.
 * Under the MIT License
 */
public class PatientDB extends SQLiteOpenHelper {

    private static final int    DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db";
    private static final String TABLE_DATA = "patient";

    private static final String KEY_ID = "patient_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_PHONE = "phone";

    public PatientDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PATIENT_TABLE =
                "CREATE TABLE " + TABLE_DATA +
                        "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + KEY_NAME     + "TEXT,"
                        + KEY_AGE     + "INTEGER,"
                        + KEY_ADDRESS  +" TEXT,"
                        + KEY_GENDER   +" BOOLEAN,"
                        + KEY_PHONE    +" TEXT"
                        +")";
        db.execSQL(CREATE_PATIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




    public List<PatientDetail> getAllData() {

        List<PatientDetail> AccList = new ArrayList<PatientDetail>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PatientDetail acc = new PatientDetail();
                acc.setPatient_id(Integer.parseInt(cursor.getString(0)));
                acc.setName(cursor.getString(1));
                acc.setAge(cursor.getInt(2));
                acc.setAddress(cursor.getString(3));
                acc.setGender(cursor.getInt(4) > 0);
                acc.setPhoneno(cursor.getString(5));

                // Adding contact to list
                AccList.add(acc);
            } while (cursor.moveToNext());
        }
        return AccList;
    }

    public long insertData (PatientDetail patient){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, patient.getName());
        values.put(KEY_AGE, patient.getAge());
        values.put(KEY_ADDRESS, patient.getAddress());
        values.put(KEY_GENDER, patient.getGender());
        values.put(KEY_PHONE, patient.getPhoneno());

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
