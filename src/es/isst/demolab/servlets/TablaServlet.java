package es.isst.demolab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.isst.demolab.dao.CircunscripcionDAO;
import es.isst.demolab.dao.CircunscripcionDAOImplementation;
import es.isst.demolab.dao.EleccionDAO;
import es.isst.demolab.dao.EleccionDAOImplementation;
import es.isst.demolab.dao.PartidoDAO;
import es.isst.demolab.dao.PartidoDAOImplementation;
import es.isst.demolab.model.Circunscripcion;
import es.isst.demolab.model.Partido;

import java.util.HashMap;
import java.util.Map;



@WebServlet({ "/TablaServlet" })
public class TablaServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		PartidoDAO pdao = PartidoDAOImplementation.getInstancia();
//		req.getSession().setAttribute( "partido_list", pdao.readAll() );
//		
//		CircunscripcionDAO cdao = CircunscripcionDAOImplementation.getInstancia();
//		req.getSession().setAttribute( "circunscripcion_list", cdao.readAll() );
//		
		
//		Map<Partido, Map<Circunscripcion, Integer>> votos = new HashMap<Partido,Map<Circunscripcion,Integer>>();
		
		
		
//		req.getSession().setAttribute("votos", votos);
		
		
		getServletContext().getRequestDispatcher( "/TablaView.jsp" ).forward( req, resp );
	}
}
