package es.isst.demolab.dao;

import java.util.Collection;

import es.isst.demolab.model.Circunscripcion;
import es.isst.demolab.model.Elec_Circ_Part;
import es.isst.demolab.model.Eleccion;
import es.isst.demolab.model.Partido;

public interface Elec_Circ_PartDAO {
	public void create(Elec_Circ_Part elec_Circ_Part);

	public Elec_Circ_Part read(int id);

	public void update(Elec_Circ_Part elec_Circ_Part);

	public void delete(Elec_Circ_Part elec_Circ_Part);

	public Collection<Elec_Circ_Part> readAll();

}
