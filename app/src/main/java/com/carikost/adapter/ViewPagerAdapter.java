package com.carikost.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.carikost.R;

public class ViewPagerAdapter extends PagerAdapter {
    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    Context context;

    int sliderAllImage [] = {R.drawable.comfort,R.drawable.fast,R.drawable.secure};
    int sliderAllTitle [] = {R.string.slider_Title1,R.string.slider_Title2,R.string.slider_Title3};
    int sliderAllDescription [] = {R.string.slider_Description1,R.string.slider_Description2,R.string.slider_Description3};

    public int getCount() {
        return sliderAllTitle.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_screen,container,false);

        ImageView sliderImage = (ImageView) view.findViewById(R.id.slider_Image);
        TextView sliderTitle = (TextView) view.findViewById(R.id.slider_Title);
        TextView sliderDescription = (TextView) view.findViewById(R.id.slider_Description);

        sliderImage.setImageResource(sliderAllImage[position]);
        sliderTitle.setText(sliderAllTitle[position]);
        sliderDescription.setText(sliderAllDescription[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
