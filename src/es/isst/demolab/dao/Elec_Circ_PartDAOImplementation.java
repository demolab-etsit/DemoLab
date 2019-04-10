package es.isst.demolab.dao;

import java.util.Collection;

import org.hibernate.Session;

import es.isst.demolab.model.Circunscripcion;
import es.isst.demolab.model.Elec_Circ_Part;
import es.isst.demolab.model.Eleccion;
import es.isst.demolab.model.Partido;
import es.isst.demolab.dao.SessionFactoryService;


public class Elec_Circ_PartDAOImplementation implements Elec_Circ_PartDAO {

	private static Elec_Circ_PartDAOImplementation instancia = null;

	private Elec_Circ_PartDAOImplementation() {}
	public static Elec_Circ_PartDAOImplementation getInstancia() {
		if( null == instancia ) 
			instancia = new Elec_Circ_PartDAOImplementation();
		return instancia;
	}
	
	@Override
	public void create(Elec_Circ_Part elec_Circ_Part) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  session.save(elec_Circ_Part);
		  session.getTransaction().commit();
		} catch (Exception e) {
		  // manejar excepciones
		} finally {
		  session.close();
		}
	}

	@Override
	public Elec_Circ_Part read(int id) {
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  Elec_Circ_Part elec_Circ_Part = session.load(Elec_Circ_Part.class, id);
		  session.getTransaction().commit();
		  return elec_Circ_Part;
		} catch (Exception e) {
		  // manejar excepciones
			return null;
		} finally {
		  session.close();
		}
		
	}

	@Override
	public void update(Elec_Circ_Part elec_Circ_Part) {
		
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  session.saveOrUpdate(elec_Circ_Part);
		  session.getTransaction().commit();		  
		} catch (Exception e) {
		  // manejar excepciones
		} finally {
		  session.close();
		}
		
	}

	@Override
	public void delete(Elec_Circ_Part elec_Circ_Part) {
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  session.delete(elec_Circ_Part);
		  session.getTransaction().commit();		
		} catch (Exception e) {
		  // manejar excepciones			
		} finally {
		  session.close();
		}
		
	}

	@Override
	public Collection<Elec_Circ_Part> readAll() {
		Session session = SessionFactoryService.get().openSession();
		try {
		  session.beginTransaction();
		  // operaciones
		  Collection<Elec_Circ_Part> elec_Circ_Parts = session.createQuery("from Elec_Circ_Part").list();
		  session.getTransaction().commit();
		  return elec_Circ_Parts;
		} catch (Exception e) {
		  // manejar excepciones
			return null;
		} finally {
		  session.close();
		}
	}
}
