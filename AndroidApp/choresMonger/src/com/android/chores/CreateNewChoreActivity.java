package com.android.chores;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.choremonger.shared.Chore;
import com.choremonger.shared.User;

public class CreateNewChoreActivity extends Activity implements OnClickListener {
    
	private static String TAG=CreateNewChoreActivity.class.getName();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_chore);
        View ButtonCreateReward= findViewById(R.id.Button_Create_Chore);
        ButtonCreateReward.setOnClickListener(this);
    }
        @Override
    	public void onClick(View v) {
        	switch(v.getId()){
        		case R.id.Button_Create_Chore:
        			try{
        			// create reward 
        			double pointsValue=(Double.parseDouble(((EditText)findViewById(R.id.txviewCreateRewardPointsVal)).getText().toString()));
        			String choreName=((EditText)findViewById(R.id.editTxtCreateRewardTitleVal)).getText().toString();
        			String user=((EditText)findViewById(R.id.editxtCreateChoreUserVal)).getText().toString();
        			User user0 = UserImpl.getUserByName(user);
        			List<User> users= new ArrayList<User>();
        			users.add(user0);
        			createChore(choreName, pointsValue, users);
        			}
        			catch(Exception exception){
        				Log.e(TAG,exception.getMessage());
        			}
        		break;
        	}
    	}
        
        public void createChore(String choreName,double pointsValue, List<User> users){
        	ChoreImpl chore=new ChoreImpl(choreName, null, pointsValue, users);
        	startActivity(new Intent(CreateNewChoreActivity.this,ChoreManagementActivity.class));
			Log.d(TAG,"Reward Created");
			Log.d(TAG,chore.getName());
			Log.d(TAG,chore.getId());
        }
}
