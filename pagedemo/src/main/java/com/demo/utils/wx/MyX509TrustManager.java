package com.demo.utils.wx;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;

/**
 * <p>信任管理器</p>
 * @author jianglan @date 2018-05-07<br>
 * @version 1.0<br>
 */
public class MyX509TrustManager implements X509TrustManager {

	/**
	 * <p>检查客户端证书</p>
	 */
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		
	}

	/**
	 * <p>检查服务器端证书</p>
	 */
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		
	}

	/**
	 * <p>返回受信任的X509证书数组</p>
	 */
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}
