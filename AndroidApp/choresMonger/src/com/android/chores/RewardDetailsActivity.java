package com.android.chores;

import android.app.Activity;
import android.content.Context;
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
    		((RewardImpl)(reward)).add
    		reward=RewardImpl.updateReward(reward);
    		reward=RewardImpl.updateReward(reward);
    	}
    	// TODO: Display error message
    	else
    		return;
    }
}
