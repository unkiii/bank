package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controlador.GestorDB;
import menus.menusglobals;

public class principal {
	GestorDB gdb = new GestorDB();
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	menusglobals mg = new menusglobals();

	public principal() {
		welcome();
		inicisessio();
	}

	private void welcome() {
		System.out.println("-------------------------------------------------------");
		System.out.println("----------------------- urBank-------------------------");
		System.out.println("----------------- Benvingut al teu Bank ---------------");
		System.out.println("-------------------------------------------------------");
		System.out.println();

	}

	private void inicisessio() {
		Sessio ses = new Sessio(); // retorna 1 o 2 segons si es admin o client.

		// Si es 1, es mostrara el menu d'administrador
		// Si es 1, s'envia al metode per al casos del administrador.

		mg.menuUsuari(); // si es 2, es mostra aquest
		casosMenuUsuari(); // si es 2, es mostra aquest
	}

	private void casosMenuUsuari() { // Menu NO compartit amb el admistrador, ja
										// que un no te res a veure amb l'altre,
										// el menu d'adminsitrador l'unic que fa
										// es administrar els comptes i els
										// usuaris, assignan a cada client un
										// compte corrent i creant nous comptes.
		int opcio = -1;
		try {
			opcio = Integer.parseInt(br.readLine()); // int
			// entrada = br.readLine(); // String
			// opcio = Integer.parseInt(entrada); // int
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		switch (opcio) {
		case 1:
			Comptes cm = new Comptes();
			break;
		case 2:
			welcome();
			break;
		case 3:
			welcome();
			break;
		case 4:
			welcome();
			break;
		case 5:
			welcome();
			break;
		}

	}

	private void casosMenuAdmin() { // Menu NO compartit amb client. ja que el
									// client l'unic que fa es manejar el
									// seu propi compte corrent, no el pot
									// gestionar, ni esborrar ni modificar
									// res de les seves dades, aixo no es
									// està permes per a un administrador.

		// les opcions per al administrador son:
		// crear usuari,
		// crear compte corrent,
		// crear la vinculacio de usuari i compte.
		int opcio = -1;
		try {
			opcio = Integer.parseInt(br.readLine()); // int
			// entrada = br.readLine(); // String
			// opcio = Integer.parseInt(entrada); // int
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		switch (opcio) {
		case 1:
			Comptes cm = new Comptes();
			break;
		case 2:
			welcome();
			break;
		case 3:
			welcome();
			break;
		case 4:
			welcome();
			break;
		case 5:
			welcome();
			break;
		}

	}
}
