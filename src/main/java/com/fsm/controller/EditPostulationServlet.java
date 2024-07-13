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
import com.fsm.models.Postulation;

@WebServlet("/editPostulation")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class EditPostulationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "uploads";

    public EditPostulationServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Postulation postulation = DefaultServices.getInstance().getOnePostulation(id);
                if (postulation != null) {
                    request.setAttribute("postulation", postulation);
                    request.getRequestDispatcher("editPostulation.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Postulation not found");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid postulation id format");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing postulation id");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing postulation id");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            Postulation postulation = DefaultServices.getInstance().getOnePostulation(id);
            if (postulation == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Postulation not found");
                return;
            }

            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadFilePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Handle CV upload
            String cvFileName = request.getParameter("existingCv");
            Part filePartCV = request.getPart("cv");
            if (filePartCV != null && filePartCV.getSize() > 0) {
                cvFileName = getFileName(filePartCV);
                File cvFile = new File(uploadDir, cvFileName);
                try (var inputStream = filePartCV.getInputStream()) {
                    Files.copy(inputStream, cvFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }

            // Handle LM upload
            String lmFileName = request.getParameter("existingLm");
            Part filePartLM = request.getPart("lm");
            if (filePartLM != null && filePartLM.getSize() > 0) {
                lmFileName = getFileName(filePartLM);
                File lmFile = new File(uploadDir, lmFileName);
                try (var inputStream2 = filePartLM.getInputStream()) {
                    Files.copy(inputStream2, lmFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }

            postulation.setCv(cvFileName);
            postulation.setLm(lmFileName);
            DefaultServices.getInstance().updatePostulation(postulation);

            response.sendRedirect(request.getContextPath() + "/MesPostulations");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid postulation id format");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "File upload failed: " + e.getMessage());
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
