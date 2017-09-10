package com.dw.controll;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dw.dao.ResourceDao;
import com.dw.dao.impl.ResourceDaoImpl;


/**
 * 删除病历功能的实现
 * @author yongshengXia
 *
 */
public class DeleteBingliServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    
    doPost(req, resp);
}
/**
 * 现将数据从数据中删除，然后将文件删除，然后转到信息页面将删除结果显示给用户
 */
@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	String openid=req.getParameter("openid");
	String filename=req.getParameter("filename");
	ResourceDao dao= new ResourceDaoImpl();
	boolean flag=dao.delete(openid,filename);
	if(flag){
	  
	    //fileSaveRootPath=路径webapps/upload
	      String fileSaveRootPath = getServletContext().getRealPath("/"); 
	      fileSaveRootPath=fileSaveRootPath.replaceAll("Doctor1020", "");
	      fileSaveRootPath=fileSaveRootPath.substring(0, fileSaveRootPath.length()-1);
	       //通过文件名找出文件的所在目录
	        String path = fileSaveRootPath+ "\\" +openid;
	        //得到要下载的文件
	        File file = new File(fileSaveRootPath+ "\\" +openid+ "\\" +filename);
	        if(!file.exists()){
	            System.out.println(openid+"的"+filename+"文件不存在");
	            }
	            else{
	            System.out.println(openid+"的"+filename+"文件存在，删除文件");
	            file.delete();//删除文件
	            req.setAttribute("message", "资源已被删除！！");
	            String mainPage = "message.jsp";
			req.setAttribute("mainPage", mainPage);
			RequestDispatcher dispatcher = req.getRequestDispatcher("main.jsp");
			dispatcher.forward(req, resp);
	            }
	} else{
	    req.setAttribute("message", "该文件不存在");
	    String mainPage = "message.jsp";
		req.setAttribute("mainPage", mainPage);
		RequestDispatcher dispatcher = req.getRequestDispatcher("main.jsp");
		dispatcher.forward(req, resp);
        return;
        }
    }
}
