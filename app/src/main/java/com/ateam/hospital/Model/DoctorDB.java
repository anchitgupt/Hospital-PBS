package com.ateam.hospital.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ateam.hospital.Controller.Doctor;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-26.
 * Under the MIT License
 */
public class DoctorDB extends SQLiteOpenHelper {
    private static final int    DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db";
    private static final String TABLE_DATA = "doctor";

    private static final String KEY_ID = "doctor_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_SPECIAL = "special";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_DEPT = "dept";
    private static final String KEY_CHARGES = "charges";

    public DoctorDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_DOCTOR_TABLE =
                "CREATE TABLE " + TABLE_DATA +
                        "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + KEY_NAME     + "TEXT,"
                        + KEY_AGE     + "INTEGER,"
                        + KEY_SPECIAL  +" TEXT,"
                        + KEY_GENDER   +" BOOLEAN,"
                        + KEY_DEPT    +" TEXT,"
                        + KEY_CHARGES    +" INTEGER"
                        +")";
        db.execSQL(CREATE_DOCTOR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




    public List<Doctor> getAllData() {

        List<Doctor> AccList = new ArrayList<Doctor>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Doctor acc = new Doctor();
                acc.setDoctor_id(Integer.parseInt(cursor.getString(0)));
                acc.setName(cursor.getString(1));
                acc.setAge(cursor.getInt(2));
                acc.setSpecialization(cursor.getString(3));
                acc.setGender(cursor.getInt(4) > 0);
                acc.setDepartment(cursor.getString(5));
                acc.setCharges(cursor.getDouble(6));

                // Adding contact to list
                AccList.add(acc);
            } while (cursor.moveToNext());
        }
        return AccList;
    }

    public long insertData (Doctor doctor){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, doctor.getName());
        values.put(KEY_AGE, doctor.getAge());
        values.put(KEY_SPECIAL, doctor.getSpecialization());
        values.put(KEY_CHARGES, doctor.getCharges());
        values.put(KEY_DEPT, doctor.getDepartment());
        values.put(KEY_GENDER, doctor.getGender());

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
