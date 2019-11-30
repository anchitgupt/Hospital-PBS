package com.ateam.hospital.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.ateam.hospital.Controller.Bill;
import com.ateam.hospital.Controller.Doctor;
import com.ateam.hospital.Controller.PatientDetail;
import com.ateam.hospital.Controller.Prescription;
import com.ateam.hospital.Controller.Room;
import com.ateam.hospital.Controller.Treatment;
import com.ateam.hospital.R;

import java.util.Date;
import java.util.List;

public class BillGenreator extends AppCompatActivity implements View.OnClickListener {


    AutoCompleteTextView actv;
    Button assignRoom, assignDoctor, assingTreatment, submit;
    int room=-1;
    int doc =-1;
    int treat=-1;
    List<Integer> ids;
    ArrayAdapter<Integer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_genreator);

        actv       = findViewById(R.id.auto_patientid);
        assignRoom = findViewById(R.id.btn_roomAddere);
        assignDoctor = findViewById(R.id.btn_doctorAssign);
        assingTreatment = findViewById(R.id.btn_treatmentAssign);
        submit = findViewById(R.id.btn_submit);

        ids = new PatientDetail().getIDs(this);
        adapter = new ArrayAdapter<Integer>(this,android.R.layout.select_dialog_item,ids);

        actv.setThreshold(1);
        actv.setAdapter(adapter);
        actv.setTextColor(Color.RED);

        findViewById(R.id.btn_addpatient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BillGenreator.this, AddPatient.class));
            }
        });

        assignRoom.setOnClickListener(this);
        assignDoctor.setOnClickListener(this);
        assingTreatment.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == assignRoom)
        {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(BillGenreator.this);
            builderSingle.setIcon(R.drawable.ic_launcher_foreground);
            builderSingle.setTitle("Select One Name:-");

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(BillGenreator.this, android.R.layout.select_dialog_singlechoice);
            final List<Room> re = new Room().getAllData(this);

            for (Room r:
                 re) {
                if(r.getStatus())
                    arrayAdapter.add(r.getBedno());
            }

            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final String strName = arrayAdapter.getItem(which);
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(BillGenreator.this);
                    builderInner.setMessage(strName);
                    for (Room r:
                            re) {
                        if(r.getBedno().equals(strName))
                            room=r.getRoom_id();
                        Toast.makeText(BillGenreator.this, "" + room, Toast.LENGTH_SHORT).show();
                    }

                    builderInner.setTitle("Your Selected Item is");
                    builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,int which) {
                            assignRoom.setText(strName);
                            dialog.dismiss();
                        }
                    });
                    builderInner.show();
                }
            });
            builderSingle.show();
        }
        if (view == assignDoctor)
        {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(BillGenreator.this);
            builderSingle.setIcon(R.drawable.ic_launcher_foreground);
            builderSingle.setTitle("Select Doctor:-");

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(BillGenreator.this,android.R.layout.simple_list_item_1);
            final List<Doctor> re = new Doctor().getAllData(this);

            for (Doctor d:
                 re) {
                arrayAdapter.add(d.getName());
            }


            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final String strName = arrayAdapter.getItem(which);
                    for (Doctor d:
                            re) {
                        if (d.getName().equals(strName)){
                            doc =d.getDoctor_id();
                            break;
                        }
                    }
//                    Log.e("Index", "onClick: "+String.valueOf(index));
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(BillGenreator.this);
                    builderInner.setMessage(strName);
                    builderInner.setTitle("Your Selected Doctor:");
                    builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,int which) {
                            assignDoctor.setText(strName);
                            dialog.dismiss();
                        }
                    });
                    builderInner.show();
                }
            });
            builderSingle.show();
        }
        if (view == assingTreatment)
        {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(BillGenreator.this);
            builderSingle.setIcon(R.drawable.ic_launcher_foreground);
            builderSingle.setTitle("Select Doctor:-");

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(BillGenreator.this,android.R.layout.simple_list_item_1);
            final List<Treatment> re = new Treatment().getAllData(this);

            for (Treatment t:
                    re) {
                arrayAdapter.add(t.getTreatmentname());
            }


            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final String strName = arrayAdapter.getItem(which);
                    for (Treatment t:
                            re) {
                        if (t.getTreatmentname().equals(strName)){
                            // here actual data can be get specially treatment id
//                            Toast.makeText(BillGenreator.this, "" + t.getTreatment_id(), Toast.LENGTH_SHORT).show();
                            treat=t.getTreatment_id();
                            break;
                        }
                    }
//                    Log.e("Index", "onClick: "+String.valueOf(index));
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(BillGenreator.this);
                    builderInner.setMessage(strName);
                    builderInner.setTitle("Your Selected Doctor:");
                    builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,int which) {
                            assingTreatment.setText(strName);
                            dialog.dismiss();
                        }
                    });
                    builderInner.show();
                }
            });
            builderSingle.show();
        }
        if (view == submit)
        {
            if (room == -1 || treat == -1 || doc == -1 || actv.getText().toString().trim().equals("")){
                Toast.makeText(this, "Choose All values", Toast.LENGTH_LONG).show();
            } else {
//                ProgressDialog progressDialog = new ProgressDialog(this);
//                progressDialog.setMessage("Creating Bill");






                Room rooms = new Room().getDataById(this, room);


//
                int pid = Integer.parseInt(actv.getText().toString().trim());

//
                PatientDetail patientDetail = new PatientDetail().getDataById(this, pid);

                Doctor d    = new Doctor();
                Doctor doctor =  d.getDataById(this, doc);

                Treatment t = new Treatment();
                Treatment treatment    = t.getDataById(this, treat);

                Log.e("Treat", "onClick: " + treatment.getTreatment_id());
                Log.e("Doctor", "onClick: " + doctor.getDoctor_id());

                Prescription p = new Prescription(doctor,treatment);

                Log.e("Prescription", "onClick: " + p.getDoctorIDFromPres());

                long l = p.insertData(this, p);

                if (l>0){
                    Toast.makeText(this, "Created Prescription:  " + String.valueOf(l), Toast.LENGTH_LONG).show();
                }

                int presid  = p.getLastID(this);

                Log.e("Prescription Created", "onClick: " + presid);

                Prescription prescription = p.getData(this, presid);

                String arrdate = new Date().toLocaleString();
                String depdate = new Date().toLocaleString();

                Log.e("Date Arr", "onClick: " + arrdate );
                Log.e("Date Dep", "onClick: " + depdate );

                int status = 1;

                Bill bill = new Bill(patientDetail, prescription, rooms, status, arrdate, depdate);

                l = bill.insertData(this, bill);

                if (l>0){
                    Toast.makeText(this, "Created Prescription:  " + String.valueOf(l), Toast.LENGTH_LONG).show();
                }

                Log.e("Bill", "onClick: Bill Created");

                rooms.setStatus(this, room, 0);

                Log.e("Room", "onClick: Room Status Updated");

            }
        }
    }
}
