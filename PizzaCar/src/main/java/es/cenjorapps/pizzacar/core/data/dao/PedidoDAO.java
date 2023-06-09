package es.cenjorapps.pizzacar.core.data.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import es.cenjorapps.pizzacar.core.data.model.PedidoEntity;

@Service
public class PedidoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public boolean save(PedidoEntity pedido, Session session){
		session.persist(pedido);
		return true;
	}
	
	public boolean merge(PedidoEntity pedido, Session session){
		session.merge(pedido);
		return true;
	}
	
	public boolean update(PedidoEntity pedido, Session session){
		session.update(pedido);
		return true;
	}

	public boolean eliminar(PedidoEntity pedido, Session session) {
		session.delete(pedido);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<PedidoEntity> findAll(Session session){
		return session.getNamedQuery("PedidoEntity.findAll").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PedidoEntity> findNextPedido(Session session) {
		return session.getNamedQuery("PedidoEntity.findNextPedido").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PedidoEntity> findById(int id, Session session) {
		return session.getNamedQuery("PedidoEntity.findById").setLong("id", id).list();
	}

}
