package com.fsm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fsm.metier.DefaultServices;
import com.fsm.models.Offre;
import com.fsm.models.Postulation;


@WebServlet("/postulations")
public class PostulationDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PostulationDetailsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            // Retrieve the specific offer from the database
            List<Postulation> postulations = DefaultServices.getInstance().getPostulationsOfOffer(id);
            request.setAttribute("postulations", postulations);
            System.out.println(postulations);
            Offre offre = DefaultServices.getInstance().getOneOffre(id);
            request.setAttribute("offre", offre);
            request.getRequestDispatcher("postulations.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing offer id");
        } 		 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
