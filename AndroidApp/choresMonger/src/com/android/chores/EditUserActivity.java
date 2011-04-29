package com.android.chores;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.choremonger.shared.User;

public class EditUserActivity extends Activity implements OnClickListener  {
	private static final int DATE_DIALOG_ID=0;
	private static final int PROGRESS_DIALOG_ID=1;
	private User user;
	private Time selectedDay = null;
	private String userID;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);
        
        userID=getIntent().getStringExtra("userID");
        initalizeElements();
        
        
        View ButtonUpdateUser= findViewById(R.id.Button_Update_User);
        View ButtonDeleteUser = findViewById(R.id.Button_Delete_User);
        ButtonUpdateUser.setOnClickListener(this);
        ButtonDeleteUser.setOnClickListener(this);
    }
        @Override
    	public void onClick(View v) {
        	switch(v.getId()){
        		case R.id.Button_Update_User:
        		// update user
                	showDialog(PROGRESS_DIALOG_ID);
        			updateUser();
        		break;
        		case R.id.Button_Delete_User:
            		// delete user
        			deleteUser();
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
        
        public void initalizeElements(){
        	user=UserImpl.getUser(userID);
        	((EditText)(findViewById(R.id.editTxtUserNameVal))).setText(user.getName(), TextView.BufferType.EDITABLE);
        	((EditText)(findViewById(R.id.editxtUserEmailVal))).setText(user.getEmail(), TextView.BufferType.EDITABLE);
        	((EditText)(findViewById(R.id.txviewUserPointsVal))).setText(Double.toString(user.getRewardPoints()), TextView.BufferType.EDITABLE);
        	TextView dobInfo = (TextView) findViewById(R.id.TextView_DOB_Info);
			dobInfo.setText(DateFormat.format("MMMM dd, yyyy", user.getDob().getTime()));
 
        }
        
        public void updateUser(){
        	user.setName(((EditText)findViewById(R.id.editTxtUserNameVal)).getText().toString());
        	user.setEmail(((EditText)findViewById(R.id.editxtUserEmailVal)).getText().toString());
        	user.subtractRewardPoints(user.getRewardPoints());
        	user.addRewardPoints(Double.valueOf(((EditText)findViewById(R.id.txviewUserPointsVal)).getText().toString()));
        	Date DoB = new Date(selectedDay.toMillis(true));
        	user.setDob(DoB);
        	EditUserActivity.this.finish();
        }
        public void deleteUser(){     	
        	UserImpl.deleteUser(user.getId());
        }
        
        protected Dialog onCreateDialog(int id) {
        	switch(id){
        	case PROGRESS_DIALOG_ID:
        		return ProgressDialog.show(EditUserActivity.this, "", 
                        "Updating. Please wait...", true);
        	case DATE_DIALOG_ID:    	
        		return
            	new DatePickerDialog(this,
            		new DatePickerDialog.OnDateSetListener() {
            			@Override
            			public void onDateSet(DatePicker picker, int year, int month, int day) {
                        	EditUserActivity.this.dismissDialog(DATE_DIALOG_ID);
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