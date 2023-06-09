package es.cenjorapps.pizzacar.core.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.cenjorapps.pizzacar.core.data.dao.PedidoDAO;
import es.cenjorapps.pizzacar.core.data.model.PedidoEntity;

public class PedidoService extends AbstractHibernateDBService  implements Serializable {

	private static final long serialVersionUID = 1L;
	private PedidoDAO pedidoDao;
	
	public PedidoDAO getPedidoDao() {
		return pedidoDao;
	}
	
	public void setPedidoDao(PedidoDAO pedidoDao) {
		this.pedidoDao = pedidoDao;
	}
	
	public boolean save(PedidoEntity pedido) throws Exception{
		Session session = openSession();
		Transaction tx = initTransaction(session);
		boolean resultado = false;
		try {	
			resultado = pedidoDao.save(pedido, session);
		} catch (Exception e) {
			rollback(tx);
			closeSession(session);
			throw e;
		}
		closeSessionAndEndTransaction(tx, session);
		return resultado;
	}
	
	public boolean update(PedidoEntity pedido) throws Exception {
		Session session = openSession();
		Transaction tx = initTransaction(session);
		boolean resultado = false;
		try {	
			resultado = pedidoDao.merge(pedido, session);
		} catch (Exception e) {
			rollback(tx);
			closeSession(session);
			throw e;
		}
		closeSessionAndEndTransaction(tx, session);
		return resultado;
	}
	
	public boolean delete(PedidoEntity pedido) throws Exception {
		Session session = openSession();
		Transaction tx = initTransaction(session);
		boolean resultado = false;
		try {	
			resultado = pedidoDao.eliminar(pedido, session);
		} catch (Exception e) {
			rollback(tx);
			closeSession(session);
			throw e;
		}
		closeSessionAndEndTransaction(tx, session);
		return resultado;
	}
	
	public List<PedidoEntity> findAll() throws Exception {
		Session session = openSession();
		List<PedidoEntity> lista = new ArrayList<PedidoEntity>();
		try {
			lista = pedidoDao.findAll(session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(!lista.isEmpty())
			return lista;
		return null;
	}

	public PedidoEntity findNextPedido() throws Exception {
		Session session = openSession();
		List<PedidoEntity> lista = new ArrayList<PedidoEntity>();
		try {
			lista = pedidoDao.findNextPedido(session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(!lista.isEmpty())
			return lista.get(0);	
		return null;
	}

	public PedidoEntity findById(int id) throws Exception {
		Session session = openSession();
		List<PedidoEntity> lista = new ArrayList<PedidoEntity>();
		try {
			lista = pedidoDao.findById(id, session);
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
