package szorgalmi_felhasznalok_es_jogosultsagok;


public class Felhasznalo_jogos {
	
	//azonosító, jogosultság (Vendég=1, Felhasználó=2, Admin=3), név.

	private String felhasznaloiAzonosito;
	private int jogosultsag;
	private String nev;
	
	
	
	public Felhasznalo_jogos(String felhasznaloiAzonosito, int jogosultsag, String nev) {
		this.felhasznaloiAzonosito = felhasznaloiAzonosito;
		this.jogosultsag = jogosultsag;
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
