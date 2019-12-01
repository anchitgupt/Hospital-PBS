package com.ateam.hospital.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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



        if (TextUtils.isEmpty(etname.getText().toString().trim())||
                TextUtils.isEmpty(etcharges.getText().toString().trim())
                || TextUtils.isEmpty(etmed.getText().toString().trim()))
        {
            Toast.makeText(this, "Fill All Values", Toast.LENGTH_SHORT).show();
        }
        else {
            String name = etname.getText().toString().trim();
            int charges = Integer.parseInt(etcharges.getText().toString().trim());
            int med = Integer.parseInt(etmed.getText().toString().trim());

            long l = new Treatment().insertData(this, new Treatment(name, charges, med));
            if (l > 0) {
                Toast.makeText(this, "Your ID is:  " + String.valueOf(l), Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(this, BillActivity.class));
            } else {
                Log.e("Insert", "onClick: " + l);
            }
        }

    }
}
