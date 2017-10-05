package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controlador.Constants;
import controlador.GestorDB;

public class comptesDAO {
	GestorDB gestorDB;

	public comptesDAO() {
		gestorDB = new GestorDB(Constants.SERVER, Constants.PORT, Constants.BD);
	}

	public List<String> infoCompte(String nif) throws SQLException {
		List<String> llista = new ArrayList<String>();
		ResultSet rs = null;
		String consultaSQL = "SELECT saldo, tipus FROM comptes WHERE tipus like 1 AND numcompte IN (select numcompte from titular where titular1 like '"
				+ nif + "'); ";
		rs = gestorDB.consultaRegistres(consultaSQL);
		while (rs.next()) {
			llista.add(rs.getString("saldo"));
			llista.add("" + rs.getInt("tipus"));
		}
		return llista;
	}

	public List<String> tipusCompte(String tipus) throws SQLException {
		List<String> llista = new ArrayList<String>();
		ResultSet rs = null;
		String consultaSQL = "SELECT tipus FROM llibretes WHERE num =" + tipus;
		rs = gestorDB.consultaRegistres(consultaSQL);
		while (rs.next()) {
			llista.add(rs.getString("tipus"));
		}
		return llista;
	}

	public String tipusCorrent() throws SQLException {
		ResultSet rs = null;
		String consultaSQL = "SELECT tipus FROM llibretes WHERE num like 1";
		rs = gestorDB.consultaRegistres(consultaSQL);
		String resultat = "";
		while (rs.next()) {
			resultat = rs.getString("tipus");
		}
		return resultat;
	}

	public List<String> infoMoneder(String nif) throws SQLException {
		List<String> llista = new ArrayList<String>();
		ResultSet rs = null;
		String consultaSQL = "select numclient, metalic from clients where nif like '" + nif + "';";
		rs = gestorDB.consultaRegistres(consultaSQL);
		while (rs.next()) {
			llista.add(rs.getString("metalic"));
			llista.add(rs.getString("numclient"));
		}
		return llista;
	}

	public List<String> factImpa(String numclient) throws SQLException {
		List<String> llista = new ArrayList<String>();
		ResultSet rs = null;
		String consultaSQL = "select pagada from facturacio where pagada like '" + 0 + "';";
		rs = gestorDB.consultaRegistres(consultaSQL);
		while (rs.next()) {
			llista.add(rs.getString("metalic"));
			llista.add(rs.getString("numclient"));
		}
		return llista;
	}

	public int sumafactImpa(String numclient) throws SQLException {
		List<String> llista = new ArrayList<String>();
		ResultSet rs = null;
		String consultaSQL = "SELECT quantitat FROM rebuts WHERE id IN (select idfactura from facturacio where pagada like '0'); ";
		rs = gestorDB.consultaRegistres(consultaSQL);

		int cont = 0;
		while (rs.next()) {
			cont = cont + Integer.parseInt(rs.getString("quantitat"));
		}
		return cont;
	}

	public int nfactImpa(String numclient) throws SQLException {

		ResultSet rs = null;
		String consultaSQL = "select pagada from facturacio where numclient=" + numclient + " and pagada like '" + 0
				+ "';";
		rs = gestorDB.consultaRegistres(consultaSQL);

		int cont = 0;
		while (rs.next()) {
			cont++;
		}
		return cont;
	}

	public void tancarConn() {
		gestorDB.tancarConnexio();
	}
}
