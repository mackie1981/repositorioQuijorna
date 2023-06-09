package es.cenjorapps.pizzacar.core.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.cenjorapps.pizzacar.core.data.dao.ProductoDAO;
import es.cenjorapps.pizzacar.core.data.model.ProductoEntity;

public class ProductoService extends AbstractHibernateDBService  implements Serializable {

	private static final long serialVersionUID = 1L;
	private ProductoDAO productoDao;
	
	public ProductoDAO getProductoDao() {
		return productoDao;
	}
	public void setProductoDao(ProductoDAO productoDao) {
		this.productoDao = productoDao;
	}
	
	public boolean save(ProductoEntity producto) throws Exception{
		Session session = openSession();
		Transaction tx = initTransaction(session);
		boolean resultado = false;
		try {	
			resultado = productoDao.save(producto, session);
		} catch (Exception e) {
			rollback(tx);
			closeSession(session);
			throw e;
		}
		closeSessionAndEndTransaction(tx, session);
		return resultado;
	}
	
	public boolean update(ProductoEntity producto) throws Exception {
		Session session = openSession();
		Transaction tx = initTransaction(session);
		boolean resultado = false;
		try {	
			resultado = productoDao.merge(producto, session);
		} catch (Exception e) {
			rollback(tx);
			closeSession(session);
			throw e;
		}
		closeSessionAndEndTransaction(tx, session);
		return resultado;
	}
	
	public boolean delete(ProductoEntity producto) throws Exception {
		Session session = openSession();
		Transaction tx = initTransaction(session);
		boolean resultado = false;
		try {	
			resultado = productoDao.eliminar(producto, session);
		} catch (Exception e) {
			rollback(tx);
			closeSession(session);
			throw e;
		}
		closeSessionAndEndTransaction(tx, session);
		return resultado;
	}
	
	public List<ProductoEntity> findAll() throws Exception {
		Session session = openSession();
		List<ProductoEntity> lista = new ArrayList<ProductoEntity>();
		try {
			lista = productoDao.findAll(session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(!lista.isEmpty())
			return lista;
		return null;
	}
	
	public List<ProductoEntity> findAllOrderByNombre() throws Exception {
		Session session = openSession();
		List<ProductoEntity> lista = new ArrayList<ProductoEntity>();
		try {
			lista = productoDao.findAllOrderByNombre(session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(!lista.isEmpty())
			return lista;
		return null;
	}
	
	public List<ProductoEntity> findByCategoria(int idCategoria) throws Exception {
		Session session = openSession();
		List<ProductoEntity> lista = new ArrayList<ProductoEntity>();
		try {
			lista = productoDao.findByCategoria(idCategoria, session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(!lista.isEmpty())
			return lista;
		return null;
	}
	
	public List<ProductoEntity> findDestacados() {
		Session session = openSession();
		List<ProductoEntity> lista = new ArrayList<ProductoEntity>();
		try {
			lista = productoDao.findDestacados(session);
			
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(!lista.isEmpty())
			return lista;
		return null;
	}
	
	public ProductoEntity findByNombre(String nombre) throws Exception {
		Session session = openSession();
		ProductoEntity res = null;
		try {
			res = productoDao.findByNombre(nombre, session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		return res;
	}
	
	public ProductoEntity findByIdAndNombre(String nombre, long id) throws Exception {
		Session session = openSession();
		ProductoEntity res = null;
		try {
			res = productoDao.findByIdAndNombre(nombre, id, session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		return res;
	}
	
	public boolean esNombreValido(String nombre) throws Exception {
		if(null != findByNombre(nombre)) {
			return false;
		}
		return true;
	}
	
	public boolean esNombreValidoUpdate(String nombre, long id) throws Exception {
		if(null != findByIdAndNombre(nombre, id)) {
			return false;
		}
		return true;
	}

}
