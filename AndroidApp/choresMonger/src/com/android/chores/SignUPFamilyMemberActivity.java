package com.android.chores;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class SignUPFamilyMemberActivity extends Activity implements OnClickListener {
	private static final int DATE_DIALOG_ID=0;
    private Time selectedDay  = null;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_new_family_member);


	}
	
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.Button_SignUp_Family_Member:
			SignUpFamilyMemberAccount();
			break;
		case R.id.Button_DOB_Family_Member:
	        // Initialize the Date picker
	        initDatePicker();
	        break;
		}
	}
	
	public void SignUpFamilyMemberAccount(){
	}
	
	/**
     * Initialize the Date picker
     */
    private void initDatePicker() {
        TextView dobInfo = (TextView) findViewById(R.id.TextView_DOB_Info_Family_Member);
        dobInfo.setText("Date not set");
        // Handle date picking dialog
        Button pickDateButton = (Button) findViewById(R.id.Button_DOB_Family_Member);
        pickDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	showDialog(DATE_DIALOG_ID);
            }
        });
    }
    protected Dialog onCreateDialog(int id) {
    	switch(id){
    	case DATE_DIALOG_ID:
    	
    		return
        	new DatePickerDialog(this,
        		new DatePickerDialog.OnDateSetListener() {
        			@Override
        			public void onDateSet(DatePicker picker, int year, int month, int day) {
                    	SignUPFamilyMemberActivity.this.dismissDialog(DATE_DIALOG_ID);
        				selectedDay = new Time();
        				selectedDay.set(day, month, year);
        				TextView dobInfo = (TextView) findViewById(R.id.TextView_DOB_Info_Family_Member);
        				dobInfo.setText(DateFormat.format("MMMM dd, yyyy", selectedDay.toMillis(true)));
        			}
        		}, 2000, 1, 1);
    	
    	default:
    		return null;
    	}
    }
    
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
    	switch (id) {
    	    case DATE_DIALOG_ID:
    	        DatePickerDialog datePicker = (DatePickerDialog)dialog;
    	        if (selectedDay != null) {
    	        	datePicker.updateDate(selectedDay.year, selectedDay.month, selectedDay.monthDay);
    	        } else {
    	        	Calendar cal = Calendar.getInstance();
    	        	
    	        	datePicker.updateDate(1997, cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    	        }
    	        break;
    	}
    }
}