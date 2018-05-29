package nl.hu.rafaeldorzada.bep.friendspammer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.MessagingException;

public class EmailSender {
	private EmailSender() {
		throw new IllegalStateException("Utility class");
	}
	public static void sendEmail(String subject, String to, String messageBody, boolean asHtml) throws MessagingException {
		EmailConfig emailconfig = new EmailConfig();
		Session session = emailconfig.getSession();
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("spammer@spammer.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(subject);

			if (asHtml) {
					message.setContent(messageBody, "text/html; charset=utf-8");
			} else {
				message.setText(messageBody);
			}
			Transport.send(message);

			MongoSaver.saveEmail(to, "spammer@spamer.com", subject, messageBody, asHtml);

		} catch (MessagingException e) {
			throw new MessagingException();
		}
	}

	public static void sendEmail(String subject, String[] toList, String messageBody, boolean asHtml) throws MessagingException {
		Logger logger = LoggerFactory.getLogger(MongoSaver.class);
		EmailConfig emailconfig = new EmailConfig();
		Session session = emailconfig.getSession();
		try {

			for (int index = 0; index < toList.length; index++) {
			
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("spammer@spammer.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toList[index]));
				message.setSubject(subject);
				
				if (asHtml) {
						message.setContent(messageBody, "text/html; charset=utf-8");
				} else {
					message.setText(messageBody);	
				}
				Transport.send(message);
				logger.info("Done");
			}

		} catch (MessagingException e) {
			throw new MessagingException();
		}
	}
	
}
