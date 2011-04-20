package com.android.chores;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.text.GetChars;
import android.util.Log;

public class HttpRequestExecutor {

	public static final String RESOURCE_ROOT = "http://choremonger.appspot.com/resources";
<<<<<<< HEAD
	private static final String TAG =HttpRequestExecutor.class.getName();
	// right now this just executes the request, but this could also
	// encapsulate authentication issues
	public static HttpResponse executeRequest(HttpUriRequest request) {
		Log.d(TAG,"Executing HTTP request");
=======
	public static final String TAG = HttpRequestExecutor.class.getName();

	// right now this just executes the request, but this could also
	// encapsulate authentication issues
	public static HttpResponse executeRequest(HttpUriRequest request) {
		Log.d(TAG, "Executing HTTP request");
>>>>>>> c23ccbf20aa0d982618e09b3e87082657053a769
		HttpClient hc = new DefaultHttpClient();
		try {
			// request.setHeader("Cookie", cookieVar);
			HttpResponse retval = hc.execute(request);
<<<<<<< HEAD
			if(retval.getStatusLine().getStatusCode()==200)
				Log.d(TAG,((Integer)retval.getStatusLine().getStatusCode()).toString());
			return retval;
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();
			System.exit(1);
=======
			if (retval != null) {

				Log.d(TAG,
						"Response code "
								+ ((Integer) retval.getStatusLine()
										.getStatusCode()).toString());
			}
			return retval;
		} catch (ClientProtocolException e) {
			Log.e(TAG, "ClientProtocolException" + e.getMessage());
>>>>>>> c23ccbf20aa0d982618e09b3e87082657053a769
		} catch (IOException e) {
			Log.e(TAG, "IOException: " + e.getMessage());
		}
		return null;
	}

}
