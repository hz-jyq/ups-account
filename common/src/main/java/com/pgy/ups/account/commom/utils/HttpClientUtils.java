package com.pgy.ups.account.commom.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    
	/**
	 * 发送http请求，返回响应字符串
	 * @param url
	 * @param params
	 * @return
	 */
	public static String sentRequest(String url, Map<String, Object> params) {
		String paramStr = transferToRequestStr(params);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpPost postRequest = new HttpPost(new URI(url + "?" + paramStr));
			HttpResponse response = httpClient.execute(postRequest);
			//获取应答码
			int responseCode = response.getStatusLine().getStatusCode();
			//返回报文
			String resultStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			if(responseCode!=200) {
				logger.error("Http下载请求失败！请求地址:"+url + ",报文：" + resultStr+",状态码：" + responseCode);
			}else {
				return resultStr;
			}			
			return StringUtils.EMPTY;
		} catch (URISyntaxException | IOException e) {
			logger.error("httpClient发送请求失败{}", e);
			return StringUtils.EMPTY;
		}
	}

	/**
	 * map參數轉string
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String transferToRequestStr(Map<String, Object> paramMap) {
		if (MapUtils.isEmpty(paramMap)) {
			return StringUtils.EMPTY;
		}
		String params = "";
		for (String key : paramMap.keySet()) {
			if (!key.isEmpty()) {
				params += key + "=" + paramMap.get(key) + "&";
			}
		}
		// 去掉最後一個&
		params = params.substring(0, params.length() - 1);
		return params;
	}

}
