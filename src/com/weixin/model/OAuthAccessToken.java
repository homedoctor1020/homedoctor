package com.weixin.model;

/**
 * ΢��OAuth2.0��Ȩ-��ȡaccessToken
 * */
public class OAuthAccessToken {
    private String accessToken; // ��ҳ��Ȩ�ӿڵ���ƾ֤,ע�⣺��access_token�����֧�ֵ�access_token��ͬ
    private int expiresIn;//  access_token�ӿڵ���ƾ֤��ʱʱ�䣬��λ���룩
    private String refreshToken;//�û�ˢ��access_token
    private String openid;//�û�Ψһ��ʶ����ע�⣬��δ��ע���ں�ʱ���û����ʹ��ںŵ���ҳ��Ҳ�����һ���û��͹��ں�Ψһ��OpenID
    private String scope;//�û���Ȩ��������ʹ�ö��ţ�,���ָ� 
	
    
    public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
    
  
}
