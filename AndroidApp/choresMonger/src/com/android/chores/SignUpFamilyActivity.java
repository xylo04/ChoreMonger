package com.android.chores;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.choremonger.shared.Family;

public class SignUpFamilyActivity extends Activity implements OnClickListener {
	private static final int DATE_DIALOG_ID = 0;
	private static final int ALERT_DIALOG_ID = 1;

	private Time selectedDay = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up_family);

		// TODO: Check if the user is authenticated, then launch the Main menu
		// Activity

		// Handle Sign Up Family

		View pickDateButton = findViewById(R.id.Button_DOB);
		pickDateButton.setOnClickListener(this);
		View signUpFamilyButton = findViewById(R.id.Button_SignUpFamily);
		signUpFamilyButton.setOnClickListener(this);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Button_SignUpFamily:
			SignUpFamilyAccount();
			break;
		case R.id.Button_DOB:
			// Initialize the Date picker
			initDatePicker();
			break;
		}
	}

	// Handling Sign up a family account
	public void SignUpFamilyAccount() {
		// TODO
		EditText familyNameText = (EditText) findViewById(R.id.FamilyName);
		Family newFamily = FamilyImpl.createFamily(familyNameText.getText()
				.toString());

		EditText displayNameText = (EditText) findViewById(R.id.EditText_DisplayName);
		EditText emailText = (EditText) findViewById(R.id.EditText_Email);

		UserImpl newUser = (UserImpl) UserImpl.createUser();
		newUser.setName(displayNameText.getText().toString());
		newUser.setEmail(emailText.getText().toString());
		newUser.setDob(new Date(selectedDay.toMillis(false)));
		newUser.setFamily(newFamily);

		// Add new family member? Yes/No
		showDialog(ALERT_DIALOG_ID);
	}

	/**
	 * Initialize the Date picker
	 */
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
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker picker, int year,
								int month, int day) {
							SignUpFamilyActivity.this
									.dismissDialog(DATE_DIALOG_ID);
							selectedDay = new Time();
							selectedDay.set(day, month, year);
							TextView dobInfo = (TextView) findViewById(R.id.TextView_DOB_Info);
							dobInfo.setText(DateFormat.format("MMMM dd, yyyy",
									selectedDay.toMillis(true)));
						}
					}, 2000, 1, 1);
		case ALERT_DIALOG_ID:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Do you want to add members?").setTitle(
					"Add Members?");
			builder.setNegativeButton("No",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int whichButton) {
							SignUpFamilyActivity.this
									.dismissDialog(ALERT_DIALOG_ID);
							startActivity(new Intent(SignUpFamilyActivity.this,
									MainMenuActivity.class));
						}
					});
			builder.setPositiveButton("YES",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
								int whichButton) {
							SignUpFamilyActivity.this
									.dismissDialog(ALERT_DIALOG_ID);
							startActivity(new Intent(SignUpFamilyActivity.this,
									AddNewFamilyMember.class));
						}
					});
			return builder.create();

		default:
			return null;
		}
	}

	protected void onPrepareDialog(int id, Dialog dialog) {
		super.onPrepareDialog(id, dialog);
		switch (id) {
		case DATE_DIALOG_ID:
			DatePickerDialog datePicker = (DatePickerDialog) dialog;
			if (selectedDay != null) {
				datePicker.updateDate(selectedDay.year, selectedDay.month,
						selectedDay.monthDay);
			} else {
				Calendar cal = Calendar.getInstance();

				datePicker.updateDate(1997, cal.get(Calendar.MONTH),
						cal.get(Calendar.DAY_OF_MONTH));
			}
			break;
		case ALERT_DIALOG_ID:
			break;
		}
	}
}
