package com.android.chores;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.choremonger.shared.Reward;
import com.choremonger.shared.User;

public class RewardDetailsActivity extends Activity implements OnClickListener {
	private Reward reward;
	private String rewardID;
	private String user_id;
	private User current_user;
	private static final int ALERT_DIALOG_ID = 1;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward_details);
        SharedPreferences sharedprefs=getSharedPreferences(SignInActivity.PREFS_NAME, Context.MODE_PRIVATE);
        user_id=sharedprefs.getString("USER_ID", "");
        if(user_id!=null||user_id!="")
        	current_user=UserImpl.getUser(user_id);
        
        rewardID=getIntent().getStringExtra("rewardID");
        // if the calling activity is the history rewards then hide the redeem button
        	
        initalizeElements();
        
        
        View ButtonRedeemReward = findViewById(R.id.Button_Redeem_Reward);
        ButtonRedeemReward.setOnClickListener(this);
        
        // if the calling activity is the history rewards then hide the redeem button
        if(getIntent().getStringExtra("history")!=null)
        	ButtonRedeemReward.setVisibility(View.INVISIBLE);
    }
    
        public void initalizeElements(){
        	reward=RewardImpl.getReward(rewardID);
        	((TextView)(findViewById(R.id.txviewRewardTitleDetailsVal))).setText(reward.getName(), TextView.BufferType.EDITABLE);
        	((TextView)(findViewById(R.id.txviewRewardDescrDetailsVal))).setText(reward.getDescription(), TextView.BufferType.EDITABLE);
        	((TextView)(findViewById(R.id.txviewRewardPointsDetailsVal))).setText(Double.toString(reward.getPointValue()), TextView.BufferType.EDITABLE);
        	TextView txvwIsOneTime=(TextView)(findViewById(R.id.txviewRewardISOneTime));
        	if(reward.isOneTime())
        		txvwIsOneTime.setText("Yes");
        	else
        		txvwIsOneTime.setText("No");
        }
    	
    
    
    @Override
	public void onClick(View v) {
    	switch(v.getId()){
    	case R.id.Button_Redeem_Reward:
    		redeemReward();
    		break;
    	}
    }
    
    public void redeemReward(){
    	if(current_user.getRewardPoints()>=reward.getPointValue())
    	{
    		((RewardImpl)(reward)).addUser(user_id);
    		//reward=RewardImpl.updateReward(reward);
    		/*if(reward!=null)
    			{
    			// deduct the reward's points and update user
    				current_user.subtractRewardPoints(reward.getPointValue());
    			}*/
    	}
    	// TODO: Display error message
    	else
    		showDialog(ALERT_DIALOG_ID);
    }
    
    protected Dialog onCreateDialog(int id) {
    	switch(id){
    	case ALERT_DIALOG_ID:
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		builder.setMessage("Sorry, you don't have enough points to redeem this reward!")
    	       .setCancelable(false)
    	       .setPositiveButton("OK!", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   dialog.cancel();
    	           }
    	       });
    		return builder.create();
    		default: 
    			return null;
    	}
    }
}
