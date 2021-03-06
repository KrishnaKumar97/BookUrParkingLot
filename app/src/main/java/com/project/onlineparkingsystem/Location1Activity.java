package com.project.onlineparkingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.lang.Object;
import android.graphics.Color;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Location1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location1);
        TextView titleTxt=(TextView)findViewById(R.id.titleTxt);
        final GridView slotGridView=(GridView)findViewById(R.id.gridView);
        Button proceedBtn=(Button) findViewById(R.id.proceedBtn);
        //final ImageView previousSelectedItem = (ImageView) null;
        final SlotAdapter adapter = new SlotAdapter(this);

        slotGridView.setAdapter(adapter);

        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( AppData.getSelectedSlot() == -1){
                    // Display Error
                    AlertDialog alertDialog = new AlertDialog.Builder(Location1Activity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Please select a slot");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), UserDetailsActivity.class);
                    startActivity(intent);
                }
            }
        });

        slotGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(AppData.isSlotBooked(i+1)) {
                    return;
                }
                slotGridView.getSelectedItemPosition();
                ImageView iv = (ImageView) view;

                if(adapter.getPreviousItem() != null){
                    adapter.getPreviousItem().setImageResource(R.mipmap.blue_icon);
                }
                iv.setImageResource(R.mipmap.green_icon);
                adapter.setPreviousItem(iv);
                AppData.setSelectedSlot(i+1);
            }
        });

    }
}

