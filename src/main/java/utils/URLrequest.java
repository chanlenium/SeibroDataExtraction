package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class URLrequest {
	public StringBuilder urlRequest(String urlStr) {
		URL url;
		StringBuilder xmlString = null;
		String line;
		
		try {
			url = new URL(urlStr.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();	// open URL
	        conn.setRequestMethod("GET");	// GET : 웹 서버로부터 리소스를 가져옴
	        conn.setRequestProperty("Content-type", "application/xml");	// 타입설정
	        //System.out.println("Response code: " + conn.getResponseCode());
	        
	        /** Read data */
	        // Buffer를 이용하여 데이터를 읽기 위한 변수(입력이 있을때마다 한 문자씩 버퍼로 전송하며, 버퍼가 가득차거나 개행(open)문자가 나타나면 버퍼의 내용을 한꺼번에 전송)
	        BufferedReader bufferedReader;	
	        if(conn.getResponseCode() == 200) {
	        	bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        	/** Read data **/
		        xmlString = new StringBuilder();	// memory에 값을 append하기 위한 변수 (synchronization이 적용되지 않으므로, thread환경에서 불리)
		        while ((line = bufferedReader.readLine()) != null) {	// 한 라인씩 읽어버 붙임
		        	xmlString.append(line);
		        }
	        } else {
	        	bufferedReader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
	        }
	        bufferedReader.close();	// 다 읽으면 buffer를 닫음
	        conn.disconnect();	// connection 종료
		} catch (MalformedURLException | ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
		return xmlString;
	}
}
