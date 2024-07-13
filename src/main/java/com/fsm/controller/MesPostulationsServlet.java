package com.fsm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fsm.metier.DefaultServices;
import com.fsm.models.Postulation;
import com.fsm.models.User;

@WebServlet("/MesPostulations")
public class MesPostulationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MesPostulationsServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User demandeur = (User) request.getSession().getAttribute("user");
        
        List<Postulation> postulations = DefaultServices.getInstance().getPostulationsofUser(demandeur);
        
        request.setAttribute("postulations", postulations);

        request.getRequestDispatcher("mespostulations.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
