package pl.jkan.support.smtp;

import javax.mail.MessagingException;

public interface Mailer {
    public void send(String recipient, String subject,
                     String content , String from) throws MailerException;
}
