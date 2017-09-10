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
 * ɾ���������ܵ�ʵ��
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
 * �ֽ����ݴ�������ɾ����Ȼ���ļ�ɾ����Ȼ��ת����Ϣҳ�潫ɾ�������ʾ���û�
 */
@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	String openid=req.getParameter("openid");
	String filename=req.getParameter("filename");
	ResourceDao dao= new ResourceDaoImpl();
	boolean flag=dao.delete(openid,filename);
	if(flag){
	  
	    //fileSaveRootPath=·��webapps/upload
	      String fileSaveRootPath = getServletContext().getRealPath("/"); 
	      fileSaveRootPath=fileSaveRootPath.replaceAll("Doctor1020", "");
	      fileSaveRootPath=fileSaveRootPath.substring(0, fileSaveRootPath.length()-1);
	       //ͨ���ļ����ҳ��ļ�������Ŀ¼
	        String path = fileSaveRootPath+ "\\" +openid;
	        //�õ�Ҫ���ص��ļ�
	        File file = new File(fileSaveRootPath+ "\\" +openid+ "\\" +filename);
	        if(!file.exists()){
	            System.out.println(openid+"��"+filename+"�ļ�������");
	            }
	            else{
	            System.out.println(openid+"��"+filename+"�ļ����ڣ�ɾ���ļ�");
	            file.delete();//ɾ���ļ�
	            req.setAttribute("message", "��Դ�ѱ�ɾ������");
	            String mainPage = "message.jsp";
			req.setAttribute("mainPage", mainPage);
			RequestDispatcher dispatcher = req.getRequestDispatcher("main.jsp");
			dispatcher.forward(req, resp);
	            }
	} else{
	    req.setAttribute("message", "���ļ�������");
	    String mainPage = "message.jsp";
		req.setAttribute("mainPage", mainPage);
		RequestDispatcher dispatcher = req.getRequestDispatcher("main.jsp");
		dispatcher.forward(req, resp);
        return;
        }
    }
}
