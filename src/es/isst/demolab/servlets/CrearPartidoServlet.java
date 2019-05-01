package es.isst.demolab.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.w3c.dom.Document;

import es.isst.demolab.dao.CircunscripcionDAOImplementation;
import es.isst.demolab.dao.PartidoDAO;
import es.isst.demolab.dao.PartidoDAOImplementation;
import es.isst.demolab.model.Partido;
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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Borrar todas los partidos existentes
		Collection<Partido> coleccionPartidos = PartidoDAOImplementation.getInstancia().readAll();
		for(Partido seleccionada : coleccionPartidos) {
			PartidoDAOImplementation.getInstancia().delete(seleccionada);
		}
		
		//Directorio al XML PartidosPoliticos.xml
		String urlToXML = this.getServletContext().getRealPath("/WEB-INF/xml/PartidosPoliticos.xml");  
		System.out.println("\n Working directory = "+ urlToXML + "\n");
		File xml = new File(urlToXML);
		
		//Inicializacion para leer el XML
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		Document document = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Va obteniendo los datos del XML y los pasa a la base de datos
		int numeroPartidos = document.getElementsByTagName("nombre").getLength();
		//Numero para contener el numero de Partidos, para ignorar los minoritarios
		numeroPartidos = 10;
		//Recorremos todos los partidos del documento XML
		String nombre = "";
		for(int i = 0; i<numeroPartidos; i++) {
			Partido partido = new Partido();
			nombre = document.getElementsByTagName("nombre").item(i).getTextContent();
			nombre.strip();
			
			partido.setNombre(nombre);
			partido.setAcronimo(nombre);
			PartidoDAOImplementation.getInstancia().create(partido);
		}
		
		req.getSession().setAttribute( "partido_list", PartidoDAOImplementation.getInstancia().readAll() );
		getServletContext().getRequestDispatcher( "/CrearPartidoView.jsp" ).forward( req, resp );
	}
	
}
