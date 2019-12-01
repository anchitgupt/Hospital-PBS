package com.ateam.hospital.Controller;

import android.content.Context;

import com.ateam.hospital.Model.BillDB;
import com.ateam.hospital.Model.RoomDB;
import com.ateam.hospital.Model.TreatmentDB;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
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
    int total;

    public Bill(int bill_id, PatientDetail patientDetail, Prescription prescription, Room room, int status, String arrdate, String depdate, int total) {
        this.bill_id = bill_id;
        this.patientDetail = patientDetail;
        this.prescription = prescription;
        this.room = room;
        this.status = status;
        this.arrdate = arrdate;
        this.depdate = depdate;
        this.total = total;
    }

    public Bill() {

    }

    public Bill(PatientDetail patientDetail, Prescription prescription, Room room, int status, String arrdate, String depdate, int total) {
        this.patientDetail = patientDetail;
        this.prescription = prescription;
        this.room = room;
        this.status = status;
        this.arrdate = arrdate;
        this.depdate = depdate;
        this.total = total;
    }

    public int getPatientIDFromBill(){
        return patientDetail.getPatient_id();
    }

    public int getRoomIDFromBill(){
        return room.getRoom_id();
    }

    public int getPrescriptionIDFromBill(){
        return prescription.getPres_id();
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public long insertData(Context context, Bill bill){
        return new BillDB(context).insertData(bill);
    }
    public List<Bill> getAllData(Context context) {
        BillDB billDB = new BillDB(context);
        return billDB.getAllData();
    }
    public List<Bill> getAllDataSetStatus(Context context) {
        BillDB billDB = new BillDB(context);
        return billDB.getAllDataSetStatus();
    }

    public Bill getDataById(Context context, int id){
        return new BillDB(context).getData(id);
    }
    public void setStatus(Context context, int id, int status){
        new BillDB(context).setStatus(id,status);
    }
    public void setTotal(Context context, int id, int total){
        new BillDB(context).setTotal(id,total);
    }
}
