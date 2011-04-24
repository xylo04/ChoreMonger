package com.android.chores;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class AcceptInvitationActivity extends Activity implements OnClickListener {
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accept_invitation);
        View acceptInvitationButton = findViewById(R.id.Button_AcceptInvitation);
        View cancelInvitationButton = findViewById(R.id.Button_CancelInvitation);
        acceptInvitationButton.setOnClickListener(this);
        cancelInvitationButton.setOnClickListener(this);
    }
    @Override
	public void onClick(View v) {
    	switch(v.getId()){
    		case R.id.Button_AcceptInvitation:
		// handle accept Invitation
    			acceptInvitation();
		break;
    	case R.id.Button_CancelInvitation:
    		// handle cancel Invitation
    		cancelInvitation();
    	}
	}
    public void acceptInvitation(){
    	// TODO Check if the user is already existed in the database
    	startActivity(new Intent(this,SignUPFamilyMemberActivity.class));
    }
    public void cancelInvitation(){
    	startActivity(new Intent(this,WelcomeActivity.class));
    }

}
