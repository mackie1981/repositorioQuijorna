package es.cenjorapps.pizzacar.core.data.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import es.cenjorapps.pizzacar.core.data.model.DetallePedidoEntity;


@Service
public class DetallePedidoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<DetallePedidoEntity> findAll(Session session){
		List<DetallePedidoEntity> detallePedidoLista = session.getNamedQuery("DetallePedidoEntity.findAll").list();
		return detallePedidoLista;
	}
}
