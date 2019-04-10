package es.isst.demolab.dao;

import java.util.Collection;

import es.isst.demolab.model.Partido;

public interface PartidoDAO {

	public void create(Partido partido);

	public Partido read(int id);

	public void update(Partido partido);

	public void delete(Partido partido);

	public Collection<Partido> readAll();
}
