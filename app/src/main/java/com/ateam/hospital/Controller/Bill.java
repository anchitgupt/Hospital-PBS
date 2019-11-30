package com.ateam.hospital.Controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-25.
 * Under the MIT License
 */
public class Bill {

    int bill_id;
    PatientDetail patientDetail;
    Prescription prescription;
    Room room;
    int status;
    String arrdate;
    String depdate;

    public Bill(int bill_id, PatientDetail patientDetail, Prescription prescription, Room room, int status, String arrdate, String depdate) {
        this.bill_id = bill_id;
        this.patientDetail = patientDetail;
        this.prescription = prescription;
        this.room = room;
        this.status = status;
        this.arrdate = arrdate;
        this.depdate = depdate;
    }

    public Bill() {

    }


    public int getPatientIDFromBill(){
        return patientDetail.getPatient_id();
    }

    public int getRoomIDFromBill(){
        return room.getRoom_id();
    }


    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public PatientDetail getPatientDetail() {
        return patientDetail;
    }

    public void setPatientDetail(PatientDetail patientDetail) {
        this.patientDetail = patientDetail;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getArrdate() {
        return arrdate;
    }

    public void setArrdate(String arrdate) {
        this.arrdate = arrdate;
    }

    public String getDepdate() {
        return depdate;
    }

    public void setDepdate(String depdate) {
        this.depdate = depdate;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
