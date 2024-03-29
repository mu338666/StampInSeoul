package com.example.mu338.stampinseoul;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

    // == 각 튜토리얼 Fragment 관리하는 뷰 페이저 어댑터 클래스.

public class tutorialViewPagerAdapter extends FragmentPagerAdapter {

    public tutorialViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // 뷰페이저에서 프래그먼트 교체를 보여주는 역할.
    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:

                return tutorial_fragment1.newInstance();

            case 1:

                return tutorial_fragment2.newInstance();

            case 2:

                return tutorial_fragment3.newInstance();

            case 3:

                return tutorial_fragment4.newInstance();

            case 4:

                return tutorial_fragment5.newInstance();

            case 5:

                return tutorial_fragment6.newInstance();

            case 6:

                return tutorial_fragment7.newInstance();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 7;
    }


}
