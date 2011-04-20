package com.android.chores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;

import android.text.GetChars;
import android.util.Log;

public class HttpRequestExecutor {

	public static final String RESOURCE_ROOT = "http://choremonger.appspot.com/resources";
	private static final String TAG =HttpRequestExecutor.class.getName();
	// right now this just executes the request, but this could also
	// encapsulate authentication issues
	public static HttpResponse executeRequest(HttpUriRequest request) {
		Log.d(TAG,"Executing HTTP request");
		HttpClient hc = new DefaultHttpClient();
		try {
			//request.setHeader("Cookie", cookieVar);
			HttpResponse retval = hc.execute(request);
			if(retval.getStatusLine().getStatusCode()==200)
				Log.d(TAG,((Integer)retval.getStatusLine().getStatusCode()).toString());
			return retval;
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
		
}
