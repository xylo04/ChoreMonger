package com.android.chores;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class CreateNewRewardActivity extends Activity implements OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_reward);
        
        View ButtonCreateReward= findViewById(R.id.Button_Create_New_Reward);
        View ButtonCancelReward = findViewById(R.id.Button_Cancel_Create_Reward);
        ButtonCreateReward.setOnClickListener(this);
        ButtonCancelReward.setOnClickListener(this);
    }
        @Override
    	public void onClick(View v) {
        	switch(v.getId()){
        		case R.id.Button_Create_New_Reward:
        		// create reward
        		break;
        	case R.id.Button_Cancel_Create_Reward:
        		startActivity(new Intent(CreateNewRewardActivity.this,RewardManagementActivity.class));
        		break;
        	}
    	}
}
