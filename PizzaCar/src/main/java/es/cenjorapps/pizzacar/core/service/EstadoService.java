package es.cenjorapps.pizzacar.core.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.cenjorapps.pizzacar.core.data.dao.EstadoDAO;
import es.cenjorapps.pizzacar.core.data.model.EstadoEntity;

public class EstadoService extends AbstractHibernateDBService  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EstadoDAO estadoDao;

	public EstadoDAO getEstadoDao() {
		return estadoDao;
	}

	public void setEstadoDao(EstadoDAO estadoDao) {
		this.estadoDao = estadoDao;
	}
	
	public boolean save(EstadoEntity estado) throws Exception {
		Session session = openSession();
		Transaction tx = initTransaction(session);
		boolean resultado = false;
		try {	
			resultado = estadoDao.save(estado, session);
		} catch (Exception e) {
			rollback(tx);
			closeSession(session);
			throw e;
		}
		closeSessionAndEndTransaction(tx, session);
		return resultado;
	}
	
	public boolean update(EstadoEntity estado) throws Exception {
		Session session = openSession();
		Transaction tx = initTransaction(session);
		boolean resultado = false;
		try {	
			resultado = estadoDao.merge(estado, session);
		} catch (Exception e) {
			rollback(tx);
			closeSession(session);
			throw e;
		}
		closeSessionAndEndTransaction(tx, session);
		return resultado;
	}
	
	public boolean delete(EstadoEntity estado) throws Exception {
		Session session = openSession();
		Transaction tx = initTransaction(session);
		boolean resultado = false;
		try {	
			resultado = estadoDao.eliminar(estado, session);
		} catch (Exception e) {
			rollback(tx);
			closeSession(session);
			throw e;
		}
		closeSessionAndEndTransaction(tx, session);
		return resultado;
	}
	
	public List<EstadoEntity> findAll() throws Exception {
		Session session = openSession();
		List<EstadoEntity> lista = new ArrayList<EstadoEntity>();
		try {
			lista = estadoDao.findAll(session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(!lista.isEmpty())
			return lista;
		return null;
	}

	public EstadoEntity findByNombre(String nombre) throws Exception {
		Session session = openSession();
		List<EstadoEntity> lista = new ArrayList<EstadoEntity>();
		try {
			lista = estadoDao.findByNombre(nombre, session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(!lista.isEmpty())
			return lista.get(0);
		return null;
	}
	
}
