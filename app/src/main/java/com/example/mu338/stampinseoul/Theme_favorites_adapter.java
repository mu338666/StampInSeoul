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

    // == 찜목록

public class Theme_favorites_adapter extends RecyclerView.Adapter<Theme_favorites_adapter.CustomViewHolder> {

    private int layout;
    private ArrayList<ThemeFavoritesData> list;

    public Theme_favorites_adapter(int layout, ArrayList<ThemeFavoritesData> list) {
        this.layout = layout;
        this.list = list;
    }


    @NonNull
    @Override
    public Theme_favorites_adapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final Theme_favorites_adapter.CustomViewHolder customViewHolder, final int position) {

        // customViewHolder.imaProfile.setImageResource(list.get(position).getMissionImgProfile());
        customViewHolder.fTxtName.setText(list.get(position).getfTxtName());
        // customViewHolder.txtContent.setText(list.get(position).getMissionTxtContent());

        customViewHolder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return (list != null) ? (list.size()) : (0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public ImageView fImgProfile;
        public TextView fTxtName;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            fImgProfile = itemView.findViewById(R.id.fImgProfile);
            fTxtName = itemView.findViewById(R.id.fTxtName);

        }
    }
}
