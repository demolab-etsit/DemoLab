package es.isst.demolab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import es.isst.demolab.dao.EleccionDAO;
import es.isst.demolab.dao.EleccionDAOImplementation;
import es.isst.demolab.model.Eleccion;
import es.isst.demolab.model.TipoElecciones;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;



@WebServlet("/CrearEleccionServlet")
public class CrearEleccionServlet extends HttpServlet {
	
	public static Date ParseFecha(String Fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate = null;
		try {
			fechaDate = formato.parse(Fecha);
		}
		catch(ParseException ex) {
			System.out.println(ex);
		}
		return fechaDate;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String fecha = req.getParameter( "date" );
		TipoElecciones tipo = TipoElecciones.valueOf(req.getParameter("type")); 
		Date date = ParseFecha(fecha);
		Eleccion elec = new Eleccion();
		elec.setFecha( date );
//		elec.setFechaString(fecha);
		elec.setTipo( tipo );
		
		
		
		EleccionDAO edao = EleccionDAOImplementation.getInstancia();
		edao.create( elec );
		
		req.getSession().setAttribute( "eleccion_list", edao.readAll() );
		getServletContext().getRequestDispatcher( "/CrearEleccionView.jsp" ).forward( req, resp );
	}
	
}
