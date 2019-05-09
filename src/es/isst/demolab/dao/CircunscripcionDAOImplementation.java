package es.isst.demolab.dao;

import java.util.Collection;

import org.hibernate.Session;

import es.isst.demolab.model.Circunscripcion;


public class CircunscripcionDAOImplementation implements CircunscripcionDAO {

	private static CircunscripcionDAOImplementation instancia = null;

	private CircunscripcionDAOImplementation() {}
	public static CircunscripcionDAOImplementation getInstancia() {
		if( null == instancia ) 
			instancia = new CircunscripcionDAOImplementation();
		return instancia;
	}
	
	@Override
	public void create(Circunscripcion circunscripcion) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  session.save(circunscripcion);
		  session.getTransaction().commit();
		} catch (Exception e) {
		  // manejar excepciones
		} finally {
		  session.close();
		}
	}

	@Override
	public Circunscripcion read(String Nombre) {
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  Circunscripcion circunscripcion = session.get(Circunscripcion.class, Nombre);
		  session.getTransaction().commit();
		  return circunscripcion;
		} catch (Exception e) {
		  // manejar excepciones
			return null;
		} finally {
		  session.close();
		}
		
	}

	@Override
	public void update(Circunscripcion circunscripcion) {
		
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  session.saveOrUpdate(circunscripcion);
		  session.getTransaction().commit();		  
		} catch (Exception e) {
		  // manejar excepciones
		} finally {
		  session.close();
		}
		
	}

	@Override
	public void delete(Circunscripcion circunscripcion) {
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  session.delete(circunscripcion);
		  session.getTransaction().commit();		
		} catch (Exception e) {
		  // manejar excepciones			
		} finally {
		  session.close();
		}
		
	}

	@Override
	public Collection<Circunscripcion> readAll() {
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  Collection<Circunscripcion> circunscripciones = session.createQuery("from Circunscripcion").list();
		  session.getTransaction().commit();
		  return circunscripciones;
		} catch (Exception e) {
		  // manejar excepciones
			return null;
		} finally {
		  session.close();
		}
	}

}
