package com.ateam.hospital.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ateam.hospital.Controller.Doctor;
import com.ateam.hospital.Controller.PatientDetail;
import com.ateam.hospital.R;

public class AddDoctor extends AppCompatActivity implements View.OnClickListener {

    EditText etname, etage, etdept, etspecs, etcharges;
    private RadioGroup radioGroup;
    Boolean radio = false;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);



        radioGroup = findViewById(R.id.doctor_radiogroup);

        etname = findViewById(R.id.doctor_name);
        etage = findViewById(R.id.doctor_age);
        etdept = findViewById(R.id.doctor_department);
        etspecs = findViewById(R.id.doctor_specs);
        etcharges = findViewById(R.id.doctor_charges);
        submit = findViewById(R.id.doctor_submit);


        submit.setOnClickListener(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.doctor_male) {
                    radio = false;
                }
                if (checkedId == R.id.doctor_female) {
                    radio = true;
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        Doctor doctor = new Doctor();

        doctor.setName(etname.getText().toString().trim());
        doctor.setAge(Integer.parseInt(etage.getText().toString()));
        doctor.setDepartment(etdept.getText().toString().trim());
        doctor.setSpecialization(etspecs.getText().toString().trim());
        doctor.setCharges(Integer.parseInt(etcharges.getText().toString().trim()));
        doctor.setGender(radio);

        Log.e("Class", "onClick: Before");
        long l = doctor.addData(this, doctor);
        if (l>0){
            Toast.makeText(this, "Your ID is:  " + String.valueOf(l), Toast.LENGTH_LONG).show();
        } else {
            Log.e("Insert", "onClick: "+ l);
        }


    }
}
