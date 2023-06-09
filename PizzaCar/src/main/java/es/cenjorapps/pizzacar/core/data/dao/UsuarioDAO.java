package es.cenjorapps.pizzacar.core.data.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import es.cenjorapps.pizzacar.core.data.model.UsuarioEntity;

@Service
public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public boolean save(UsuarioEntity usuario, Session session){
		session.persist(usuario);
		return true;
	}
	
	public boolean merge(UsuarioEntity usuario, Session session){
		session.merge(usuario);
		return true;
	}
	
	public boolean update(UsuarioEntity usuario, Session session){
		session.update(usuario);
		return true;
	}

	public boolean eliminar(UsuarioEntity usuario, Session session) {
		session.delete(usuario);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioEntity> findAll(Session session){
		return session.getNamedQuery("UsuarioEntity.findAll").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioEntity> findAllOrderByUsername(Session session) {
		return session.getNamedQuery("UsuarioEntity.findAllOrderByUsername").list();
	}

	@SuppressWarnings("unchecked")
	public UsuarioEntity findByUsername(String username, Session session) {
		List<UsuarioEntity> usuarioLista = session.getNamedQuery("UsuarioEntity.findByUsername")
				.setString("usuario", username)                   
				.list();
		if(usuarioLista.isEmpty())
			return null;  
		
		return usuarioLista.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioEntity> findByIdAndUsername(String usuario, long id, Session session) {
		return session.getNamedQuery("UsuarioEntity.findByIdAndUsername")
				.setString("usuario", usuario)
				.setLong("id", id)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioEntity> findByUsernameAndPass(String username, String pass, Session session) {
		return session.getNamedQuery("UsuarioEntity.findByUsernameAndPass")
				.setString("usuario", username)
				.setString("passsword", pass)
				.list();
	}

	@SuppressWarnings("unchecked")
	public boolean findByEmail(String email, Session session) {
		List<UsuarioEntity> usuarioLista = session.getNamedQuery("UsuarioEntity.findByEmail")
				.setString("email", email)                   
				.list();
		if(usuarioLista.isEmpty())
			return false;  
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public boolean findByIdAndEmail(String email, long id,Session session) {
		List<UsuarioEntity> usuarioLista = session.getNamedQuery("UsuarioEntity.findByIdAndEmail")
				.setString("email", email)
				.setLong("id", id)
				.list();
		if(usuarioLista.isEmpty())
			return false;  
		
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioEntity> findByEmailAndToken(String email, String token, Session session) {
		return session.getNamedQuery("UsuarioEntity.findByEmailAndToken")
				.setString("email", email)
				.setString("token", token)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioEntity> findUserByEmail(String email, Session session) {
		return session.getNamedQuery("UsuarioEntity.findByEmail")
				.setString("email", email)                   
				.list();
	}
	
}

