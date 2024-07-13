package com.fsm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fsm.metier.DefaultServices;
import com.fsm.models.User;
import com.fsm.util.EmailUtil;

@WebServlet("/subscribe")
public class NewsLetterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        System.out.println("Received email: " + email);

        String message = null;
        String alertType = null;

        if (email == null || email.isEmpty()) {
            message = "La saisie de l'email est nécessaire !";
            alertType = "error";
        } else {
            try {
                User user = DefaultServices.getInstance().getUserByEmail(email);
                
                if (user == null) {
                	String subject = "Confirmation d'abonnement";
                    String messageContent = "Chèr(e) " + email + ",\n\nVous êtes abonnés à notre newsletter avec succès, Merci !";
                    EmailUtil.sendEmail(email, subject, messageContent);

                    message = "Vous êtes abonnés à notre newsletter avec succès!";
                    alertType = "success";
                    request.setAttribute("message", message);
                    request.setAttribute("alertType", alertType);
                    request.getRequestDispatcher("result.jsp").forward(request, response);
                    return;
                }

                if (user.getIsSubscribed()) {
                    message = "Vous êtes déjà abonnés à notre newsletter.";
                    alertType = "info";
                    
                } else{
                	
                    user.setIsSubscribed(true); // Set isSubscribed to true 
                    DefaultServices.getInstance().updateUser(user); // Update the user
                    
                    // Send subscription confirmation email
                    String subject = "Confirmation d'abonnement";
                    String messageContent = "Chèr(e) " + user.getUsername() + ",\n\nVous êtes abonnés à notre newsletter avec succès, Merci !";
                    EmailUtil.sendEmail(user.getEmail(), subject, messageContent);

                    message = "Vous êtes abonnés à notre newsletter avec succès!";
                    alertType = "success";
                }
                 
            } catch (Exception e) {
                message = "Une erreur s'est produite lors de l'abonnement.";
                alertType = "error";
                e.printStackTrace(); // Print the stack trace to debug the exception
            }
        }

        request.setAttribute("message", message);
        request.setAttribute("alertType", alertType);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
