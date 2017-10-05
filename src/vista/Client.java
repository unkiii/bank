package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import model.Usuari;
import model.UsuariDAO;

public class Client {
	UsuariDAO udao = new UsuariDAO();
	Usuari user = new Usuari();
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);

	public Client() {
		listarmenu();
	}

	private void listarmenu() {
		System.out.println("Mantenimiento de Usuarios.");
		System.out.println("Menu Princpal");
		System.out.println("1 - Alta Usuari");
		System.out.println("2 - Baixa Usuari");
		System.out.println("3 - Modificar Usuari");
		System.out.println("4 - Llistat Usuari");
		casos();
	}

	private void casos() {
		String entrada = null;
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
			System.out.println("Opcio 1 - Alta");
			try {
				alta();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("error en Metodo Alta.");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("error en Metodo Alta - user.");
			}
			break;

		case 2:
			System.out.println("Opcio 2 - Baja");
			baixa();
			break;

		case 3:
			System.out.println("Opcio 3 - Modificar");
			modificar();
			break;

		case 4:
			System.out.println("Opcio 4 -  Listado");
			llistat();
			break;

		default:
			System.out.println("default");
			break;
		}
	}

	private void alta() throws IOException, SQLException {
		String dni;
		String pass;
		String nom;
		String cognom;
		String datanaix;
		String dire;
		String pobl;
		String cp;

		System.out.println("DNI: ");
		dni = br.readLine();
		System.out.println("Contraseña: ");
		pass = br.readLine();
		System.out.println("Nombre: ");
		nom = br.readLine();
		System.out.println("Apellido: ");
		cognom = br.readLine();
		System.out.println("Edad: ");
		datanaix = br.readLine();
		System.out.println("Direccion: ");
		dire = br.readLine();
		System.out.println("Poblacion: ");
		pobl = br.readLine();
		System.out.println("CodigoPostal: ");
		cp = br.readLine();

		user.setNIF(dni);
		user.setPasswd(pass);
		user.setNom(nom);
		user.setCognom(cognom);
		user.setDatanaix(datanaix);
		user.setDireccio(dire);
		user.setPoblacio(pobl);
		user.setCodipostal(cp);
		udao.altaUsuari(user);
	}

	private void baixa() {

	}

	private void modificar() {

	}

	private void llistat() {
		int i = 0;
		Object[] list = null;
		try {
			list = udao.consultarUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (list[i] != null || list[i] != " ") {
			System.out.println(list[i]);
			i++;

		}
	}

}
