package com.weixin.dao.impl;

import java.net.URLEncoder;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.weixin.dao.WeiXinDao;
import com.weixin.method.HttpClientConnectionManager;
import com.weixin.model.OAuthAccessToken;

import com.weixin.model.UserEntity;
import com.weixin.model.useValue;

public class WeiXinDaoImpl implements WeiXinDao{
	public static DefaultHttpClient httpclient;
	private static Logger log=Logger.getLogger(WeiXinDaoImpl.class.getName());
	static {
		httpclient = new DefaultHttpClient();
		httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient); // �����κ�֤���������ͻ���
	}
	/**
	 * ΢��OAuth2.0��Ȩ��Ŀǰ΢��ֻ֧����΢�ſͻ��˷������ӣ�ʵ����Ȩ��
	 * */
	public String getCodeUrl(String appid, String redirect_uri,String scope,String state) throws Exception {
		redirect_uri = URLEncoder.encode(redirect_uri, "utf-8");
		String getCodeUrl=useValue.getCodeUrl.replace("APPID", appid).replace("REDIRECT_URI", redirect_uri).replace("SCOPE", scope).replace("STATE", state);
		log.info("getCodeUrl Value:"+getCodeUrl);
		return getCodeUrl;
	}
	/**
	 * ΢��OAuth2.0��Ȩ-��ȡaccessToken(����ͨ��code��ȡ����ҳ��Ȩaccess_token,�����֧���е�access_token��ͬ��
	 */
	public OAuthAccessToken getOAuthAccessToken(String appid, String secret,String code) throws Exception {
		String getOAuthAccessToken=useValue.getOAuthAccessToken.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
		log.info("getOAuthAccessToken Value:"+getOAuthAccessToken);
		HttpGet get = HttpClientConnectionManager.getGetMethod(getOAuthAccessToken);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		log.info("jsonObject Value:"+jsonObject);
		OAuthAccessToken accessToken=new OAuthAccessToken();
		accessToken.setAccessToken(jsonObject.getString("access_token"));
		accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
		accessToken.setRefreshToken(jsonObject.getString("refresh_token"));
		accessToken.setOpenid(jsonObject.getString("openid"));
		accessToken.setScope(jsonObject.getString("scope"));
		return accessToken;
	}
	/**
	 * ΢��OAuth2.0��Ȩ-ˢ��access_token�������Ҫ��
	 */
	public OAuthAccessToken refershOAuthAccessToken(String appid, String refresh_token) throws Exception {
		String getreferAccessUrl=useValue.getreferAccessUrl.replace("APPID", appid).replace("REFRESH_TOKEN", refresh_token);
		log.info("getreferAccessUrl Value:"+getreferAccessUrl);
		HttpGet get = HttpClientConnectionManager.getGetMethod(getreferAccessUrl);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		OAuthAccessToken accessToken=new OAuthAccessToken();
		accessToken.setAccessToken(jsonObject.getString("access_token"));
		accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
		accessToken.setRefreshToken(jsonObject.getString("refresh_token"));
		accessToken.setOpenid(jsonObject.getString("openid"));
		accessToken.setScope(jsonObject.getString("scope"));
		return accessToken;
	}
	/**
	 * ΢��OAuth2.0��Ȩ-������Ȩƾ֤��access_token���Ƿ���Ч
	 */
	public boolean isAccessTokenValid(String accessToken, String openId) throws Exception {
		String isOAuthAccessToken=useValue.isOAuthAccessToken.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		log.info("isOAuthAccessToken Value:"+isOAuthAccessToken);
		HttpGet get = HttpClientConnectionManager.getGetMethod(isOAuthAccessToken);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		int returnNum=jsonObject.getIntValue("errcode");
		if(returnNum==0){
			return true;
		}
		return false;
	}
	/**
	 * ΢��OAuth2.0��Ȩ-��ȡ�û���Ϣ����ҳ��Ȩ������Ϊsnsapi_userinfo�����ʱ�����߿���ͨ��access_token��openid��ȡ�û���Ϣ��
	 */
	public UserEntity acceptOAuthUserNews(String accessToken, String openId) throws Exception {
		String getOAuthUserNews=useValue.getOAuthUserNews.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		log.info("getOAuthUserNews Value:"+getOAuthUserNews);
		HttpGet get = HttpClientConnectionManager.getGetMethod(getOAuthUserNews);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		UserEntity entity=new UserEntity();
		entity.setOpenid(jsonObject.getString("openid"));
		entity.setNickname(jsonObject.getString("nickname"));
		entity.setSex(jsonObject.getIntValue("sex"));
		entity.setProvince(jsonObject.getString("province"));
		entity.setCity(jsonObject.getString("city"));
		entity.setCountry(jsonObject.getString("country"));
		entity.setHeadimgurl(jsonObject.getString("headimgurl"));
		return entity;
	}
	
}

