package szorgalmi_felhasznalok_es_jogosultsagok_SZOVEGES_JOGOSULTSAGOKKAL;

import szorgalmi_felhasznalok_es_jogosultsagok_SZOVEGES_JOGOSULTSAGOKKAL.FelsorolasOsztaly_jogos.Jogosultsag;

public class Felhasznalo_jogos {
	
	//azonosító, jogosultság (Vendég=1, Felhasználó=2, Admin=3), név.

	private String felhasznaloiAzonosito;
	private int jogosultsag;
	private String nev;
	
	
	//KONSTRUKTOR_1 ARRA AZ ESETRE, AMIKOR FÁJLBÓL ÉRKEZNEK AZ ADATOK (ILYENKOR A JOGOSULTSÁG SZÁMKÉNT JÖN)  
	public Felhasznalo_jogos(String felhasznaloiAzonosito, int jogosultsag, String nev) {
		this.felhasznaloiAzonosito = felhasznaloiAzonosito;
		this.jogosultsag = jogosultsag;
		this.nev = nev;
	}


	//KONSTRUKTOR_2 ARRA AZ ESETRE, AMIKOR A FELHASZNÁLÓTÓL, FELVITELNÉL ÉRKEZNEK AZ ADATOK (ILYENKOR A JOGOSULTSÁG ENUM TIPUSKÉNT JÖN)  
	public Felhasznalo_jogos(String felhasznaloiAzonosito, Jogosultsag jogosultsag, String nev) {
		this.felhasznaloiAzonosito = felhasznaloiAzonosito;
		this.jogosultsag = jogosultsag.getjogosultsagSzam();
		this.nev = nev;
	}



	public String getFelhasznaloiAzonosito() {
		return felhasznaloiAzonosito;
	}


	
		public int getJogosultsag() {
			return jogosultsag;
		}


	public String getNev() {
		return nev;
	}



	@Override
	public String toString() {
		return "azonositó: " + felhasznaloiAzonosito + ", jogosultság: " + jogosultsag
				+ ", név: " + nev + "";
	}
	
	
	
	
}
