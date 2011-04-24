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

	// right now this just executes the request, but this could also
	// encapsulate authentication issues
	public static HttpResponse executeRequest(HttpUriRequest request) {
		System.out.println("Executing HTTP request");
		HttpClient hc = new DefaultHttpClient();
		try {
			//request.setHeader("Cookie", cookieVar);
			return hc.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

}
