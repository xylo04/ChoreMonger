package com.android.chores;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class SignInActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
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
    	//TODO
    }
    public void forgotMyPassword(){
    	startActivity(new Intent(this,LostPasswordActivity.class));
    }
}
