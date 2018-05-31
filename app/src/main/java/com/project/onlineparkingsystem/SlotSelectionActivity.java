package com.project.onlineparkingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageView;

public class SlotSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_grid_item);

        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        SlotAdapter imageAdapter = new SlotAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageResource(R.mipmap.red_icon);
    }
}
