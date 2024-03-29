package com.ateam.hospital.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.ateam.hospital.Controller.Doctor;
import com.ateam.hospital.Controller.PatientDetail;
import com.ateam.hospital.Controller.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-26.
 * Under the MIT License
 */
public class PatientDB extends SQLiteOpenHelper {

    private static final int    DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db3";
    private static final String TABLE_DATA = "patient";

    private static final String KEY_ID = "patient_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_BLOOD = "blood";

    public PatientDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PATIENT_TABLE =
                "CREATE TABLE " + TABLE_DATA +
                        "("
                        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + KEY_NAME     + " TEXT,"
                        + KEY_AGE     + " INTEGER,"
                        + KEY_ADDRESS  +" TEXT,"
                        + KEY_GENDER   +" BOOLEAN,"
                        + KEY_PHONE    +" TEXT,"
                        + KEY_BLOOD    +" TEXT"
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
                acc.setBlood(cursor.getString(6));

                // Adding contact to list
                AccList.add(acc);
            } while (cursor.moveToNext());
        }
        return AccList;
    }

    public long insertData (PatientDetail patient){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        Log.e("Class", "insertData: "+patient.getName());
        Log.e("Class", "insertData: "+patient.getAddress());
        Log.e("Class", "insertData: "+patient.getGender());
        Log.e("Class", "insertData: "+patient.getPhoneno());
        Log.e("Class", "insertData: "+patient.getBlood());



        values.put(KEY_NAME, patient.getName());
        values.put(KEY_AGE, patient.getAge());
        values.put(KEY_ADDRESS, patient.getAddress());
        values.put(KEY_GENDER, patient.getGender());
        values.put(KEY_PHONE, patient.getPhoneno());
        values.put(KEY_BLOOD, patient.getBlood());

        long k = db.insert(TABLE_DATA, null, values);
        Log.e("Patient", "insertData: " + String.valueOf(k) );
        return k;
    }

    public boolean deleteData (int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_DATA, KEY_ID + "=" + id, null) > 0;
    }

    //TODO get by particular id
    public PatientDetail getData(int id) {

        String selectQuery = "SELECT  * FROM " + TABLE_DATA + " WHERE patient_id=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        PatientDetail acc = new PatientDetail();
        if (cursor.moveToFirst()) {
            do {
                acc.setPatient_id(Integer.parseInt(cursor.getString(0)));
                acc.setName(cursor.getString(1));
                acc.setAge(cursor.getInt(2));
                acc.setAddress(cursor.getString(3));
                acc.setGender(cursor.getInt(4) > 0);
                acc.setPhoneno(cursor.getString(5));
                acc.setBlood(cursor.getString(6));
            } while (cursor.moveToNext());
        }
        return acc;
    }

    public List<Integer> getDataIDs() {
        List<Integer> l = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                l.add(Integer.parseInt(cursor.getString(0)));
            } while (cursor.moveToNext());
        }
        return l;
    }


    public void getDbTableNames() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        String  k ="";
        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                k = c.getString(c.getColumnIndex("name"));
                Log.e("Name", c.getString(c.getColumnIndex("name")));
                c.moveToNext();
            }
        }

        Cursor c1 = db.rawQuery("SELECT name FROM '"+k+"'", null);
        if (c1.moveToFirst()) {
            while ( !c1.isAfterLast() ) {

                Log.e("COunt: ", "getDbTableNames: "+ c1.getColumnCount() );
                c1.moveToNext();
            }
        }

    }




}
