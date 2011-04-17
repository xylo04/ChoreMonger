package com.android.chores;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RewardsCenterActivity extends Activity {
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewards_center);
        
       ListView menuListView=(ListView)findViewById(R.id.ListView_Rewards_Center_Menu);
       menuListView.setCacheColorHint(Color.BLUE); 
       String[] items={"My Rewards",
        		"Rewards Management",
        		"Rewards History"};
        
        ArrayAdapter<String>adapt=new ArrayAdapter<String>(this, R.layout.menu_item,items);
        menuListView.setAdapter(adapt);
       
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View itemClicked, int arg2,
					long arg3) {
				 TextView textView = (TextView) itemClicked;
	                String strText = textView.getText().toString();
	                if (strText.equalsIgnoreCase("My Rewards")) {
	                    // Launch the My Rewards
	                	startActivity(new Intent(RewardsCenterActivity.this,MyRewardsActivity.class));
	                }
	                else if (strText.equalsIgnoreCase("Rewards Management")) {
	                    // Launch the Rewards Management
	                	startActivity(new Intent(RewardsCenterActivity.this,RewardManagementActivity.class));
	                }
	                else if (strText.equalsIgnoreCase("Rewards History")) {
	                    // Launch the Rewards History
	                	//startActivity(new Intent(RewardsCenterActivity.this,MyRewardsActivity.class));
	                }
				
			}
        });
    }
}
