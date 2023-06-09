package es.cenjorapps.pizzacar.core.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import es.cenjorapps.pizzacar.core.data.dao.UsuarioDAO;
import es.cenjorapps.pizzacar.core.data.model.UsuarioEntity;

public class UsuarioService extends AbstractHibernateDBService  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDao;
	
	private PasswordEncoder passwordEncoder;

	public UsuarioDAO getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	public boolean save(UsuarioEntity usuario) throws Exception{
		Session session = openSession();
		Transaction tx = initTransaction(session);
		boolean resultado = false;
		try {	
			resultado = usuarioDao.save(usuario, session);
		} catch (Exception e) {
			rollback(tx);
			closeSession(session);
			throw e;
		}
		closeSessionAndEndTransaction(tx, session);
		return resultado;
	}
	
	public boolean update(UsuarioEntity usuario) throws Exception {
		Session session = openSession();
		Transaction tx = initTransaction(session);
		boolean resultado = false;
		try {	
			resultado = usuarioDao.merge(usuario, session);
		} catch (Exception e) {
			rollback(tx);
			closeSession(session);
			throw e;
		}
		closeSessionAndEndTransaction(tx, session);
		return resultado;
	}
	
	public boolean delete(UsuarioEntity usuario) throws Exception {
		Session session = openSession();
		Transaction tx = initTransaction(session);
		boolean resultado = false;
		try {	
			resultado = usuarioDao.eliminar(usuario, session);
		} catch (Exception e) {
			rollback(tx);
			closeSession(session);
			throw e;
		}
		closeSessionAndEndTransaction(tx, session);
		return resultado;
	}
	
	public List<UsuarioEntity> findAll() throws Exception {
		Session session = openSession();
		List<UsuarioEntity> lista = new ArrayList<UsuarioEntity>();
		try {
			lista = usuarioDao.findAll(session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(!lista.isEmpty())
			return lista;
		return null;
	}
	
	public List<UsuarioEntity> findAllOrderByUsername() throws Exception {
		Session session = openSession();
		List<UsuarioEntity> lista = new ArrayList<UsuarioEntity>();
		try {
			lista = usuarioDao.findAllOrderByUsername(session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(!lista.isEmpty())
			return lista;
		return null;
	}
	
	public UsuarioEntity findByUsername(String usuario) throws Exception {
		Session session = openSession();
		UsuarioEntity res = null;
		try {
			res = usuarioDao.findByUsername(usuario, session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		return res;
	}
	
	public List<UsuarioEntity> findByIdAndUsername(String usuario, long id) throws Exception {
		Session session = openSession();
		List<UsuarioEntity> lista = new ArrayList<UsuarioEntity>();
		try {
			lista = usuarioDao.findByIdAndUsername(usuario, id, session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(!lista.isEmpty())
			return lista;
		return null;
	}
	
	public List<UsuarioEntity> findByUsernameAndPass(String usuario, String pass) throws Exception {
		Session session = openSession();
		List<UsuarioEntity> lista = new ArrayList<UsuarioEntity>();
		try {
			lista = usuarioDao.findByUsernameAndPass(usuario, pass, session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(!lista.isEmpty())
			return lista;
		return null;
	}
	
	public boolean findByEmail(String email) throws Exception {
		Session session = openSession();
		boolean res = false;
		try {
			res = usuarioDao.findByEmail(email, session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		return res;
	}
	
	public boolean findByEmailUpdate(String email, long id) throws Exception {
		Session session = openSession();
		boolean res = false;
		try {
			res = usuarioDao.findByIdAndEmail(email, id, session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		return res;
	}

	public UsuarioEntity findByEmailAndToken(String email, String token) throws Exception {
		Session session = openSession();
		List<UsuarioEntity> lista = new ArrayList<UsuarioEntity>();
		try {
			lista = usuarioDao.findByEmailAndToken(email, token, session);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSession(session);
		}
		if(!lista.isEmpty())
			return lista.get(0);
		return null;
	}
	
	public boolean esUsernameValido(String usuario) throws Exception {
		if(null != findByUsername(usuario)) {
			return false;
		}
		return true;
	}
	
	public boolean esUsernameValidoUpdate(String usuario, long id) throws Exception {
		if(null != findByIdAndUsername(usuario, id)) {
			return false;
		}
		return true;
	}

	public UsuarioEntity findUserByEmail(String email) throws Exception{
		Session session = openSession();
		List<UsuarioEntity> lista = new ArrayList<UsuarioEntity>();
		try {
			lista = usuarioDao.findUserByEmail(email, session);
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
