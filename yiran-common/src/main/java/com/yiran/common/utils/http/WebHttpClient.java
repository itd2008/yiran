package com.yiran.common.utils.http;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yiran.common.utils.EncodedPostMethod;
import com.yiran.common.utils.HttpEncoding;

public class WebHttpClient {

	private static final Logger log = LoggerFactory.getLogger(WebHttpClient.class);

	public static String postRequest(Map<String, String> data, String url,
			ProtocolSocketFactory protocolSocketFactory,
			Map<String, String> headerMap) {
		return postRequest(data, url, HttpEncoding.UTF8, protocolSocketFactory,
				headerMap);
	}

	public static String postRequest(String data, String url,
			ProtocolSocketFactory protocolSocketFactory,Map<String, String> headerMap) {
		return postRequest(data, url, HttpEncoding.UTF8, protocolSocketFactory,headerMap);
	}

	public static String postRequest(Map<String, String> data, String url,
			HttpEncoding encoding, ProtocolSocketFactory protocolSocketFactory,
			Map<String, String> headerMap) {
		if (protocolSocketFactory != null) {
			Protocol https = new Protocol("https", protocolSocketFactory, 443);
			Protocol.registerProtocol("https", https);
		}
		PostMethod postMethod = getPostMethod(data, url, encoding, headerMap);
		return postRequest(postMethod);
	}

	public static String postRequest(String data, String url,
			HttpEncoding encoding, ProtocolSocketFactory protocolSocketFactory,Map<String, String> headerMap) {
		if (protocolSocketFactory != null) {
			Protocol https = new Protocol("https", protocolSocketFactory, 443);
			Protocol.registerProtocol("https", https);
		}
		PostMethod postMethod = getPostMethod(data, url, encoding,headerMap);

		return postRequest(postMethod);
	}

	public static String postRequest(PostMethod method) {
		try {

			HttpClient httpClient = new HttpClient();
			httpClient.executeMethod(method);

			String response = new String(method.getResponseBodyAsString()
					.getBytes());

			return response;
		} catch (Exception e) {
			log.error("HTTP请求发送失败", e);
			return null;
		} finally {
			method.releaseConnection();
		}
	}

	private static PostMethod getPostMethod(Map<String, String> request,
			String url, HttpEncoding encoding, Map<String, String> headerMap) {

		EncodedPostMethod postMethod = new EncodedPostMethod(url, encoding);

		NameValuePair[] body = null;

		if (request == null) {
			body = new NameValuePair[0];
		} else {
			Set<Entry<String, String>> entries = request.entrySet();

			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			for (Entry<String, String> entry : entries) {
				pairs.add(new NameValuePair(entry.getKey(), entry.getValue()));
			}
			body = pairs.toArray(new NameValuePair[] {});
		}
		// 新增清空缓存
		postMethod.setRequestHeader("Cache-control", "no-cache");
		postMethod.setRequestHeader("Pragma", "No-cache");
		postMethod.setRequestHeader("Expires", "0");
		addPostMethod(headerMap,postMethod);

		postMethod.setRequestBody(body);

		return postMethod;
	}

	private static void addPostMethod(Map<String, String> headerMap,
			EncodedPostMethod postMethod) {
		if (headerMap != null && headerMap.size() > 0) {
			Iterator<String> keys = headerMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				postMethod.setRequestHeader(key, headerMap.get(key));
			}
		}
	}

	@SuppressWarnings("deprecation")
	private static PostMethod getPostMethod(String request, String url,
			HttpEncoding encoding,Map<String, String> headerMap) {

		EncodedPostMethod postMethod = new EncodedPostMethod(url, encoding);

		postMethod.setRequestBody(request);

		postMethod.setRequestHeader("Content-Type", "application/stream");
		
		addPostMethod(headerMap,postMethod);

		return postMethod;
	}
}
