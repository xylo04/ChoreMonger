package com.android.chores;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.choremonger.shared.User;

public class SignInActivity extends Activity implements OnClickListener {
	public static final String PREFS_NAME="USER_ID";
	private static final int ALERT_DIALOG_ID = 1;
    
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
        // store the User id in the shared prefs
    	if(current_user!=null){
    		String user_id=current_user.getId();
    		SharedPreferences sharedprefs = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
    		SharedPreferences.Editor editor = sharedprefs.edit();
    		editor.putString("USER_ID", user_id);
    		editor.commit();
    		Intent signInIntent=new Intent(this,MainMenuActivity.class);
    		signInIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
    		startActivity(signInIntent);
    	}
    	//TODO: else display error msg
    	else{
    		showDialog(ALERT_DIALOG_ID);
    	}
    }
    
    protected Dialog onCreateDialog(int id) {
    	switch(id){
    	case ALERT_DIALOG_ID:
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		builder.setMessage("Sorry, the user name doesn't exist!")
    	       .setCancelable(false)
    	       .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   dialog.cancel();
    	           }
    	       });
    		return builder.create();
    	
    	default:
    		return null;
    	}
    }
    
    
    public void forgotMyPassword(){
		Intent forgotPWIntent=new Intent(this,LostPasswordActivity.class);
		forgotPWIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		startActivity(forgotPWIntent);
    }
}
