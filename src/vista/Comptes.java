package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import menus.menusglobals;
import model.gestioComptes;

public class Comptes {
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	menusglobals mg = new menusglobals();
	gestioComptes gcom = new gestioComptes();

	public Comptes() {
		listmenu();
	}

	private void listmenu() {
		// System.out.println("1 - Informacio dels meus comptes."); // numero,
		// saldo,
		// moviments
		// System.out.println("2 - Gestio dels meus comptes"); // crear,
		// modificar,
		// eliminar
		mg.comptesUsuari();
		int opcio = -1;
		try {
			opcio = Integer.parseInt(br.readLine()); // int
			// entrada = br.readLine(); // String
			// opcio = Integer.parseInt(entrada); // int
		} catch (IOException e) {
			e.printStackTrace();
		}

		switch (opcio) {
		case 1:
			informacioComptesClient();

			break;
		}
	}

	private void informacioComptesClient() {
		System.out.println("Informacio dels teus comptes bancaris: ");

	}

	private void crearCompte() {
		int opcio = -1;
		System.out.println("Opcio 1 - Crear Compte:");
		System.out.println("Tipus de compte? ");
		System.out.println("1.- Compte corrent.");
		System.out.println("2.- Compte Ahorro.");
		System.out.println("3.- Compte Pla de Pensions.");
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
			cCorrent();

			break;
		case 2:
			cAhorro();

			break;
		case 3:
			cPlaPensions();

			break;
		}

	}

	private void cPlaPensions() {
		gcom.crearComptePlaPensions();
	}

	private void cAhorro() {
		gcom.crearCompteAhorro();
	}

	private void cCorrent() {
		gcom.crearCompteCorrent();
	}
}
