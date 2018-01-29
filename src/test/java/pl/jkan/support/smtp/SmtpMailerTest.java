package pl.jkan.support.smtp;

import org.junit.Ignore;
import org.junit.Test;

import javax.mail.MessagingException;

public class SmtpMailerTest {
    @Test
    @Ignore
    public void itAllowSendEmail() {
        SmtpMailer smtpMailer = new SmtpMailer(
            new SmtpConfiguration(
                    "",
                    "",
                    "",
                    ""
            )
        );

        try {
            smtpMailer.send("", "subject", "message", "");
        } catch (MailerException e) {
            System.out.println(e.getMessage());
        }
    }
}
