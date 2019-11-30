package com.ateam.hospital.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ateam.hospital.Controller.PatientDetail;
import com.ateam.hospital.Model.PatientDB;
import com.ateam.hospital.R;

public class AddPatient extends AppCompatActivity implements View.OnClickListener {


    EditText etname, etage, etblood, etaddress, etphone;
    private RadioGroup radioGroup;
    Boolean radio = false;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);


        radioGroup = findViewById(R.id.patient_radiogroup);

        etname = findViewById(R.id.patient_name);
        etage = findViewById(R.id.patient_age);
        etblood = findViewById(R.id.patient_blood);
        etaddress = findViewById(R.id.patient_address);
        etphone = findViewById(R.id.patient_phone);

        submit = findViewById(R.id.patient_submit);


        submit.setOnClickListener(this);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.patient_male) {
                    radio = false;
                }
                if (checkedId == R.id.patient_female) {
                    radio = true;
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        PatientDetail patientDetail = new PatientDetail();

        patientDetail.setName(etname.getText().toString().trim());
        patientDetail.setAge(Integer.parseInt(etage.getText().toString()));
        patientDetail.setBlood(etblood.getText().toString().trim());
        patientDetail.setAddress(etaddress.getText().toString().trim());
        patientDetail.setPhoneno(etphone.getText().toString().trim());
        patientDetail.setGender(radio);

        Log.e("Class", "onClick: Before");
        long l = patientDetail.addData(this, patientDetail);
        if (l>0){
            Toast.makeText(this, "Your ID is:  " + String.valueOf(l), Toast.LENGTH_LONG).show();
        } else {
            Log.e("Insert", "onClick: "+ l);
        }
    }
}
