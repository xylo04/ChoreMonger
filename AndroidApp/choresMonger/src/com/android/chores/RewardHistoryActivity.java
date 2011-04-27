package com.android.chores;

import java.util.List;

import com.choremonger.shared.Reward;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RewardHistoryActivity extends Activity {
	private List<Reward> myrewardsCollection;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward_history);
        ListView menuListView=(ListView)findViewById(R.id.ListView_reward_history);
        menuListView.setCacheColorHint(Color.BLUE); 
        
        // TODO change this to get the redeemed rewards
        RewardImpl myrewardImpl=new RewardImpl();
        myrewardsCollection= myrewardImpl.getRewardsCollection();

         String[] items=new String[myrewardsCollection.size()];
        
      int i=0;
        for(Reward reward:myrewardsCollection){
     	   items[i]=reward.getName();
     	   i++;
        }
        
         ArrayAdapter<String>adapt=new ArrayAdapter<String>(this, R.layout.menu_item,items);
         menuListView.setAdapter(adapt);
         
         menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

 			@Override
 			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
 	             
 	             if(position>=0){
 	            	Intent intent=new Intent(RewardHistoryActivity.this,RewardDetailsActivity.class);
 	             	intent.putExtra("rewardID", myrewardsCollection.get(position).getId());
 	             	intent.putExtra("history", "history");
 	             	startActivity(intent);
 	             }
 			}
         });
	}
}
