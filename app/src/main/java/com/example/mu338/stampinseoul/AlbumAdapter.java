package com.example.mu338.stampinseoul;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

    // == AlbumActivity 리사이클러뷰 어댑터

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.CustomViewHolder> {

    private int layout;
    private ArrayList<ThemeData> list;

    public AlbumAdapter(int layout, ArrayList<ThemeData> list) {
        this.layout = layout;
        this.list = list;
    }


    @NonNull
    @Override
    public AlbumAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AlbumAdapter.CustomViewHolder customViewHolder, final int position) {

        customViewHolder.txtPola.setText(list.get(position).getContent_pola());

        customViewHolder.txtTitle.setText(list.get(position).getContent_title());
        customViewHolder.txtContent.setText(list.get(position).getContents());
        customViewHolder.txtID.setText(list.get(position).getTitle());

        customViewHolder.itemView.setTag(position);

        if (list.get(position).getPicture() != null) {

            Bitmap bitmap = BitmapFactory.decodeFile(list.get(position).getPicture());
            customViewHolder.imgReview.setImageBitmap(bitmap);

        } else {
            customViewHolder.imgReview.setImageResource(R.drawable.a_dialog_design);
        }

        if (list.get(position).getFirstImage() != null) {

            Glide.with(customViewHolder.itemView.getContext()).load(list.get(position).getFirstImage()).override(500, 300).into(customViewHolder.imgChoice);

        } else {
            customViewHolder.imgChoice.setImageResource(R.drawable.a_dialog_design);
        }

    }

    @Override
    public int getItemCount() {
        return (list != null) ? (list.size()) : (0);
    }


    // ==== 내부 클래스
    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgReview;
        public CircleImageView imgChoice;

        public TextView txtPola;
        public TextView txtTitle;
        public TextView txtContent;
        public TextView txtID;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            imgReview = itemView.findViewById(R.id.imgReview);
            imgChoice = itemView.findViewById(R.id.imgChoice);

            txtPola = itemView.findViewById(R.id.txtPola);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtContent = itemView.findViewById(R.id.txtContent);
            txtID = itemView.findViewById(R.id.txtID);

        }
    }

    private int exiforToDe(int exifOrientation) {

        switch (exifOrientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:

                return 90;

            case ExifInterface.ORIENTATION_ROTATE_180:

                return 180;

            case ExifInterface.ORIENTATION_ROTATE_270:

                return 270;

        }

        return 0;

    }

    private Bitmap rotate(Bitmap bitmap, int exifDegres) {

        Matrix matrix = new Matrix();

        matrix.postRotate(exifDegres);

        Bitmap teepre = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);

        return teepre;

    }
}
