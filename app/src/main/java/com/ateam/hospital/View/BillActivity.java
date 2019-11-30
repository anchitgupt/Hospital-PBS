package com.ateam.hospital.View;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ateam.hospital.R;
import com.google.firebase.auth.FirebaseAuth;

public class BillActivity extends AppCompatActivity {

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent  = new Intent(BillActivity.this, BillGenreator.class);
                startActivity(intent);


            }
        });
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
        if (id == R.id.add_room) {
            Intent intent = new Intent(this, AddRoom.class);
            startActivity(intent);
        }

            return true;
    }

}
