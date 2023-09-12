package com.example.tablayoutapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.TabLayout);
        viewPager = (ViewPager) findViewById(R.id.ViewPager);

        ArrayList<String> arrayList = new ArrayList<String>(0);

        arrayList.add("Basic");
        arrayList.add("Advance");
        arrayList.add("Pro");

        tabLayout.setupWithViewPager(viewPager);
        prepareViewPager(viewPager,arrayList);

    }

    private void prepareViewPager(ViewPager viewPager,ArrayList<String> arrayList)
    {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());

        fragment_main mainFragment = new fragment_main();

        for (int i=0 ; i<arrayList.size() ; i++)
        {
            Bundle bundle = new Bundle();

            bundle.putString("title", arrayList.get(i));

            mainFragment.setArguments(bundle);

            adapter.addFragment(mainFragment , arrayList.get(i));
            mainFragment = new fragment_main();

        }

        viewPager.setAdapter(adapter);

    }

    public class MainAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragmentArrayList= new ArrayList<>();
        ArrayList<String> stringArrayList=new ArrayList<>();

        int image[] = {R.drawable.basic,R.drawable.advance,R.drawable.pro};
        public MainAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        public void addFragment(Fragment fragment, String s) {

            fragmentArrayList.add(fragment);
            stringArrayList.add(s);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @NonNull
        public CharSequence getPageTitle(int position)
        {
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),image[position]);

            drawable.setBounds(0,1,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());

            SpannableString spannableString = new SpannableString(""+ stringArrayList.get(position));

            ImageSpan imageSpan = new ImageSpan(drawable,ImageSpan.ALIGN_BOTTOM);

            spannableString.setSpan(imageSpan, 0 , 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            return spannableString;
        }

    }

}