package com.fsm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fsm.metier.DefaultServices;
import com.fsm.models.Postulation;

@WebServlet("/deletePostulation")
public class DeletePostulationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeletePostulationServlet() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing postulation id");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            Postulation p = DefaultServices.getInstance().getOnePostulation(id);
            DefaultServices.getInstance().deletePostulation(p);
         		
    		response.sendRedirect(request.getContextPath()+"/MesPostulations");
          
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid postulation id format");
        }
    }
}
