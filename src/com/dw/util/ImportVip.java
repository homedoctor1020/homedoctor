package com.dw.util;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.dw.model.Vip;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 将关注公众号的用户信息获取到放入Arraylist
 * @author yongshengXia
 *
 */
public class ImportVip {

    
    /**
     * 获取关注公众号的用户openID列表
     * @param access_token
     * @return 用户openID 的列表
     * @throws ParseException
     */
    public static List getVipOpenid(String access_token) throws ParseException{
	String VipOpenidurl="https://api.weixin.qq.com/cgi-bin/user/get?access_token="+access_token;
	JSONObject jsonObject = CommonUtil.httpsRequest(VipOpenidurl, "GET", null);
	JSONObject json = jsonObject.getJSONObject("data");
	JSONArray jsonArray=json.getJSONArray("openid");
	List<String> openid=new ArrayList<String>();
	openid=jsonArray.toList(jsonArray);
	//System.out.println(openid.toString());
	return openid;
	
    }
    /**
     * main函数用于测试单个功能
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
	List<String>openid=getVipOpenid(CommonUtil.getAccessToken());
	System.out.println(openid.toString());
	getVipInfo();
    }
    /**
     * 获取用户的信息列表
     * @return
     * @throws ParseException
     */
    public static List<Vip> getVipInfo() throws ParseException{
	String VipInfourl="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	String access_token=CommonUtil.getAccessToken();
	List<String> openid=getVipOpenid(CommonUtil.getAccessToken());
	JSONObject jsonObject=new JSONObject();
	List<Vip> viplist=new ArrayList<Vip>();
	String url;
	for (int i = 0; i < openid.size(); i++) {
	    url=VipInfourl.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid.get(i));
	    jsonObject = CommonUtil.httpsRequest(url, "GET", null);
	    if(jsonObject!=null){
		Vip vipinfo=new Vip();
		vipinfo.setCity(jsonObject.getString("city"));
		vipinfo.setCountry(jsonObject.getString("country"));
		vipinfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
		vipinfo.setLanguage(jsonObject.getString("language"));
		vipinfo.setNickname(jsonObject.getString("nickname"));
		vipinfo.setSex(jsonObject.getInt("sex"));
		vipinfo.setOpenId(jsonObject.getString("openid"));
		vipinfo.setSubscribe(jsonObject.getInt("subscribe"));
		vipinfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
		vipinfo.setProvince(jsonObject.getString("province"));
//		System.out.println(openid.get(i)+"="+jsonObject.toString());
		
		viplist.add(vipinfo);
	    }
	}
	System.out.println(viplist.toString());
	return viplist;
    }
}
