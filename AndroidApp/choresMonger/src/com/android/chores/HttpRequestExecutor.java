package com.android.chores;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpRequestExecutor {

	public static final String RESOURCE_ROOT = "http://choremonger.appspot.com/resources";
	public static final String TAG = HttpRequestExecutor.class.getName();

	// right now this just executes the request, but this could also
	// encapsulate authentication issues
	public static HttpResponse executeRequest(HttpUriRequest request) {
		Log.d(TAG, "Executing HTTP request");
		HttpClient hc = new DefaultHttpClient();
		try {
			// request.setHeader("Cookie", cookieVar);
			HttpResponse retval = hc.execute(request);
			if (retval != null) {

				Log.d(TAG,
						"Response code "
								+ ((Integer) retval.getStatusLine()
										.getStatusCode()).toString());
			}
			return retval;
		} catch (ClientProtocolException e) {
			Log.e(TAG, "ClientProtocolException" + e.getMessage());
		} catch (IOException e) {
			Log.e(TAG, "IOException: " + e.getMessage());
		}
		return null;
	}

}
