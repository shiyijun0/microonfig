package com.jwk.common.utils.pay;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.SocketTimeoutException;
import java.security.KeyStore;
import java.security.KeyStoreException;

/**
 * http请求
 */
public class HttpRequest {

	private int socketTimeout = 10000;
	private int connectTimeout = 30000;
	// 创建HttpClientBuilder
	private HttpClientBuilder httpClientBuilder = null;
	// HttpClient
	private CloseableHttpClient httpClient = null;
	// HttpClient
	private CloseableHttpClient httpsClient = null;
	// 请求配置
	private RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
			.setConnectTimeout(connectTimeout).build();

	public HttpRequest() {
	}

	/**
	 * 发送https请求
	 *
	 * @param url
	 * @param postDataXML
	 * @return
	 */
	public String sendSSLRequest(String certFilePath, String keyPassword, String url, String postDataXML) {

		initHttpsConfig(certFilePath, keyPassword);

		return doPost(url, postDataXML, true);
	}

	/**
	 * 发送http请求
	 *
	 * @param url
	 * @param postDataXML
	 * @return
	 */
	public String sendPost(String url, String postDataXML) {

		initHttpConfig();

		return doPost(url, postDataXML, false);
	}

	/**
	 * 初始化http请求配置
	 */
	private void initHttpConfig() {
		if (null == httpClientBuilder || null == httpClient) {
			httpClientBuilder = HttpClientBuilder.create();
			httpClient = httpClientBuilder.build();
		}
	}

	/**
	 * 初始化https请求所需配置
	 */
	private void initHttpsConfig(String certFilePath, String keyPassword) {

		if (StringUtils.isBlank(certFilePath)) {
			throw new RuntimeException("未提供证书路径");
		}

		if (StringUtils.isBlank(keyPassword)) {
			throw new RuntimeException("未提供证书密码");
		}

		if (null != httpsClient) {
			return;
		}

		KeyStore keyStore = null;

		try {
			// 指定读取证书格式为PKCS12
			keyStore = KeyStore.getInstance("PKCS12");
		} catch (KeyStoreException e) {
			throw new RuntimeException("证书读取格式失败");
		}

		// 读取本机存放的PKCS12证书文件
		FileInputStream instream = null;
		try {
			instream = new FileInputStream(new File(certFilePath));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("证书文件读取失败");
		}

		SSLContext sslcontext = null;

		try {
			try {
				// 指定PKCS12的密码(商户ID)
				keyStore.load(instream, keyPassword.toCharArray());
			} finally {
				if (null != instream) {
					instream.close();
				}
			}

			sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, keyPassword.toCharArray()).build();

		} catch (Exception e) {
			throw new RuntimeException("证书加载读取失败");
		}

		// 指定TLS版本
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

		// 设置httpclient的SSLSocketFactory
		httpsClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}

	/**
	 * 执行pos操作
	 *
	 * @param url
	 * @param postDataXML
	 * @param isSSLRequest
	 *            是否ssl请求
	 * @return
	 */
	private String doPost(String url, String postDataXML, boolean isSSLRequest) {
		HttpPost httpPost = new HttpPost(url);

		// 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
		StringEntity postEntity = null;
		try {
			postEntity = new StringEntity(postDataXML, "UTF-8");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.setEntity(postEntity);

		// 设置请求器的配置
		httpPost.setConfig(requestConfig);
		String result = "";

		try {

			HttpResponse response = null;
			if (isSSLRequest) {
				response = httpsClient.execute(httpPost);
			} else {
				response = httpClient.execute(httpPost);
			}

			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
		} catch (ConnectionPoolTimeoutException e) {
			throw new RuntimeException("http get throw ConnectionPoolTimeoutException(wait time out)");
		} catch (ConnectTimeoutException e) {
			throw new RuntimeException("http get throw ConnectTimeoutException");
		} catch (SocketTimeoutException e) {
			throw new RuntimeException("http get throw SocketTimeoutException");
		} catch (Exception e) {
			throw new RuntimeException("http get throw Exception");
		} finally {
			httpPost.abort();
		}

		return result;
	}
}
