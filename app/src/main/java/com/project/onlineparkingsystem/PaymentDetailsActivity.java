package com.project.onlineparkingsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.Arrays;
import android.widget.ArrayAdapter;
import java.util.List;
import java.util.StringJoiner;

public class PaymentDetailsActivity extends Activity implements
        AdapterView.OnItemSelectedListener {

//    String[] month = { "January", "February", "March", "April", "May",
//    "June","July","August","September","October","November","December"};
//
//    String[] year={"2018","2019","2020","2021","2022","2023","2024","2025","2026","2027"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_details);

        Intent intent = getIntent();
        int amount = intent.getIntExtra("amount", 0);

        TextView amountTxt=(TextView)findViewById(R.id.txtAmount);
        TextView tileTxt=(TextView)findViewById(R.id.titleTxt);
        final RadioButton radioBtn1=(RadioButton) findViewById(R.id.radioButton1);
        final RadioButton radioBtn2=(RadioButton) findViewById(R.id.radioButton2);
        TextView cardnoTxt=(TextView)findViewById(R.id.txtcardno);
        final EditText cardnoeditTxt=(EditText) findViewById(R.id.cardnoedittxt);
        TextView cvvTxt=(TextView)findViewById(R.id.txtcvv);
        final EditText cvveditTxt=(EditText) findViewById(R.id.cvvedittxt);
        TextView cardnameTxt=(TextView)findViewById(R.id.txtcardname);
        final EditText cardnameEditTxt=(EditText)findViewById(R.id.cardnoedittxt);
        TextView expmonthTxt=(TextView) findViewById(R.id.txtexpirymonth);
        TextView expyearTxt=(TextView)findViewById(R.id.txtexpiryyear);
        Spinner expmonthSpinner=(Spinner) findViewById(R.id.monthspinner);
        Spinner expyearSpinner=(Spinner)findViewById(R.id.yearspinner);
        Button payBtn=(Button)findViewById(R.id.payBtn);
        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radiogrp);
        final LinearLayout paymentdetailsLayout=(LinearLayout)findViewById(R.id.paymentdetailsLayout);


        paymentdetailsLayout.setVisibility(View.INVISIBLE);

//        expmonthSpinner.setOnItemSelectedListener(this);
//        ArrayAdapter monthAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,month);
//
//        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        expmonthSpinner.setAdapter(monthAdapter);
//
//        expyearSpinner.setOnItemSelectedListener(this);
//        expmonthSpinner.setOnItemSelectedListener(this);
//        ArrayAdapter yearAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,year);
//
//        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        expmonthSpinner.setAdapter(yearAdapter);

        ArrayAdapter<CharSequence> adapterMonth;
        ArrayAdapter<CharSequence> adapterYear;

        String[] MonthArr = {"January", "February","March","April","May","June","July","August","September","October","November","December"};
        String[] YearArr = {"2018","2019","2020","2021","2022","2023","2024","2025","2026","2027"};

        Spinner monthDrp =(Spinner)findViewById(R.id.monthspinner);
        Spinner yearDrp    =(Spinner)findViewById(R.id.yearspinner);

        adapterMonth =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,MonthArr);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthDrp.setAdapter(adapterMonth);

        adapterYear=     new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,YearArr);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearDrp.setAdapter(adapterYear);
        amountTxt.setText("Amount payable is " + Integer.toString(amount));
        String selectedAge  = monthDrp.getSelectedItem().toString();
        String selectedSex  = yearDrp.getSelectedItem().toString();
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioBtn1.isChecked()) {
                    paymentdetailsLayout.setVisibility(View.VISIBLE);
                } else if (radioBtn2.isChecked()) {
                    paymentdetailsLayout.setVisibility(View.VISIBLE);
                }
            }
        });

              payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save to shared preferences
                AppData.bookSlot(AppData.getSelectedSlot());
                Context mContext = getApplicationContext();
                SharedPreferences.Editor editor = mContext.getSharedPreferences("bookedSlots", Context.MODE_PRIVATE).edit();
                String serializedData = "";
                List bookedSlots = AppData.getBookedSlots();
                for(int j=1; j<=10; j++) {
                    if(bookedSlots.contains(j)){
                        if(serializedData != ""){
                            serializedData += ",";
                        }
                        serializedData += Integer.toString(j);
                    }
                }

                editor.putString(AppData.getSlotName(), serializedData);
                editor.commit();

                if( cardnoeditTxt.getText().toString().length() == 0 ){
                    cardnoeditTxt.setError( "Please enter card number" );
                }
                else if(cardnoeditTxt.getText().toString().length()<16 || cardnameEditTxt.getText().toString().length()>16){
                    cardnoeditTxt.setError("Please enter valid card number");
                }
                else if( cvveditTxt.getText().toString().length() == 0 ){
                    cvveditTxt.setError( "Please enter cvv" );
                }
                else if(cvveditTxt.getText().toString().length()<3){
                    cvveditTxt.setError("Please enter valid cvv");
                }
                else if(cardnameEditTxt.getText().toString().length()==0){
                    cardnameEditTxt.setError("Please enter your name on card");
                }
                else {
                    AppData.bookSlot(AppData.getSelectedSlot());
                    Intent intent = new Intent(getApplicationContext(), FeedbackActivity.class);
                    startActivity(intent);
                }
            }

        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
