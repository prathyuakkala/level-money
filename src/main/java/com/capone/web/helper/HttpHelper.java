package com.capone.web.helper;

import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import org.springframework.stereotype.Component;

@Component
public class HttpHelper {

	public String httpClientPost(String urlStr, String params) {

		HttpURLConnection conn = null;
		String output = null;
		try {

			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			conn.setRequestProperty("Accept", "application/json");

			String input = "{\"qty\":100,\"name\":\"iPad 4\"}";
			String args = "{\"args\": {\"uid\":  1110590645, \"token\":  \"02A99E1646645488172CA089AD9CE184\", \"api-token\":  \"AppTokenForInterview\", \"json-strict-mode\": false, \"json-verbose-response\": false}}";
			OutputStream os = conn.getOutputStream();
			os.write(args.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			
			System.out.println("Output from Server .... \n");
			output = br.readLine();
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			conn.disconnect();
		}
		return output;

	}

}
