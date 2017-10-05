package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controlador.Constants;
import controlador.GestorDB;

public class UsuariDAO {

	GestorDB gestorDB;

	public UsuariDAO() {
		gestorDB = new GestorDB(Constants.SERVER, Constants.PORT, Constants.BD);
	}

	public List<String> validarLogin(Usuari usuari) throws SQLException {
		ResultSet rs = null;
		List<String> llista = new ArrayList<String>();

		rs = gestorDB.consultaRegistres("SELECT nif, pass FROM clients WHERE nif='" + usuari.getNIF() + "' AND pass='"
				+ usuari.getPasswd() + "'");

		while (rs.next()) {
			llista.add(rs.getString("nif"));
			llista.add(rs.getString("pass"));
			System.out.println("validarlogin :: -NIF: " + rs.getString("nif") + " -pw: " + rs.getString("pass"));
		}

		return llista;
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

	public void altaUsuari(Usuari usuari) throws SQLException {
		String consultaSQL = null;
		consultaSQL = "INSERT INTO clients VALUES(0,'" + usuari.getNIF() + "','" + usuari.getPasswd() + "','"
				+ usuari.getNom() + "','" + usuari.getDatanaix() + "','" + usuari.getDireccio() + "','"
				+ usuari.getPoblacio() + "','" + usuari.getCodipostal() + "');";

		System.out.println(consultaSQL);
		gestorDB.modificarRegistre(consultaSQL);

	}

	public Object[] consultarUsers() throws SQLException {
		ResultSet rs = null;
		String consultaSQL = "SELECT * from clients";
		ArrayList<Object> fila = new ArrayList<Object>();
		int i = 0;

		rs = gestorDB.consultaRegistres(consultaSQL);
		while (rs.next()) {
			fila.add(rs.getObject("nif"));
		}

		rs.close();
		return fila.toArray();
	}

	public String nomClient(String nif) throws SQLException {
		List<String> llista = new ArrayList<String>();
		ResultSet rs = null;
		String consultaSQL = "SELECT nom FROM clients where nif like '" + nif + "';";
		rs = gestorDB.consultaRegistres(consultaSQL);
		String resultat = null;
		while (rs.next()) {
			resultat = rs.getString("nom");
		}
		return resultat;
	}

	/*
	 * 
	 * public int consultaID(String nif) { String consultaSQL =
	 * "SELECT id_usuari FROM usuari WHERE nif='" + nif + "';"; ResultSet rs =
	 * gestorDB.consultaRegistres(consultaSQL); int id = 0; try { while
	 * (rs.next()) { id = rs.getInt("id_usuari"); } } catch (SQLException e) {
	 * e.printStackTrace(); } return id; }
	 * 
	 * public Usuari consultaUsuari(String nif){ String
	 * consultaSQL="SELECT * FROM Usuari WHERE NIF='"+nif+"';"; ResultSet
	 * rs=gestorDB.consultaRegistres(consultaSQL); Usuari u = null; try {
	 * while(rs.next()){ u=new
	 * Usuari(rs.getString("NIF"),rs.getString("passwd"),rs.getString("nom");
	 * 
	 * } }catch (SQLException e) { e.printStackTrace(); } return u; }
	 * 
	 * public void modificarUsuari(String NIF, Usuari usu) throws SQLException {
	 * String consultaSQL = "UPDATE usuari SET NIF='" + usu.getNIF() + "'," +
	 * "password='" + usu.getPasswd() + "'," + "nom='" + usu.getNom() + "'," +
	 * "primer_cognom='" + usu.getCognom1() + "'," + "segon_cognom='" +
	 * usu.getCognom2() + "',mail='" + usu.getMail() + "',permisos=" +
	 * usu.getPermis() + ",id_centre=" + usu.getIdCentre() + " " + "WHERE nif='"
	 * + NIF + "';"; System.out.println(consultaSQL);
	 * gestorDB.modificarRegistre(consultaSQL); }
	 * 
	 * public Object[] consultaNifs(String nif) { ResultSet rs = null; String
	 * consultaSQL = "SELECT NIF FROM usuari"; rs =
	 * gestorDB.consultaRegistres(consultaSQL); ArrayList<Object> fila = new
	 * ArrayList<>(); int i = 0; try { while (rs.next()) { for (i = 0; i < 1;
	 * i++) { fila.add(rs.getObject(i + 1)); } } } catch (Exception e) {
	 * e.printStackTrace(); } return fila.toArray(); }
	 * 
	 * public List<String> valorsUsuari(String nif, String passwd) throws
	 * SQLException { List<String> llista = new ArrayList<String>(); ResultSet
	 * rs = null; String consultaSQL =
	 * "SELECT u.nom, u.primer_cognom, u.segon_cognom, u.mail, u.permisos " +
	 * "FROM usuari AS u " + "WHERE u.NIF='" + nif + "' AND u.password='" +
	 * passwd + "'"; rs = gestorDB.consultaRegistres(consultaSQL); while
	 * (rs.next()) { llista.add(rs.getString("nom"));
	 * llista.add(rs.getString("primer_cognom"));
	 * llista.add(rs.getString("segon_cognom"));
	 * llista.add(rs.getString("mail")); llista.add("" + rs.getInt("permisos"));
	 * } return llista; }
	 * 
	 * public boolean compararNIF(String NIF) { String consultaSQL =
	 * "SELECT NIF FROM usuari WHERE NIF='" + NIF + "';"; boolean existeix =
	 * false; ResultSet rs = null; rs = gestorDB.consultaRegistres(consultaSQL);
	 * try { if (rs.isFirst()) { existeix = true; } } catch (SQLException e) {
	 * System.out.println("Error comparar NIF"); } return existeix; }
	 * 
	 */
	public void tancarConn() {
		gestorDB.tancarConnexio();
	}
}