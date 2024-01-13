package creditdirect.clientmicrocervice.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendConfirmationEmail(String recipientEmail, String password) {


        MimeMessage mimeMessage = emailSender.createMimeMessage();
        String activationUrl = "http://localhost:8000/clients/activate?email=" + recipientEmail;

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(recipientEmail);
            helper.setSubject("Subscription Confirmation");

            String htmlBody = "<html>"
                    + "<head>"
                    + "<title>Subscription Confirmation</title>"
                    + "<style>"
                    + "body { font-family: Arial, sans-serif; }"
                    + "h2 { color: #0056b3; }"
                    + "p { color: #333; }"
                    + ".btn { display: inline-block; padding: 10px 20px; background-color: #0056b3; color: #fff; text-decoration: none; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<h2>Thank you for subscribing!</h2>"
                    + "<p>Your password is: <strong>" + password + "</strong></p>"
                    + "<p><a href='" + activationUrl + "' class='btn'>activate your compte</a></p>"
                    + "</body></html>";



            helper.setText(htmlBody, true); // Set the email body as HTML
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // Send the email
        emailSender.send(mimeMessage);
    }







    public void sendPasswordEmail(String recipientEmail, String password) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(recipientEmail);
            helper.setSubject("Your Password");

            String htmlBody = "<html>"
                    + "<head>"
                    + "<title>Your Password</title>"
                    + "<style>"
                    + "body { font-family: Arial, sans-serif; }"
                    + "h2 { color: #0056b3; }"
                    + "p { color: #333; }"
                    + ".password { font-weight: bold; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<h2>Your Password</h2>"
                    + "<p>Your password is: <span class='password'>" + password + "</span></p>"
                    + "</body></html>";

            helper.setText(htmlBody, true); // Set the email body as HTML
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // Send the email
        emailSender.send(mimeMessage);
    }







    public void sendanotherConfirmation(String recipientEmail) {


        MimeMessage mimeMessage = emailSender.createMimeMessage();
        String activationUrl = "http://localhost:8000/clients/activate?email=" + recipientEmail;

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(recipientEmail);
            helper.setSubject("Subscription Confirmation");

            String htmlBody = "<html>"
                    + "<head>"
                    + "<title>Subscription Confirmation</title>"
                    + "<style>"
                    + "body { font-family: Arial, sans-serif; }"
                    + "h2 { color: #0056b3; }"
                    + "p { color: #333; }"
                    + ".btn { display: inline-block; padding: 10px 20px; background-color: #0056b3; color: #fff; text-decoration: none; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<h2>Thank you for subscribing!</h2>"
                    + "<p>Your password is: <strong>YourPasswordHere</strong></p>"
                    + "<p><a href='" + activationUrl + "' class='btn'>activate your compte</a></p>"
                    + "</body></html>";



            helper.setText(htmlBody, true); // Set the email body as HTML
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // Send the email
        emailSender.send(mimeMessage);
    }

    public void sendPasswordResetEmail(String email, String newPassword, String recipientEmail) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(recipientEmail);
            helper.setSubject("Password Reset");

            String resetUrl = "http://localhost:8000/clients/reset-password?email=" + recipientEmail;

            String htmlBody = "<html>"
                    + "<head>"
                    + "<title>Password Reset</title>"
                    + "<style>"
                    + "body { font-family: Arial, sans-serif; }"
                    + "h2 { color: #0056b3; }"
                    + "p { color: #333; }"
                    + ".btn { display: inline-block; padding: 10px 20px; background-color: #0056b3; color: #fff; text-decoration: none; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<h2>Password Reset</h2>"
                    + "<p>Your new password is: <strong>" + newPassword + "</strong></p>"
                    + "<p><a href='" + resetUrl + "' class='btn'>Reset your password</a></p>"
                    + "</body></html>";

            helper.setText(htmlBody, true); // Set the email body as HTML
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // Send the email
        emailSender.send(mimeMessage);
    }
    }






/*
@Service
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendConfirmationEmail(String recipientEmail) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setTo(recipientEmail);
            helper.setSubject("Subscription Confirmation");
            helper.setText("Thank you for subscribing! Your password is: ");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // Send the email
        emailSender.send(mimeMessage);
    }


}*/
