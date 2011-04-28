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
        		// update chore
                	showDialog(PROGRESS_DIALOG_ID);
        			updateChore();
        		break;
        		case R.id.Button_Delete_Chore:
            		// delete chore
        			deleteChore();
            		break;
        	}
    	}
        
        public void initalizeElements(){
        	chore=new ChoreImpl(choreID);
        	((EditText)(findViewById(R.id.editTxtChoreTitleVal))).setText(chore.getName(), TextView.BufferType.EDITABLE);
        	if (chore.getUsers().size() > 0)
        		((EditText)(findViewById(R.id.editxtCreateChoreUserVal))).setText(chore.getUsers().get(0).getName(), TextView.BufferType.EDITABLE);
        	else
        		((EditText)(findViewById(R.id.editxtCreateChoreUserVal))).setText("", TextView.BufferType.EDITABLE);
        		
        	((EditText)(findViewById(R.id.txviewChorePointsVal))).setText(Double.toString(chore.getPointValue()), TextView.BufferType.EDITABLE);
        }
        
        public void updateChore(){
        	chore.setName(((EditText)findViewById(R.id.editTxtChoreTitleVal)).getText().toString());
        	User user = UserImpl.getUserByName(((EditText)findViewById(R.id.editxtCreateChoreUserVal)).getText().toString());
        	if (chore.getUsers().size() > 0)
        		chore.addUser(user);
        	chore.setPointValue(Double.parseDouble(((EditText)findViewById(R.id.txviewChorePointsVal)).getText().toString()));
        	EditChoreActivity.this.finish();
        }
        public void deleteChore(){     	
        	ChoreImpl.deleteChore(chore.getId());
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