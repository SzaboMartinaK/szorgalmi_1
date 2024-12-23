package szorgalmi_felhasznalok_es_jogosultsagok_SZOVEGES_JOGOSULTSAGOKKAL;

//jogosultság (Vendég=1, Felhasználó=2, Admin=3)

//metódus:
	//fromKod(): Ez a metódus lehetővé teszi a szám alapján történő konverziót, így ha egy számot olvasunk be a fájlból, akkor a megfelelő Jogosultsag enumot tudjuk kiválasztani.
//
public class FelsorolasOsztaly_jogos {
	
//	Az információ látszólag háromszor van megadva, de valójában mindegyik külön célt szolgál:
//		Szám: A belső feldolgozáshoz és fájltároláshoz.
//		Szöveg: A felhasználói felülethez.
//		Enum név: A programozói logikához.
	
	public enum Jogosultsag{
		VENDEG(1,"vendég"), FELHASZNALO(2,"felhasználó"), ADMIN(3,"admin"); //a fájlban számokként tárolódnak a jogosultságok, a legördülőben + a JListben szövegesen
	//	Programozói olvashatóság: Az enum nevei (VENDEG, FELHASZNALO, ADMIN) 
		private final int jogosultsagSzam; // 1, 2, 3 - Számként tárolt érték (fájlban és belső logikában használatos)
		private final String jogosultsagMegnevezes; // "vendég", "felhasználó", "admin" - Szöveges megnevezés (megjelenítéshez)

		
		private Jogosultsag(int jogosultsagSzam, String jogosultsagMegnevezes) {  //KONSTRUKTOR
			this.jogosultsagSzam = jogosultsagSzam;
			this.jogosultsagMegnevezes = jogosultsagMegnevezes;
		}
		
		// Visszaadja a számot, ami a jogosultsághoz tartozik; 
		// Fájlba mentéskor kell
        public int getjogosultsagSzam() { //GETTER kell a számhoz
            return jogosultsagSzam;
        }

     // Visszaadja az elnevezést, ami a jogosultsághoz tartozik
     // GUI megjelenítéshez kell
        public String getjogosultsagMegnevezes() { //GETTER kell a szöveges adathoz
            return jogosultsagMegnevezes;
        }
            
		
		//fromKod(): Ez a metódus lehetővé teszi a szám alapján történő konverziót, így ha egy számot olvasunk be a fájlból, akkor a megfelelő Jogosultsag enumot tudjuk kiválasztani.
        // A kód alapján visszaadja a megfelelő jogosultságot
     // Szám alapján megtalálja az enum értéket
        public static Jogosultsag fromKod(int jogosultsagSzam) {
            for (Jogosultsag jogosultsag : values()) {
                if (jogosultsag.jogosultsagSzam == jogosultsagSzam) {
                    return jogosultsag;
                }
            }
            throw new IllegalArgumentException("Érvénytelen jogosultsági kód: " + jogosultsagSzam);
        }
    
	
        //a felhasználó a ComboBoxból String-et (jogosultsagMegnevezes) választ. 
        //Mivel ez visszaadja a megfelelő enum értéket, lekérhetjük a számot.
        public static Jogosultsag fromMegnevezes(String jogosultsagMegnevezes) {
            for (Jogosultsag jogosultsag : values()) {
                if (jogosultsag.jogosultsagMegnevezes.equals(jogosultsagMegnevezes)) {
                    return jogosultsag;
                	}
                }
			return null;
            }
        
        

	@Override
	//a szöveges megnevezés mindenhol automatikusan megjelenjen, ahol az enum-ot kiírja a program
	public String toString() {
	    return jogosultsagMegnevezes;
		}
	}
}



