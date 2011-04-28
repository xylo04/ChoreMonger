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
import android.widget.TextView;

public class MyRewardsActivity extends Activity {
	private List<Reward> myrewardsCollection;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_rewards);
        ListView menuListView=(ListView)findViewById(R.id.ListView_My_Rewards_Menu);
        menuListView.setCacheColorHint(Color.BLUE); 
        
        RewardImpl myrewardImpl=new RewardImpl();
        myrewardsCollection= myrewardImpl.getRewardsCollection();

      String[] items=new String[myrewardsCollection.size()];
      int i=0;
      String users=null;
      boolean user_can_redeem_rewards=false;
        for(Reward reward:myrewardsCollection){
        	users=((RewardImpl)reward).getUsers();
        	
        	// Display redeemable reward
        	if(users==null||users==""||(reward.isOneTime()==false&&users!=null))
        	{
    			items[i]=reward.getName();
    			i++;
    			user_can_redeem_rewards=true;
        	}
     	   
        }


        
        if(!user_can_redeem_rewards)
        {
        	((TextView)(findViewById(R.id.txviewMyRewards))).setText("No redeemed rewards for you!", TextView.BufferType.EDITABLE);
        	menuListView.setVisibility(View.INVISIBLE);
        	
        }
        else{
        	
        	String []rewarditems=new String[i];
            for(int j=0;j<i;j++)
            	rewarditems[j]=items[j];
            
        	ArrayAdapter<String>adapt=new ArrayAdapter<String>(this, R.layout.menu_item,rewarditems);
        	menuListView.setAdapter(adapt);
         
        	menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

 			@Override
 			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
 	             
 	             if(position>=0){
 	            	Intent intent=new Intent(MyRewardsActivity.this,RewardDetailsActivity.class);
 	             	intent.putExtra("rewardID", myrewardsCollection.get(position).getId());
 	             	startActivity(intent);
 	             }
 			}
         });
        }
	}
}

