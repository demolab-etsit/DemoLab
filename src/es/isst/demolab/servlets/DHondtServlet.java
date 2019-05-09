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
				System.out.println("partCirc= " + partCirc); //Imprimir cosas
				req.setCharacterEncoding("UTF-8");
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

			asignaColorCircunscripcion(votaciones, circunscripcionArray[i]);
		}
		
		//Conjuntos de escaños para toda España
		/*
		 * int[] resultado= getArrayEscanosPartido(partidoArray,votaciones);
		 * req.getSession().setAttribute("arrayEscanos", resultado);
		 */
		Elec_Circ_Part[] ecp =getArrayEscanosPartido(partidoArray, votaciones); 
		String[] labels= getLabels(ecp);
		int[] escanios=getEscanos(ecp);
		
		req.getSession().setAttribute("labels", labels);
		req.getSession().setAttribute("escanios", escanios);
		Collection<Circunscripcion> circunscripcionList2= cdao.readAll() ;
		
		Circunscripcion[] circunscripcionArray2 = circunscripcionList2.toArray(new Circunscripcion[circunscripcionList2.size()]);
		
		
		
		req.getSession().setAttribute( "partidoArray", partidoArray );
		req.getSession().setAttribute( "circunscripcionArray", circunscripcionArray );
		req.getSession().setAttribute( "circunscripcionArray2", circunscripcionArray2 );

		req.getSession().setAttribute( "circunscripcionList2", circunscripcionList2 );
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
	
	private void asignaColorCircunscripcion(Collection<Elec_Circ_Part> votaciones, Circunscripcion c) {
		Collection <Elec_Circ_Part> ECPSporCirc = new ArrayList();
		ECPSporCirc=sacaECPCircunscripcion(votaciones, c);
		Elec_Circ_Part ECPganador = new Elec_Circ_Part();
		int mayor=-2999;
		for(Elec_Circ_Part ECP : ECPSporCirc) {
			if(ECP.getNEscanos()>mayor) {
				mayor=ECP.getNEscanos();
				ECP.getCircunscripcion().setColorCircunscripcion(ECP.getPartido().getColor());
				System.out.println("Nombre de la circunscripcion: " + ECP.getCircunscripcion().getNombre()
						+ "Nombre del partido: " + ECP.getPartido().getNombre() 
						+ "Color de la circunscripciï¿½n: " + ECP.getCircunscripcion().getColorCircunscripcion()
						+ "Nï¿½mero de escannos de la circunscripciï¿½n: " +ECP.getNEscanos());
				
				ECPganador=ECP;
				
				CircunscripcionDAOImplementation.getInstancia().update(ECP.getCircunscripcion());
			}
			else if(ECP.getNEscanos()==mayor){
				if(ECP.getNVotos()> ECPganador.getNVotos()) {
					ECP.getCircunscripcion().setColorCircunscripcion(ECP.getPartido().getColor());
					System.out.println("CASO DE EMPATE!!!!!!!!!!!!!!!!!!!!!!!!!!! Nombre de la circunscripcion: " + ECP.getCircunscripcion().getNombre()
							+ "Nombre del partido: " + ECP.getPartido().getNombre() 
							+ "Color de la circunscripciï¿½n: " + ECP.getCircunscripcion().getColorCircunscripcion()
							+ "Nï¿½mero de escannos de la circunscripciï¿½n: " +ECP.getNEscanos());
				
					CircunscripcionDAOImplementation.getInstancia().update(ECP.getCircunscripcion());

				
				}
				
			}
			
			
		}
		
	}
	
	public Elec_Circ_Part getEscanosPartido(Partido p, Collection<Elec_Circ_Part> vot) {
		int nEscanosTotal=0;
		Elec_Circ_Part ecpResultado = new Elec_Circ_Part();
		for (Elec_Circ_Part ecp : vot) {
			if(ecp.getPartido().getNombre()==p.getNombre()) {
				nEscanosTotal = nEscanosTotal + ecp.getNEscanos();
			}
		}
		ecpResultado.setPartido(p);
		ecpResultado.setNEscanos(nEscanosTotal);
		return ecpResultado;
	}
	
	public Elec_Circ_Part[] getArrayEscanosPartido(Partido[] arrayPartidos, Collection<Elec_Circ_Part> vot) {
		Elec_Circ_Part[] resultado = new Elec_Circ_Part[arrayPartidos.length];
		int i=0;
		for(Partido p:arrayPartidos) {
			resultado[i]=getEscanosPartido(p, vot);
			i++;
		}
		System.out.println("Método getArrayEscanosPartido = " + "Posición 0: " +resultado[0].getPartido().getNombre() + "Posición 1: " + resultado[1].getPartido().getNombre());
		
		return resultado;
	}
	
	public Elec_Circ_Part[] getArrayEscanosPartidosGanadores(Elec_Circ_Part[] ecps) {
		Elec_Circ_Part[] ganadores = new Elec_Circ_Part[5];
		Elec_Circ_Part ecp0 = new Elec_Circ_Part();
		Elec_Circ_Part ecp1 = new Elec_Circ_Part();
		Elec_Circ_Part ecp2 = new Elec_Circ_Part();
		Elec_Circ_Part ecp3 = new Elec_Circ_Part();
		Elec_Circ_Part ecp4 = new Elec_Circ_Part();
		ganadores[0]= ecp0;
		ganadores[1]= ecp1;
		ganadores[2]= ecp2;
		ganadores[3]= ecp3;
		ganadores[4]= ecp4;
		//Ordeno bubble sort
		 int n = ecps.length;
		    for (int i = 1; i <= n - 1; i++) {
		        int x = ecps[i].getNEscanos();
		        int j = i - 1;
		        while (j >= 0 && x < ecps[j].getNEscanos()) {
		        	ecps[j + 1] =ecps[j];
		            j = j - 1;
		        }
		        ecps[j + 1] = ecps[i];
		    }
	    for(int t=0; t<5; t++) {
	    	ganadores[t].setNEscanos(ecps[t].getNEscanos());
	    	ganadores[t].setPartido(ecps[t].getPartido());
	    }
	    System.out.println("Ganadores: Ganador 1: " + ganadores[0].getPartido().getNombre() + "Ganador 2 :" + ganadores[1].getPartido().getNombre());
		return ganadores;
	}
	
	public String[] getLabels(Elec_Circ_Part[] ganadores) {
		String[] labels = new String[5];
		labels[0] =ganadores[0].getPartido().getNombre();
		labels[1] =ganadores[1].getPartido().getNombre();
		labels[2] =ganadores[2].getPartido().getNombre();
		labels[3] =ganadores[3].getPartido().getNombre();
		labels[4] =ganadores[4].getPartido().getNombre();
		

		
		
		
		return labels;
	}
	
	public int[] getEscanos(Elec_Circ_Part[] ganadores) {
		int[] data = new int[5];
		data[0] =ganadores[0].getNEscanos();
		data[1] =ganadores[1].getNEscanos();
		data[2] =ganadores[2].getNEscanos();
		data[3] =ganadores[3].getNEscanos();
		data[4] =ganadores[4].getNEscanos();
		

		
		
		return data;
	}
}
