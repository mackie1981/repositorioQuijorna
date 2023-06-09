package es.cenjorapps.pizzacar.core.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import es.cenjorapps.pizzacar.core.service.UserAuthenticationService;

public class DatabaseAuthenticationProvider implements AuthenticationProvider {
	
	private UserAuthenticationService userAuthenticationService;
	
	public UserAuthenticationService getUserAuthenticationService() {
		return userAuthenticationService;
	}

	public void setUserAuthenticationService(UserAuthenticationService userAuthenticationService) {
		this.userAuthenticationService = userAuthenticationService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
	    String password = authentication.getCredentials().toString();
		
	    try {
			UserDetails usuario = userAuthenticationService.loadUserByUsername(username);
			if (!userAuthenticationService.getUsuarioService().getPasswordEncoder().matches(password, usuario.getPassword())) {
	            throw new BadCredentialsException("Credenciales inválidas");
	        }
	    
			return new UsernamePasswordAuthenticationToken(usuario, password, usuario.getAuthorities());
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
