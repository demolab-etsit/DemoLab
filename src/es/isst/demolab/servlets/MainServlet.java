package es.isst.demolab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import es.isst.demolab.dao.CircunscripcionDAO;
import es.isst.demolab.dao.CircunscripcionDAOImplementation;
import es.isst.demolab.dao.EleccionDAO;
import es.isst.demolab.dao.EleccionDAOImplementation;
import es.isst.demolab.dao.PartidoDAO;
import es.isst.demolab.dao.PartidoDAOImplementation;


@WebServlet({ "/" })
public class MainServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PartidoDAO pdao = PartidoDAOImplementation.getInstancia();
		req.getSession().setAttribute( "partido_list", pdao.readAll() );
		
		CircunscripcionDAO cdao = CircunscripcionDAOImplementation.getInstancia();
		req.getSession().setAttribute( "circunscripcion_list", cdao.readAll() );
		
		EleccionDAO edao = EleccionDAOImplementation.getInstancia();
		req.getSession().setAttribute( "eleccion_list", edao.readAll() );
		
		getServletContext().getRequestDispatcher( "/IndexView.jsp" ).forward( req, resp );
	}
	
}