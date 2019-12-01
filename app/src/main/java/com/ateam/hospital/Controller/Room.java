package com.ateam.hospital.Controller;

import android.content.Context;

import com.ateam.hospital.Model.DoctorDB;
import com.ateam.hospital.Model.RoomDB;
import com.ateam.hospital.Model.TreatmentDB;

import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-26.
 * Under the MIT License
 */
public class Room {

    private int room_id;
    private String bedno;
    private String ward;
    private boolean status;

    public Room(int room_id, String bedno, String ward, boolean status) {
        this.room_id = room_id;
        this.bedno = bedno;
        this.ward = ward;
        this.status = status;
    }


    public Room(String bedno, String ward, boolean status) {
        this.bedno = bedno;
        this.ward = ward;
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Room() {

    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getBedno() {
        return bedno;
    }

    public void setBedno(String bedno) {
        this.bedno = bedno;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public int getTotalRooms(Context context){
       return new RoomDB(context).getRoomCount();
    }

    public long insertInDB(Context context, Room room){
        RoomDB roomDB = new RoomDB(context);
        long l = roomDB.insertData(room);
        return l;
    }

    public List<Integer> getTotalAvailableRooms(Context context){
        return new RoomDB(context).getTotalAvailableRooms();
    }

    public void setStatus(Context context, int id, int status){
        new RoomDB(context).setStatus(id,status);
    }

    public List<Room> getAllData(Context context) {
        return new RoomDB(context).getAllData();
    }

    public Room getDataById(Context context, int id){
        return new RoomDB(context).getData(id);
    }


}
