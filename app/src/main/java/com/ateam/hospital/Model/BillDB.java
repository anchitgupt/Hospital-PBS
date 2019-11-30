package com.ateam.hospital.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ateam.hospital.Controller.Bill;
import com.ateam.hospital.Controller.PatientDetail;
import com.ateam.hospital.Controller.Prescription;
import com.ateam.hospital.Controller.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-30.
 * Under the MIT License
 */
public class BillDB extends SQLiteOpenHelper {

    private static final int    DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db";
    private static final String TABLE_DATA = "bill";
    private Context context;
    private static final String KEY_ID      = "bill_id";
    private static final String KEY_PATIENT_ID    = "patient_id";
    private static final String KEY_PRES_ID     = "pres_id";
    private static final String KEY_ROOM_ID = "room_id";
    private static final String KEY_STATUS  = "status";
    private static final String KEY_ARR   = "arrdate";
    private static final String KEY_DEP = "depdate";

    public BillDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_DOCTOR_TABLE =
                "CREATE TABLE " + TABLE_DATA +
                        "("
                        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + KEY_PATIENT_ID     + "INTEGER,"
                        + KEY_PRES_ID     + "INTEGER,"
                        + KEY_ROOM_ID  +" INTEGER,"
                        + KEY_STATUS   +" INTEGER,"
                        + KEY_ARR    +" TEXT,"
                        + KEY_DEP    +" TEXT"
                        +")";
        db.execSQL(CREATE_DOCTOR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Bill> getAllData() {

        List<Bill> AccList = new ArrayList<Bill>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Bill acc = new Bill();
                acc.setBill_id(Integer.parseInt(cursor.getString(0)));

                int pid = cursor.getInt(1);
                PatientDB patientDB = new PatientDB(context);
                PatientDetail patient = patientDB.getData(pid);

                int presid = cursor.getInt(2);
                PrescriptionDB prescriptionDB = new PrescriptionDB(context);
                Prescription p = prescriptionDB.getData(presid);

                int roomid = cursor.getInt(3);
                RoomDB roomDB = new RoomDB(context);
                Room r = roomDB.getData(roomid);

                acc.setPatientDetail(patient);
                acc.setPrescription(p);
                acc.setRoom(r);

                acc.setStatus(cursor.getInt(4));
                acc.setArrdate(cursor.getString(5));
                acc.setDepdate(cursor.getString(6));

                // Adding contact to list
                AccList.add(acc);
            } while (cursor.moveToNext());
        }
        return AccList;
    }


    public long insertData (Bill bill){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

       values.put(KEY_ID        , bill.getBill_id());
       values.put(KEY_PATIENT_ID, bill.getBill_id());
       values.put(KEY_PRES_ID   , bill.getPatientIDFromBill());
       values.put(KEY_ROOM_ID   , bill.getRoomIDFromBill());
       values.put(KEY_STATUS    , bill.getRoomIDFromBill());
       values.put(KEY_ARR       , bill.getArrdate());
       values.put(KEY_DEP       , bill.getDepdate());

        long k = db.insert(TABLE_DATA, null, values);

        return k;
    }


    public boolean deleteData (int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_DATA, KEY_ID + "=" + id, null) > 0;
    }

    //TODO get by particular id
    public  Bill getData(int id) {

        String selectQuery = "SELECT  * FROM " + TABLE_DATA + "WHERE bill_id=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Bill acc = new Bill();
        if (cursor.moveToFirst()) {
            do {
                acc.setBill_id(Integer.parseInt(cursor.getString(0)));

                int pid = cursor.getInt(1);
                PatientDB patientDB = new PatientDB(context);
                PatientDetail patient = patientDB.getData(pid);

                int presid = cursor.getInt(2);
                PrescriptionDB prescriptionDB = new PrescriptionDB(context);
                Prescription p = prescriptionDB.getData(presid);

                int roomid = cursor.getInt(3);
                RoomDB roomDB = new RoomDB(context);
                Room r = roomDB.getData(roomid);

                acc.setPatientDetail(patient);
                acc.setPrescription(p);
                acc.setRoom(r);

                acc.setStatus(cursor.getInt(4));
                acc.setArrdate(cursor.getString(5));
                acc.setDepdate(cursor.getString(6));

            } while (cursor.moveToNext());
        }
        return acc;
    }


}
