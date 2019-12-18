package com.example.mu338.stampinseoul;

import android.view.View;

    // 인위적으로 만든 리사이클러뷰터 클릭 리스너 인터페이스.

public interface ClickListener {

    void onClick(View view, int position);

    void onLongClick(View view, int position);
}


