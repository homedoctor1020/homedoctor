package com.dw.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;



/**https请求的信任管理器
 * @author crossnote
 *
 */
public class MyX509TrustManager implements X509TrustManager {

    public void checkClientTrusted(X509Certificate[] chain, String authType)
	    throws CertificateException {
	// TODO Auto-generated method stub

    }

    public void checkServerTrusted(X509Certificate[] chain, String authType)
	    throws CertificateException {
	// TODO Auto-generated method stub

    }

    public X509Certificate[] getAcceptedIssuers() {
	// TODO Auto-generated method stub
	return null;
    }

}
