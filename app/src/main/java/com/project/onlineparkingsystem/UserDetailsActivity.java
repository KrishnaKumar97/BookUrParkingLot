package com.project.onlineparkingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.Calendar;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.widget.TimePicker;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class UserDetailsActivity extends AppCompatActivity {
    EditText nameEditTxt ;
    EditText  vehnoEditTxt;
    TextView amountdisplayTxt;
    private static Button starttimeBtn,endtimeBtn;
    private static TextView starttimeTxt,endtimeTxt;
    private final static int Time_id = 0;
    private final  static int EndTime_id=1;
    private String dialog;
    boolean startBtn=false;
    boolean endBtn=false;
    String  time1,endtime;
    float total_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdetails);

        TextView titleTxt=(TextView)findViewById(R.id.titleTxt);
        TextView nameTxt =(TextView)findViewById(R.id.nameTxt);
        nameEditTxt=(EditText)findViewById(R.id.nameEditTxt);
        TextView vehnoTxtTxt=(TextView)findViewById(R.id.vehnoTxt);
        vehnoEditTxt=(EditText) findViewById(R.id.vehnoEditTxt);
        TextView timeslotTxt=(TextView)findViewById(R.id.timeslotTxt);
        TextView toTxt=(TextView)findViewById(R.id.toTxt);
        starttimeTxt=(TextView)findViewById(R.id.fromTimeTxt);
        endtimeTxt=(TextView)findViewById(R.id.toTimeTxt);
        starttimeBtn=(Button)findViewById(R.id.starttimeBtn);
        endtimeBtn=(Button)findViewById(R.id.endtimeBtn);
        Button bookslotBtn=(Button)findViewById(R.id.bookslotBtn);

        starttimeBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //startBtn=true;
                // Show time dialog
                dialog="Time_id";
                showDialog(Time_id);
            }
        });

        endtimeBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //  endBtn=true;
                // Show time dialog
                dialog = "EndTime_id";
                showDialog(EndTime_id);
            }
        });

        bookslotBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String[] startTimeArr = starttimeTxt.getText().toString().split(":");
                float startTime = Integer.parseInt(startTimeArr[0]) + Integer.parseInt(startTimeArr[1])/60;
                String[] endTimeArr = endtimeTxt.getText().toString().split(":");
                float endTime = Integer.parseInt(endTimeArr[0]) + Integer.parseInt(endTimeArr[1])/60;
                if( nameEditTxt.getText().toString().length() == 0 ){
                    nameEditTxt.setError( "Please enter your name" );
                }
                 else if( vehnoEditTxt.getText().toString().length() == 0 ){
                    vehnoEditTxt.setError( "Please enter your vehicle no" );
                }
                else if(starttimeTxt.getText()=="00:00" || endtimeTxt.getText()=="00:00"){
                    endtimeTxt.setError("Please select the time");
                }
                else if(startTime>endTime){
                    endtimeTxt.setError("Please select valid time");
                }
                else{
                    total_time = endTime - startTime;
                    int totalAmt = Math.round(total_time * 100);
                   // amountdisplayTxt.setText(Integer.toString(totalAmt));
                    Intent intent = new Intent(getApplicationContext(), PaymentDetailsActivity.class);
                    intent.putExtra("amount", totalAmt);
                    startActivity(intent);
                }
            }
        });
    }

    TimePickerDialog.OnTimeSetListener time_listener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            time1 = String.valueOf(hour) + ":" + String.valueOf(minute);
            endtime = String.valueOf(hour) + ":" + String.valueOf(minute);
            if ( dialog == "Time_id" )
            {
                starttimeTxt.setText(time1);
            }
                else if(dialog=="EndTime_id") {
                endtimeTxt.setText(endtime);
//                int s_time=Integer.parseInt(time1);
//                int e_time=Integer.parseInt(endtime);
//                CalculateTime(s_time, e_time);
            }
            }
//else if ( dialog == DIALOG_TO_TIME_PICKER )
//            {
//            // store the data in one string and set it to text
//            if(startBtn==true) {
//                String time1 = String.valueOf(hour) + ":" + String.valueOf(minute);
//                starttimeTxt.setText(time1);
//            }
//            else if(endBtn==true){
//                String time2 = String.valueOf(hour) + ":" + String.valueOf(minute);
//                endtimeTxt.setText(time2);
//            }
            };

    protected Dialog onCreateDialog(int id) {

        // Get the calander
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        switch (id) {
            case Time_id:
                // Open the timepicker dialog
                return new TimePickerDialog(UserDetailsActivity.this, time_listener, hour,
                        minute, false);
            case EndTime_id:
                return new TimePickerDialog(UserDetailsActivity.this, time_listener, hour,
                        minute, false);

        }
            return null;
        }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Location2Activity.class);
        startActivity(intent);
    }
}
