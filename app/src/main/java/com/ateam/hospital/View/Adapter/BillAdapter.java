package com.ateam.hospital.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ateam.hospital.Controller.Bill;
import com.ateam.hospital.Controller.PatientDetail;
import com.ateam.hospital.Controller.Prescription;
import com.ateam.hospital.R;
import com.ateam.hospital.View.BillStatus;

import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-12-01.
 * Under the MIT License
 */
public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> implements View.OnClickListener {

    public Context context;
    private Bill billDetail;
    private List<Bill> listbillDetails;

    public BillAdapter(Context context, List<Bill> listbillDetails) {
        this.context = context;
        this.listbillDetails = listbillDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_bill, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        billDetail = listbillDetails.get(position);
        holder.tvid.setText(String.valueOf(billDetail.getBill_id()));
        holder.tvname.setText(billDetail.getPatientDetail().getName());
        holder.tvdname.setText(billDetail.getPrescription().getDoctor().getName());
        holder.tvardate.setText(billDetail.getArrdate());
        holder.tvdpdate.setText(billDetail.getDepdate());

        holder.llm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billDetail = listbillDetails.get(position);
                Intent intent = new Intent(context.getApplicationContext(), BillStatus.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                int id = billDetail.getBill_id();
                intent.putExtra("id", id);
               try {
                   context.startActivity(intent);
               }catch(Exception e){
                   Log.e("Exception: ", "onClick: " + e.getMessage());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listbillDetails.size();
    }

    @Override
    public void onClick(View view) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvid, tvname, tvdname, tvardate, tvdpdate;
        public LinearLayout llm;

        public ViewHolder(View itemView) {
            super(itemView);
            llm = itemView.findViewById(R.id.llm);
            tvid = itemView.findViewById(R.id.textViewid);
            tvname = itemView.findViewById(R.id.textViewPatientName);
            tvdname = itemView.findViewById(R.id.textViewDoctorName);
            tvardate = itemView.findViewById(R.id.textViewArrDate);
            tvdpdate = itemView.findViewById(R.id.textViewDrrDate);
        }
    }
}
