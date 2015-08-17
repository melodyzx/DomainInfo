package com.cdzmqm;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Request {
	public static String doPost(String url,String param)
	{
		String data = "";
		try {
			URL u = new URL(url);
			URLConnection uc = u.openConnection();
			uc.setDoOutput(true);
			PrintWriter out = new PrintWriter(uc.getOutputStream());
			out.write(param);
			out.flush();
			out.close();
			Scanner scanner = new Scanner(uc.getInputStream());
			while(scanner.hasNextLine()) {
				data += scanner.nextLine()+"\r\n";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			data = "error";
			e.printStackTrace();
		}
		return data;
	}
	public static String doPost(String url,String param,HashMap<String,String> advance)
	{
		String data = "";
		try {
			URL u = new URL(url);
			URLConnection uc = u.openConnection();
			uc.setDoOutput(true);
			Set<Map.Entry<String, String>> headers = advance.entrySet();
			for (Map.Entry<String, String> header: headers)
			{
				uc.setRequestProperty(header.getKey(),header.getValue());
			}
			PrintWriter out = new PrintWriter(uc.getOutputStream());
			out.write(param);
			out.flush();
			out.close();
			Scanner scanner = new Scanner(uc.getInputStream());
			while(scanner.hasNextLine()) {
				data += scanner.nextLine()+"\r\n";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			data = "error";
			e.printStackTrace();
		}
		return data;
	}
	public static String doGet(String url)
	{
		String data = "";
		try {
			URL u = new URL(url);
			InputStream in = u.openStream();
			Scanner scanner = new Scanner(in);
			while(scanner.hasNextLine()) {
				data += scanner.nextLine()+"\r\n";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			data = "error";
		}
		return data;
	}
	public static String doGet(String url,HashMap<String,String> advance)
	{
		String data = "";
		try {
			URL u = new URL(url);
			URLConnection uc = u.openConnection();
			Set<Map.Entry<String, String>> headers = advance.entrySet();
			for (Map.Entry<String, String> header: headers)
			{
				uc.setRequestProperty(header.getKey(),header.getValue());
			}
			Scanner scanner = new Scanner(uc.getInputStream());
			while(scanner.hasNextLine()) {
				data += scanner.nextLine()+"\r\n";
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
