package com.android.chores;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.choremonger.shared.User;


import com.choremonger.shared.Reward;

public class EditChoreActivity extends Activity implements OnClickListener  {
	private static final int PROGRESS_DIALOG_ID=1;
	private ChoreImpl chore;
	private String choreID;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_chore);
        
        choreID=getIntent().getStringExtra("choreID");
        initalizeElements();
        
        
        View ButtonUpdateReward= findViewById(R.id.Button_Update_Chore);
        View ButtonDeleteReward = findViewById(R.id.Button_Delete_Chore);
        ButtonUpdateReward.setOnClickListener(this);
        ButtonDeleteReward.setOnClickListener(this);
    }
        @Override
    	public void onClick(View v) {
        	switch(v.getId()){
        		case R.id.Button_Update_Chore:
        		// update reward
                	showDialog(PROGRESS_DIALOG_ID);
        			updateReward();
        		break;
        		case R.id.Button_Delete_Reward:
            		// delete reward
        			deleteReward();
            		break;
        	}
    	}
        
        public void initalizeElements(){
        	chore=new ChoreImpl(choreID);
        	((EditText)(findViewById(R.id.editTxtRewardTitleVal))).setText(chore.getName(), TextView.BufferType.EDITABLE);
        	((EditText)(findViewById(R.id.editTxtRewardTitleVal))).setText(chore.getUsers().get(1).getId(), TextView.BufferType.EDITABLE);
        	chore.removeUser(chore.getUsers().get(1));
        	((EditText)(findViewById(R.id.txviewRewardPointsVal))).setText(Double.toString(chore.getPointValue()), TextView.BufferType.EDITABLE);
        }
        
        public void updateReward(){
        	chore.setName(((EditText)findViewById(R.id.editTxtRewardTitleVal)).getText().toString());
        	User user = UserImpl.getUserByName(((EditText)findViewById(R.id.editTxtRewardTitleVal)).getText().toString());
        	chore.addUser(user);
        	chore.setPointValue(Double.parseDouble(((EditText)findViewById(R.id.txviewRewardPointsVal)).getText().toString()));
        	EditChoreActivity.this.finish();
        }
        public void deleteReward(){     	
        	RewardImpl.deleteReward(chore.getId());
        }
        
        protected Dialog onCreateDialog(int id) {
        	switch(id){
        	case PROGRESS_DIALOG_ID:
        		return ProgressDialog.show(EditChoreActivity.this, "", 
                        "Updating. Please wait...", true);
        	default:
        		return null;
        	}
        }
}