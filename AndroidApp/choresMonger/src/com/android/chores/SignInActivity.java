package com.android.chores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.choremonger.shared.User;

public class SignInActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	public static final String PREFS_NAME="USER_ID";
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        
        View signInButton = findViewById(R.id.Button_SignIn);
        View forgotPasswordButton = findViewById(R.id.Button_ForgotPassword);
        signInButton.setOnClickListener(this);
        forgotPasswordButton.setOnClickListener(this);
    }
	
    @Override
	public void onClick(View v) {
    	switch(v.getId()){
    		case R.id.Button_SignIn:
    		// handle the sign in process
    		signIn();
    		break;
    	case R.id.Button_ForgotPassword:
    		// handle forgot password
    		forgotMyPassword();
    		break;
    	}
	}
    public void signIn(){
    	// Check if the user name is existed, then store the user's ID in the shared preferences
    	String user_name=((EditText)((View)findViewById(R.id.EditText_UserNameSignIn))).getText().toString();
    	User current_user=UserImpl.getUserByName(user_name);
    	String user_id=current_user.getId();
        // store the User id in the shared prefs
    	if(user_id!=null && user_id!=""){
    		SharedPreferences sharedprefs = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
    		SharedPreferences.Editor editor = sharedprefs.edit();
    		editor.putString("USER_ID", user_id);
    		editor.commit();
    		startActivity(new Intent(this,MainMenuActivity.class));
    	}
    	//TODO: else display error msg
    }
    public void forgotMyPassword(){
    	startActivity(new Intent(this,LostPasswordActivity.class));
    }
}
