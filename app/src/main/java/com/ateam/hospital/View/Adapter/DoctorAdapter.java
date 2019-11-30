package com.ateam.hospital.View.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ateam.hospital.Controller.Doctor;
import com.ateam.hospital.R;

import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-30.
 * Under the MIT License
 */
public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private Context mContext;
    private Doctor doctorDetail;
    private List<Doctor> listdoctorDetails;

    public DoctorAdapter(Context context, List<Doctor> listdoctorDetails) {
        this.mContext = context;
        this.listdoctorDetails = listdoctorDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_doctor, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        doctorDetail = listdoctorDetails.get(position);

        holder.tvid.setText(String.valueOf(doctorDetail.getDoctor_id()));
        holder.tvage.setText(String.valueOf(doctorDetail.getCharges()));
        holder.tvblood.setText(doctorDetail.getSpecialization());
        holder.tvname.setText(doctorDetail.getName());

    }

    @Override
    public int getItemCount() {
        return listdoctorDetails.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvid, tvname, tvblood, tvage;

        public ViewHolder(View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.textViewid);
            tvname = itemView.findViewById(R.id.textViewName);
            tvblood = itemView.findViewById(R.id.textViewSpecs);
            tvage = itemView.findViewById(R.id.textViewCharges);
        }
    }
}
