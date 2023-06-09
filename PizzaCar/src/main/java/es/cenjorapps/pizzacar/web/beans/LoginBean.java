package es.cenjorapps.pizzacar.web.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import es.cenjorapps.pizzacar.core.data.model.UsuarioEntity;
import es.cenjorapps.pizzacar.core.service.UserAuthenticationService;
import es.cenjorapps.pizzacar.util.ApplicationContextUtils;


public class LoginBean implements Serializable {
  
	private static final long serialVersionUID = 1L;
	private String username;
    private String password;
    private SessionBean sessionBean;
    private UserAuthenticationService userAuthenticationService;
    private AuthenticationManager authenticationManager = null;
    
    public String getUsername() {
        return username;
    }
    
    @PostConstruct
	public void initializeLoginBean() {
		userAuthenticationService = (UserAuthenticationService) ApplicationContextUtils.getBean("userAuthenticationServiceBean");
		authenticationManager = (AuthenticationManager) ApplicationContextUtils.getBean("authManager");
    } 

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public UserAuthenticationService getUserAuthenticationService() {
		return userAuthenticationService;
	}

	public void setUserAuthenticationService(UserAuthenticationService userAuthenticationService) {
		this.userAuthenticationService = userAuthenticationService;
	}
	
	public String login() {
        try {
        	Authentication request = new UsernamePasswordAuthenticationToken(username, password);
            Authentication auth = authenticationManager.authenticate(request);
            
            if (null != auth) { 
            	UsuarioEntity usuarioTemporal = userAuthenticationService.findByUsername(username);
            	SecurityContextHolder.getContext().setAuthentication(auth);
				sessionBean.setUsuario(usuarioTemporal.getUsername());
				sessionBean.setRol(usuarioTemporal.getRol().getNombre());
				sessionBean.getPedidoCliente().setUsuario(usuarioTemporal);
				sessionBean.setUsuarioActivo(usuarioTemporal);
				return "home.xhtml?faces-redirect=true"; // Redirige a la página de inicio
            } else {
            	if (!userAuthenticationService.esUsernameValido(username)) {
            		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no encontrado", ""));
    			    return null; // Permanece en la página de login
            	}
            	UsuarioEntity usuarioTemporal = userAuthenticationService.findByUsername(username);
				
				if(null != usuarioTemporal) {
					if(userAuthenticationService.getUsuarioService().getPasswordEncoder().matches(password, usuarioTemporal.getPass())) {
						SecurityContextHolder.getContext().setAuthentication(auth);
						sessionBean.setUsuario(usuarioTemporal.getUsername());
						sessionBean.setRol(usuarioTemporal.getRol().getNombre());
						sessionBean.getPedidoCliente().setUsuario(usuarioTemporal);
						return "home.xhtml?faces-redirect=true"; // Redirige a la página de inicio
					}
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña incorrecta", ""));
				    return null; // Permanece en la página de login
				}
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña incorrecta", ""));
			    return null; // Permanece en la página de login
            }
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credenciales inválidas", ""));
		    return null; // Permanece en la página de login
		}
    }
    
}
