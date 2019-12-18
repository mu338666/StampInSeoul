package com.example.mu338.stampinseoul;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

    // AlbumActivity 리사이클러뷰 어댑터

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.CustomViewHolder> {

    private int layout;
    private ArrayList<CameraData> list;

    public AlbumAdapter(int layout, ArrayList<CameraData> list) {
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

        // customViewHolder.txtID.setText(list.get(position).getReviewTxtID());
        customViewHolder.txtPola.setText(list.get(position).getEdtPola());
        customViewHolder.txtTitle.setText(list.get(position).getEdtTitle());
        customViewHolder.txtContent.setText(list.get(position).getEdtContents());

        customViewHolder.itemView.setTag(position);

        Bitmap bitmap = BitmapFactory.decodeFile(list.get(position).getImgPhoto());

        ExifInterface exifInterface = null;

        try {

            exifInterface = new ExifInterface(list.get(position).getImgPhoto());

        } catch (IOException e) {
            e.printStackTrace();
        }

        int exifOrientation; //방향
        int exifDegres; //각도

        if (exifInterface != null) {

            exifOrientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            exifDegres = exiforToDe(exifOrientation);

        }else{
            exifDegres=0;
        }

        Bitmap bitmapTeep = rotate(bitmap,exifDegres);

        customViewHolder.imgReview.setImageBitmap(bitmapTeep);



    }

    @Override
    public int getItemCount() {
        return (list != null) ? (list.size()) : (0);
    }

    // ==== 내부 클래스

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgReview;

        public TextView txtPola;
        public TextView txtTitle;
        public TextView txtContent;
        public TextView txtID;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            imgReview = itemView.findViewById(R.id.imgReview);

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
