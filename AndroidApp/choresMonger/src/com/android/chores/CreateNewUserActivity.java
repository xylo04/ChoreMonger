package com.android.chores;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.choremonger.shared.User;

public class CreateNewUserActivity extends Activity implements OnClickListener {
	private static final int DATE_DIALOG_ID=0;
	private Time selectedDay  = null;
	private static String TAG=CreateNewUserActivity.class.getName();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_user);
        View pickDateButton = findViewById(R.id.Button_DOB);
        pickDateButton.setOnClickListener(this);
        View ButtonCreateUser= findViewById(R.id.Button_Create_User);
        ButtonCreateUser.setOnClickListener(this);
    }
        @Override
    	public void onClick(View v) {
        	switch(v.getId()){
        		case R.id.Button_Create_User:
        			try{
        			// create user 
        			double pointsValue=(Double.parseDouble(((EditText)findViewById(R.id.txviewCreateUserPointsVal)).getText().toString()));
        			String userName=((EditText)findViewById(R.id.editTxtCreateUserTitleVal)).getText().toString();
        			String email=((EditText)findViewById(R.id.editxtCreateUserEmailVal)).getText().toString();
        			
        			createUser(email, userName, pointsValue);
        			}
        			catch(Exception exception){
        				Log.e(TAG,exception.getMessage());
        			}
        			break;
        		case R.id.Button_DOB:
        			initDatePicker();
        			break;
        	}
    	}
        
        private void initDatePicker() {
            TextView dobInfo = (TextView) findViewById(R.id.TextView_DOB_Info);
            dobInfo.setText("Date not set");
            // Handle date picking dialog
            Button pickDateButton = (Button) findViewById(R.id.Button_DOB);
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
                        	CreateNewUserActivity.this.dismissDialog(DATE_DIALOG_ID);
            				selectedDay = new Time();
            				selectedDay.set(day, month, year);
            				TextView dobInfo = (TextView) findViewById(R.id.TextView_DOB_Info);
            				dobInfo.setText(DateFormat.format("MMMM dd, yyyy", selectedDay.toMillis(true)));
            			}
            		}, 2000, 1, 1);
        	default:
        		return null;
        	}
        }
        
        public void createUser(String email,String userName,double pointsValue){
        	User createdUser=UserImpl.createUser();
        	createdUser.setEmail(email);
        	createdUser.setName(userName);
        	createdUser.addRewardPoints(pointsValue);
        	Date DoB = new Date(selectedDay.toMillis(true));
        	createdUser.setDob(DoB);
        	if (createdUser==null){
				Log.e(TAG,"Oooops! No user created!");
				System.exit(1);
			}
        	startActivity(new Intent(CreateNewUserActivity.this,UserManagementActivity.class));
			Log.d(TAG,"User Created");
			Log.d(TAG,createdUser.getName());
			Log.d(TAG,createdUser.getId());
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
