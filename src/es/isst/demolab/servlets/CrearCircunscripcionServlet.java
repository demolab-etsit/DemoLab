package es.isst.demolab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import es.isst.demolab.dao.CircunscripcionDAO;
import es.isst.demolab.dao.CircunscripcionDAOImplementation;
import es.isst.demolab.model.Circunscripcion;



@WebServlet("/CrearCircunscripcionServlet")
public class CrearCircunscripcionServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter( "name" );
		String NElec = req.getParameter( "NElec" );
		String NEscanos = req.getParameter( "NEscanos" );
		int electores = Integer.parseInt(NElec);
		int escanos = Integer.parseInt(NEscanos);
		Circunscripcion circ = new Circunscripcion();
		circ.setNombre( name );
		circ.setNElectores( electores );
		circ.setNMaxEscanos( escanos );
		
		
		
		CircunscripcionDAO cdao = CircunscripcionDAOImplementation.getInstancia();
		cdao.create( circ );
		
		req.getSession().setAttribute( "circunscripcion_list", cdao.readAll() );
		getServletContext().getRequestDispatcher( "/CrearCircunscripcionView.jsp" ).forward( req, resp );
	}
	
}
