package com.cdzmqm;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Request {
	public static String getData(String url,String method,String tmp)
	{
		String data = null;
		try {
			URL u = new URL(url);
			URLConnection uc = u.openConnection();
			if(method.toLowerCase() == "GET"){
				System.out.println("get");
			} else if(method.toLowerCase() == "POST"){
				uc.setDoOutput(true);
				
				System.out.println("post");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			data = "error";
		}
		return data;
	}
	public static String getData(String url)
	{
		String data = null;
		try {
			URL u = new URL(url);
			InputStream in = u.openStream();
			Scanner scanner = new Scanner(in);
			while(scanner.hasNext()) {
				data += scanner.nextLine();
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			data = "error";
		}
		return data;
	}
	public static String getResponse(String url,String data)
	{
		Map<String, List<String>> header = null;
		Map<String,String> headers = new HashMap<>();
		try {
			URL u = new URL(url);
			URLConnection uc = u.openConnection();
			header = uc.getHeaderFields();
			for (Map.Entry<String, List<String>> entry : header.entrySet())
			{
				String key = entry.getKey();
				if (key==null) {
					key = "Code";
				} 
				for (String value : entry.getValue())
				{
					headers.put(key, value);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			data = "error";
		}
		return headers.get(data);
	}
	public static int getResponseCode(String url)
	{
		int code = 0;
		try {	
			URL u = new URL(url);
			HttpURLConnection huc = (HttpURLConnection) u.openConnection();
			huc.connect();
			code = huc.getResponseCode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = 0;
		}
		return code;
	}
}
