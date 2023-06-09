package es.cenjorapps.pizzacar.web.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;

public class AperturaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean localAbierto;
	
	@PostConstruct
	public void initializeAperturaBean() {
		localAbierto = false;
	}

	public boolean isLocalAbierto() {
		return localAbierto;
	}

	public void setLocalAbierto(boolean localAbierto) {
		this.localAbierto = localAbierto;
	}
}
