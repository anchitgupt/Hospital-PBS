package com.ateam.hospital.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.ateam.hospital.Controller.Doctor;
import com.ateam.hospital.Controller.PatientDetail;
import com.ateam.hospital.Controller.Treatment;
import com.ateam.hospital.R;
import com.ateam.hospital.View.Adapter.DoctorAdapter;
import com.ateam.hospital.View.Adapter.PatientAdapter;
import com.ateam.hospital.View.Adapter.TreatmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShowData extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        TextView tv = findViewById(R.id.tvhead);

        RecyclerView recyclerView = findViewById(R.id.data_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (getIntent().getStringExtra("type").equals("patient")) {
            tv.setText("Patient List");
            List<PatientDetail> listPatient = new ArrayList<>();
            PatientDetail p = new PatientDetail();
            listPatient = p.getAllData(this);
            recyclerView.setAdapter(new PatientAdapter(this, listPatient));
        }
        if (getIntent().getStringExtra("type").equals("doc")) {
            tv.setText("Doctor List");
            List<Doctor> listDcotor = new ArrayList<>();
            Doctor d = new Doctor();
            listDcotor = d.getAllData(this);
            recyclerView.setAdapter(new DoctorAdapter(this, listDcotor));
        }
        if (getIntent().getStringExtra("type").equals("treatment")) {
            tv.setText("Treatments List");
            List<Treatment> listTreatment = new ArrayList<>();
            Treatment t = new Treatment();
            listTreatment = t.getAllData(this);
            recyclerView.setAdapter(new TreatmentAdapter(this, listTreatment));
        }
    }
}
