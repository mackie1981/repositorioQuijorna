package es.cenjorapps.pizzacar.core.data.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import es.cenjorapps.pizzacar.core.data.model.RolEntity;

@Service
public class RolDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<RolEntity> findAll(Session session) {
		List<RolEntity> lista = session.getNamedQuery("RolEntity.findAll")
				.list();
		
		return lista;
	}
	
}
