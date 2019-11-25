package com.ateam.hospital.Controller;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-25.
 * Under the MIT License
 */
public class PatientDetail extends UserBasic {

    int patient_id;
    String address;
    String phoneno;

    public int getPatient_id() {
        return patient_id;
    }

    public PatientDetail() {
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public PatientDetail(int patient_id, String name, boolean gender, int age, String address, String phoneno) {
        this.patient_id = patient_id;
        this.name    = name;
        this.gender  = gender;
        this.age     = age;
        this.address = address;
        this.phoneno = phoneno;
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
}
