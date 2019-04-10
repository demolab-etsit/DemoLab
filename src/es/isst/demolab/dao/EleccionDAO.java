package es.isst.demolab.dao;

import java.util.Collection;

import es.isst.demolab.model.Eleccion;

public interface EleccionDAO {
	public void create(Eleccion eleccion);

	public Eleccion read(int id);

	public void update(Eleccion eleccion);

	public void delete(Eleccion eleccion);

	public Collection<Eleccion> readAll();

}
