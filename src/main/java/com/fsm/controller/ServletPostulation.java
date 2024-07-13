package com.fsm.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fsm.metier.DefaultServices;
import com.fsm.models.Offre;
import com.fsm.models.Postulation;
import com.fsm.models.User;

@WebServlet("/postuler")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ServletPostulation extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "uploads";

    public ServletPostulation() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 // Capture the id parameter from the URL
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            // Retrieve the specific offer from the database
            Offre offre =  DefaultServices.getInstance().getOneOffre(id);
            if (offre != null) {
                // Set the offer as a request attribute
                request.setAttribute("id_offre", id);
                request.setAttribute("offre", offre);
                // Forward the request to the JSP page
                request.getRequestDispatcher("postuler.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Offre not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing offer id");
        } 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        Postulation p = null; 
        int id = 5;

        File uploadDir = new File(uploadFilePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        try {
            // Handle CV upload
            Part filePart = request.getPart("cv");
            String cvFileName = getFileName(filePart);
            File cvFile = new File(uploadDir, cvFileName);
            try (var inputStream = filePart.getInputStream()) {
                Files.copy(inputStream, cvFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            // Handle LM upload
            Part filePart2 = request.getPart("lm");
            String lmFileName = getFileName(filePart2);
            File lmFile = new File(uploadDir, lmFileName);
            try (var inputStream2 = filePart2.getInputStream()) {
                Files.copy(inputStream2, lmFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            // Get other form data
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                throw new IllegalArgumentException("L'identifiant de l'offre est requis.");
            }

            id = Integer.parseInt(idParam);
            request.setAttribute("id", id);
            
            User demandeur = (User) request.getSession().getAttribute("user");
            Offre offre = DefaultServices.getInstance().getOneOffre(id);

            // Create a new Postulation object
            Postulation postulation = new Postulation(cvFileName, lmFileName, demandeur, offre);
            System.out.println(postulation);
            
            p = DefaultServices.getInstance().addPostulation(postulation);
            
            if (p == null) {
                request.setAttribute("message", "Please try again later");
                request.getRequestDispatcher("postuler.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/MesPostulations");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println( "File upload failed: " + e.getMessage());
        }
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String content : contentDisposition.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
