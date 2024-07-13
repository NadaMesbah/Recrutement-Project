package com.fsm.controller;

import com.fsm.metier.DefaultServices;
import com.fsm.models.Responsabilite;
import com.fsm.models.Qualification;
import com.fsm.models.Attente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
    name = "ServletDelete",
    urlPatterns = {
        "/ServletResponsabiliteSupprimer",
        "/ServletQualificationSupprimer",
        "/ServletAttenteSupprimer"
    }
)
public class ServletDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletDelete() {
        super();
    }

    private void deleteResponsabilite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Responsabilite res = DefaultServices.getInstance().getOneResponsabilite(id);
        if (res != null) {
            DefaultServices.getInstance().deleteResponsabilite(res);
            response.getWriter().write("Responsibility deleted");
        }
    }

    private void deleteQualification(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Qualification qual = DefaultServices.getInstance().getOneQualification(id);
        if (qual != null) {
            DefaultServices.getInstance().deleteQualification(qual);
            response.getWriter().write("Qualification deleted");
        }
    }

    private void deleteAttente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Attente att = DefaultServices.getInstance().getOneAttente(id);
        if (att != null) {
            DefaultServices.getInstance().deleteAttente(att);
            response.getWriter().write("Expectation deleted");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/ServletResponsabiliteSupprimer":
                deleteResponsabilite(request, response);
                break;
            case "/ServletQualificationSupprimer":
                deleteQualification(request, response);
                break;
            case "/ServletAttenteSupprimer":
                deleteAttente(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
