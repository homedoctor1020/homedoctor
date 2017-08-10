package com.dw.controll;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dw.dao.VipDao;
import com.dw.dao.impl.VipDaoImpl;
import com.dw.model.Vip;
import com.dw.util.ImportVip;

public class RefreshVipListServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    // TODO Auto-generated method stub
    doPost(req, resp);
}
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// TODO Auto-generated method stub
	try {
	    List<Vip> viplist=ImportVip.getVipInfo();
	    VipDao dao = new VipDaoImpl();
	    for (int i = 0; i < viplist.size(); i++) {
		
		dao.addVip(viplist.get(i));
		
	    }
	    List list = dao.vipSelect();
		String mainPage="displaystudent.jsp";
		request.setAttribute("list", list);
		request.setAttribute("mainPage", mainPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
