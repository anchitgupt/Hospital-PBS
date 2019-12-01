package com.ateam.hospital.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ateam.hospital.Controller.Bill;
import com.ateam.hospital.Controller.Room;
import com.ateam.hospital.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class BillStatus extends AppCompatActivity implements View.OnClickListener {


    TextView tvptname, tvarradate, tvdpdate, tvdocname, tvdocchr, tvtratname,tvtratcharges, tvroom, tvroomchr, tvmedchr, tvtotal;
    Button submit;
    int id;
    String arrdate;
    int total;
    Bill bill;
    int room;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_status);

        id = getIntent().getIntExtra("id", 0);


        if (id == 0){
            finish();
            startActivity(new Intent(this, BillActivity.class));
        }

        bill = new Bill().getDataById(this,id);

        int general = 1000;
        int icu = 2000;


        tvptname = findViewById(R.id.patient_name);
        tvarradate = findViewById(R.id.arr_date);
        tvdpdate = findViewById(R.id.dep_date);
        tvdocname = findViewById(R.id.doctor_name);
        tvdocchr = findViewById(R.id.doctor_charges);
        tvtratcharges = findViewById(R.id.treatment_charges);
        tvroom = findViewById(R.id.room);
        tvroomchr = findViewById(R.id.room_charges);
        tvmedchr = findViewById(R.id.medicine_charges);
        tvtotal = findViewById(R.id.total_charges);
        tvtratname = findViewById(R.id.treatment);

        tvtotal.setVisibility(View.GONE);
        submit = findViewById(R.id.patient_submit);
        tvarradate.setVisibility(View.GONE);



        tvptname.setText(bill.getPatientDetail().getName());
        tvarradate.setText(bill.getArrdate());
        tvdpdate.setText(bill.getDepdate());
        tvdocname.setText(bill.getPrescription().getDoctor().getName());

        try{
            tvtratname.setText(bill.getPrescription().getTreatment().getTreatmentname());
        } catch (Exception e){
            Log.e("getTreatmentname", "onCreate: " +e.getMessage() );
        }
        try {
            tvdocchr.setText(String.valueOf(bill.getPrescription().getDoctor().getCharges()));
        }catch (Exception e){
            Log.e("getDoctor", "onCreate: " +e.getMessage() );
        }
        try
        {
            tvtratcharges.setText(String.valueOf(bill.getPrescription().getTreatment().getCharges()));
        }catch (Exception e){
            Log.e("getPrescription", "onCreate: " +e.getMessage() );
        }


        tvmedchr.setText(String.valueOf(bill.getPrescription().getTreatment().getMedicine_charges()));


        tvroom.setText(bill.getRoom().getBedno());
        tvroomchr.setText(String.valueOf(200));

        room = 1;
        if (bill.getRoom().getWard().equals("General"))
            room *=general;
        else
            room *=icu;


        tvtotal.setText(String.valueOf(total));
        submit.setOnClickListener(this);
        arrdate = bill.getArrdate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MMM-yyyy kk:mm:ss a").parse(arrdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date dt2 = new Date();

        long diff = dt2.getTime() - date.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        int diffInDays = (int) ((dt2.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));

        if (diffInDays > 1) {
            Toast.makeText(this, String.valueOf(diffInDays), Toast.LENGTH_SHORT).show();
        } else
            diffInDays=1;

        total = (int)bill.getPrescription().getTreatment().getMedicine_charges() + (int)bill.getPrescription().getTreatment().getCharges()
                +(int) bill.getPrescription().getDoctor().getCharges() + room*diffInDays;

        final AlertDialog.Builder builderSingle = new AlertDialog.Builder(BillStatus.this);
        builderSingle.setIcon(R.drawable.ic_launcher_foreground);
        builderSingle.setTitle("Select One Name:-");

        String message = "Doctor Charges: "+ String.valueOf(bill.getPrescription().getDoctor().getCharges())+"\n"
                +"Treatment Name: " + bill.getPrescription().getTreatment().getTreatmentname().trim() +"\n"
                +"Treatment Charges: " +String.valueOf(bill.getPrescription().getTreatment().getCharges()) +"\n"
                +"Medicine Charges: " + String.valueOf(bill.getPrescription().getTreatment().getMedicine_charges()) +"\n"
                +"Room Charges: " +String.valueOf(room) +" x "+ String.valueOf(diffInDays)+ " = "+room*diffInDays+"\n"
                +"Total: "+total;

        builderSingle.setMessage(message);

        builderSingle.setPositiveButton("Pay & Discharge", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    bill.setStatus(BillStatus.this, bill.getBill_id(), 0);
                    int roomid = bill.getRoom().getRoom_id();
                    new Room().setStatus(BillStatus.this, roomid, 1);
                    submit.setVisibility(View.GONE);
            }
        });
        builderSingle.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builderSingle.show();




    }
}
