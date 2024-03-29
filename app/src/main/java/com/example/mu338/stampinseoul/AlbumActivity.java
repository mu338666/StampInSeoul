package com.example.mu338.stampinseoul;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

    // 내 정보 => 3번 Fragment => AlbumActivity

public class AlbumActivity extends Fragment implements View.OnClickListener, View.OnTouchListener {


    private ArrayList<ThemeData> cameraList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AlbumAdapter albumAdapter;

    private View view;


    // == 플로팅 버튼, 드로어

    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;

    private FloatingActionButton fab, fab1, fab2;
    private DrawerLayout drawerLayout;
    private ConstraintLayout drawer;

    private ImageView stamp1;
    private ImageView stamp2;
    private ImageView stamp3;
    private ImageView stamp4;
    private ImageView stamp5;
    private ImageView stamp6;
    private ImageView stamp7;
    private ImageView stamp8;

    private ImageView stamp1C;
    private ImageView stamp2C;
    private ImageView stamp3C;
    private ImageView stamp4C;
    private ImageView stamp5C;
    private ImageView stamp6C;
    private ImageView stamp7C;
    private ImageView stamp8C;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_album, container, false);


        // == 리사이클러뷰

        recyclerView = view.findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(view.getContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        albumAdapter = new AlbumAdapter(R.layout.album_item, cameraList);

        recyclerView.setAdapter(albumAdapter);

        cameraList.removeAll(cameraList);

        // == 플로팅 버튼 , 드로어
        fab = view.findViewById(R.id.fab);
        fab1 = view.findViewById(R.id.fab1);
        fab2 = view.findViewById(R.id.fab2);

        fab_open = AnimationUtils.loadAnimation(view.getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(view.getContext(), R.anim.fab_close);

        drawerLayout = view.findViewById(R.id.drawerLayout);
        drawer = view.findViewById(R.id.drawer);

        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);

        drawer.setOnTouchListener(this);
        drawerLayout.setDrawerListener(listener);


        stamp1 = view.findViewById(R.id.stamp1);
        stamp2 = view.findViewById(R.id.stamp2);
        stamp3 = view.findViewById(R.id.stamp3);
        stamp4 = view.findViewById(R.id.stamp4);
        stamp5 = view.findViewById(R.id.stamp5);
        stamp6 = view.findViewById(R.id.stamp6);
        stamp7 = view.findViewById(R.id.stamp7);
        stamp8 = view.findViewById(R.id.stamp8);

        stamp1C = view.findViewById(R.id.stamp1C);
        stamp2C = view.findViewById(R.id.stamp2C);
        stamp3C = view.findViewById(R.id.stamp3C);
        stamp4C = view.findViewById(R.id.stamp4C);
        stamp5C = view.findViewById(R.id.stamp5C);
        stamp6C = view.findViewById(R.id.stamp6C);
        stamp7C = view.findViewById(R.id.stamp7C);
        stamp8C = view.findViewById(R.id.stamp8C);



        stamoInvisible();

        // == 카메라

        // cameraList = (ArrayList<CameraData>)getActivity().getIntent().getSerializableExtra("list");

        MainActivity.db = MainActivity.dbHelper.getWritableDatabase();

        String searchComplete = "SELECT * FROM STAMP_"+LoginActivity.userId+" WHERE complete=1;";

        Cursor cursorComplete = MainActivity.db.rawQuery(searchComplete,null);

        while(cursorComplete.moveToNext()){
            cameraList.add(new ThemeData(cursorComplete.getString(1), cursorComplete.getString(5),
                    cursorComplete.getString(6), cursorComplete.getString(7), cursorComplete.getString(8), cursorComplete.getString(9),
                    cursorComplete.getInt(10)));
        }

        cursorComplete.moveToFirst();


        // == 도장 애니메이션

        Animation ai;

        for(int i=1;i<=cursorComplete.getCount();i++){

            switch (i){

                case 1:

                    stamp1C.setVisibility(View.VISIBLE);

                    ai = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);
                    stamp1C.startAnimation(ai);
                    break;

                case 2:

                    stamp2C.setVisibility(View.VISIBLE);
                    ai = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);
                    stamp2C.startAnimation(ai);
                    break;

                case 3:

                    stamp3C.setVisibility(View.VISIBLE);
                    ai = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);
                    stamp3C.startAnimation(ai);
                    break;

                case 4:

                    stamp4C.setVisibility(View.VISIBLE);
                    ai = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);
                    stamp4C.startAnimation(ai);
                    break;

                case 5:

                    stamp5C.setVisibility(View.VISIBLE);
                    ai = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);
                    stamp5C.startAnimation(ai);
                    break;

                case 6:

                    stamp6C.setVisibility(View.VISIBLE);
                    ai = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);
                    stamp6C.startAnimation(ai);
                    break;

                case 7:

                    stamp7C.setVisibility(View.VISIBLE);
                    ai = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);
                    stamp7C.startAnimation(ai);
                    break;

                case 8:

                    stamp8C.setVisibility(View.VISIBLE);
                    ai = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);
                    stamp8C.startAnimation(ai);
                    break;
            }
        }

        albumAdapter = new AlbumAdapter(R.layout.album_item, cameraList);

        recyclerView.setAdapter(albumAdapter);
        albumAdapter.notifyDataSetChanged();

        return view;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fab :

                anim();

                break;

            case R.id.fab1 :

                anim();
                drawerLayout.openDrawer(drawer);


                break;

                // == 미션 포기 버튼

            case R.id.fab2 :

                anim();

                View viewDialog = View.inflate(v.getContext(), R.layout.dialog_search_message, null);

                Button btnGiveUp = viewDialog.findViewById(R.id.btnGiveUp);
                Button btnExit = viewDialog.findViewById(R.id.btnExit);

                TextView txt_Detail_title = viewDialog.findViewById(R.id.txt_Detail_title);
                TextView txtWarning = viewDialog.findViewById(R.id.txtWarning);

                final Dialog noSearchDlg = new Dialog(v.getContext());

                noSearchDlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                txt_Detail_title.setText("주의");
                txtWarning.setText("정말 미션을 포기 하시겠어요?");

                noSearchDlg.setContentView(viewDialog);
                noSearchDlg.show();

                btnGiveUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        cameraList.removeAll(cameraList);

                        MainActivity.db = MainActivity.dbHelper.getWritableDatabase();
                        MainActivity.db.execSQL("DROP TABLE IF EXISTS STAMP_" + LoginActivity.userId + ";");
                        MainActivity.db.execSQL("CREATE TABLE IF NOT EXISTS STAMP_" + LoginActivity.userId  + "("
                                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                + "title TEXT, "
                                + "addr TEXT, "
                                + "mapX REAL, "
                                + "mapY REAL, "
                                + "firstImage TEXT, "
                                + "picture TEXT, "
                                + "content_pola TEXT, "
                                + "content_title TEXT, "
                                + "contents TEXT, "
                                + "complete INTEGER);");

                        Intent intent = new Intent(v.getContext(), ThemeActivity.class);
                        startActivity(intent);

                        getActivity().finish();

                    }
                });

                btnExit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        noSearchDlg.dismiss();
                    }
                });


                break;

            default: break;

        }

    }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {

        // 슬라이딩을 시작 했을때 이벤트 발생
        @Override
        public void onDrawerSlide(@NonNull View view, float v) {

        }

        // 메뉴가 열었을때 이벤트 발생
        @Override
        public void onDrawerOpened(@NonNull View view) {

        }

        // 메뉴를 닫았을때 이벤트 발생
        @Override
        public void onDrawerClosed(@NonNull View view) {

        }

        // 메뉴 바가 상태가 바뀌었을때 이벤트 발생
        @Override
        public void onDrawerStateChanged(int i) {

        }
    };


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


    // 도장 애니메이션 기본 세팅
    private void stamoInvisible() {

        stamp1C.setVisibility(View.INVISIBLE);
        stamp2C.setVisibility(View.INVISIBLE);
        stamp3C.setVisibility(View.INVISIBLE);
        stamp4C.setVisibility(View.INVISIBLE);
        stamp5C.setVisibility(View.INVISIBLE);
        stamp6C.setVisibility(View.INVISIBLE);
        stamp7C.setVisibility(View.INVISIBLE);
        stamp8C.setVisibility(View.INVISIBLE);

    }

    // 플로팅 버튼 애니메이션 메소드
    public void anim() {

        if (isFabOpen) {

            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;

        } else {

            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;

        }
    }

}
