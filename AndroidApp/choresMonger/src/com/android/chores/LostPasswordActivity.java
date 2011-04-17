package com.android.chores;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class LostPasswordActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lost_password);
        
        View submitforgottonPWButton = findViewById(R.id.Button_SubmitforgottonPW);
        submitforgottonPWButton.setOnClickListener(this);
    }
    @Override
	public void onClick(View v) {
    	switch(v.getId()){
    	case R.id.Button_SubmitforgottonPW:
    		EditText editText_Email=(EditText)findViewById(R.id.EditText_EmailAddressLostPW);
    		String emailAddress=editText_Email.getText().toString();
    		retrievePassword(emailAddress);
    		break;
    	}
    }
    	public void retrievePassword(String emailAddress){
    		// TODO
    	}
}
