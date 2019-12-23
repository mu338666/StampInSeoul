package com.example.mu338.stampinseoul;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

    // == Gps Activity 어댑터 클래스.

public class GpsAdapter extends RecyclerView.Adapter<GpsAdapter.CustomViewHolder> {

    private int layout;
    private ArrayList<ThemeData> list;

    public GpsAdapter(int layout, ArrayList<ThemeData> list) {
        this.layout = layout;
        this.list = list;
    }


    @NonNull
    @Override
    public GpsAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GpsAdapter.CustomViewHolder customViewHolder, final int position) {

        customViewHolder.txtName.setText(list.get(position).getTitle());
        customViewHolder.txtContent.setText(list.get(position).getAddr());

        customViewHolder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return (list != null) ? (list.size()) : (0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public ImageView imaProfile;
        public TextView txtName;
        public TextView txtContent;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            imaProfile = itemView.findViewById(R.id.fImgProfile);
            txtName = itemView.findViewById(R.id.fTxtName);
            txtContent = itemView.findViewById(R.id.txtContent);

        }
    }
}
