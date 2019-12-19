package com.example.mu338.stampinseoul;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

    // == ThemeActivity => 검색 하면 이동 되는 액티비티

public class SearchActivity extends AppCompatActivity {

    final static String TAG = "ThemeActivity";

    String keyword;

    private EditText edtSearch2;
    private ImageButton btnSearch2;

    RequestQueue queue;

    ArrayList<ThemeData> list = new ArrayList<>();

    RecyclerView recyclerView;
    SearchAdapter adapter;
    LinearLayoutManager layoutManager;
    ProgressDialog pDialog;

    static final String KEY = "OEZDFxQGYkA8crUzSlj51nwQQb9Jh78Y5UWvaW5gXccZ5t2ttRXNjcdXjJJ8FsHlriUWu%2B%2FVhFfuI32FbuMhTA%3D%3D";
    static final String appName = "Zella";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        edtSearch2 = findViewById(R.id.edtSearch2);
        btnSearch2 = findViewById(R.id.btnSearch2);

        Intent intent = getIntent();
        String word = intent.getStringExtra("word");

        btnSearch2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String word = edtSearch2.getText().toString().trim();

                if(word.length() > 1){
                    searchData(word);
                }else{
                    Toast.makeText(getApplicationContext(), "두 글자 이상 입력해 주세요", Toast.LENGTH_LONG).show();
                }

            }
        });

        searchData(word);
    }


    public void searchData(String word) {

        try {
            keyword = URLEncoder.encode(word, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        queue = Volley.newRequestQueue(getApplicationContext());

        String url = "http://api.visitkorea.or.kr/openapi/service/"
                + "rest/KorService/searchKeyword?ServiceKey=" + KEY
                + "&keyword=" + keyword + "&areaCode=1&listYN=Y&arrange=P"
                + "&numOfRows=20&pageNo=1&MobileOS=AND&MobileApp="
                + appName + "&_type=json";


            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        MainActivity.db = MainActivity.dbHelper.getWritableDatabase();

                        Cursor cursor;

                        cursor = MainActivity.db.rawQuery("SELECT title FROM ZZIM_"+LoginActivity.userId+";", null);

                        try {

                            JSONObject parse_response = (JSONObject) response.get("response");
                            JSONObject parse_body = (JSONObject) parse_response.get("body");
                            JSONObject parse_items = (JSONObject) parse_body.get("items");
                            JSONArray parse_itemlist = (JSONArray) parse_items.get("item");

                            list.removeAll(list);

                            for (int i = 0; i < parse_itemlist.length(); i++) {

                                JSONObject imsi = (JSONObject) parse_itemlist.get(i);

                                ThemeData themeData = new ThemeData();
                                themeData.setFirstImage(imsi.getString("firstimage"));
                                themeData.setTitle(imsi.getString("title"));
                                themeData.setContentsID(Integer.valueOf(imsi.getString("contentid")));


                                while(cursor.moveToNext()){
                                    if(cursor.getString(0).equals(themeData.getTitle())){
                                        themeData.setHart(true);
                                    }
                                }
                                cursor.moveToFirst();
                                list.add(themeData);

                            }

                            recyclerView.setAdapter(adapter);

                        } catch (ClassCastException e1) {
                            e1.printStackTrace();

                            View viewDialog = View.inflate(getApplicationContext(), R.layout.dialog_search_message, null);

                            Button btnExit = viewDialog.findViewById(R.id.btnExit);

                            final Dialog noSearchDlg = new Dialog(SearchActivity.this);

                            noSearchDlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                            noSearchDlg.setContentView(viewDialog);
                            noSearchDlg.show();

                            btnExit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    noSearchDlg.dismiss();
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();
                    }

                });

        queue.add(jsObjRequest);

        recyclerView = findViewById(R.id.grid_recyclerview);

        adapter = new SearchAdapter(R.layout.item_theme, SearchActivity.this, list);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
    }

}
