package com.fsm.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailTest {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP host
        properties.put("mail.smtp.port", "587"); // Replace with your SMTP port
        properties.put("mail.smtp.user", "nada.mesbah@usmba.ac.ma"); // Replace with your email
        properties.put("mail.smtp.password", "raykod2003"); // Replace with your email password

        final String username = properties.getProperty("mail.smtp.user");
        final String password = properties.getProperty("mail.smtp.password");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sangot2003@gmail.com")); // Replace with the recipient's email
            message.setSubject("Test Email");
            message.setText("This is a test email from JavaMail API.");

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
}
