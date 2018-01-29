package pl.jkan.support.smtp;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class SmtpMailer implements Mailer {
    private SmtpConfiguration configuration;

    public SmtpMailer(SmtpConfiguration configuration) {
        this.configuration = configuration;
    }

    public void send(String recipient, String subject,
                     String content , String from) throws MailerException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", configuration.getServerAddress());
        props.put("mail.smtp.port", configuration.getPort());

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(configuration.getUser(), configuration.getPassword());
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
