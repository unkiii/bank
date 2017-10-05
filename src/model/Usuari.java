package model;

public class Usuari {

	private int idUsuari;
	private String NIF;
	private String passwd;
	private String nom;
	private String cognom;
	private String datanaix;
	private String direccio;
	private String poblacio;
	private String codipostal;
	private int permis;

	public Usuari() {
	}

	public Usuari(int idUsuari, String nIF, String passwd, String nom, String cognom, String datanaix, String direccio,
			String poblacio, String codipostal, int permis) {
		super();
		this.idUsuari = idUsuari;
		NIF = nIF;
		this.passwd = passwd;
		this.nom = nom;
		this.cognom = cognom;
		this.datanaix = datanaix;
		this.direccio = direccio;
		this.poblacio = poblacio;
		this.codipostal = codipostal;
		this.permis = permis;
	}

	public int getIdUsuari() {
		return idUsuari;
	}

	public void setIdUsuari(int idUsuari) {
		this.idUsuari = idUsuari;
	}

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognom() {
		return cognom;
	}

	public void setCognom(String cognom) {
		this.cognom = cognom;
	}

	public String getDatanaix() {
		return datanaix;
	}

	public void setDatanaix(String datanaix) {
		this.datanaix = datanaix;
	}

	public String getDireccio() {
		return direccio;
	}

	public void setDireccio(String direccio) {
		this.direccio = direccio;
	}

	public String getPoblacio() {
		return poblacio;
	}

	public void setPoblacio(String poblacio) {
		this.poblacio = poblacio;
	}

	public String getCodipostal() {
		return codipostal;
	}

	public void setCodipostal(String codipostal) {
		this.codipostal = codipostal;
	}

	public int getPermis() {
		return permis;
	}

	public void setPermis(int permis) {
		this.permis = permis;
	}

}