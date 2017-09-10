package com.weixin.dao;


import com.weixin.model.OAuthAccessToken;
import com.weixin.model.UserEntity;


public interface WeiXinDao {
	/**
	 * ΢��OAuth2.0��Ȩ��Ŀǰ΢��ֻ֧����΢�ſͻ��˷������ӣ�ʵ����Ȩ��
	 * */
	public String getCodeUrl(String appid, String redirect_uri,String scope,String state) throws Exception;
	/**
	 * ΢��OAuth2.0��Ȩ-��ȡaccessToken(����ͨ��code��ȡ����ҳ��Ȩaccess_token,�����֧���е�access_token��ͬ��
	 */
	public OAuthAccessToken getOAuthAccessToken(String appid, String secret,String code) throws Exception;
	/**
	 * ΢��OAuth2.0��Ȩ-ˢ��access_token�������Ҫ��
	 */
	public OAuthAccessToken refershOAuthAccessToken(String appid, String refresh_token) throws Exception;
	/**
	 * ΢��OAuth2.0��Ȩ-������Ȩƾ֤��access_token���Ƿ���Ч
	 */
	public boolean isAccessTokenValid(String accessToken, String openId) throws Exception;
	/**
	 * ΢��OAuth2.0��Ȩ-��ȡ�û���Ϣ����ҳ��Ȩ������Ϊsnsapi_userinfo�����ʱ�����߿���ͨ��access_token��openid��ȡ�û���Ϣ��
	 */
	public UserEntity acceptOAuthUserNews(String accessToken, String openId) throws Exception;
}

