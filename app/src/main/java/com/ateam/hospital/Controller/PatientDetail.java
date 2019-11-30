package com.ateam.hospital.Controller;

import android.content.Context;

import com.ateam.hospital.Model.PatientDB;
import com.ateam.hospital.Model.RoomDB;

import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-25.
 * Under the MIT License
 */
public class PatientDetail extends UserBasic {


    int patient_id;
    String address;
    String phoneno;
    String blood;

    public int getPatient_id() {
        return patient_id;
    }

    public PatientDetail() {
    }

    public PatientDetail(int patient_id, String address, String phoneno, String blood) {
        this.patient_id = patient_id;
        this.address = address;
        this.phoneno = phoneno;
        this.blood = blood;
    }


    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public List<PatientDetail> getAllData(Context context) {
        PatientDB patientDB = new PatientDB(context);
        return patientDB.getAllData();
    }

    public long addData(Context context, PatientDetail p){
        PatientDB patientDB = new PatientDB(context);
        return patientDB.insertData(p);
    }

    public List<Integer> getIDs(Context context){
        PatientDB patientDB = new PatientDB(context);
        return patientDB.getDataIDs();
    }

    public PatientDetail getDataById(Context context, int id){
        return new PatientDB(context).getData(id);
    }
}
