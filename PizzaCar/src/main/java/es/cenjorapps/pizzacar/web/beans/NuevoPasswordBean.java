package es.cenjorapps.pizzacar.web.beans;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import es.cenjorapps.pizzacar.core.data.model.UsuarioEntity;
import es.cenjorapps.pizzacar.core.service.EmailService;
import es.cenjorapps.pizzacar.core.service.UsuarioService;
import es.cenjorapps.pizzacar.util.ApplicationContextUtils;


public class NuevoPasswordBean implements Serializable {
	  
	private static final long serialVersionUID = 1L;
		
	private String email;
	private UsuarioService usuarioService;
	private EmailService emailService;
	
	@PostConstruct
	public void initializeNuevoPasswordBean() {
		usuarioService = (UsuarioService) ApplicationContextUtils.getBean("usuarioServiceBean");
		emailService = (EmailService) ApplicationContextUtils.getBean("emailServiceBean");
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public String solicitar() {
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
		//Obtener el usuario
		UsuarioEntity u;
		try {
			u = usuarioService.findUserByEmail(email);
			if(null == u) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El email no pertenece a ningún usuario", ""));
				return null;
			}
			String solicitudLink = "http://localhost:8080/PizzaCar/recupera_pass.xhtml?token=" + u.getToken() + "&email=" + email;
			emailService.sendChangePasswordPeticion(email, solicitudLink);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error solicitando cambio de contraseña", ""));
			e.printStackTrace();
			return null;
		}
		
		return "home.xhtml?faces-redirect=true";
	}
}
