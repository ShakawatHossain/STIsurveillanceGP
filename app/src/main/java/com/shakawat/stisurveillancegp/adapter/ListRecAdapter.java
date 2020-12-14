package com.shakawat.stisurveillancegp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shakawat.stisurveillancegp.ListActivity;
import com.shakawat.stisurveillancegp.MainActivity;
import com.shakawat.stisurveillancegp.R;
import com.shakawat.stisurveillancegp.model.ListModel;
import com.shakawat.stisurveillancegp.model.MainModel;

import java.util.ArrayList;

public class ListRecAdapter extends RecyclerView.Adapter<ListRecAdapter.MyViewHolder> {
    Context ctx;
    ListActivity listActivity;
    RecyclerView recyclerView;
    ArrayList<ListModel> modelLists;

    public ListRecAdapter(Context context,RecyclerView recyclerView,ListActivity listActivity,ArrayList<ListModel> listDataStores){
        this.ctx = context;
        this.listActivity = listActivity;
        this.modelLists = listDataStores;
        this.recyclerView = recyclerView;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_record,parent,
                false);
        v.setOnClickListener(clickListener);
        return new MyViewHolder(v);
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = recyclerView.getChildAdapterPosition(v);
            clickedPosition(position);
        }
    };
    public void clickedPosition(int position) {
        ListModel modelList = modelLists.get(position);
//        Remove Empty cells
        ArrayList<String> keys = new ArrayList<>();
        for(String key:modelList.mainParam.keySet()) if(modelList.mainParam.get(key).isEmpty()) keys.add(key);
        for (String k:keys)  modelList.mainParam.remove(k);
        keys.clear();
        for(String key:modelList.demoParam.keySet()) if(modelList.demoParam.get(key).isEmpty()) keys.add(key);
        for (String k: keys) modelList.demoParam.remove(k);
        keys.clear();
        for(String key:modelList.clinParam.keySet()) if(modelList.clinParam.get(key).isEmpty()) keys.add(key);
        for (String k: keys) modelList.clinParam.remove(k);
        keys.clear();
        for(String key:modelList.pastParam.keySet()) if(modelList.pastParam.get(key).isEmpty()) keys.add(key);
        for (String k: keys) modelList.pastParam.remove(k);
        keys.clear();
        for(String key:modelList.riskParam.keySet()) if(modelList.riskParam.get(key).isEmpty()) keys.add(key);
        for (String k: keys) modelList.riskParam.remove(k);
        keys.clear();
        for(String key:modelList.visitParam.keySet()) if(modelList.visitParam.get(key).isEmpty()) keys.add(key);
        for (String k: keys) modelList.visitParam.remove(k);
        keys.clear();
//        Pass data to update
        MainModel.mainParam = modelList.mainParam;
        MainModel.demoParam = modelList.demoParam;
        MainModel.clinParam = modelList.clinParam;
        MainModel.pastParam = modelList.pastParam;
        MainModel.riskParam = modelList.riskParam;
        MainModel.visitParam = modelList.visitParam;
        ctx.startActivity(new Intent(ctx, MainActivity.class));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListModel modelList = modelLists.get(position);
        holder.serid.setText("Case ID: "+modelList.mainParam.get("case_id"));
        holder.h_mob.setText("Mobile no: : "+modelList.demoParam.get("mob"));
        holder.h_name.setText("Name: "+modelList.demoParam.get("name"));
        holder.crt_at.setText("Date: "+modelList.mainParam.get("in_date"));
        Log.d(String.valueOf(position)+" Component",modelList.demoParam.get("name"));
    }

    @Override
    public int getItemCount() {
        Log.d("No.Record"," "+modelLists.size());return modelLists.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView serid,h_name,h_mob,crt_at;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            serid = (TextView) itemView.findViewById(R.id.serid);
            h_name = (TextView) itemView.findViewById(R.id.h_name);
            h_mob = (TextView) itemView.findViewById(R.id.h_mob);
            crt_at = (TextView) itemView.findViewById(R.id.crt_at);
        }
    }
}
