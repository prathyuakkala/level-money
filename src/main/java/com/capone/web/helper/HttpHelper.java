package com.capone.web.helper;

import java.net.URL;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import org.springframework.stereotype.Component;


/**
 * Helper class to add some of the post request params
 * @author Prathyusha
 * @since 10-18-2016
 */
@Component

public class HttpHelper {
	
	private static final String BKND_URL = "https://2016.api.levelmoney.com/api/v2/core/";
	

    /**
     * To make HTTP post call
     * @param transactionType
     * @param parmsMapAsJson
     * @return String
     */
	public String httpClientPost(String transactionType, String parmsMapAsJson) {

		HttpURLConnection conn = null;
		String output = null;
		try {
			URL url = new URL(BKND_URL + transactionType);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			
			OutputStream os = conn.getOutputStream();
			os.write(parmsMapAsJson.getBytes());
			os.flush();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
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
