package com.weixin.OAuth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.dw.dao.ResourceDao;
import com.dw.dao.impl.ResourceDaoImpl;
import com.weixin.dao.impl.WeiXinDaoImpl;
import com.weixin.model.OAuthAccessToken;
import com.weixin.model.UserEntity;
import com.weixin.model.useValue;

@SuppressWarnings("serial")
public class OAuthAPIServlet extends HttpServlet {
    private static Logger log = Logger
	    .getLogger(OAuthAPIServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=utf-8");
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	PrintWriter write = response.getWriter();
	String code = request.getParameter("code");
	String filetype = request.getParameter("filetype");
	log.info("code:" + code);
	WeiXinDaoImpl dao = new WeiXinDaoImpl();
	UserEntity entity = null;
	try {
	    OAuthAccessToken oac = dao.getOAuthAccessToken(useValue.AppId,
		    useValue.AppSecret, code);// 通过code换取网页授权access_token
	    log.info("--------------------" + oac.getAccessToken() + ";"
		    + oac.getRefreshToken() + ";" + oac.getScope() + ";"
		    + oac.getOpenid());
	    OAuthAccessToken oacd = dao.refershOAuthAccessToken(useValue.AppId,
		    oac.getRefreshToken());// 刷新access_token
	    log.info("--------------------" + oacd.getAccessToken() + ";"
		    + oacd.getRefreshToken() + ";" + oacd.getScope()
		    + ";Openid:" + oacd.getOpenid());
	    entity = dao.acceptOAuthUserNews(oacd.getAccessToken(),
		    oacd.getOpenid());// 获取用户信息
	    log.info("--------------------" + entity.getNickname() + ";"
		    + entity.getCountry());
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	log.info("-------------------sdffsd-" + entity.getNickname() + ";"
		+ entity.getCountry());
	//request.setAttribute("user", entity);
	//获得用户的openID
	String openid = entity.getOpenid();
	ResourceDao redao = new ResourceDaoImpl();
	//获得用户的病历记录
	String filename= redao.findfileByOpenid(openid,filetype);
	request.setAttribute("openid", openid);
	request.setAttribute("filename", filename);
	request.getRequestDispatcher("/wxshowfile.jsp").forward(request, response);
	
    }

}
