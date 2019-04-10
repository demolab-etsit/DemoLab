package es.isst.demolab.dao;

import java.util.Collection;

import org.hibernate.Session;

import es.isst.demolab.model.Eleccion;

import es.isst.demolab.dao.SessionFactoryService;


public class EleccionDAOImplementation implements EleccionDAO {

	private static EleccionDAOImplementation instancia = null;

	private EleccionDAOImplementation() {}
	public static EleccionDAOImplementation getInstancia() {
		if( null == instancia ) 
			instancia = new EleccionDAOImplementation();
		return instancia;
	}
	
	@Override
	public void create(Eleccion eleccion) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  session.save(eleccion);
		  session.getTransaction().commit();
		} catch (Exception e) {
		  // manejar excepciones
		} finally {
		  session.close();
		}
	}

	@Override
	public Eleccion read(int id) {
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  Eleccion eleccion = session.load(Eleccion.class, id);
		  session.getTransaction().commit();
		  return eleccion;
		} catch (Exception e) {
		  // manejar excepciones
			return null;
		} finally {
		  session.close();
		}
		
	}

	@Override
	public void update(Eleccion eleccion) {
		
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  session.saveOrUpdate(eleccion);
		  session.getTransaction().commit();		  
		} catch (Exception e) {
		  // manejar excepciones
		} finally {
		  session.close();
		}
		
	}

	@Override
	public void delete(Eleccion eleccion) {
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  session.delete(eleccion);
		  session.getTransaction().commit();		
		} catch (Exception e) {
		  // manejar excepciones			
		} finally {
		  session.close();
		}
		
	}

	@Override
	public Collection<Eleccion> readAll() {
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  Collection<Eleccion> elecciones = session.createQuery("from Eleccion").list();
		  session.getTransaction().commit();
		  return elecciones;
		} catch (Exception e) {
		  // manejar excepciones
			return null;
		} finally {
		  session.close();
		}
	}
}
