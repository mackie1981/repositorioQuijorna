package es.cenjorapps.pizzacar.web.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import es.cenjorapps.pizzacar.core.service.UsuarioService;
import es.cenjorapps.pizzacar.util.ApplicationContextUtils;
import es.cenjorapps.pizzacar.util.ManagedBeanUtils;

public class ConfirmarBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean confirmacionExitosa;
	private UsuarioService usuarioService;
	
	
	@PostConstruct
	public void initializeConfirmarBean() {
		usuarioService = (UsuarioService) ApplicationContextUtils.getBean("usuarioServiceBean");
		confirmacionExitosa = ManagedBeanUtils.validarToken(usuarioService);
	}
	
	public boolean isConfirmacionExitosa() {
		return confirmacionExitosa;
	}

	public void setConfirmacionExitosa(boolean confirmacionExitosa) {
		this.confirmacionExitosa = confirmacionExitosa;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	public String loguearse( ) {
		return "login.xhtml?faces-redirect=true";
	}
}
