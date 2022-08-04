package server.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtils {
	
	private String smtpServer="smtp.gmail.com"; //aqui tu ip de servidor smtp
	
	public void send(String to, String from, String subject, String body,String adjun){
	  	try
	    {
            Properties props = new Properties();
            props.put("mail.smtp.host", smtpServer);
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "enwipstaff@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, null);
	  
            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setText(body);
            
            
            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            
            
            // Se compone el adjunto con la imagen
            if(adjun!=null){
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(adjun)));
            adjunto.setFileName(adjun);
            multiParte.addBodyPart(adjunto);
            }
            
          

           

            // Se compone el correo, dando to, from, subject y el
            // contenido.
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(multiParte);

            // Se envia el correo.
            Transport t = session.getTransport("smtp");
            t.connect("enwipstaff@gmail.com", "si1234567");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
}

}
