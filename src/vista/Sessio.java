package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import model.SessioDAO;
import model.Usuari;

public class Sessio {
	principal prin;
	SessioDAO sdao = new SessioDAO();
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	Usuari user = new Usuari();

	public Sessio() {
		try {
			entraDades();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void entraDades() throws SQLException {
		int iNom = 0;
		int iClau = 0;

		int correcte = -1;
		// ------------------------------------------------------------- NAME
		System.out.println("-Entra les teves dades per accedir al teu compte corrent:");
		do {
			System.out.print("DNI: ");
			String eNom = null;
			try {
				eNom = br.readLine(); // String
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error de lectura eNom.");
			}

			// ------------------------------------------------------------ PASS

			System.out.print("Contrasenya: ");
			String eClau = null;
			try {
				eClau = br.readLine(); // int
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error de lectura eClau.");
			}

			user.setNIF(eNom);
			user.setPasswd(eClau);
			correcte = sdao.validarLogin(user);
			// ------------------------------------------------------------- IN

			if (correcte == 1) {
				System.out.println("Login Correcte");
			} else {
				System.out.println("Les dades introduides No son correctes.");
			}
		} while (correcte != 1);

		// comprovar a la BDD si es admin o no, retorna 1 0 2, 1 admin 2 client.
		// mostrar el menu que toca segons el que sigui.
		// return 1;

	}
}
