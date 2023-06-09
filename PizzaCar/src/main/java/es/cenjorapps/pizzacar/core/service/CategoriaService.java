package es.cenjorapps.pizzacar.core.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.cenjorapps.pizzacar.core.data.dao.CategoriaDAO;
import es.cenjorapps.pizzacar.core.data.model.CategoriaEntity;

public class CategoriaService extends AbstractHibernateDBService  implements Serializable {

	private static final long serialVersionUID = 1L;
	private CategoriaDAO categoriaDao;
	
	public CategoriaDAO getCategoriaDao() {
		return categoriaDao;
	}
	
	public void setCategoriaDao(CategoriaDAO categoriaDao) {
		this.categoriaDao = categoriaDao;
	}
	
	public boolean save(CategoriaEntity categoria) throws Exception {
		Session session = openSession();
		Transaction tx = initTransaction(session);
		boolean resultado = false;
		try {	
			resultado = categoriaDao.save(categoria, session);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", e.getMessage()));	
			rollback(tx);
			closeSession(session);
			throw e;
		}
		closeSessionAndEndTransaction(tx, session);
		return resultado;
	}
	
	public boolean update(CategoriaEntity categoria) throws Exception {
		Session session = openSession();
		Transaction tx = initTransaction(session);
		boolean resultado = false;
		try {	
			resultado = categoriaDao.merge(categoria, session);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", e.getMessage()));	
			rollback(tx);
			closeSession(session);
			throw e;
		}
		closeSessionAndEndTransaction(tx, session);
		return resultado;
	}
	
	public boolean delete(CategoriaEntity categoria) throws Exception {
		Session session = openSession();
		Transaction tx = initTransaction(session);
		boolean resultado = false;
		try {	
			resultado = categoriaDao.eliminar(categoria, session);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", e.getMessage()));	
			rollback(tx);
			closeSession(session);
			throw e;
		}
		closeSessionAndEndTransaction(tx, session);
		return resultado;
	}
	
	public List<CategoriaEntity> findAll() throws Exception {
		Session session = openSession();
		List<CategoriaEntity> lista = new ArrayList<CategoriaEntity>();
		try {
			lista = categoriaDao.findAll(session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(!lista.isEmpty())
			return lista;
		return null;
	}
	
	public boolean esNombreValido(String nombre) throws Exception {
		Session session = openSession();
		List<CategoriaEntity> lista = new ArrayList<CategoriaEntity>();
		try {
			lista = categoriaDao.findByNombre(nombre, session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(lista.isEmpty())
			return true;
		return false;
	}

	public boolean esNombreValidoUpdate(String nombre, int id) throws Exception {
		Session session = openSession();
		List<CategoriaEntity> lista = new ArrayList<CategoriaEntity>();
		try {
			lista = categoriaDao.findByIdAndNombre(nombre, id, session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(lista.isEmpty())
			return true;
		return false;
	}

}
