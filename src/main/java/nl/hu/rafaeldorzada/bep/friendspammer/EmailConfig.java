package nl.hu.rafaeldorzada.bep.friendspammer;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class EmailConfig {

    public Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mailtrap.io");
        props.put("mail.smtp.port", "2525");
        props.put("mail.smtp.auth", "true");

        final String username = "eb3be6ab52ecd7";
        final String password = "9c38225c543a30";

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        return session;
    }
}
