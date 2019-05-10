package es.isst.demolab.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.isst.demolab.dao.CircunscripcionDAOImplementation;
import es.isst.demolab.model.Circunscripcion;

class TestCircunscripcionDAOImplementation {

	@BeforeEach
	void setUp() throws Exception {
Circunscripcion circ = new Circunscripcion();
		
		String Nombre ="adios";
	
		circ.setNombre(Nombre);
		
		CircunscripcionDAOImplementation cdao = CircunscripcionDAOImplementation.getInstancia();
		
		cdao.create(circ);
	}

	@AfterEach
	void tearDown() throws Exception {
		Circunscripcion circ = new Circunscripcion();
		Circunscripcion circ2 = new Circunscripcion();

		String nombre="hola";
		String nombre2="adios";
		
		circ.setNombre(nombre);
		circ2.setNombre(nombre2);
		
		CircunscripcionDAOImplementation.getInstancia().delete(circ);
		CircunscripcionDAOImplementation.getInstancia().delete(circ2);
	}

	@Test
	void testCreate() {
		Circunscripcion circ = new Circunscripcion();
		
		String Nombre ="hola";
	
		circ.setNombre(Nombre);
		
		CircunscripcionDAOImplementation cdao = CircunscripcionDAOImplementation.getInstancia();
		
		cdao.create(circ);
		
		assertEquals(circ.getNombre(), cdao.read(Nombre).getNombre());
	}
	
	@Test
	void testRead() {
		assertEquals("adios" , CircunscripcionDAOImplementation.getInstancia().read("adios").getNombre());
	}

	@Test
	void testUpdate() {
		Circunscripcion circ = new Circunscripcion();
		String nombre="hola";
		int NElectores =100;
		
		circ.setNombre(nombre);
		circ.setNElectores(NElectores);
		
		CircunscripcionDAOImplementation.getInstancia().update(circ);
		
		assertEquals(circ.getNElectores(), CircunscripcionDAOImplementation.getInstancia().read(nombre).getNElectores());
	}

	@Test
	void testDelete() {
		Circunscripcion circ = new Circunscripcion();
		String nombre="hola";
		
		circ.setNombre(nombre);
		
		CircunscripcionDAOImplementation.getInstancia().delete(circ);
		
		assertNull( CircunscripcionDAOImplementation.getInstancia().read(nombre));
	}

	@Test
	void testReadAll() {
		Collection<Circunscripcion> circs = CircunscripcionDAOImplementation.getInstancia().readAll();
		
		for (Circunscripcion circ : circs) {
			assertEquals(circ.getNombre(), CircunscripcionDAOImplementation.getInstancia().read(circ.getNombre()).getNombre());
		}
	}

}