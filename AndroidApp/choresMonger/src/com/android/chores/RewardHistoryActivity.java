package com.android.chores;

import java.util.List;

import com.choremonger.shared.Reward;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RewardHistoryActivity extends Activity {
	private List<Reward> myrewardsCollection;
	private String user_id;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward_history);
        
        SharedPreferences sharedprefs=getSharedPreferences(SignInActivity.PREFS_NAME, Context.MODE_PRIVATE);
        user_id=sharedprefs.getString("USER_ID", "");
        
        ListView menuListView=(ListView)findViewById(R.id.ListView_reward_history);
        menuListView.setCacheColorHint(Color.BLUE); 
        
        // TODO change this to get the redeemed rewards
        RewardImpl myrewardImpl=new RewardImpl();
        myrewardsCollection= myrewardImpl.getRewardsCollection();

         String[] items=new String[myrewardsCollection.size()];
        
         int i=0;
         boolean user_has_rewards=false;
         String usersID=null;
         for(Reward reward:myrewardsCollection){
        	 usersID=((RewardImpl)reward).getUsers();
        	 if(usersID!=null&&usersID.contains(user_id)){
        		 items[i]=reward.getName();
        		 i++;
        		 user_has_rewards=true;
        	 }
        	 else
        		 i++;
         }
        if(!user_has_rewards)
        {
        	((TextView)(findViewById(R.id.txviewRewadrHistory))).setText("No redeemed rewards for you!", TextView.BufferType.EDITABLE);
        	menuListView.setVisibility(View.INVISIBLE);
        }
        else
        {
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
}
