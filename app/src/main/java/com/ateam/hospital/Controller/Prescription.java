package com.ateam.hospital.Controller;

import android.content.Context;
import android.util.Log;

import com.ateam.hospital.Model.PatientDB;
import com.ateam.hospital.Model.PrescriptionDB;

import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-25.
 * Under the MIT License
 */


public class Prescription {

    int       pres_id;
    Doctor    doctor;
    Treatment treatment;

    public Prescription(Doctor doctor, Treatment treatment) {
        this.doctor = doctor;
        this.treatment = treatment;
    }

    public Prescription() {

    }

    public int getPres_id() {
        return pres_id;
    }

    public int getDoctorIDFromPres(){
        return doctor.getDoctor_id();
    }

    public int getTreatmentIDFromPres(){
        return treatment.getTreatment_id();
    }

    public void setPres_id(int pres_id) {
        this.pres_id = pres_id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public long insertData(Context context, Prescription prescription){
        return new PrescriptionDB(context).insertData(prescription);
    }
    public int getLastID(Context context){
        List<Prescription> list =  new PrescriptionDB(context).getAllData();
        int size = list.size();
        Prescription p = list.get(size-1);
        return p.getPres_id();
    }

    public Prescription getData(Context context, int id){
        return new PrescriptionDB(context).getData(id);
    }

    public Prescription getDataById(Context context, int id){
        return new PrescriptionDB(context).getData(id);
    }

}
