package es.isst.demolab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.shiro.crypto.hash.Sha256Hash;

import es.isst.demolab.dao.PartidoDAO;
import es.isst.demolab.dao.PartidoDAOImplementation;
import es.isst.demolab.model.Partido;


@WebServlet("/CrearPartidoServlet")
public class CrearPartidoServlet extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter( "name" );
		String color = req.getParameter( "color" );
		String acronimo = req.getParameter( "acronimo" );
		Partido partido = new Partido();
		partido.setNombre( name );
		partido.setColor( color );
		partido.setAcronimo( acronimo);
		
		
		
		PartidoDAO pdao = PartidoDAOImplementation.getInstancia();
		pdao.create( partido );
		
		req.getSession().setAttribute( "partido_list", pdao.readAll() );
		getServletContext().getRequestDispatcher( "/CrearPartidoView.jsp" ).forward( req, resp );
		
	}
	
}
