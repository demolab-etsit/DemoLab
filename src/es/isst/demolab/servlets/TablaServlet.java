package es.isst.demolab.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import es.isst.demolab.dao.CircunscripcionDAO;
import es.isst.demolab.dao.CircunscripcionDAOImplementation;
import es.isst.demolab.dao.EleccionDAO;
import es.isst.demolab.dao.EleccionDAOImplementation;
import es.isst.demolab.dao.PartidoDAO;
import es.isst.demolab.dao.PartidoDAOImplementation;
import es.isst.demolab.model.Circunscripcion;
import es.isst.demolab.model.Elec_Circ_Part;
import es.isst.demolab.model.Partido;

import java.util.ArrayList;
import java.util.Collection;
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
		
		Collection<Partido> partidos = PartidoDAOImplementation.getInstancia().readAll();
		Collection<Circunscripcion> circunscripciones = CircunscripcionDAOImplementation.getInstancia().readAll();
		Collection<Elec_Circ_Part> votaciones = new ArrayList();

		String urlToXML = this.getServletContext().getRealPath("/WEB-INF/xml/2019/");  
		File xmlDir = new File(urlToXML);
		File[] xmlFiles = xmlDir.listFiles();
		
		System.out.println("\n Working directory(TablaServlet) = "+ urlToXML + "\n");

		//Inicializacion para leer el XML
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		Document document = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			for(File seleccionada : xmlFiles) {
				document = documentBuilder.parse(seleccionada);
				String nombreProvincia = seleccionada.getName();
				nombreProvincia = nombreProvincia.replaceAll(".xml", "");
				Circunscripcion circSeleccionada = null;
				for(Circunscripcion tempCirc : circunscripciones) {
//					System.out.println("tempCirc.getNombre()=" + tempCirc.getNombre()
//					+ "==nombreProvincia=" + nombreProvincia +"\n");
					if(tempCirc.getNombre().equals(nombreProvincia)) {
//						System.out.println("TablaServlet tempCirc encontrada:\n");
//						System.out.println("tempCirc.getNombre() = " + tempCirc.getNombre()
//						+ " == nombreProvincia " + nombreProvincia +"\n");
						circSeleccionada = tempCirc;
					}
				}
				
//				System.out.println("El nombre de la provincia del XML seleccionado es = " + nombreProvincia + "\n");
				int numeroPartidos = document.getElementsByTagName("nombre").getLength();
				String[] nombrePartido = new String[numeroPartidos];
				String[] numVotosDelPartido = new String[numeroPartidos];
				for(int i = 0; i<numeroPartidos; i++) {
					nombrePartido[i] =  document.getElementsByTagName("nombre").item(i).getTextContent();
					numVotosDelPartido[i] = document.getElementsByTagName("votos_numero").item(i).getTextContent(); 
					if(nombrePartido[i].contains("PSOE")) {
						nombrePartido[i] = "PSOE";
					}
					if(nombrePartido[i].equals("PSC")) {
						nombrePartido[i] = "PSOE";
					}
					if(nombrePartido[i].equals("PSE-EE")) {
						nombrePartido[i] = "PSOE";
					}
					if(nombrePartido[i].equals("C's")) {
						nombrePartido[i] = "Cs";
					}
					if(nombrePartido[i].contains("PODEMOS")) {
						nombrePartido[i] = "PODEMOS";
					}
					if(nombrePartido[i].contains("GUANYEM")) {
						nombrePartido[i] = "PODEMOS";
					}
				}

				Partido partSeleccionado = null;
				for(Partido tmpPartido : partidos) {
					//						System.out.println("tempPartido.getAcronimos()=" + tmpPartido.getAcronimo()
					//						+ "==nombrePartido=" + nombrePartido +"\n");
					int j = 0;
					boolean partidoEncontrado = false;
					for(String nombrePartidoSingle : nombrePartido) {
						if(tmpPartido.getAcronimo().equals(nombrePartidoSingle)) {
							System.out.println("TablaServlet tempPartido encontrado:\n");
							System.out.println("tempPartido.getAcronimos()=" + tmpPartido.getAcronimo()
							+ "==nombrePartido=" + nombrePartidoSingle +"\n");
							partSeleccionado = tmpPartido;

							Elec_Circ_Part elec_circ_part_i = new Elec_Circ_Part();
							elec_circ_part_i.setCircunscripcion(circSeleccionada);
							elec_circ_part_i.setPartido(partSeleccionado);
							elec_circ_part_i.setNVotos(Integer.parseInt(numVotosDelPartido[j]));
							votaciones.add(elec_circ_part_i);
							partidoEncontrado = true;
						}
						j++;
					}
					if(!partidoEncontrado) {
						Elec_Circ_Part elec_circ_part_i = new Elec_Circ_Part();
						elec_circ_part_i.setCircunscripcion(circSeleccionada);
						elec_circ_part_i.setPartido(tmpPartido);
						elec_circ_part_i.setNVotos(0);
						votaciones.add(elec_circ_part_i);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Elec_Circ_Part i : votaciones) {
			System.out.println("Provincia= " + i.getCircunscripcion().getNombre() + "\n");
			System.out.println("\tEl partido " + i.getPartido().getAcronimo() + " ha obtenido "
					+ i.getNvotosString() + " votos\n");
		}
		req.getSession().setAttribute("votaciones", votaciones);
		req.getSession().setAttribute( "partido_list", partidos );
		req.getSession().setAttribute( "circunscripcion_list", circunscripciones );
		getServletContext().getRequestDispatcher( "/TablaView.jsp" ).forward( req, resp );
	}
}
