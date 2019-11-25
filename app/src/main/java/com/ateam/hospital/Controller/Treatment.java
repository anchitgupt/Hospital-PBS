package com.ateam.hospital.Controller;

import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-25.
 * Under the MIT License
 */
public class Treatment {

    int treatment_id;
    String treatmentname;
    int charges;
    double medicine_charges;

    public Treatment(int treatment_id, String treatmentname, int charges, double medicine_charges) {
        this.treatment_id = treatment_id;
        this.treatmentname = treatmentname;
        this.charges = charges;
        this.medicine_charges = medicine_charges;
    }

    public Treatment(String treatmentname, int charges, double medicine_charges) {
        this.treatmentname = treatmentname;
        this.charges = charges;
        this.medicine_charges = medicine_charges;
    }

    public Treatment() {

    }

    public int getTreatment_id() {
        return treatment_id;
    }

    public void setTreatment_id(int treatment_id) {
        this.treatment_id = treatment_id;
    }

    public String getTreatmentname() {
        return treatmentname;
    }

    public void setTreatmentname(String treatmentname) {
        this.treatmentname = treatmentname;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }

    public double getMedicine_charges() {
        return medicine_charges;
    }

    public void setMedicine_charges(double medicine_charges) {
        this.medicine_charges = medicine_charges;
    }

    public List<Treatment> getAll(){
        return null;
    }

}
