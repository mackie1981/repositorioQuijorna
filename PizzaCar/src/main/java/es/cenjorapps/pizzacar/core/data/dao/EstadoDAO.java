package es.cenjorapps.pizzacar.core.data.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import es.cenjorapps.pizzacar.core.data.model.EstadoEntity;

@Service
public class EstadoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public boolean save(EstadoEntity estado, Session session){
		session.persist(estado);
		return true;
	}
	
	public boolean merge(EstadoEntity estado, Session session){
		session.merge(estado);
		return true;
	}
	
	public boolean update(EstadoEntity estado, Session session){
		session.update(estado);
		return true;
	}

	public boolean eliminar(EstadoEntity estado, Session session) {
		session.delete(estado);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<EstadoEntity> findAll(Session session){
		return session.getNamedQuery("EstadoEntity.findAll").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<EstadoEntity> findByNombre(String nombre, Session session) {
		return session.getNamedQuery("EstadoEntity.findByNombre")
				.setString("nombre", nombre)
				.list();
	}
}
