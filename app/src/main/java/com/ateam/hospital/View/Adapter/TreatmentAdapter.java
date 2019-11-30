package com.ateam.hospital.View.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ateam.hospital.Controller.PatientDetail;
import com.ateam.hospital.Controller.Treatment;
import com.ateam.hospital.R;

import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-12-01.
 * Under the MIT License
 */
public class TreatmentAdapter extends RecyclerView.Adapter<TreatmentAdapter.ViewHolder>{

    private Context mContext;
    private Treatment treatmentDetail;
    private List<Treatment> listtreatmentDetails;

    public TreatmentAdapter(Context context, List<Treatment> listtreatmentDetails){
        this.mContext = context;
        this.listtreatmentDetails = listtreatmentDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        treatmentDetail = listtreatmentDetails.get(position);

        holder.tvid.setText(String.valueOf(treatmentDetail.getTreatment_id()));
        holder.tvage.setText(String.valueOf(treatmentDetail.getCharges()));
        holder.tvblood.setText(String.valueOf(treatmentDetail.getMedicine_charges()));
        holder.tvname.setText(treatmentDetail.getTreatmentname());

    }

    @Override
    public int getItemCount() {
        return listtreatmentDetails.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvid, tvname, tvblood, tvage;

        public ViewHolder(View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.textViewid);
            tvname = itemView.findViewById(R.id.textViewName);
            tvblood = itemView.findViewById(R.id.textViewBlood);
            tvage = itemView.findViewById(R.id.textViewAge);
        }
    }
}