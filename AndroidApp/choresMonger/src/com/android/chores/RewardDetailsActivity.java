package com.android.chores;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class RewardDetailsActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward_details);
        
        View ButtonRedeemReward = findViewById(R.id.Button_Redeem_Reward);
        ButtonRedeemReward.setOnClickListener(this);
    }
    @Override
	public void onClick(View v) {
    	switch(v.getId()){
    	case R.id.Button_Redeem_Reward:
    		break;
    	}
    }
}
