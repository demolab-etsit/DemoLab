package es.isst.demolab.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.isst.demolab.dao.PartidoDAOImplementation;
import es.isst.demolab.model.Partido;

@WebServlet("/BorrarPartidosServlet")
public class BorrarPartidosServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Borrar todas los partidos existentes
		Collection<Partido> coleccionPartidos = PartidoDAOImplementation.getInstancia().readAll();
		for(Partido seleccionada : coleccionPartidos) {
			PartidoDAOImplementation.getInstancia().delete(seleccionada);
		}
		
		req.getSession().setAttribute( "partido_list", PartidoDAOImplementation.getInstancia().readAll() );
		getServletContext().getRequestDispatcher( "/CrearPartidoView.jsp" ).forward( req, resp );
	}
}
