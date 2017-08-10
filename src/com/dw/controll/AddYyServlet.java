package com.dw.controll;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dw.dao.YuyueDao;
import com.dw.dao.impl.YuyueDaoImpl;
import com.dw.model.Yuyue;

public class AddYyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Student(stId,stName,stSex,stAge,stTel,stDept,stAddress)
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		Long phone = Long.parseLong(request.getParameter("phone"));
		String jztime = request.getParameter("jztime");
		
		Yuyue yuyue = new Yuyue(name, sex, age, phone, jztime);
		YuyueDao yudao = new YuyueDaoImpl();
		boolean flag = yudao.addYuyue(yuyue);
		if (flag && !(phone == null) && !"".equals(phone)) {
			request.setAttribute("msg", "添加成功!!");
			request.getRequestDispatcher("afteryuyue.jsp").forward(request,
					response);
		} else {
			request.getRequestDispatcher("afteryuyue.jsp").forward(request,
					response);
			System.out.println("添加失败!!");
		}

	}


}
