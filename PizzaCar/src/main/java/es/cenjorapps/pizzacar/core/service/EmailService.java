package es.cenjorapps.pizzacar.core.service;

import java.io.Serializable;
import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailService extends AbstractHibernateDBService  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private JavaMailSenderImpl mailSender;
	
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public void sendConfirmationEmail(String recipientEmail, String confirmationLink) {
		try {
			mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587);
	        mailSender.setUsername("djalvarocenjor@gmail.com");
	        mailSender.setPassword("gnghlziheienmsbv");

	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "true");
			SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo(recipientEmail);
	        mailMessage.setSubject("Confirmación de correo electrónico");
	        mailMessage.setText("Por favor, haga clic en el siguiente enlace para confirmar su correo electrónico y activar su cuenta: " + confirmationLink);
	        
	        mailSender.send(mailMessage);
		} catch (Exception e) {
			throw e;
		}
		
    }
	
	public void enviarSugerencia(String remitente, String asunto, String mensaje) {
		try {
			mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587);
	        mailSender.setUsername("djalvarocenjor@gmail.com");
	        mailSender.setPassword("gnghlziheienmsbv");

	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "true");
			SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo("djalvarocenjor@gmail.com");
	        mailMessage.setSubject("Comentario de " + remitente + ": " + asunto);
	        mailMessage.setText(mensaje);
	        
	        mailSender.send(mailMessage);
		} catch (Exception e) {
			throw e;
		}
		
	}

	public void sendChangePasswordPeticion(String email, String solicitudLink) {
		try {
			mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587);
	        mailSender.setUsername("djalvarocenjor@gmail.com");
	        mailSender.setPassword("gnghlziheienmsbv");

	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "true");
			SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo(email);
	        mailMessage.setSubject("Solicitud de cambio de contraseña");
	        mailMessage.setText("Por favor, haga clic en el siguiente enlace para reestablecer su contraseña: " + solicitudLink);
	        
	        mailSender.send(mailMessage);
		} catch (Exception e) {
			throw e;
		}
		
	}

}
