package com.android.chores;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RewardManagementActivity extends Activity implements OnClickListener {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward_management);
        View ButtonCreateReward= findViewById(R.id.Button_Create_Reward);
        ButtonCreateReward.setOnClickListener(this);
        
       ListView menuListView=(ListView)findViewById(R.id.ListView_My_RewardsManagement_Menu);
       menuListView.setCacheColorHint(Color.BLUE); 
       String[] items={"Going For a Bike Ride",
         		"Hiking",
         		"$20 Amazon Gift Card"};
        
        ArrayAdapter<String>adapt=new ArrayAdapter<String>(this, R.layout.menu_item,items);
        menuListView.setAdapter(adapt);
       
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View itemClicked, int arg2,
					long arg3) {
				 TextView textView = (TextView) itemClicked;
	             String strText = textView.getText().toString();
	             startActivity(new Intent(RewardManagementActivity.this,EditRewardActivity.class));
			}
        });
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
    	switch (v.getId()){
		case R.id.Button_Create_Reward:
			startActivity(new Intent(RewardManagementActivity.this,CreateNewRewardActivity.class));
	}
}
}