package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controlador.Constants;
import controlador.GestorDB;

public class SessioDAO {

	GestorDB gestorDB;

	public SessioDAO() {
		gestorDB = new GestorDB(Constants.SERVER, Constants.PORT, Constants.BD);
	}

	public boolean iniciSessio(String nif, String pw) {
		try {
			ResultSet rs = null;
			String consultaSQL = "SELECT * FROM clients WHERE nif='" + nif + "' AND pass='" + pw + "'";
			System.out.println("inciciSessio :: -NIF: " + nif + " -pw: " + pw);
			rs = gestorDB.consultaRegistres(consultaSQL);

			if (rs.first()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public int validarLogin(Usuari usuari) {
		int correcte = -1;
		ResultSet rs = null;
		List<String> llista = new ArrayList<String>();

		rs = gestorDB.consultaRegistres("SELECT nif, pass FROM clients WHERE nif='" + usuari.getNIF() + "' AND pass='"
				+ usuari.getPasswd() + "'");

		try {
			while (rs.next()) {
				correcte = 1;
				llista.add(rs.getString("nif"));
				llista.add(rs.getString("pass"));
				// System.out.println("validarlogin :: -NIF: " +
				// rs.getString("nif") + " -pw: " + rs.getString("pass"));
			}
		} catch (SQLException e) {
			System.out.println("Usuari / Pass INcorrectes.");
			correcte = 0;
		}

		return correcte;
	}

	public List<String> valorsUsuari(String nif, String pass) throws SQLException {
		List<String> llista = new ArrayList<String>();
		ResultSet rs = null;
		String consultaSQL = "SELECT u.nom, u.permis FROM clients AS u " + "WHERE u.nif='" + nif + "' AND u.pass='"
				+ pass + "'";
		System.out.println("valorsusuari :: -NIF: " + nif + " -pw: " + pass);

		rs = gestorDB.consultaRegistres(consultaSQL);
		while (rs.next()) {
			llista.add(rs.getString("nom"));
			llista.add("" + rs.getInt("permis"));
		}
		return llista;
	}

	public void tancarConn() {
		gestorDB.tancarConnexio();
	}
}