package es.isst.demolab.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.isst.demolab.dao.CircunscripcionDAOImplementation;
import es.isst.demolab.model.Circunscripcion;

@WebServlet("/BorrarCircunscripcionesServlet")
public class BorrarCircunscripcionesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Borrar todas las circunscripciones existentes
		Collection<Circunscripcion> coleccionCirc = CircunscripcionDAOImplementation.getInstancia().readAll();
		for(Circunscripcion seleccionada : coleccionCirc) {
			CircunscripcionDAOImplementation.getInstancia().delete(seleccionada);
		}
		
		req.getSession().setAttribute( "circunscripcion_list", CircunscripcionDAOImplementation.getInstancia().readAll() );
		getServletContext().getRequestDispatcher( "/CrearCircunscripcionView.jsp" ).forward( req, resp );
	}
}
