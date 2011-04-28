package com.android.chores;

import com.choremonger.shared.Reward;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class RewardDetailsActivity extends Activity implements OnClickListener {
	private Reward reward;
	private String rewardID;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward_details);
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
    		break;
    	}
    }
}
