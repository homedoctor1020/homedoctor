package com.weixin.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class YuLan_wx extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    doPost(req, resp);
}
@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	String openid=req.getParameter("openid");
	String filename=req.getParameter("filename");
	String path = getServletContext().getRealPath("/"); 
	       path=path.replaceAll("Doctor1020", "");
	       path=path.substring(0, path.length()-1);
	      System.out.println("path="+path);
	      //文件存储路径
	      String filePath = path+"//upload//"+openid+"//"+filename;
	      System.out.println("文件路徑="+filePath);
	try{
	    File file=new File(filePath);
	    FileInputStream fileInputStream= new FileInputStream(filePath);
	    resp.setHeader("Content-Disposition", "attachment:fileName="+filename);
	    resp.setContentType("multipart/form-data");
	    OutputStream out=resp.getOutputStream();
	    IOUtils.write(IOUtils.toByteArray(fileInputStream),out);
	    
	}catch(Exception e){
	    e.printStackTrace();
	    
	}
    }
}
