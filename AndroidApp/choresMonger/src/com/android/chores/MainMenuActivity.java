package com.android.chores;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainMenuActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        TextView txvwDisplayName=(TextView)findViewById(R.id.TextView_DisplayName);
        txvwDisplayName.setText("Khalid");
        TextView txvwEarnedPointsVal=(TextView)findViewById(R.id.TextView_assignedChoresVal);
        txvwEarnedPointsVal.setText("2");
        
       View ButtonMyChores=findViewById(R.id.Button_myChores);
       View ButtonChoresManagement=findViewById(R.id.Button_ChoresManagement);
       View ButtonRewardsCenter=findViewById(R.id.Button_RewardsCenter);
       View ButtonProfile=findViewById(R.id.Button_Profile);
       View ButtonSettings=findViewById(R.id.Button_Settings);
       
       ButtonMyChores.setOnClickListener(this);
       ButtonChoresManagement.setOnClickListener(this);
       ButtonRewardsCenter.setOnClickListener(this);
       ButtonProfile.setOnClickListener(this);
       ButtonSettings.setOnClickListener(this);
    }
           
    @Override
	public void onClick(View v) {
    	switch(v.getId()){
    		case R.id.Button_myChores:
    			// launch my chores activity here
    		break;
    		case R.id.Button_ChoresManagement:
    			// launch chores management activity here
    		break;
    		case R.id.Button_RewardsCenter:
    			startActivity(new Intent(this,RewardsCenterActivity.class));
    			// launch rewards center activity here
    		break;
    		case R.id.Button_Profile:
    			// launch profile activity here
    		break;
    		case R.id.Button_Settings:
    			// launch settings activity here
    		break;
    	}
	}

}
