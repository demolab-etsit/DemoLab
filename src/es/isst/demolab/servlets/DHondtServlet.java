package es.isst.demolab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
		
		Collection<Elec_Circ_Part> votaciones = new ArrayList();
		
		
	//	Elec_Circ_Part[][] votaciones2 = new Elec_Circ_Part[circunscripcionArray.length][partidoArray.length];
		
		for(int i=0; i<circunscripcionArray.length;i++) {
			for(int j=0; j<partidoArray.length; j++) {
				Elec_Circ_Part cosa = new Elec_Circ_Part();
				cosa.setCircunscripcion(circunscripcionArray[i]);
				cosa.setPartido(partidoArray[j]);
				
				String part = partidoArray[j].getAcronimo();
				String circ = circunscripcionArray[i].getNombre();
				String partCirc= part.concat(circ);
			//	System.out.println(partCirc); //Imprimir cosas
				cosa.setNVotos(Integer.parseInt(req.getParameter(partCirc)));
				votaciones.add(cosa);
			//	System.out.println(cosa.getNVotos());
			}
		}
		
		
		//Secuencia,
		
		for(int i=0; i<circunscripcionArray.length; i++) {
			calculaDHondt(ordenaPorVotos(
					sacaDivisiones(
					sacaECPCircunscripcion(votaciones, circunscripcionArray[i])
					)
					),
					votaciones);

		}
		
		
		
		req.getSession().setAttribute( "partidoArray", partidoArray );
		req.getSession().setAttribute( "circunscripcionArray", circunscripcionArray );
		req.getSession().setAttribute( "votaciones", votaciones );
		getServletContext().getRequestDispatcher( "/ResultadosView.jsp" ).forward( req, resp );
	}
	
	
	private ArrayList<Elec_Circ_Part> sacaECPCircunscripcion(Collection<Elec_Circ_Part> votaciones, Circunscripcion c) {
		
		ArrayList<Elec_Circ_Part> votosPorCirc=new ArrayList<Elec_Circ_Part>();
		for(Elec_Circ_Part ECP: votaciones) {
			if(ECP.getCircunscripcion().getNombre() == c.getNombre()) {
				votosPorCirc.add(ECP);
			}
		}
		

		return votosPorCirc;
	}
	
	
	private ArrayList<Elec_Circ_Part> sacaDivisiones(ArrayList<Elec_Circ_Part> votosPorCirc){
		ArrayList<Elec_Circ_Part> votosDivididosPorCirc=new ArrayList<Elec_Circ_Part>();
		votosDivididosPorCirc=votosPorCirc;
		System.out.println("2)votosPorCirc.size()= "+ votosPorCirc.size());

		int tamano = votosPorCirc.size();
		
		for(int j=0; j<tamano; j++) { //Elec_Circ_Part ECP: votosPorCirc) {
			//System.out.println("Primer for del sacaDivisiones");
			//System.out.println("votosPorCirc.size()= "+ votosPorCirc.size());

			for(int i=2; i<votosPorCirc.get(j).getCircunscripcion().getNMaxEscanos();i++) {
				Elec_Circ_Part ECPDivididos = new Elec_Circ_Part();
				ECPDivididos.setCircunscripcion(votosPorCirc.get(j).getCircunscripcion());
				ECPDivididos.setPartido(votosPorCirc.get(j).getPartido());
				ECPDivididos.setNVotos(votosPorCirc.get(j).getNVotos()/i);
				votosDivididosPorCirc.add(ECPDivididos);
			//	System.out.println("2)Dentro for" + votosDivididosPorCirc.get(i).getNVotos());
			}
		}
		
		
		return votosDivididosPorCirc;
	}
	
	
	private ArrayList<Elec_Circ_Part> ordenaPorVotos(ArrayList<Elec_Circ_Part> votosDivididosPorCirc){
		
		
		Elec_Circ_Part[] ECPOrdenadosArray = new Elec_Circ_Part[votosDivididosPorCirc.size()];
		ECPOrdenadosArray= votosDivididosPorCirc.toArray(ECPOrdenadosArray);
		
		int i,j;
		Elec_Circ_Part aux = new Elec_Circ_Part();
		for(i=0; i<ECPOrdenadosArray.length-1;i++) {
			for(j=0;j<ECPOrdenadosArray.length-i-1;j++) {
				if(ECPOrdenadosArray[j+1].getNVotos()>ECPOrdenadosArray[j].getNVotos()) {
					aux=ECPOrdenadosArray[j+1];
					ECPOrdenadosArray[j+1]=ECPOrdenadosArray[j];
					ECPOrdenadosArray[j]=aux;
				}
			}
		}
		ArrayList<Elec_Circ_Part> ECPOrdenados=new ArrayList<Elec_Circ_Part>(Arrays.asList(ECPOrdenadosArray));
		System.out.println("3)NumVotos mas alto= "+ ECPOrdenados.get(0).getNVotos());

		return ECPOrdenados;
	}
	
	private void calculaDHondt(ArrayList<Elec_Circ_Part> ECPOrdenados, Collection<Elec_Circ_Part> votaciones) {
		for(int i=0; i<ECPOrdenados.get(0).getCircunscripcion().getNMaxEscanos(); i++) {
			for(Elec_Circ_Part vot:votaciones) {
				if(ECPOrdenados.get(i).getPartido()==vot.getPartido() && ECPOrdenados.get(i).getCircunscripcion()== vot.getCircunscripcion()) {

					vot.addEscanos();
					System.out.println("4)escanos "+ vot.getCircunscripcion().getNombre()+vot.getPartido().getAcronimo()+vot.getNEscanos());

				}
			}
		}

	}
	
}
