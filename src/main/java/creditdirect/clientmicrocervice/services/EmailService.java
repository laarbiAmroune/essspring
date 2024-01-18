package creditdirect.clientmicrocervice.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;




@Service
public class EmailService {
    private final EncryptionService encryptionService;
    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender ,EncryptionService encryptionService) {
        this.emailSender = emailSender;  this.encryptionService = encryptionService;
    }

    public void sendConfirmationEmail(String recipientEmail, String password) {


        MimeMessage mimeMessage = emailSender.createMimeMessage();


        String encryptedEmail = encryptionService.encrypt(recipientEmail);
        System.out.print(encryptedEmail);
       // String activationUrl = "http://localhost:8000/clients/activate/"+encryptedEmail;
        String activationUrl = "https://thin-laugh-production.up.railway.app/clients/activate?email=" + encryptedEmail;
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(recipientEmail);
            helper.setSubject("Subscription Confirmation");



            String htmlBody = "<html>"
                    + "<head>"
                    + "<title>Confirmation d'inscription</title>"
                    + "<style>"
                    + "body { font-family: Arial, sans-serif; }"

                    + ".buttontext { color: #fff; }"
                    + "h2 { color: #0056b3; }"
                    + "p { color: #333; }"
                    +  " h1 { color: #742484; }"
                    + ".btn  .btn { \n" +
                    "            display: inline-block; \n" +
                    "            padding: 10px 20px; \n" +
                    "            width: 90%; \n" +
                    "            max-width: 200px; \n" +
                    "            background-color: #742484; \n" +
                    "            color: white; \n" +
                    "            text-decoration: none; \n" +
                    "            border: none; \n" +
                    "            border-radius: 15px; \n" +
                    "            box-shadow: 0px -12px 16px 8px rgba(0, 0, 0, 0.25) inset; \n" +
                    "            cursor: pointer; \n" +
                    "        }"
                    + ".btn:hover { background-color: #004080; }"  // Optional: Add hover effect
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<h2>Merci de votre inscription !</h2>"
                    +"  <h1>Bienvenu chez CreditDirect</h1> "
                    + "<p class:'buttontext'>Votre mot de passe est : <strong>" + password + "</strong></p>"

                    + "<h3>clicker ici pour <a href='" + activationUrl + "' class='btn'>activer votre  compte</a></h3>"
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
