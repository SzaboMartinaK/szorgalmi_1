//package szorgalmi_felhasznalok_es_jogosultsagok;
//
////jogosultság (Vendég=1, Felhasználó=2, Admin=3)
//
////metódus:
//	//fromKod(): Ez a metódus lehetővé teszi a szám alapján történő konverziót, így ha egy számot olvasunk be a fájlból, akkor a megfelelő Jogosultsag enumot tudjuk kiválasztani.
//
//public class FelsorolasOsztaly_jogos {
//	
//	public enum Jogosultsag{
//		VENDEG(1), FELHASZNALO(2), ADMIN(3); //a fájlban számokként tárolódnak a jogosultságok, a legördülőben + a JListben szövegesen
//		private final int jogKod;
//
//		private Jogosultsag(int jogKod) {  //KONSTRUKTOR
//			this.jogKod = jogKod;
//		}
//		
//		// Visszaadja a számot, ami a jogosultsághoz tartozik
//        public int getJogKod() { //GETTER
//            return jogKod;
//        }
//		
//		
//		
//		//fromKod(): Ez a metódus lehetővé teszi a szám alapján történő konverziót, így ha egy számot olvasunk be a fájlból, akkor a megfelelő Jogosultsag enumot tudjuk kiválasztani.
//        
//        // A kód alapján visszaadja a megfelelő jogosultságot
//        public static Jogosultsag fromKod(int kod) {
//            for (Jogosultsag jogosultsag : values()) {
//                if (jogosultsag.jogKod == kod) {
//                    return jogosultsag;
//                }
//            }
//            throw new IllegalArgumentException("Érvénytelen jogosultsági kód: " + kod);
//        }
//    
//		
//	}
//	
//		
//}
