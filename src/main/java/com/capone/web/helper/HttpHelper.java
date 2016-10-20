package com.capone.web.helper;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Helper class to add some post request params
 * @author Prathyusha
 * @since 10-18-2016
 */
@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class HttpHelper {
	
	private static final String BKND_URL = "https://2016.api.levelmoney.com/api/v2/core/";
	private static final String API_TOKEN = "AppTokenForInterview";
	private static final String TOKEN = "02A99E1646645488172CA089AD9CE184";
	private static final int UID = 1110590645;


	public String httpClientPost(String transactionType, Map<String, Object> params) {

		HttpURLConnection conn = null;
		String output = null;
		try {
			URL url = new URL(BKND_URL + transactionType);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			Map<String, Object> parmsMap = new HashMap();
			//parmsMap.put("uid", UID);
			//parmsMap.put("api-token", API_TOKEN);
			//parmsMap.put("token",TOKEN);
			parmsMap.putAll(params);
			parmsMap.put("json-verbose-response", false);
			parmsMap.put("json-strict-mode", false);
			Map<String, Object> argsMap = new HashMap();
			argsMap.put("args", parmsMap);
			String parmsMapAsJson = new ObjectMapper().writeValueAsString(argsMap);
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
