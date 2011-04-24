package com.android.chores;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.choremonger.shared.Reward;

public class EditRewardActivity extends Activity implements OnClickListener  {
	private Reward reward;
	private String rewardID;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_reward);
        
        rewardID=getIntent().getStringExtra("rewardID");
        initalizeElements();
        
        
        View ButtonUpdateReward= findViewById(R.id.Button_Update_Reward);
        View ButtonDeleteReward = findViewById(R.id.Button_Delete_Reward);
        ButtonUpdateReward.setOnClickListener(this);
        ButtonDeleteReward.setOnClickListener(this);
    }
        @Override
    	public void onClick(View v) {
        	switch(v.getId()){
        		case R.id.Button_Update_Reward:
        		// update reward
        			updateReward();
        		break;
        		case R.id.Button_Delete_Reward:
            		// delete reward
        			deleteReward();
            		break;
        	}
    	}
        
        public void initalizeElements(){
        	reward=RewardImpl.getReward(rewardID);
        	((EditText)(findViewById(R.id.editTxtRewardTitleVal))).setText(reward.getName(), TextView.BufferType.EDITABLE);
        	((EditText)(findViewById(R.id.editxtRewardDescrVal))).setText(reward.getDescription(), TextView.BufferType.EDITABLE);
        	((EditText)(findViewById(R.id.txviewRewardPointsVal))).setText(Double.toString(reward.getPointValue()), TextView.BufferType.EDITABLE);
        	CheckBox chkBox=(CheckBox)(findViewById(R.id.checkboxUpdateOneTime));
        	if(reward.isOneTime())
        		chkBox.setChecked(true);
        	else 
        		chkBox.setChecked(false);
        }
        
        public void updateReward(){
        	reward.setName(((EditText)findViewById(R.id.editTxtRewardTitleVal)).getText().toString());
        	reward.setDescription(((EditText)findViewById(R.id.editxtRewardDescrVal)).getText().toString());
        	reward.setPointValue(Double.parseDouble(((EditText)findViewById(R.id.txviewRewardPointsVal)).getText().toString()));
        	CheckBox chkBox=(CheckBox)(findViewById(R.id.checkboxUpdateOneTime));
        	if(chkBox.isChecked())
        		reward.setOneTime(true);
        	else
        		reward.setOneTime(false);
        	
        	reward=RewardImpl.updateReward(reward);
        	
        }
        public void deleteReward(){
        	RewardImpl.deleteReward(reward.getId());
        }
}