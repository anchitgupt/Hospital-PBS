package com.ateam.hospital.View;

import android.content.Intent;
import android.os.Bundle;

import com.ateam.hospital.Controller.Bill;
import com.ateam.hospital.Controller.PatientDetail;
import com.ateam.hospital.Controller.Room;
import com.ateam.hospital.Model.DoctorDB;
import com.ateam.hospital.Model.PatientDB;
import com.ateam.hospital.View.Adapter.BillAdapter;
import com.ateam.hospital.View.Adapter.PatientAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ateam.hospital.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class BillActivity extends AppCompatActivity {

    public BillActivity(){}

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(BillActivity.this, BillGenreator.class);
                startActivity(intent);


            }
        });

        List<Bill> listBill = new ArrayList<>();
        Bill b = new Bill();
        listBill = b.getAllDataSetStatus(this);

        Log.e("Size", "onCreate: " +listBill.size() );
        recyclerView = findViewById(R.id.bill_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(BillActivity.this));
        recyclerView.setAdapter(new BillAdapter(BillActivity.this, listBill));

        initRooms();
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Bill> listBill = new ArrayList<>();
        Bill b = new Bill();
        listBill = b.getAllDataSetStatus(BillActivity.this);
        Log.e("Size", "onCreate: " +listBill.size() );
        recyclerView.setAdapter(new BillAdapter(this, listBill));
    }

    private void initRooms() {

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

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bill_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.menu_signout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(BillActivity.this, LoginActivity.class));
            finish();
        }
        if (id == R.id.add_doctor){
            Intent intent = new Intent(this, AddDoctor.class);
            startActivity(intent);
        }
//        if (id == R.id.add_room) {
//            Intent intent = new Intent(this, AddRoom.class);
//            startActivity(intent);
//        }
        if (id == R.id.show_patients) {
            Intent intent = new Intent(this, ShowData.class);
            intent.putExtra("type", "patient");
            startActivity(intent);
        }
        if (id == R.id.show_doctor) {
            Intent intent = new Intent(this, ShowData.class);
            intent.putExtra("type", "doc");
            startActivity(intent);
        }
        if (id == R.id.show_treat) {
            Intent intent = new Intent(this, ShowData.class);
            intent.putExtra("type", "treatment");
            startActivity(intent);
        }
        if (id == R.id.add_treat) {
            Intent intent = new Intent(this, AddTreatment.class);
            startActivity(intent);
        }


            return true;
    }

}
