package com.android.chores;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;

public class MyRewardsActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_rewards);
        ListView menuListView=(ListView)findViewById(R.id.ListView_My_Rewards_Menu);
        menuListView.setCacheColorHint(Color.BLUE); 
        String[] items={"Going For a Bike Ride",
         		"Hiking",
         		"$20 Amazon Gift Card"};
         
         ArrayAdapter<String>adapt=new ArrayAdapter<String>(this, R.layout.menu_item,items);
         menuListView.setAdapter(adapt);
         menuListView.setClickable(true);
         
         menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
 			@Override
 			public void onItemClick(AdapterView<?> arg0, View itemClicked, int arg2,
 					long arg3) {
 				 TextView textView = (TextView) itemClicked;
 	                String strText = textView.getText().toString();
 	                // Listen for item clicks 
 	                startActivity(new Intent(MyRewardsActivity.this, RewardDetailsActivity.class));
 			}
         });
	}
}
