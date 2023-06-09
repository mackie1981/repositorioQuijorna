package es.cenjorapps.pizzacar.core.data.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import es.cenjorapps.pizzacar.core.data.model.ProductoEntity;

@Service
public class ProductoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public boolean save(ProductoEntity producto, Session session){
		session.persist(producto);
		return true;
	}
	
	public boolean merge(ProductoEntity producto, Session session){
		session.merge(producto);
		return true;
	}
	
	public boolean update(ProductoEntity producto, Session session){
		session.update(producto);
		return true;
	}

	public boolean eliminar(ProductoEntity producto, Session session) {
		session.delete(producto);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<ProductoEntity> findAll(Session session){
		return session.getNamedQuery("ProductoEntity.findAll").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductoEntity> findAllOrderByNombre(Session session) {
		return session.getNamedQuery("ProductoEntity.findAllOrderByNombre").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductoEntity> findByCategoria(int idCategoria, Session session){
		return session.getNamedQuery("ProductoEntity.findByCategoria").setInteger("idCategoria", idCategoria).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductoEntity> findDestacados(Session session) {
		return session.getNamedQuery("ProductoEntity.findDestacados").list();
	}
	
	@SuppressWarnings("unchecked")
	public ProductoEntity findByNombre(String nombre, Session session) {
		List<ProductoEntity> productoLista = session.getNamedQuery("ProductoEntity.findByNombre")
				.setString("nombre", nombre)                   
				.list();
		if(productoLista.isEmpty())
			return null;  
		
		return productoLista.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public ProductoEntity findByIdAndNombre(String nombre, long id, Session session) {
		List<ProductoEntity> productoLista = session.getNamedQuery("ProductoEntity.findByIdAndNombre")
				.setString("nombre", nombre)
				.setLong("id", id)
				.list();
		if(productoLista.isEmpty())
			return null;  
		
		return productoLista.get(0);
	}

}
