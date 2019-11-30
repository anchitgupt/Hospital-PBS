package com.ateam.hospital.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ateam.hospital.Controller.Prescription;
import com.ateam.hospital.Controller.Treatment;
import com.ateam.hospital.R;

public class AddTreatment extends AppCompatActivity implements View.OnClickListener {


    private EditText etname, etcharges, etmed;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_treatment);

        etname =  findViewById(R.id.treatment_name);
        etcharges = findViewById(R.id.treatment_charges);
        etmed = findViewById(R.id.treatment_medicine);

        submit = findViewById(R.id.treatment_submit);

        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = etname.getText().toString().trim();
        int charges = Integer.parseInt(etcharges.getText().toString().trim());
        int med = Integer.parseInt(etmed.getText().toString().trim());


           long l =  new Treatment().insertData(this, new Treatment(name, charges, med));
            if (l>0){
                Toast.makeText(this, "Your ID is:  " + String.valueOf(l), Toast.LENGTH_LONG).show();
            } else {
                Log.e("Insert", "onClick: "+ l);
            }

    }
}
