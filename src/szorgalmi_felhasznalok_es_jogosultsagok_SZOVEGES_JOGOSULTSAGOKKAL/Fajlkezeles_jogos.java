package szorgalmi_felhasznalok_es_jogosultsagok_SZOVEGES_JOGOSULTSAGOKKAL;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.JOptionPane;



public class Fajlkezeles_jogos {

	//public static List<Felhasznalo> felhasznalokbeolvasas(String fajlnev, String elvalaszto) throws IOException{
	
	//ÁTVESZI PARAMÉTERBEN A LIST-ET ÉS AZT TÖLTI FEL A TXT-BŐL BEOLVASOTT ADATOKKAL. NEM KELL RETURN
	
		public static void felhasznalokBeolvasas(String fajlnev, String elvalaszto, List<Felhasznalo_jogos> felhasznalok) throws IOException{
			
			//2 lehetőség van:
			//vagy public a metódus, és akkor példányositani kell a Fajlkezeles osztályt, és majd azon a példányon keresztül érjük el a beolvasás mtódust
			//vagy public static void a metódus, és ilyenkor nem kell az osztályt példányositani
			
			try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fajlnev),"UTF-8"))) {
				while(br.ready()) { //br.ready miatt IOException is lehet
					String[] csvsor = br.readLine().split(elvalaszto);
					felhasznalok.add(new Felhasznalo_jogos(
							csvsor[0],
							Integer.parseInt(csvsor[1]),  //A TXT-BŐL ÉRKEZŐ JOGOSLUTSÁGOT INTKÉNT VETTÜK FEL ADATTAGKÉNT A FELHASZNALO OSZTALYBAN
							csvsor[2])
							);
				}
				br.close();
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
		
		public static void felhasznalokKiir(String fajlnev, String elvalaszto, List<Felhasznalo_jogos> felhasznalok) { //a teljes listát adjuk át, nem egyesével hivjuk
				
			
			try {
				OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fajlnev),"UTF-8");
				
				for(Felhasznalo_jogos felhasznalo : felhasznalok) {
					out.write(felhasznalo.getFelhasznaloiAzonosito()+elvalaszto+felhasznalo.getJogosultsag()+elvalaszto+felhasznalo.getNev()+"\n");
				}
				
				out.close(); //nem kell ez a close, ha a try fejrészébe betetettük volna az erőforrás-hozzárendelést: try with resources
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Hiba a fájl kiirásakor!", "Fájlművelet", JOptionPane.ERROR_MESSAGE);  //nem tudunk megadni komponenst, ezért null az első 
			}
			
		}
		
}
