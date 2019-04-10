package es.isst.demolab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import es.isst.demolab.dao.PartidoDAO;
import es.isst.demolab.dao.PartidoDAOImplementation;
import es.isst.demolab.model.Partido;


@WebServlet("/DHondtServlet")
public class DHondtServlet extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		int votoPrueba = Integer.parseInt(req.getParameter( "PSOE" ));
		
		req.getSession().setAttribute( "voto", votoPrueba );
		getServletContext().getRequestDispatcher( "/ResultadosView.jsp" ).forward( req, resp );
	}
	
}
