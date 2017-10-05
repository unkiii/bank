package menus;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import controlador.GestorDB;

public class menusglobals {
	GestorDB gdb = new GestorDB();
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);

	public menusglobals() {
	}

	private void entraOpcio() {
		System.out.print("-Entra una opcio: ");
	}

	private void espais() {
		System.out.println();
		System.out.println();
		System.out.println();
	}

	public void menuUsuari() {
		espais();
		System.out.println("-------------------------------------------------------");
		System.out.println("------------------ Menu Principal.---------------------");
		System.out.println("-------------------------------------------------------");
		System.out.println("1 - Visualitza els Comptes Corrents");
		System.out.println("2 - Ingress");
		System.out.println("3 - Extract");
		System.out.println("4 - Traspass 'en cas de tenir 2 o mes comptes.' ");
		System.out.println("5 - Pagaments ");
		System.out.println("6 - AutoMoviments");
		System.out.println("7 - Administracio");
		entraOpcio();
	}

	public void administracio() {
		espais();
		System.out.println("-------------------------------------------------------");
		System.out.println("------------------ Administracio. ---------------------");
		System.out.println("-------------------------------------------------------");
		System.out.println("1.- Crear compte.");
		System.out.println("2.- Eliminar compte.");
		entraOpcio();
	}

	public void comptesUsuari() {
		espais();
		System.out.println("Opcio 1 - Comptes Corrents");
		System.out.println("-------------------------------------------------------");
		System.out.println("------------------ Els teus comptes -------------------");
		System.out.println("-------------------------------------------------------");
		System.out.println("1.- Veure comptes.");
		entraOpcio();
	}
}
