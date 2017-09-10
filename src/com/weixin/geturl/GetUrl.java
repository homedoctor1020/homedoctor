package com.weixin.geturl;

import com.weixin.dao.impl.WeiXinDaoImpl;
import com.weixin.model.useValue;

public class GetUrl {

	public static void main(String[] args){

		
		String url="http://www.1020doctor.cn/Doctor1020/OAuthAPIServlet?filetype=1";
		WeiXinDaoImpl dao=new WeiXinDaoImpl();
		String pathUrl=null;
		try {
			pathUrl=dao.getCodeUrl(useValue.AppId,url,"snsapi_userinfo", "state");
		} catch (Exception e) {
			
		}
		System.out.println("pathurl="+pathUrl);
	}
}
