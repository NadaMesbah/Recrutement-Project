package com.fsm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fsm.metier.DefaultServices;
import com.fsm.models.Attente;
import com.fsm.models.Offre;
import com.fsm.models.Qualification;
import com.fsm.models.Responsabilite;
import com.fsm.models.TypeOffre;
import com.fsm.models.User;
import com.fsm.util.EmailUtil;

@WebServlet(
    name = "ServletOffre",
    urlPatterns = {"/ServletOffreAfficher", "/ServletOffreModifier", "/ServletOffreAjouter", 
                   "/ServletOffreSupprimer"}
)
public class ServletOffre extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletOffre() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
	    switch (path) {
	        case "/ServletOffreAfficher":
	        	afficherOffres(request, response);
	            break;
	        case "/ServletOffreModifier":
	        	showOffreForEdit(request, response);
	            break;
	        case "/ServletOffreAjouter":
	        	request.getRequestDispatcher("addOffre.jsp").forward(request, response);
	            break;
	        case "/ServletOffreSupprimer":
	        	supprimerOffre(request, response);
	            break;
	        default:
	            break;
	    }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String path = request.getServletPath();
	    switch (path) {
	        case "/ServletOffreAjouter":
	        	ajouterOffre(request, response);
	            break;
	        case "/ServletOffreModifier":
	        	modifierOffre(request, response);
	            break;
	        default:
	            break;
	    }
	}

 
    private void afficherOffres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	User user = (User) request.getSession().getAttribute("user");
    		List<Offre> offres = DefaultServices.getInstance().getAllOffres();
    	    request.setAttribute("offres", offres);
    	    request.setAttribute("user", user);
    	    request.getRequestDispatcher("listOffres.jsp").forward(request, response);  
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid filiere parameter");
        }
    }

    private void showOffreForEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Offre offre = DefaultServices.getInstance().getOneOffre(id);
        request.setAttribute("offre", offre);
        request.getRequestDispatcher("editOffre.jsp").forward(request, response);
    }
    
    public int typeOffre(String s) {
		switch (s) {
		case "Temporary":
			return 5;
		case "Freelance":
			return 4;
		case "Stage":
			return 3;
		case "Part-Time":
			return 2;
		case "Full-Time":
			return 1;
		default:
			return 0;
		}
	}
    
    private void sendNewsletter(Offre offre) {
        List<User> users = DefaultServices.getInstance().getAllSubscribedUsers();

        String subject = "Nouvelle Offre: " + offre.getTitre();
        StringBuilder body = new StringBuilder();
        body.append("Une nouvelle offre a été ajoutée.\n\n");
        body.append("Titre: ").append(offre.getTitre()).append("\n");
        body.append("Description: ").append(offre.getDescription()).append("\n\n");

        body.append("Responsabilités:\n");
        for (Responsabilite responsabilite : offre.getResponsabilites()) {
            body.append("- ").append(responsabilite.getDescription()).append("\n");
        }
        body.append("\n"); 

        body.append("Qualifications:\n");
        for (Qualification qualification : offre.getQualifications()) {
            body.append("- ").append(qualification.getDescription()).append("\n");
        }
        body.append("\n"); 
        
        body.append("Attentes:\n");
        for (Attente attente : offre.getAttentes()) {
            body.append("- ").append(attente.getDescription()).append("\n");
        }
        body.append("\n");


        body.append("Cordialement,\n");
        body.append("L'équipe de recrutement");

        for (User user : users) {
            EmailUtil.sendEmail(user.getEmail(), subject, body.toString());
        }
    }

    
    private void ajouterOffre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Responsabilite> responsabilites = new ArrayList<>();
            List<Qualification> qualifications = new ArrayList<>();
            List<Attente> attentes = new ArrayList<>();

            String titre = request.getParameter("titre");
            String description = request.getParameter("description");
            String type = request.getParameter("inputType");

            // Récupérer les nouvelles informations depuis la requête
            String[] responsabilites_p = request.getParameterValues("responsabilites");
            String[] qualifications_p = request.getParameterValues("qualifications");
            String[] attentes_p = request.getParameterValues("attentes");

            if (responsabilites_p != null) {
                for (String resp : responsabilites_p) {
                    Responsabilite r = DefaultServices.getInstance().addResponsabilite(resp);
                    responsabilites.add(r);
                }
            }

            if (qualifications_p != null) {
                for (String qual : qualifications_p) {
                    Qualification q = DefaultServices.getInstance().addQualification(qual);
                    qualifications.add(q);
                }
            }

            if (attentes_p != null) {
                for (String at : attentes_p) {
                    Attente a = DefaultServices.getInstance().addAttente(at);
                    attentes.add(a);
                }
            }

            int typeId = typeOffre(type);

            if (titre.isEmpty() || description.isEmpty() || type.isEmpty()) {
                request.setAttribute("message", "All fields are required");
                request.getRequestDispatcher("addOffre.jsp").forward(request, response);
                return;
            }

            User createur = (User) request.getSession().getAttribute("user");

            TypeOffre typeOffre = DefaultServices.getInstance().getOneTypeOffre(typeId);

            if (typeOffre == null) {
                request.setAttribute("message", "Invalid offer type");
                request.getRequestDispatcher("addOffre.jsp").forward(request, response);
                return;
            }

            Offre offre = new Offre(titre, description, createur, typeOffre, responsabilites, qualifications, attentes);

            // Ajouter l'offre à la base de données
            Offre newOffre = DefaultServices.getInstance().addOffre(offre);

            if (newOffre == null) {
                request.setAttribute("message", "Please try again later");
                request.getRequestDispatcher("addOffre.jsp").forward(request, response);
            } else {
                // Envoyer la newsletter après avoir ajouté l'offre
                sendNewsletter(newOffre);
                response.sendRedirect(request.getContextPath() + "/ServletOffreAfficher");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

//    
//    private void ajouterOffre(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//            List<Responsabilite> responsabilites = new ArrayList<>();
//            List<Qualification> qualifications = new ArrayList<>();
//            List<Attente> attentes = new ArrayList<>();
//
//            String titre = request.getParameter("titre");
//            String description = request.getParameter("description");
//            String type = request.getParameter("inputType");
//
//            // Récupérer les nouvelles informations depuis la requête
//            String[] responsabilites_p = request.getParameterValues("responsabilites");
//            String[] qualifications_p = request.getParameterValues("qualifications");
//            String[] attentes_p = request.getParameterValues("attentes");
//
//            if (responsabilites_p != null) {
//                for (String resp : responsabilites_p) {
//                    Responsabilite r = DefaultServices.getInstance().addResponsabilite(resp);
//                    responsabilites.add(r);
//                }
//            }
//
//            if (qualifications_p != null) {
//                for (String qual : qualifications_p) {
//                    Qualification q = DefaultServices.getInstance().addQualification(qual);
//                    qualifications.add(q);
//                }
//            }
//
//            if (attentes_p != null) {
//                for (String at : attentes_p) {
//                    Attente a = DefaultServices.getInstance().addAttente(at);
//                    attentes.add(a);
//                }
//            }
//
//            int typeId = typeOffre(type);
//
//            if (titre.isEmpty() || description.isEmpty() || type.isEmpty()) {
//                request.setAttribute("message", "All fields are required");
//                request.getRequestDispatcher("addOffre.jsp").forward(request, response);
//                return;
//            }
//
//            User createur = (User) request.getSession().getAttribute("user");
//
//            TypeOffre typeOffre = DefaultServices.getInstance().getOneTypeOffre(typeId);
//
//            if (typeOffre == null) {
//                request.setAttribute("message", "Invalid offer type");
//                request.getRequestDispatcher("addOffre.jsp").forward(request, response);
//                return;
//            }
//
//            Offre offre = new Offre(titre, description, createur, typeOffre, responsabilites, qualifications, attentes);
//
//            // Ajouter l'offre à la base de données
//            Offre newOffre = DefaultServices.getInstance().addOffre(offre);
//
//            if (newOffre == null) {
//                request.setAttribute("message", "Please try again later");
//                request.getRequestDispatcher("addOffre.jsp").forward(request, response);
//            } else {
//                response.sendRedirect(request.getContextPath() + "/ServletOffreAfficher");
//            }
//        } catch (NumberFormatException e) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
//        } catch (Exception e) {
//            throw new IOException(e);
//        }
//    }

    private void modifierOffre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String id = request.getParameter("id");
            String titre = request.getParameter("titre");
            String description = request.getParameter("description");
            String type = request.getParameter("inputType");

            List<Responsabilite> responsabilites = new ArrayList<Responsabilite>();
            List<Qualification> qualifications = new ArrayList<Qualification>();
            List<Attente> attentes = new ArrayList<Attente>();

            // Récupérer les nouvelles informations depuis la requête
            String[] responsabilites_p = request.getParameterValues("responsabilites");
            String[] qualifications_p = request.getParameterValues("qualifications");
            String[] attentes_p = request.getParameterValues("attentes");

            for (String resp : responsabilites_p) {
                Responsabilite r = DefaultServices.getInstance().addResponsabilite(resp);
                responsabilites.add(r);
            }

            for (String qual : qualifications_p) {
                Qualification q = DefaultServices.getInstance().addQualification(qual);
                qualifications.add(q);
            }

            for (String at : attentes_p) {
                Attente a = DefaultServices.getInstance().addAttente(at);
                attentes.add(a);
            }

            User createur = (User) request.getSession().getAttribute("user");

            int typeId = typeOffre(type);
            TypeOffre typeOffre = DefaultServices.getInstance().getOneTypeOffre(typeId);

            if (typeOffre == null) {
                request.setAttribute("message", "Invalid offer type");
                request.getRequestDispatcher("editOffre.jsp").forward(request, response);
                return;
            }

            Offre offre = new Offre(Integer.parseInt(id), titre, description, createur, typeOffre, responsabilites, qualifications, attentes);

            Offre updatedOffre = DefaultServices.getInstance().updateOffre(offre);

            if (updatedOffre == null) {
                request.setAttribute("message", "Please try again later");
                request.getRequestDispatcher("editOffre.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/ServletOffreAfficher");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
        } catch (Exception e) {
            throw new IOException(e);
        }
    }


    private void supprimerOffre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
		Offre offre = null;
		offre = DefaultServices.getInstance().getOneOffre(id);
		DefaultServices.getInstance().deleteOffre(offre);		
		response.sendRedirect(request.getContextPath()+"/ServletOffreAfficher");
 	}

}
