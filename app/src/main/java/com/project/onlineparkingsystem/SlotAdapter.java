package com.project.onlineparkingsystem;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.util.HashMap;
import java.lang.Object;
import java.util.List;

import android.graphics.Color;

import org.json.JSONArray;

public class SlotAdapter extends BaseAdapter {
    private Context mContext;
    private int selectedPosition=-1;
    public ImageView previousItem=null;
    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.mipmap.blue_icon, R.mipmap.blue_icon,
            R.mipmap.blue_icon, R.mipmap.blue_icon,
            R.mipmap.blue_icon, R.mipmap.blue_icon,
            R.mipmap.blue_icon, R.mipmap.blue_icon,
            R.mipmap.blue_icon, R.mipmap.blue_icon

    };

    // Constructor
    public SlotAdapter(Context c){
        mContext = c;
        setInitialColors();
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public void getSelectedPosition(int position)
    {
        selectedPosition=position;
    }

    public void setPreviousItem(ImageView item) {
        previousItem=item;
    }

    public ImageView getPreviousItem() {
        return previousItem;
    }

    private void setInitialColors() {
        List bookedSlots = AppData.getBookedSlots();
        for(int ind=1; ind<=mThumbIds.length; ind++){
            if(bookedSlots.contains(ind)) {
                // Set color of booked slot
                mThumbIds[ind-1] = R.mipmap.red_icon;
            }
        }
        if(AppData.getSelectedSlot() != -1) {
            mThumbIds[AppData.getSelectedSlot()-1] = R.mipmap.green_icon;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.print(position);
        int selectedSlotPosition = AppData.getSelectedSlot() - 1;
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
        if(position == selectedSlotPosition) {
            setPreviousItem(imageView);
        }
        return imageView;
    }
}

