package com.example.mu338.stampinseoul;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

    // == 내 정보 => 4번째 Fragment => MoreActivity

public class MoreActivity extends Fragment {

    private String[] txtContent = { "내 정보", "고객 센터", "이용 약관", "App 정보"};

    private ArrayList<String> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MoreAdapter moreAdapter;

    private View view;

    // == 카카오

    private Button btnLogout;
    private ImageView imgKakao;
    private TextView txtKakaoName;

    String strNickname, strProfile;
    Bitmap bitmap;

    // == ExpandableList 펼치고 접는 리스트뷰

    private ExpandableListView eListView;

    private ArrayList<String> groupList = new ArrayList<>();
    private ArrayList<ArrayList<String>> childList = new ArrayList<>();

    private ArrayList<String> chileListContent1 = new ArrayList<>();
    private ArrayList<String> chileListContent2 = new ArrayList<>();
    private ArrayList<String> chileListContent3 = new ArrayList<>();
    private ArrayList<String> chileListContent4 = new ArrayList<>();
    private ArrayList<String> chileListContent5 = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_more, container, false);

        eListView = view.findViewById(R.id.eListView);

        groupList.removeAll(groupList);

        groupList.add("내 정보");
        groupList.add("고객 센터");
        groupList.add("이용 약관");
        groupList.add("App 정보");
        groupList.add("개발자 정보");

        chileListContent1.add("내 정보 테스트 입니다.");
        chileListContent2.add("고객 센터 테스트 입니다.");
        chileListContent3.add("이용 약관 테스트 입니다.");
        chileListContent4.add("App 정보 테스트 입니다.");
        chileListContent5.add("이무권"+"\n"+"박은혜"+"\n"+"서종주");

        childList.add(chileListContent1);
        childList.add(chileListContent2);
        childList.add(chileListContent3);
        childList.add(chileListContent4);
        childList.add(chileListContent5);

        eListView.setAdapter(new MoreAdapter(view.getContext(), groupList, childList));

        // 그룹 클릭 했을때 이벤트
        eListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
              @Override
              public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {


                  return false;
              }
          });

        // 자식 클릭 했을때 이벤트
        eListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


                return false;
            }
        });


        btnLogout = view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogout();
                Toast.makeText(view.getContext(), "로그아웃 합니다.", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }


    private void onClickLogout() {
        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {

                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}
