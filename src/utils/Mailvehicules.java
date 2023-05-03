package utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;

public class Mailvehicules {

    private static final String USERNAME = "mouhamedkhaled.baoueb@esprit.tn";
    private static final String PASSWORD = "201JMT3105";

    public static void envoyer(String email) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
        Message message = prepareMessage(session, email);
        Transport.send(message);
        System.out.println("Message envoyé avec succès.");
    }

    private static Message prepareMessage(Session session, String email) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Suppression d'un véhicule avec succès");

            String content = "<html>\n"
                    + "    <head>\n"
                    + "        <title>Suppression d'un véhicule avec succès</title>\n"
                    + "        <style>\n"
                    + "            body {\n"
                    + "                font-family: Arial, sans-serif;\n"
                    + "                font-size: 14px;\n"
                    + "                line-height: 1.5;\n"
                    + "            }\n"
                    + "            h1 {\n"
                    + "                font-size: 20px;\n"
                    + "                font-weight: bold;\n"
                    + "            }\n"
                    + "            p {\n"
                    + "                margin-bottom: 10px;\n"
                    + "            }\n"
                    + "        </style>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <h1>Suppression d'un véhicule avec succès</h1>\n"
                    + "        <p>Bonjour,</p>\n"
                    + "        <p>Nous vous confirmons la suppression du véhicule.</p>\n"
                    + "        <p>Cordialement,</p>\n"
                    + "        <p>L'équipe de support</p>\n"
                    + "    </body>\n"
                    + "</html>";

            message.setContent(content, "text/html");
            return message;
        } catch (MessagingException e) {
            Logger.getLogger(Mailvehicules.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
