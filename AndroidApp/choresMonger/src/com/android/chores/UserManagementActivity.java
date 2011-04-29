package com.android.chores;


import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.choremonger.shared.Reward;
import com.choremonger.shared.User;

public class UserManagementActivity extends Activity implements OnClickListener {
	private List<User> myuserCollection;
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_management);
        View ButtonCreateUser= findViewById(R.id.Button_Create_User);
        ButtonCreateUser.setOnClickListener(this);
        
       ListView menuListView=(ListView)findViewById(R.id.ListView_My_UserManagement_Menu);
       menuListView.setCacheColorHint(Color.BLUE);
       myuserCollection= UserImpl.getUsersCollection();
   
        String[] items=new String[myuserCollection.size()];
      
       int i=0;
       for(User user:myuserCollection){
    	   items[i]=user.getName();
    	   i++;
       }

        ArrayAdapter<String>adapt=new ArrayAdapter<String>(this, R.layout.menu_item,items);
        menuListView.setAdapter(adapt);
       
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	             
	             if(position>=0){
	            	Intent intent=new Intent(UserManagementActivity.this,EditUserActivity.class);
	             	intent.putExtra("userID", myuserCollection.get(position).getId());
	             	startActivity(intent);
	             }
			}
        });
    }

	@Override
	public void onClick(View v) {
    	switch (v.getId()){
		case R.id.Button_Create_User:
			startActivity(new Intent(UserManagementActivity.this,CreateNewUserActivity.class));
	}
}
}