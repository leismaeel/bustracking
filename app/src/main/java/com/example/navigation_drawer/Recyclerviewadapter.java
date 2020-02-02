package com.example.navigation_drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Recyclerviewadapter extends RecyclerView.Adapter<Recyclerviewadapter.ViewHolder>{

    private ArrayList<String> names =new ArrayList<>();
    private ArrayList<String> dates = new ArrayList<>();
    private Context mContext;

    public Recyclerviewadapter(ArrayList<String> names, Context mContext, ArrayList<String> dates) {
        this.names = names;
        this.mContext = mContext;
        this.dates = dates;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitems,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.a.setText(names.get(position));
        holder.dates.setText(dates.get(position));

        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, names.get(position), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView a;
        TextView dates;
        RelativeLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            a = itemView.findViewById(R.id.textviewoflistitemsview);
            dates = itemView.findViewById(R.id.dates);
            parent_layout =itemView.findViewById(R.id.parent_layout);
        }
    }
}
