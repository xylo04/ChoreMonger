package com.android.chores;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.EditText;
import com.choremonger.shared.Reward;

public class CreateNewRewardActivity extends Activity implements OnClickListener {
    
	private static String TAG=CreateNewRewardActivity.class.getName();
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
        			try{
        			// create reward 
        			double pointsValue=(Double.parseDouble(((EditText)findViewById(R.id.txviewRewardPointsVal)).getText().toString()));
        			String rewardName=((EditText)findViewById(R.id.txviewRewardTitleVal)).getText().toString();
        			String description=((EditText)findViewById(R.id.editxtRewardDescrVal)).getText().toString();
        			CheckBox isOneTimeRewardChckBox=(CheckBox)findViewById(R.id.checkbox1);
        			
        			boolean isOneTimeReward;
        			if(isOneTimeRewardChckBox.isChecked())
        				isOneTimeReward=true;
        			else
        				isOneTimeReward=false;
        			createReward(description, rewardName, pointsValue, isOneTimeReward);
        			}
        			catch(Exception exception){
        				Log.e(TAG,exception.getMessage());
        			}
        		break;
        	case R.id.Button_Cancel_Create_Reward:
        		startActivity(new Intent(CreateNewRewardActivity.this,RewardManagementActivity.class));
        		break;
        	}
    	}
        
        public void createReward(String description,String rewardName,double pointsValue,boolean isOneTimeReward){
        	Reward reward=new RewardImpl("",description,rewardName,pointsValue,isOneTimeReward);
<<<<<<< HEAD
        	Reward createdReward=RewardImpl.createReward(reward);
        	if (createdReward==null){
				Log.e(TAG,"Oooops! No rewards created!");
				System.exit(1);
			}
        	startActivity(new Intent(CreateNewRewardActivity.this,RewardManagementActivity.class));
			Log.d(TAG,"Reward Created");
			Log.d(TAG,createdReward.getName());
			Log.d(TAG,createdReward.getId());
=======
        	RewardImpl.createReward(reward);
>>>>>>> c23ccbf20aa0d982618e09b3e87082657053a769
        }
}
