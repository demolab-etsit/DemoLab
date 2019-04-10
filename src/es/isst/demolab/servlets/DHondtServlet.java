package es.isst.demolab.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.isst.demolab.dao.CircunscripcionDAO;
import es.isst.demolab.dao.CircunscripcionDAOImplementation;
import es.isst.demolab.dao.PartidoDAO;
import es.isst.demolab.dao.PartidoDAOImplementation;
import es.isst.demolab.model.Circunscripcion;
import es.isst.demolab.model.Elec_Circ_Part;
import es.isst.demolab.model.Partido;


@WebServlet("/DHondtServlet")
public class DHondtServlet extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
		PartidoDAO pdao = PartidoDAOImplementation.getInstancia();
		
		Collection<Partido> partidoList= pdao.readAll() ;
		
		
		CircunscripcionDAO cdao = CircunscripcionDAOImplementation.getInstancia();
		
		Collection<Circunscripcion> circunscripcionList= cdao.readAll() ;
		
		Circunscripcion[] circunscripcionArray = circunscripcionList.toArray(new Circunscripcion[circunscripcionList.size()]);
		
		Partido[] partidoArray = partidoList.toArray(new Partido[partidoList.size()]);

		
		
		Elec_Circ_Part[][] votaciones = new Elec_Circ_Part[circunscripcionArray.length][partidoArray.length];
		
		for(int i=0; i<circunscripcionArray.length;i++) {
			for(int j=0; j<partidoArray.length; j++) {
				votaciones[i][j] = new Elec_Circ_Part();
				votaciones[i][j].setCircunscripcion(circunscripcionArray[i]);
				votaciones[i][j].setPartido(partidoArray[j]);
				
				String part = partidoArray[j].getAcronimo();
				String circ = circunscripcionArray[i].getNombre();
				String partCirc= part.concat(circ);
				System.out.println(partCirc);
				votaciones[i][j].setNVotos(Integer.parseInt(req.getParameter(partCirc)));
			}
		}
		
		
		
		
		req.getSession().setAttribute( "votaciones", votaciones );
		getServletContext().getRequestDispatcher( "/ResultadosView.jsp" ).forward( req, resp );
	}
	
}
