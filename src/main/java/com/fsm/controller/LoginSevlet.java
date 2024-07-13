package com.fsm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fsm.models.User;
import com.fsm.metier.DefaultServices;

@WebServlet("/login")
public class LoginSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginSevlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("pwd");
	
		User user = null;

		if((username!=null && !username.equals("")) && (password!=null && !password.equals(""))) {
		
		user = DefaultServices.getInstance().login(username, password);
		
		if (user==null) {
		    request.setAttribute("password", "incorrect password");
		    request.getRequestDispatcher("login.jsp").forward(request, response);
		    return ;
		}
		
		request.getSession().setAttribute("Authenticated", true);
		request.getSession().setAttribute("user", user);
		
		response.sendRedirect(request.getContextPath()+"/ServletOffreAfficher");
		return;
		}
		
	}
	

}
