package es.isst.demolab.dao;

import java.util.Collection;

import es.isst.demolab.model.Circunscripcion;

public interface CircunscripcionDAO {

	public void create(Circunscripcion circunscripcion);

	public Circunscripcion read(int id);

	public void update(Circunscripcion circunscripcion);

	public void delete(Circunscripcion circunscripcion);

	public Collection<Circunscripcion> readAll();
}
