package es.isst.demolab.servlets;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Borrar todas las circunscripciones existentes
		Collection<Circunscripcion> coleccionCirc = CircunscripcionDAOImplementation.getInstancia().readAll();
		for(Circunscripcion seleccionada : coleccionCirc) {
			CircunscripcionDAOImplementation.getInstancia().delete(seleccionada);
		}
		
		//Directorio al XML Provincias_3.xml
		String urlToXML = this.getServletContext().getRealPath("/WEB-INF/xml/Provincias_5.xml");  
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
		int numeroProvincias = document.getElementsByTagName("nombre").getLength();
		String nombre = "";
		String nElectores = "";
		String nMaxEscanos = "";
		String colorCircunscripcion = "";
		for(int i = 0; i<numeroProvincias; i++) {
			//Iniciar circunscripcion por circunscripcion
			Circunscripcion circunscripcion = new Circunscripcion();
			//SET
			nombre = document.getElementsByTagName("nombre").item(i).getTextContent();
			nElectores = document.getElementsByTagName("NElectores").item(i).getTextContent();
			nMaxEscanos = document.getElementsByTagName("NMaxEscanos").item(i).getTextContent();
			colorCircunscripcion = document.getElementsByTagName("ColorCircunscripcion").item(i).getTextContent();
			
			System.out.println("Circunscripcion = "+ nombre + "/" + nElectores + "/" 
					+ nMaxEscanos + "/" + colorCircunscripcion + "\n");
			circunscripcion.setNombre(nombre);
			circunscripcion.setNElectores(Integer.parseInt(nElectores));
			circunscripcion.setNMaxEscanos(Integer.parseInt(nMaxEscanos));
			circunscripcion.setColorCircunscripcion(colorCircunscripcion);
			CircunscripcionDAOImplementation.getInstancia().create(circunscripcion);
		}
		
		req.getSession().setAttribute( "circunscripcion_list", CircunscripcionDAOImplementation.getInstancia().readAll() );
		getServletContext().getRequestDispatcher( "/CrearCircunscripcionView.jsp" ).forward( req, resp );
	}
	
}
