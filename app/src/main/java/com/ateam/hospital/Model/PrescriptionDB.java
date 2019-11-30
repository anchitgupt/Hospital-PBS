package com.ateam.hospital.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ateam.hospital.Controller.Doctor;
import com.ateam.hospital.Controller.Prescription;
import com.ateam.hospital.Controller.Treatment;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-30.
 * Under the MIT License
 */
public class PrescriptionDB extends SQLiteOpenHelper {

    private static final int    DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db4";
    private static final String TABLE_DATA   = "prescription";
    private static final String KEY_ID       = "pres_id";
    private static final String KEY_DOC_ID   = "doctor_id";
    private static final String KEY_TREAT_ID = "treat_id";
    Context context;

    public PrescriptionDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_DOCTOR_TABLE =
                "CREATE TABLE " + TABLE_DATA +
                        "("
                        + KEY_ID           + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + KEY_DOC_ID       + " INTEGER,"
                        + KEY_TREAT_ID     + " INTEGER"
                        +")";
        db.execSQL(CREATE_DOCTOR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Prescription> getAllData() {

        List<Prescription> AccList = new ArrayList<Prescription>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Prescription acc = new Prescription();
                acc.setPres_id(Integer.parseInt(cursor.getString(0)));

                int docid = cursor.getInt(1);
                DoctorDB db1 = new DoctorDB(context);
                Doctor d = db1.getData(docid);

                int treatid = cursor.getInt(2);
                TreatmentDB tdb = new TreatmentDB(context);
                Treatment t = tdb.getData(treatid);
                acc.setDoctor(d);
                acc.setTreatment(t);

                AccList.add(acc);
            } while (cursor.moveToNext());
        }
        return AccList;
    }

    public long insertData (Prescription prescription){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_DOC_ID, prescription.getDoctorIDFromPres());
        values.put(KEY_TREAT_ID, prescription.getTreatmentIDFromPres());


        long k = db.insert(TABLE_DATA, null, values);

        return k;
    }

    public boolean deleteData (int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_DATA, KEY_ID + "=" + id, null) > 0;
    }

    //TODO get by particular id

    public Prescription getData(int id) {

        String selectQuery = "SELECT  * FROM " + TABLE_DATA + " WHERE pres_id=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Prescription acc = new Prescription();
        if (cursor.moveToFirst()) {
            do {
                acc.setPres_id(Integer.parseInt(cursor.getString(0)));

                int docid = cursor.getInt(1);
                DoctorDB db1 = new DoctorDB(context);
                Doctor d = db1.getData(docid);

                int treatid = cursor.getInt(2);
                TreatmentDB tdb = new TreatmentDB(context);
                Treatment t = tdb.getData(treatid);

                acc.setDoctor(d);
                acc.setTreatment(t);
            } while (cursor.moveToNext());
        }
        return acc;
    }
}

