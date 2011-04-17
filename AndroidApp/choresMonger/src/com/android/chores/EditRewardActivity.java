package com.android.chores;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class EditRewardActivity extends Activity implements OnClickListener  {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_reward);
        
        View ButtonUpdateReward= findViewById(R.id.Button_Update_Reward);
        View ButtonCancelReward = findViewById(R.id.Button_Cancel_Edit_Reward);
        View ButtonDeleteReward = findViewById(R.id.Button_Delete_Reward);
        ButtonUpdateReward.setOnClickListener(this);
        ButtonCancelReward.setOnClickListener(this);
        ButtonDeleteReward.setOnClickListener(this);
    }
        @Override
    	public void onClick(View v) {
        	switch(v.getId()){
        		case R.id.Button_Update_Reward:
        		// update reward
        		break;
        		case R.id.Button_Delete_Reward:
            		// delete reward
            		break;
        	case R.id.Button_Cancel_Edit_Reward:
        		startActivity(new Intent(EditRewardActivity.this,RewardManagementActivity.class));
        		break;
        	}
    	}
}