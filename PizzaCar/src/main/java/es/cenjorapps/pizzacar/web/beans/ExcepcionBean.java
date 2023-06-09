package es.cenjorapps.pizzacar.web.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class ExcepcionBean implements Serializable {
	  
	private static final long serialVersionUID = 1L;
	private String error;
	private String rutaNavCss;
	private String rutaMainCss;
	private String rutaFooterCss;
	private String rutaNavJs;
	private String rutaHome;
	
	@PostConstruct
	public void initializeExcepcionBean() {
		error = "No hay error";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
        Throwable throwable = (Throwable) facesContext.getExternalContext().getRequestMap().get("javax.servlet.error.exception");
        if (throwable != null) {
        	List<?> exceptions = ExceptionUtils.getThrowableList(throwable);
        	throwable = (Throwable) exceptions.get(exceptions.size()-1);
        	
        	//Actuar en funcion de la excepción
        	if(throwable instanceof javax.faces.application.ViewExpiredException){
                try {
					externalContext.redirect("http://http://localhost:8080/PizzaCar/home.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
				}
        	} else {
        		error = ExceptionUtils.getRootCauseMessage(throwable);
        	} 
        }
        
        
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getRutaNavCss() {
		return rutaNavCss;
	}

	public void setRutaNavCss(String rutaNavCss) {
		this.rutaNavCss = rutaNavCss;
	}

	public String getRutaMainCss() {
		return rutaMainCss;
	}

	public void setRutaMainCss(String rutaMainCss) {
		this.rutaMainCss = rutaMainCss;
	}

	public String getRutaFooterCss() {
		return rutaFooterCss;
	}

	public void setRutaFooterCss(String rutaFooterCss) {
		this.rutaFooterCss = rutaFooterCss;
	}

	public String getRutaNavJs() {
		return rutaNavJs;
	}

	public void setRutaNavJs(String rutaNavJs) {
		this.rutaNavJs = rutaNavJs;
	}

	public String getRutaHome() {
		return rutaHome;
	}

	public void setRutaHome(String rutaHome) {
		this.rutaHome = rutaHome;
	}
	
}
