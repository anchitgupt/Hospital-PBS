package com.ateam.hospital.View.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ateam.hospital.Controller.PatientDetail;
import com.ateam.hospital.R;

import java.util.List;

/**
 * Project Hospital
 * Created by Anchit Gupta on 2019-11-30.
 * Under the MIT License
 */
public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder>{

    private Context mContext;
    private PatientDetail patientDetail;
    private List<PatientDetail> listpatientDetails;

    public PatientAdapter(Context context, List<PatientDetail> listpatientDetails){
        this.mContext = context;
        this.listpatientDetails = listpatientDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        patientDetail = listpatientDetails.get(position);

        Log.e("Class: ", "onBindViewHolder: "+ patientDetail.getPatient_id() );
        holder.tvid.setText(String.valueOf(patientDetail.getPatient_id()));
        holder.tvage.setText(String.valueOf(patientDetail.getAge()));
        holder.tvblood.setText(patientDetail.getBlood());
        holder.tvname.setText(patientDetail.getName());

    }

    @Override
    public int getItemCount() {
        return listpatientDetails.size();
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

//public class ViewHolder extends RecyclerView.ViewHolder {
//
//    public TextView tvid, tvname, tvblood, tvage;
//
//    public ViewHolder(@NonNull View itemView) {
//        super(itemView);
//
//
//    }
//}