package com.fsm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fsm.metier.DefaultServices;
import com.fsm.models.User;

@WebServlet("/checkSubscription")
public class VerifierAbonnementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        System.out.println("Checking subscription status for email: " + email);

        boolean isSubscribed = false;
        String message = "User Not Found";

        try {
            User user = DefaultServices.getInstance().getUserByEmail(email);

            if (user != null) {
                isSubscribed = user.getIsSubscribed();
                message = isSubscribed ? "Vous êtes déjà abonnés à notre newsletter." : "Utilisateur non abonné.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "An error occurred while checking subscription status.";
        }

        request.setAttribute("isSubscribed", isSubscribed);
        request.setAttribute("message", message);
        request.getRequestDispatcher("checkSubscriptionResult.jsp").forward(request, response);
    }
}
