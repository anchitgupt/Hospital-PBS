package com.ateam.hospital.Controller;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-25.
 * Under the MIT License
 */


public class Prescription {

    int       pres_id;
    Doctor    doctor;
    Treatment treatment;

    public Prescription(int pres_id, Doctor doctor, Treatment treatment) {
        this.pres_id = pres_id;
        this.doctor = doctor;
        this.treatment = treatment;
    }

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
}
