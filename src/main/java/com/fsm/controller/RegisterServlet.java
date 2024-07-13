package com.fsm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fsm.models.User;
import com.fsm.metier.DefaultServices;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("pwd1");
		String confirmPassword = request.getParameter("pwd2");
		
		if((username!=null && !username.equals("")) && (nom!=null && !nom.equals("")) && (prenom!=null && !prenom.equals("")) && (email!=null && !email.equals("")) && (password!=null && !password.equals("")) && (confirmPassword!=null && !confirmPassword.equals(""))) {
			if(!password.equals(confirmPassword)) {
				request.setAttribute("validation", "passwords are not matching");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
			User user = new User(nom, prenom, username, password, email);
		    user = DefaultServices.getInstance().register(user);
		    
		   if (user==null) {
			   request.setAttribute("validation", "username already exists");
				request.getRequestDispatcher("register.jsp").forward(request, response);
		   }
		   request.setAttribute("user", user);
		   request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
