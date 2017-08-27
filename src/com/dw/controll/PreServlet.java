

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
 * �߼�����ҳ����ת
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
	 * ���Ĺ���Ա����-ʵ��ҳ����ת
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
	 * ��ת���鿴��������
	 * ���openID��Ϊ�գ��鿴��Ӧ��openID���û��Ĳ���
	 * ���openIDΪ�գ��鿴�����˵Ĳ�����¼
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
 * �����û���΢���ǳ�
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
 * �����û���ͷ���ַ
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
 * ��ת���ϴ�����
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
