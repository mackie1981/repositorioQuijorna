package es.cenjorapps.pizzacar.core.service;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AbstractHibernateDBService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected void clearSession(Session session) {
		session.clear();
	}

	protected void flush(Session session) {
		session.flush();
	}
	
	protected void rollback(Transaction tx) {
		if(tx != null && tx.isActive()){
			tx.rollback();
		}
	}
	
	protected Transaction initTransaction(Session session){
		if(sesionValida(session)){
			return session.beginTransaction();
		}
		return null;
	}
	
	private boolean sesionValida(Session session) {
		return session!=null && session.isOpen();
	}

	protected Session openSessionExterna(Session[] sessions){
		if(sessions.length == 0){
			return openSession();
		}else{
			return sessions[0];
		}
	}
	
	protected void closeSessionExterna(Transaction tx, Session session, boolean sesionIndependiente) throws Exception{
		if(sesionIndependiente){
			closeSessionAndEndTransaction(tx, session);
		}
	}
	
	protected void closeSessionAndEndTransaction(Transaction tx, Session session) throws Exception{
		if(sesionValida(session)){
			try{
				if(tx!=null && tx.isActive()){
					tx.commit();
				}
			}catch (Exception e){
				if(tx!=null && tx.isActive()){
					rollback(tx);
				}
				throw e;
			}finally{
				closeSession(session);
			}
		}
	}
	
	protected Session openSession(){
		return sessionFactory.openSession();
	}
	
	protected void closeSession(Session session){
		session.close();
	}
}
