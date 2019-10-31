package com.util;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.mina.core.service.IoAcceptor;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ServersHelper {

	static IoAcceptor acceptor = null;
	

	public static int getServerDate(String stdX, String stdY, String endX, String endY) {
		// 113.810901,22.688086 113.805832,22.673524
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("https://restapi.amap.com/v3/direction/driving?origin=" + stdX + "," + stdY
				+ "&destination=" + endX + "," + endY
				+ "&extensions=base&strategy=2&nosteps=1&output=json&key=d82711c40a4cca714ab49b1d6263d040");
		CloseableHttpResponse response;
		try {
			response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					long len = entity.getContentLength();
					if (len != -1 && len < 2048) {
						JSONObject mydata = JSONObject.parseObject(EntityUtils.toString(entity));
						// System.out.println(mydata.getString("status"));
						if (mydata.getString("info").equals("OK")) {
							JSONObject mydata1 = JSONObject.parseObject(mydata.getString("route"));
							JSONArray job = (JSONArray) mydata1.get("paths");
							JSONObject mydata2 = (JSONObject) (job.get(0));
							// System.out.println(mydata2.getIntValue("duration") / 60 + " mins");

							return mydata2.getIntValue("duration") / 60;
						}
					}
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static int getRoadTime(String stdX, String stdY, String endX, String endY) {

		if(acceptor==null) {
			acceptor = SessionHandler.getAcceptor();
		}
		return getServerDate(stdX, stdY, endX, endY);

	}
	
	

	
		

	
}
