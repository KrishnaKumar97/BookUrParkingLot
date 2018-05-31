package com.project.onlineparkingsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
       final EditText editText=(EditText)findViewById(R.id.editText);

        final Button submitBtn=(Button)findViewById(R.id.sumbitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().length() == 0 ) {
                    AlertDialog alertDialog = new AlertDialog.Builder(FeedbackActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Please give your feedback");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), FinalActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
