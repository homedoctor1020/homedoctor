

package com.dw.controll;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dw.dao.ResourceDao;
import com.dw.dao.VipDao;
import com.dw.dao.impl.ResourceDaoImpl;
import com.dw.dao.impl.UserDaoImpl;
import com.dw.dao.impl.VipDaoImpl;
import com.dw.model.User;

/**
 * 逻辑处理及页面跳转
 * @author DY1101shaoyuxian
 * 
 */
public class PreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = (String) request.getParameter("method");
		if ("upload".equals(method)) {
		    upload(request, response);
		} else if ("getimage".equals(method)) {
			getImage(request, response);
		}else if("getnickname".equals(method)){
			getNickname(request, response);
		}else if("lookbingli".equals(method)){
			lookbingli(request, response);
		}else if("preupdatetepwd".equals(method)){
			preupdatetepwd(request, response);
		}
	
	}
	/**
	 * 更改管理员密码-实现页面跳转
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void preupdatetepwd(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	    String mainPage = "updatepwd.jsp";
		request.setAttribute("mainPage", mainPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
	    
	}

	/**
	 * 跳转到查看病历界面
	 * 如果openID不为空，查看对应的openID的用户的病历
	 * 如果openID为空，查看所有人的病历记录
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void lookbingli(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	    // TODO Auto-generated method stub
	    String openid = request.getParameter("openid");
	    ResourceDao dao= new ResourceDaoImpl();
	    List list=null;
	    if(openid!=null){
		list=dao.findByOpenid(openid);
	    }else{
		list=dao.selectAll();
	    }
	    request.setAttribute("list", list);
	          request.setAttribute("openid", openid);
		    String mainPage = "listbingli.jsp";
			request.setAttribute("mainPage", mainPage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);
	}
/**
 * 返回用户的微信昵称
 * @param request
 * @param response
 * @throws IOException
 */
	private void getNickname(HttpServletRequest request,
		HttpServletResponse response) throws IOException {
	    // TODO Auto-generated method stub
	    
	    String openid = request.getParameter("openid");
	    PrintWriter write=response.getWriter();
	    VipDao dao=new VipDaoImpl();
	    String nickname=dao.getNicknameByopenid(openid);
	    if(nickname!=null){
		 write.write(nickname);
	    }else{
		write.write("");
	    }
	   
	    write.flush();
	    write.close();
	    
	}
/**
 * 返回用户的头像地址
 * @param request
 * @param response
 * @throws IOException
 */
	private void getImage(HttpServletRequest request,
		HttpServletResponse response) throws IOException {
	    // TODO Auto-generated method stub
	    String openid = request.getParameter("openid");
	    VipDao dao=new VipDaoImpl();
	    PrintWriter write=response.getWriter();
	    String image =dao.getImageByopenid(openid);
	    if(image!=null){
	    write.write(image);
	    }else{
		write.write("");
	    }
	    write.flush();
	    write.close();
	}
/**
 * 跳转到上传界面
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	public void upload(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	    String openid = request.getParameter("openid");
	    request.setAttribute("openid", openid);
	    String mainPage = "upload.jsp";
		request.setAttribute("mainPage", mainPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
	}
	
}
