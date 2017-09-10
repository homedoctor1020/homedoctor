package com.dw.controll;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dw.dao.ResourceDao;
import com.dw.dao.impl.ResourceDaoImpl;
import com.dw.model.Resource;

public class UploadServlet extends HttpServlet {

  private static final long serialVersionUID = -7091418255807535366L;


  public UploadServlet() {
    super();
  }

  public void destroy() {
    super.destroy();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }
  @SuppressWarnings("unchecked")
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      String openid = request.getParameter("openid");
      Resource re=new Resource();
      //path=webapps/upload
      String path = getServletContext().getRealPath("/"); 
       path=path.replaceAll("Doctor1020", "");
       path=path.substring(0, path.length()-1);
      System.out.println("path="+path);
      //将上传的文件保存至webapps/upload/${openid}目录下
      String savePath = path+"//upload//"+openid;
	      //this.getServletContext().getRealPath("/upload/"+openid).replaceAll("//Doctor1020", "");
      //上传时生成的临时文件保存目录
      String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
    try {
      // Create a factory for disk-based file items
      DiskFileItemFactory factory = new DiskFileItemFactory();

      // Set factory constraints
      factory.setSizeThreshold(3000 * 1024 * 1024);
      File temp = new File(tempPath);
      File save = new File(savePath);
      if (!temp.exists()) {
          //创建临时目录
	  temp.mkdir();
      }
      if (!save.exists()) {
          //创建临时目录
	  save.mkdir();
      }
      factory.setRepository(temp);

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);

      // Add listener, implement the update method
      upload.setProgressListener(new UploadProgressListener(request));

      // Set overall request size constraint
      upload.setSizeMax(3000 * 1024 * 1024);
      // Parse the request

      List items = upload.parseRequest(request);

      // Process the uploaded items
      Iterator iter = items.iterator();
      while (iter.hasNext()) {
        FileItem item = (FileItem) iter.next();
        if (item.isFormField()) {
            String name = item.getFieldName();  
            String value = item.getString("UTF-8");
          String contentType = item.getContentType();
          boolean isInMemory = item.isInMemory();
          long sizeInBytes = item.getSize();
          re.setType(Integer.parseInt(value));
        } else {
          // Process a file upload
            String fileName = item.getName();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            String size=FormetFileSize(item.getSize());
          SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
          String newFileName =
              df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
          re.setOriginName(fileName);
          re.setNewfileName(newFileName);
          re.setSize(size);
          re.setOpenid(openid);
          ResourceDao resourcedao=new ResourceDaoImpl();
          resourcedao.addResource(re);
          File uploadedFile = new File(save, newFileName);
          if(!uploadedFile.exists()){
            uploadedFile.createNewFile();
          }
          item.write(uploadedFile);
          ResourceDao dao= new ResourceDaoImpl();
          List list=dao.findByOpenid(openid);
          request.setAttribute("list", list);
          request.setAttribute("openid", openid);
	    String mainPage = "listbingli.jsp";
		request.setAttribute("mainPage", mainPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
/**
 * 获得文件的大小转化成 KB、MB单位
 * @param size
 * @return
 */
  
  public static String FormetFileSize(long fileS) {//转换文件大小
      DecimalFormat df = new DecimalFormat("#.00");
      String fileSizeString = "";
      if (fileS < 1024) {
          fileSizeString = df.format((double) fileS) + "B";
      } else if (fileS < 1048576) {
          fileSizeString = df.format((double) fileS / 1024) + "K";
      } else if (fileS < 1073741824) {
          fileSizeString = df.format((double) fileS / 1048576) + "M";
      } else {
          fileSizeString = df.format((double) fileS / 1073741824) + "G";
      }
      return fileSizeString;
  }
  class UploadProgressListener implements ProgressListener {
    private HttpServletRequest request;
    private long megaBytes = -1;
    private DecimalFormat df = new DecimalFormat("#00.0");

    UploadProgressListener(HttpServletRequest request) {
      this.request = request;
    }

    public void update(long bytesRead, long bytesTotal, int items) {
      // if no process, then return
      long mBytes = bytesRead / 1024;
      if (megaBytes == mBytes) {
        return;
      }
      megaBytes = mBytes;

      double percent = (double) bytesRead * 100 / (double) bytesTotal;
      // also can use log to show the progress.
      System.out.println(df.format(percent));
      request.getSession().setAttribute("UPLOAD_PERCENTAGE", df.format(percent));
    }
  };

  public void init() throws ServletException {
    // Put your code here
  }
}
