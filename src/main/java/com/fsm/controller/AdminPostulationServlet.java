package com.fsm.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fsm.metier.DefaultServices;
import com.fsm.models.Postulation;
import com.fsm.util.EmailUtil;

@WebServlet("/AdminPostulation")
public class AdminPostulationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminPostulationServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");

        if (action == null || idParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action or id parameter.");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            Postulation postulation = DefaultServices.getInstance().getOnePostulation(id);
            Postulation updated = null;

            if (postulation != null) {
                switch (action) {
                    case "accept":
                        postulation.setStatus("accepted");
                        updated = DefaultServices.getInstance().updatePostulation(postulation);
                        if (updated != null) sendAcceptanceEmail(postulation);
                        break;
                    case "cancel":
                        postulation.setStatus("canceled");
                        updated = DefaultServices.getInstance().updatePostulation(postulation);
                        if (updated != null) sendCancellationEmail(postulation);
                        break;
                    case "scheduleInterview":
                        updated = scheduleInterview(request, postulation);
                        if (updated != null) sendInterviewScheduledEmail(postulation);
                        break;
                    case "setInterviewOutcome":
                        setInterviewOutcome(request, postulation);
                        updated = DefaultServices.getInstance().updatePostulation(postulation);
                        if (updated != null) sendInterviewOutcomeEmail(postulation);
                        break;
                    default:
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
                        return;
                }

                if (updated != null) {
                    response.sendRedirect(request.getContextPath() + "/ServletOffreAfficher");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update postulation status.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Postulation not found.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid postulation ID.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the postulation.");
        }
    }

    private void sendAcceptanceEmail(Postulation postulation) {
        try {
            String to = postulation.getDemandeur().getEmail();
            String subject = "Postulation acceptée";
            String body = "Chèr(e) " + postulation.getDemandeur().getPrenom() + ",\n\n"
                          + "Votre candidature pour le poste de : " + postulation.getOffre().getTitre() + " a été acceptée.\n"
                          + "Nous vous contacterons bientôt avec plus de détails.\n\n"
                          + "Cordialement,\n"
                          + "Équipe de recrutement";
            EmailUtil.sendEmail(to, subject, body);
            System.out.println("Acceptance email sent to: " + to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendCancellationEmail(Postulation postulation) {
        try {
            String to = postulation.getDemandeur().getEmail();
            String subject = "Postulation refusée";
            String body = "Chèr(e) " + postulation.getDemandeur().getPrenom() + ",\n\n"
                          + "Nous avons le regret de vous informer que votre candidature pour le poste de " + postulation.getOffre().getTitre() + " a été refusée.\n"
                          + "Merci de l'intérêt que vous portez à notre entreprise.\n\n"
                          + "Meilleures salutations,\n"
                          + "Équipe de recrutement";
            EmailUtil.sendEmail(to, subject, body);
            System.out.println("Cancellation email sent to: " + to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendInterviewScheduledEmail(Postulation postulation) {
        try {
            String to = postulation.getDemandeur().getEmail();
            String subject = "Entretien programmé";
            String body = "Chèr(e) " + postulation.getDemandeur().getPrenom() + ",\n\n"
                          + "Votre entretien pour le poste de " + postulation.getOffre().getTitre() + " a été programmé.\n"
                          + "Date: " + postulation.getInterviewDate() + "\n"
                          + "Heure: " + postulation.getInterviewTime() + "\n"
                          + "Lieu: " + postulation.getInterviewLocation() + "\n\n"
                          + "Cordialement,\n"
                          + "Équipe de recrutement";
            EmailUtil.sendEmail(to, subject, body);
            System.out.println("Interview scheduled email sent to: " + to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendInterviewOutcomeEmail(Postulation postulation) {
        try {
            String to = postulation.getDemandeur().getEmail();
            String subject;
            String body;

            if ("hired".equals(postulation.getInterviewOutcome())) {
                subject = "Félicitations ! Vous êtes embauché(e)";
                body = "Chèr(e) " + postulation.getDemandeur().getPrenom() + ",\n\n"
                        + "Nous avons le plaisir de vous informer que votre candidature pour le poste de " + postulation.getOffre().getTitre() + " a été acceptée.\n"
                        + "Bienvenue dans notre équipe !\n\n"
                        + "Cordialement,\n"
                        + "Équipe de recrutement";
            } else {
                subject = "Résultat de votre entretien";
                body = "Chèr(e) " + postulation.getDemandeur().getPrenom() + ",\n\n"
                        + "Nous vous remercions d'avoir passé l'entretien pour le poste de " + postulation.getOffre().getTitre() + ".\n"
                        + "Malheureusement, nous avons décidé de ne pas donner suite à votre candidature.\n"
                        + "Merci de l'intérêt que vous portez à notre entreprise.\n\n"
                        + "Cordialement,\n"
                        + "Équipe de recrutement";
            }

            EmailUtil.sendEmail(to, subject, body);
            System.out.println("Interview outcome email sent to: " + to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Postulation scheduleInterview(HttpServletRequest request, Postulation postulation) {
        try {
            String interviewDateStr = request.getParameter("interviewDate");
            String interviewTimeStr = request.getParameter("interviewTime");
            String interviewLocation = request.getParameter("interviewLocation");

            if (interviewDateStr != null && interviewTimeStr != null && interviewLocation != null) {
                LocalDate interviewDate = LocalDate.parse(interviewDateStr);
                LocalTime interviewTime = LocalTime.parse(interviewTimeStr);
                postulation.setInterviewDate(interviewDate);
                postulation.setInterviewTime(interviewTime);
                postulation.setInterviewLocation(interviewLocation);
                return DefaultServices.getInstance().updatePostulation(postulation);
            } else {
                throw new IllegalArgumentException("Missing interview details.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setInterviewOutcome(HttpServletRequest request, Postulation postulation) {
        String interviewOutcome = request.getParameter("interviewOutcome");
        String candidateStatus = "rejected"; 

        if ("hired".equals(interviewOutcome)) {
            candidateStatus = "hired";
        }

        postulation.setInterviewOutcome(interviewOutcome);
        postulation.setCandidateStatus(candidateStatus);
    }

}
