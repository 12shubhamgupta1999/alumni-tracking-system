package com.shubham.ats.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shubham.ats.dao.ATSDAO;
import com.shubham.ats.dao.collegeDAO;
import com.shubham.ats.dao.emailDAO;
import com.shubham.ats.dao.messageDAO;
import com.shubham.ats.dao.noticeDAO;
import com.shubham.ats.dto.userDTO;
//import com.shubham.webapp.utils.Cache;

/**
 * Servlet implementation class AddSellerController
 */
@WebServlet("/sendmessage")
public class AddMessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession(false);
	System.out.println("add notice session userid "+session.getAttribute("sessionuserid"));
	String sessemail = (String) session.getAttribute("sessionemail");
	String sesspwd = (String) session.getAttribute("sessionpwd");
	try {
		userDTO userdto = ATSDAO.login(sessemail, sesspwd);
//		System.out.println("add college userdto"+userdto.toString());
		request.setAttribute("uid", userdto.getUserid());
		request.setAttribute("role", userdto.getRolename());
		request.setAttribute("userdata", userdto.getRights());
		RequestDispatcher rd = request.getRequestDispatcher("sendmessage.jsp");
		rd.forward(request, response);
	} catch (ClassNotFoundException | SQLException | NamingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}	
		
}
	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession(false);
	String sessemail = (String) session.getAttribute("sessionemail");
	String sessuid = (String) session.getAttribute("sessionuserid");
	String sesspwd = (String) session.getAttribute("sessionpwd");
	
	String messagesubject = request.getParameter("subject");
	String desc = request.getParameter("desc");
	String scontact = request.getParameter("scontact");
	String rcontact = request.getParameter("rcontact");
//	String date = request.getParameter("date");
	PrintWriter out = response.getWriter();
	try {
		if(messageDAO.addmessage(sessemail,messagesubject,scontact,rcontact,desc) == true) {
//			if(WebappDAO.startupfinish()==true) {
			userDTO userdto = ATSDAO.login(sessemail, sesspwd);
			request.setAttribute("uid", userdto.getUserid());
			request.setAttribute("role", userdto.getRolename());
			request.setAttribute("userdata", userdto.getRights());
			RequestDispatcher rd = request.getRequestDispatcher("sendmessage.jsp");
			rd.forward(request, response);
			}
//		}
		else {
//		request.setAttribute("notregister", "Unable to register");
			out.println("Unable to register Message");
		}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
