package es.cenjorapps.pizzacar.web.beans;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import es.cenjorapps.pizzacar.core.data.model.UsuarioEntity;
import es.cenjorapps.pizzacar.core.service.UsuarioService;
import es.cenjorapps.pizzacar.util.ApplicationContextUtils;
import es.cenjorapps.pizzacar.util.ManagedBeanUtils;


public class RecuperaPasswordBean implements Serializable {
	  
	private static final long serialVersionUID = 1L;
		
	private String password;
	private String password2;
	private boolean usuarioValido;
	private UsuarioEntity usuario;
	private UsuarioService usuarioService;
	
	@PostConstruct
	public void initializeRecuperaPasswordBean() {
		usuarioService = (UsuarioService) ApplicationContextUtils.getBean("usuarioServiceBean");
		try {
			usuario = ManagedBeanUtils.findUsuarioByEmailAndToken(usuarioService);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error recuperando el usuario", ""));

		}
		usuarioValido = false;
		if(null != usuario)
			usuarioValido = true;
		
    }
	
	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}
	
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	public boolean isUsuarioValido() {
		return usuarioValido;
	}

	public void setUsuarioValido(boolean usuarioValido) {
		this.usuarioValido = usuarioValido;
	}
	
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public String reestablecer() {
		Pattern pattern = null;
		Matcher matcher = null;
		boolean esValido = false;
		if(StringUtils.isEmpty(password)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña es obligatorio", ""));
			return null;
		}
		if(StringUtils.isBlank(password)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña es obligatorio", ""));
			return null;
		}
		if(StringUtils.isEmpty(password2)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La repetición contraseña es obligatorio", ""));
			return null;
		}
		if(StringUtils.isBlank(password2)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La repetición contraseña es obligatorio", ""));
			return null;
		}
		pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
		matcher = pattern.matcher(password);
		esValido = matcher.matches();
		
		if(!esValido) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña debe tener entre 8 y 20 caracteres, al menos una mayúscula y una minúscula y un símbolo", ""));
			return null;
		}
		
		if(!password.equals(password2)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", ""));
			return null;
		}
		usuario.setPass(usuarioService.getPasswordEncoder().encode(password));
		try {
			usuarioService.update(usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña reestablecida", ""));
			return "login.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar la nueva contraseña", ""));
			return null;
		} 
	}
	
}
