package com.ateam.hospital.Controller;

import android.content.Context;

import com.ateam.hospital.Model.DoctorDB;
import com.ateam.hospital.Model.PatientDB;

import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-25.
 * Under the MIT License
 */
public class Doctor extends UserBasic {

    int doctor_id;
    String department;
    String specialization;
    double charges;

    public Doctor() {
    }

    public Doctor(int doctor_id, String name, boolean gender, int age, String department, String specialization, double charges) {
        this.doctor_id = doctor_id;
        this.name    = name;
        this.gender  = gender;
        this.age     = age;
        this.department = department;
        this.specialization = specialization;
        this.charges = charges;
    }

    public Doctor( String name, boolean gender, int age, String department, String specialization, double charges) {
        this.name    = name;
        this.gender  = gender;
        this.age     = age;
        this.department = department;
        this.specialization = specialization;
        this.charges = charges;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    public long insertInDB(Context context, Doctor doctor){
        DoctorDB doctorDB = new DoctorDB(context);
        long l = doctorDB.insertData(doctor);
        return l;
    }
    public List<Doctor> getAllData(Context context) {
        DoctorDB doctorDB = new DoctorDB(context);
        return doctorDB.getAllData();
    }

    public long addData(Context context, Doctor d){
        DoctorDB doctorDB = new DoctorDB(context);
        return doctorDB.insertData(d);
    }

    public Doctor getDataById(Context context, int id){
        return new DoctorDB(context).getData(id);
    }

}
