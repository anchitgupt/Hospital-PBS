package com.ateam.hospital.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ateam.hospital.Controller.Room;
import com.ateam.hospital.Model.RoomDB;
import com.ateam.hospital.R;

public class AddRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);

        Log.e("Room", "onCreate: " + String.valueOf(new Room().getTotalRooms(this)));

        if (new Room().getTotalRooms(this)==0){
            Room r1 = new Room("G01","General",true);
            Room r2 = new Room("G02","General",true);
            Room r3 = new Room("G03","General",true);
            Room r4 = new Room("G04","General",true);
            Room r5 = new Room("ICU01","ICU",true);
            Room r6 = new Room("ICU02","ICU",true);
            Room r7 = new Room("ICU03","ICU",true);
            Room r8 = new Room("ICU04","ICU",true);

            new Room().insertInDB(this, r1);
            new Room().insertInDB(this, r2);
            new Room().insertInDB(this, r3);
            new Room().insertInDB(this, r4);
            new Room().insertInDB(this, r5);
            new Room().insertInDB(this, r6);
            new Room().insertInDB(this, r7);
            new Room().insertInDB(this, r8);
        }

        startActivity(new Intent(this, BillActivity.class));

    }
}
