package com.android.chores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends Activity implements OnClickListener {
	DefaultHttpClient http_client = new DefaultHttpClient();
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        
        // TODO: Change this later
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
	protected void onResume() {
		super.onResume();
		Intent intent = getIntent();
		AccountManager accountManager = AccountManager.get(getApplicationContext());
		Account account = (Account)intent.getExtras().get("account");
		accountManager.getAuthToken(account, "ah", false, new GetAuthTokenCallback(), null);
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
    private class GetAuthTokenCallback implements AccountManagerCallback<Bundle> {
    	public void run(AccountManagerFuture<Bundle> result) {
    		Bundle bundle;
    		try {
    			bundle = result.getResult();
    			Intent intent = (Intent)bundle.get(AccountManager.KEY_INTENT);
    			if(intent != null) {
    				// User input required
    				startActivity(intent);
    			} else {
    				onGetAuthToken(bundle);
    			}
    		} catch (OperationCanceledException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (AuthenticatorException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    };

    protected void onGetAuthToken(Bundle bundle) {
    	String auth_token = bundle.getString(AccountManager.KEY_AUTHTOKEN);
    	new GetCookieTask().execute(auth_token);
    }

    private class GetCookieTask extends AsyncTask<String, Void, Boolean> {
    	protected Boolean doInBackground(String... tokens) {
    		try {
    			// Don't follow redirects
    			http_client.getParams().setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, false);

    			HttpGet http_get = new HttpGet("http://choremonger.appspot.com/_ah/login?continue=http://localhost/&auth=" + tokens[0]);
    			HttpResponse response;
    			response = http_client.execute(http_get);
    			if(response.getStatusLine().getStatusCode() != 302)
    				// Response should be a redirect
    				return false;

    			for(Cookie cookie : http_client.getCookieStore().getCookies()) {
    				if(cookie.getName().equals("ACSID"))
    					return true;
    			}
    		} catch (ClientProtocolException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} finally {
    			http_client.getParams().setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, true);
    		}
    		return false;
    	}

    	protected void onPostExecute(Boolean result) {
    		new AuthenticatedRequestTask().execute("http://choremonger.appspot.com/admin/");
    	}
    }

    private class AuthenticatedRequestTask extends AsyncTask<String, Void, HttpResponse> {
    	@Override
    	protected HttpResponse doInBackground(String... urls) {
    		try {
    			HttpGet http_get = new HttpGet(urls[0]);
    			return http_client.execute(http_get);
    		} catch (ClientProtocolException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		return null;
    	}

    	protected void onPostExecute(HttpResponse result) {
    		try {
    			BufferedReader reader = new BufferedReader(new InputStreamReader(result.getEntity().getContent()));
    			String first_line = reader.readLine();
    			Toast.makeText(getApplicationContext(), first_line, Toast.LENGTH_LONG).show();				
    		} catch (IllegalStateException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
}


