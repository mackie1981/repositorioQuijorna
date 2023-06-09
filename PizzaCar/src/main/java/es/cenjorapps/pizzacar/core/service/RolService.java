package es.cenjorapps.pizzacar.core.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.cenjorapps.pizzacar.core.data.dao.RolDAO;
import es.cenjorapps.pizzacar.core.data.model.RolEntity;

public class RolService extends AbstractHibernateDBService  implements Serializable  {

	private static final long serialVersionUID = 1L;
	private RolDAO rolDao;

	public RolDAO getRolDao() {
		return rolDao;
	}

	public void setRolDao(RolDAO rolDao) {
		this.rolDao = rolDao;
	}

	public List<RolEntity> findAll() throws Exception {
		Session session = openSession();
		
		List<RolEntity> lista = new ArrayList<RolEntity>();
		try {
			lista = rolDao.findAll(session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		return lista;
	}

}
