package com.android.chores;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.choremonger.shared.Chore;
import com.choremonger.shared.User;

public class ChoreManagementActivity extends Activity implements OnClickListener {
	private List<Chore> myChoresCollection;
	
	
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		String user_id;
		User current_user;
        setContentView(R.layout.chore_management);
        View ButtonCreateChore= findViewById(R.id.Button_Create_Chore);
        ButtonCreateChore.setOnClickListener(this);

        SharedPreferences sharedprefs=getSharedPreferences(SignInActivity.PREFS_NAME, Context.MODE_PRIVATE);
        user_id=sharedprefs.getString("USER_ID", "");

    	current_user=UserImpl.getUser(user_id);
        
       ListView menuListView=(ListView)findViewById(R.id.ListView_My_ChoreManagement_Menu);
       menuListView.setCacheColorHint(Color.BLUE); 
       myChoresCollection= current_user.getChores();
   
        String[] items=new String[myChoresCollection.size()];
      
       int i=0;
       for(Chore chore:myChoresCollection){
    	   items[i]=chore.getName();
    	   if(items[i] == null) {
    		   items[i] = "";
    	   }
    	   i++;
       }

        ArrayAdapter<String>adapt=new ArrayAdapter<String>(this, R.layout.menu_item,items);
        menuListView.setAdapter(adapt);
       
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	             
	             if(position>=0){
	            	Intent intent=new Intent(ChoreManagementActivity.this,EditChoreActivity.class);
	             	intent.putExtra("choreID", myChoresCollection.get(position).getId());
	             	startActivity(intent);
	             }
			}
        });
    }

	@Override
	public void onClick(View v) {
    	switch (v.getId()){
		case R.id.Button_Create_Chore:
			startActivity(new Intent(ChoreManagementActivity.this,CreateNewChoreActivity.class));
	}
}
}