package com.android.chores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class WelcomeActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
		SharedPreferences sharedprefs=getSharedPreferences(SignInActivity.PREFS_NAME, Context.MODE_PRIVATE);
		if(sharedprefs.getString("USER_ID", null)!=null)	{
			startActivity(new Intent(this,WelcomeActivity.class));
		}
		else{
				setContentView(R.layout.welcome);
				View SignUpFamily_Button = findViewById(R.id.SignUpFamilyButton);
				View SignIn_Button = findViewById(R.id.SignInButton);
				View AcceptInvitation_Button = findViewById(R.id.AcceptInvitationButton);
				SignUpFamily_Button.setOnClickListener(this);
				SignIn_Button.setOnClickListener(this);
				AcceptInvitation_Button.setOnClickListener(this);
		}
    }
    
    @Override
    /*protected void onResume(){
    	super.onResume();
        // if the user has already signed in, ignore this activity and go to the main menu activity
		SharedPreferences sharedprefs=getSharedPreferences(SignInActivity.PREFS_NAME, Context.MODE_PRIVATE);
		
		if(sharedprefs.getString("USER_ID", "")!=null)	
			startActivity(new Intent(this,MainMenuActivity.class));
    }*/
    

    public void onClick(View v) {
    	switch (v.getId()){
    		case R.id.SignUpFamilyButton:
    			startActivity(new Intent(this,SignUpFamilyActivity.class));
    			break;
    		case R.id.SignInButton:
    			startActivity(new Intent(this,SignInActivity.class));
    			break;
    		case R.id.AcceptInvitationButton:
    			startActivity(new Intent(this,AcceptInvitationActivity.class));
    			break;
    	}
    }
}
