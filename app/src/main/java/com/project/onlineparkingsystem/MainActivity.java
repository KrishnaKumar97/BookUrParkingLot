package com.project.onlineparkingsystem;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Intent;
import android.view.View.OnClickListener;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

;    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView locationTxt = (TextView) findViewById(R.id.locationtext);
        TextView location1Txt = (TextView) findViewById(R.id.txtBrigadeRoad);
        TextView location1selectTxt = (TextView) findViewById(R.id.selectBrigadeRoad);
        TextView location2Txt = (TextView) findViewById(R.id.txtForumMall);
        TextView location2selectTxt = (TextView) findViewById(R.id.selectForumMall);
//        Button resetbtn=(Button)findViewById(R.id.resetBtn);
//        resetbtn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Context mContext = getApplicationContext();
//                SharedPreferences.Editor editor = mContext.getSharedPreferences("bookedSlots", Context.MODE_PRIVATE).edit();
//                String serializedData = null;
//                editor.putString("forumBookedSlots", serializedData);
//                editor.putString("brigadeBookedSlots", serializedData);
//                editor.commit();
//        }
//    });
        location1selectTxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String slotName = "brigadeBookedSlots";
                AppData.setSlotName(slotName);
                Context mContext = getApplicationContext();
                SharedPreferences sharedPref = mContext.getSharedPreferences("bookedSlots", Context.MODE_PRIVATE);
                String rawBookedSlots = sharedPref.getString(slotName, null);
                List<Integer> slots = new ArrayList<Integer>();
                if(rawBookedSlots != null) {
                    String[] sample = rawBookedSlots.split(",");
                    for(int i=0;i<sample.length;i++){
                        slots.add(Integer.parseInt(sample[i]));
                    }
                }
                AppData.setBookedSlots(slots);
                AppData.setSelectedSlot(-1);
                Intent intent = new Intent(getApplicationContext(), Location1Activity.class);
                startActivity(intent);
            }
        });

        location2selectTxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String slotName = "forumBookedSlots";
                AppData.setSlotName(slotName);
                Context mContext = getApplicationContext();
                SharedPreferences sharedPref = mContext.getSharedPreferences("bookedSlots", Context.MODE_PRIVATE);
                String rawBookedSlots = sharedPref.getString(slotName, null);
                List<Integer> slots = new ArrayList<Integer>();
                if(rawBookedSlots != null) {
                    String[] sample = rawBookedSlots.split(",");
                    for(int i=0;i<sample.length;i++){
                        slots.add(Integer.parseInt(sample[i]));
                    }
                }
                AppData.setBookedSlots(slots);
                AppData.setSelectedSlot(-1);
                Intent intent = new Intent(getApplicationContext(), Location2Activity.class);
                startActivity(intent);
            }
        });
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
