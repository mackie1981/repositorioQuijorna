package es.cenjorapps.pizzacar.web.beans;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import es.cenjorapps.pizzacar.core.service.EmailService;
import es.cenjorapps.pizzacar.util.ApplicationContextUtils;

public class ContactoBean implements Serializable {
	  
		private static final long serialVersionUID = 1L;
		
		private String asunto;
		private String mensaje;
		private String email;
		
		private EmailService emailService;
		
		@PostConstruct
		public void initializeContactoBean() {
	    	emailService = (EmailService) ApplicationContextUtils.getBean("emailServiceBean");
	    }

		public String getAsunto() {
			return asunto;
		}

		public void setAsunto(String asunto) {
			this.asunto = asunto;
		}

		public String getMensaje() {
			return mensaje;
		}

		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public EmailService getEmailService() {
			return emailService;
		}

		public void setEmailService(EmailService emailService) {
			this.emailService = emailService;
		}
		
		public String enviarCorreo() {
			if(StringUtils.isEmpty(asunto)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El asunto es obligatorio", ""));
				return null;
			}
			if(StringUtils.isBlank(asunto)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El asunto es obligatorio", ""));
				return null;
			}
			if(StringUtils.isEmpty(mensaje)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El mensaje es obligatorio", ""));
				return null;
			}
			if(StringUtils.isBlank(mensaje)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El mensaje es obligatorio", ""));
				return null;
			}
			if(StringUtils.isEmpty(email)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El email es obligatorio", ""));
				return null;
			}
			if(StringUtils.isBlank(email)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El email es obligatorio", ""));
				return null;
			}
			if(!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El email no está bien formado", ""));
				return null;
			}
			try {
				emailService.enviarSugerencia(email, asunto, mensaje);
				return "home.xhtml?faces-redirect=true";
			} catch (Exception e1) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error enviando correo electrónico", ""));
				e1.printStackTrace();
				return null;
				
			}
		}
}
