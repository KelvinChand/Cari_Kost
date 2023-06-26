package com.carikost.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carikost.R;
import com.carikost.adapter.ViewPagerAdapter;

public class NavigationActivity extends AppCompatActivity {

    ViewPager slideViewPager;
    LinearLayout dotIndicator;
    ViewPagerAdapter viewPagerAdapter;
    Button back_btn , skip_btn , next_btn;
    TextView[] dots;

    ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setDotIndicator(position);

            if(position > 0){
                back_btn.setVisibility(View.VISIBLE);
            }else{
                back_btn.setVisibility(View.INVISIBLE);
            }
            if(position == 2){
                next_btn.setText("Finish");
            }else{
                next_btn.setText("Next");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        back_btn = findViewById(R.id.back_btn);
        next_btn = findViewById(R.id.next_btn);
        skip_btn = findViewById(R.id.skip_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getItem(0) > 0){
                    slideViewPager.setCurrentItem(getItem(-1),true);
                }
            }
        });

            next_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getItem(0) < 2) {
                        slideViewPager.setCurrentItem(getItem(1), true);
                    } else {
                        Intent intent = new Intent(NavigationActivity.this, GetStartedActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });

        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this , GetStartedActivity.class);
                startActivity(intent);
                finish();
            }
        });

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    next_btn.performClick();
                }
            }, 3000);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    next_btn.performClick();
                }
            }, 6000);


        slideViewPager = (ViewPager) findViewById(R.id.slide_View);
        dotIndicator = (LinearLayout) findViewById(R.id.dot_Indicator);

        viewPagerAdapter = new ViewPagerAdapter(this);
        slideViewPager.setAdapter(viewPagerAdapter);

        setDotIndicator(0);
        slideViewPager.addOnPageChangeListener(viewPagerListener);
    }
    public void setDotIndicator(int position){
        dots = new TextView[3];
        dotIndicator.removeAllViews();

        for (int i = 0 ; i < dots.length ; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226", Html.FROM_HTML_MODE_LEGACY));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.grey,getApplicationContext().getTheme()));
            dotIndicator.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.dark_green,getApplicationContext().getTheme()));
    }
    private int getItem(int i){
        return slideViewPager.getCurrentItem()+ i;
    }
}