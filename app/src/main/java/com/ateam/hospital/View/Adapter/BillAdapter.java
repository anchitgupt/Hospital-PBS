package com.ateam.hospital.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ateam.hospital.Controller.Bill;
import com.ateam.hospital.Controller.PatientDetail;
import com.ateam.hospital.Controller.Prescription;
import com.ateam.hospital.R;

import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-12-01.
 * Under the MIT License
 */
public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {

    private Context mContext;
    private Bill billDetail;
    private List<Bill> listbillDetails;

    public BillAdapter(Context context, List<Bill> listbillDetails) {
        this.mContext = context;
        this.listbillDetails = listbillDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_bill, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        billDetail = listbillDetails.get(position);
        holder.tvid.setText(String.valueOf(billDetail.getBill_id()));

        holder.tvname.setText(billDetail.getPatientDetail().getName());
        holder.tvdname.setText(billDetail.getPrescription().getDoctor().getName());
        holder.tvardate.setText(billDetail.getArrdate());
        holder.tvdpdate.setText(billDetail.getDepdate());

        holder.llm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(this, )
            }
        });

    }

    @Override
    public int getItemCount() {
        return listbillDetails.size();
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
