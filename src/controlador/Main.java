package controlador;

import vista.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		VentanaPrincipal miVentana = new VentanaPrincipal();
		GestorEventos gestor = new GestorEventos(miVentana);
		miVentana.establecerGestor(gestor);
	}

}
