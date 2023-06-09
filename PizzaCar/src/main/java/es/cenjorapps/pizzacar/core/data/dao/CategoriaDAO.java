package es.cenjorapps.pizzacar.core.data.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import es.cenjorapps.pizzacar.core.data.model.CategoriaEntity;

@Service
public class CategoriaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public boolean save(CategoriaEntity categoria, Session session){
		session.persist(categoria);
		return true;
	}
	
	public boolean merge(CategoriaEntity categoria, Session session){
		session.merge(categoria);
		return true;
	}
	
	public boolean update(CategoriaEntity categoria, Session session){
		session.update(categoria);
		return true;
	}

	public boolean eliminar(CategoriaEntity categoria, Session session) {
		session.delete(categoria);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoriaEntity> findAll(Session session){
		List<CategoriaEntity> categoriaEntityLista = session.getNamedQuery("CategoriaEntity.findAll").list();
		return categoriaEntityLista;
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoriaEntity> findByNombre(String nombre, Session session){
		List<CategoriaEntity> categoriaEntityLista = session.getNamedQuery("CategoriaEntity.findByNombre")
				.setString("nombre", nombre)  
				.list();
		
		return categoriaEntityLista;
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoriaEntity> findByIdAndNombre(String nombre, int id, Session session) {
		List<CategoriaEntity> categoriaEntityLista = session.getNamedQuery("CategoriaEntity.findByIdAndNombre")
				.setString("nombre", nombre)
				.setInteger("id", id)
				.list();
		
		return categoriaEntityLista;
	}

}
