package com.example.mu338.stampinseoul;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

    // 바텀 네비게이션 메뉴. 4개 Fragment 컨트롤

public class BottomMenuActivity extends AppCompatActivity {

    private BottomNavigationView bottomMenu;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private MapLocateActivity mapLocateActivity;
    private GpsActivity gpsActivity;
    private AlbumActivity albumActivity;
    private MoreActivity moreActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);

        bottomMenu = findViewById(R.id.bottomMenu);
        frameLayout = findViewById(R.id.frameLayout);

        mapLocateActivity = new MapLocateActivity();
        gpsActivity = new GpsActivity();
        albumActivity = new AlbumActivity();
        moreActivity = new MoreActivity();

        setChangeFragment(0);

        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.action_gps:

                        // 프래그먼트 화면전환
                        setChangeFragment(0);

                        break;

                    case R.id.action_map:

                        setChangeFragment(1);

                        break;

                    case R.id.action_review:

                        setChangeFragment(2);

                        break;

                    case R.id.action_more:

                        setChangeFragment(3);

                        break;

                }

                return true;
            }
        });
    }

    private void setChangeFragment(int position) {

        // 화면을 전환하기 위해서는 매니저가 필요하다.
        fragmentManager = getSupportFragmentManager();

        // 프래그먼트 매니저 권한을 받아서 화면 체인지하는 트렌젝션이 필요하다.
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (position){

            case 0:

                fragmentTransaction.replace(R.id.frameLayout, gpsActivity);

                break;

            case 1:

                // 프레그먼트 충돌로 구글맵에 Fragment가 아닌 MapView 형식 이용.

                MapLocateActivity mainFragment = new MapLocateActivity();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, mainFragment, "main")
                        .commit();
                break;

            case 2:

                fragmentTransaction.replace(R.id.frameLayout, albumActivity);

                break;

            case 3:

                fragmentTransaction.replace(R.id.frameLayout, moreActivity);

                break;


        }

        fragmentTransaction.commit();

        return;
    }

}
