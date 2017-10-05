package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controlador.Constants;
import controlador.GestorDB;

public class registremovimentsDAO {

	GestorDB gdb = new GestorDB();

	public registremovimentsDAO() {
		gdb = new GestorDB(Constants.SERVER, Constants.PORT, Constants.BD);
	}

	public int desaMoviment(String loclient, String haFet) throws SQLException {
		System.out.println("loclient: " + loclient);
		System.out.println("haFet: " + haFet);
		String consultaSQL = "INSERT INTO zregistremovimients (loclient, haFet) VALUES ('" + loclient + "', '" + haFet
				+ "');";
		System.out.println(consultaSQL);
		gdb.modificarRegistre(consultaSQL);
		return 1;
	}

	public String ultimlogin(String elclient) throws SQLException {
		List<String> llista = new ArrayList<String>();
		ResultSet rs = null;
		String consultaSQL = "SELECT * FROM zregistremovimients where fecha > CURRENT_DATE() AND '" + elclient
				+ "' like loclient ORDER BY fecha LIMIT 2,1";
		rs = gdb.consultaRegistres(consultaSQL);
		String resultat = "";
		while (rs.next()) {
			resultat = rs.getString("fecha");
		}
		return resultat;
	}

}
