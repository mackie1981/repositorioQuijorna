package es.cenjorapps.pizzacar.core.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import es.cenjorapps.pizzacar.core.data.model.UsuarioEntity;
import es.cenjorapps.pizzacar.core.service.UsuarioService;


public class UserAuthenticationService implements UserDetailsService, Serializable {

	private static final long serialVersionUID = 1L;
	
	private UsuarioService usuarioService;
	
    public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	public UsuarioEntity findByUsername(String username) {
		UsuarioEntity u = null;
		try {
			u = usuarioService.findByUsername(username);
        } catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public boolean esUsernameValido(String usuario) throws Exception {
		if(null != findByUsername(usuario)) {
			return true;
		}
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		// Cargar el usuario desde la base de datos usando UserRepository
        UsuarioEntity usuario;
		try {
			usuario = usuarioService.findByUsername(username);
			if (usuario == null) {
	            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
	        }
	        GrantedAuthority authority = new SimpleGrantedAuthority(usuario.getRol().getNombre());
	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        authorities.add(authority);
	        UserDetails userDetails = new User(usuario.getUsername(),usuario.getPass(),authorities);
	       
	        // Crear y devolver un objeto User de Spring Security con los detalles del usuario
	        return userDetails;
		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				e.toString();
			}
		}
        return null;
	}
	
}
