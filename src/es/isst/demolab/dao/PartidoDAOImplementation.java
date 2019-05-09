package es.isst.demolab.dao;

import java.util.Collection;

import org.hibernate.Session;

import es.isst.demolab.model.Partido;

public class PartidoDAOImplementation implements PartidoDAO {
	
	private static PartidoDAOImplementation instancia = null;

	private PartidoDAOImplementation() {}
	public static PartidoDAOImplementation getInstancia() {
		if( null == instancia ) 
			instancia = new PartidoDAOImplementation();
		return instancia;
	}
	
	@Override
	public void create(Partido partido) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  session.save(partido);
		  session.getTransaction().commit();
		} catch (Exception e) {
		  // manejar excepciones
		} finally {
		  session.close();
		}
	}

	@Override
	public Partido read(String Nombre) {
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  Partido partido = session.get(Partido.class, Nombre);
		  session.getTransaction().commit();
		  return partido;
		} catch (Exception e) {
		  // manejar excepciones
			return null;
		} finally {
		  session.close();
		}
		
	}

	@Override
	public void update(Partido partido) {
		
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  session.saveOrUpdate(partido);
		  session.getTransaction().commit();		  
		} catch (Exception e) {
		  // manejar excepciones
		} finally {
		  session.close();
		}
		
	}

	@Override
	public void delete(Partido partido) {
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  session.delete(partido);
		  session.getTransaction().commit();		
		} catch (Exception e) {
		  // manejar excepciones			
		} finally {
		  session.close();
		}
		
	}

	@Override
	public Collection<Partido> readAll() {
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  Collection<Partido> partidos = session.createQuery("from Partido").list();
		  session.getTransaction().commit();
		  return partidos;
		} catch (Exception e) {
		  // manejar excepciones
			return null;
		} finally {
		  session.close();
		}
	}
}
