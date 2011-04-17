package com.android.chores;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.util.Log;

public class AddNewFamilyMember extends Activity  implements OnClickListener {
	
	/** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_family_member);
        View sendInvitationButton = findViewById(R.id.Button_sendInvitation);
        View cancelInvitationButton = findViewById(R.id.Button_CancelInvitation_addMember);
        sendInvitationButton.setOnClickListener(this);
        cancelInvitationButton.setOnClickListener(this);
    }
    
    @Override
	public void onClick(View v) {
    	switch(v.getId()){
    		case R.id.Button_sendInvitation:
		// handle accept Invitation
    			String emailAddress = ((EditText)findViewById(R.id.EditText_Email_addMember)).toString();
    			sendInvitationTo(emailAddress);
		break;
    	case R.id.Button_CancelInvitation_addMember:
    		// handle cancel Invitation
    		cancelInvitation();
    	}
	}
    
    public void sendInvitationTo(String toEmailAddress){
    	try{
    		
    	
    	String activationCode=generateActivationCode();
    	StringBuffer messageContent=new StringBuffer();
    	messageContent.append("Chore Monger:\n");
    	messageContent.append("You have been invited by");
    	messageContent.append("User Name");
    	messageContent.append(". To join his/her group in the Chore Monger, an android Application.\n");
    	messageContent.append("Downlaod the ChoreMonger application from Android Market and enter the following activation code:\n");
    	messageContent.append("Activation Code:");
    	messageContent.append(activationCode);
    	messageContent.append("Thank you! Chore Monger team.\n");
    	 
    	// Make HTTP Request to send email
    	//.sendEmailTo(toEmailAddress,"Invitation to Chore Monger" ,messageContent.toString());
    	}
    	catch (Exception exception){
    		Log.e("SendMail", exception.getMessage(), exception); 
    	}
    	// TODO send invitation code to the member
    	/*final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
    	emailIntent.setType("plain/text");// for testing from the emulator
    	//emailIntent.setType("message/rfc822") ; // from real device
    	emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, toEmailAddress);
    	emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Chore Monger Invitation");
    	emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,messageContent.toString());
    	AddNewFamilyMember.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    	*/
    }
    
    public String generateActivationCode(){
    	return new ActivationCodeGenerator().generateCode();
    }
    
    public void cancelInvitation(){
    	startActivity(new Intent(this,WelcomeActivity.class));
    }

}
