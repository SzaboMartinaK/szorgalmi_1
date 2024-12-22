package szorgalmi_felhasznalok_es_jogosultsagok;

/*
 * FONTOS SAJÁTSÁGOK, KÓDRÉSZEK:
 * - List-be olvassuk be a txt-t, amit Listmodel-hez, majd JListhez rendelünk, hogy megjelenjen a GUI főablakában; txt beolvasása(KÓD: FAJLKEZELES_JOGOS OSZTÁLY, de ebben a kódban hivom)
 * -kiirás bezáráskor (KÓD: FAJLKEZELES_JOGOS OSZTÁLY, de ebben a kódban hivom)
 * Scrollozható a JList (EBBEN A KÓDBAN)
 * A Felhasznalo osztályban kell a toString a megfelelő beolvasáshoz
 * teljes körű kilépés
 * -DialogResult enum, amiben 2 elemet tárolunk; alapból cancelre állitjuk a sajátablakban; a sajátablakban lesz gettere; a sajátablakban állitjuk ok-ra akkor, ha sikerült példányositani a felhasznalo-t a felvitel-ben; a főablak felvitel metódusában ellenőrzök rá, h ok-e, és akkor, ha ok, hozzáadom az  új elemet a List-hez és a modellhez is
 * - fel tudok vinni új adatot: ebben a kódban adatfelvitel() metódus + SAJATABLAK-ban mentes()!!
 * - tudok módositani kijelölt adatot: ebben a kódban modositas() metódus! (a sajatablak-tól csak annyit kap, h a mentés gombra kattintott és is ok-ra állt a DialogResult)
*/
//--------------------------------------

/*
 *Készítsünk programot, mely felhasználói adatokat fog tárolni, úgy mint azonosító, jogosultság (Vendég=1, Felhasználó=2, Admin=3),
név.
• Az adatokat fájlokból szerezhetjük be (felhasznalok.txt).
• Használjunk ArrayList adatszerkezetet az adatok tárolásához.


Tudjunk adatot felvinni, módosítani és törölni is.
A felvitelt és a módosítást egy saját ablak segítségével oldjuk meg.
Program bezárásakor az adatokat írjuk ki fájlba, program indításakor pedig olvassuk be.
 */

//--------------------------------------


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;


import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.Color;

public class FoablakJogosultsagok {

	private JFrame frmFelhasznlksJogosultsgok;
	
	//list + listmodel + jList is kell
	private List<Felhasznalo_jogos> felhasznalok;
	private DefaultListModel<Felhasznalo_jogos> listmodel; 
	private JList<Felhasznalo_jogos> lstFelhasznalok;
	private JScrollPane scrollPane;
	private JButton btnExit;
	private JButton btnAdatfelvitel;
	private JButton btnTorles;
	private JButton btnModositas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoablakJogosultsagok window = new FoablakJogosultsagok();
					window.frmFelhasznlksJogosultsgok.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FoablakJogosultsagok() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFelhasznlksJogosultsgok = new JFrame();
		frmFelhasznlksJogosultsgok.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
					Object[] opciok = {"Igen","Nem"};
					
					int valasz = JOptionPane.showOptionDialog(frmFelhasznlksJogosultsgok, "Biztos ki akar lépni?", "Kilépés", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciok, opciok[1]); //mivel a "nem" lesz alapból, ezért biztos ki kell választania az "igen"t, ha ki akar lépni, nem elég az enter
					
					if(valasz == JOptionPane.YES_OPTION) {
						Fajlkezeles_jogos.felhasznalokKiir("felhasznalok.txt", ";", felhasznalok);
						System.exit(0);
					}
					
			}
		});
		frmFelhasznlksJogosultsgok.setTitle("Felhasználók és jogosultságok");
		frmFelhasznlksJogosultsgok.setBounds(100, 100, 821, 531);
		frmFelhasznlksJogosultsgok.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //teljes körű kilépés része: ÁT KELL ÁLLITANI!
		frmFelhasznlksJogosultsgok.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 785, 339);
		frmFelhasznlksJogosultsgok.getContentPane().add(scrollPane);
		
		
		felhasznalok = new ArrayList<Felhasznalo_jogos>();
		try {
			Fajlkezeles_jogos.felhasznalokBeolvasas("felhasznalok.txt", ";", felhasznalok);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //igy most az ArrayListbe beölti az adatokat. ezeket még hozzá kell rendelni a ListModel-hez, ezt fentebb csináljuk meg egy metódusban 
		
		
		
		lstFelhasznalok = new JList<>();
		scrollPane.setViewportView(lstFelhasznalok);
		
		btnExit = new JButton("Kilépés");
		btnExit.setBackground(new Color(255, 128, 0));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmFelhasznlksJogosultsgok.dispatchEvent(new WindowEvent(frmFelhasznlksJogosultsgok, WindowEvent.WINDOW_CLOSING)); //TELJES KÖRŰ KILÉPÉS RÉSZE
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExit.setBounds(662, 402, 133, 65);
		frmFelhasznlksJogosultsgok.getContentPane().add(btnExit);
		
		btnAdatfelvitel = new JButton("Adatfelvitel");
		btnAdatfelvitel.setForeground(Color.BLUE);
		btnAdatfelvitel.setBackground(new Color(128, 128, 0));
		btnAdatfelvitel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adatfelvitel();
			}
		});
		btnAdatfelvitel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdatfelvitel.setBounds(10, 357, 194, 46);
		frmFelhasznlksJogosultsgok.getContentPane().add(btnAdatfelvitel);
		
		btnTorles = new JButton("Törlés");
		btnTorles.setForeground(Color.BLUE);
		btnTorles.setBackground(new Color(128, 128, 0));
		btnTorles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				torles();
			}
		});
		btnTorles.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnTorles.setBounds(10, 446, 194, 46);
		frmFelhasznlksJogosultsgok.getContentPane().add(btnTorles);
		
		btnModositas = new JButton("Módositás");
		btnModositas.setForeground(Color.BLUE);
		btnModositas.setBackground(new Color(128, 128, 0));
		btnModositas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modositas();
			}
		});
		btnModositas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnModositas.setBounds(10, 402, 194, 46);
		frmFelhasznlksJogosultsgok.getContentPane().add(btnModositas);
		
		 //Az ArrayListbe betöltött adatokat hozzá kell rendelni a ListModel-hez!!!:
		listaModellhezRendeles();
		
	}

	
	 protected void torles() { 	//itt a felugró ablakra nincs is szükség, a törlésnél
		// TODO Auto-generated method stub
		 Object[] opciok = {"Igen","Nem"};
		 if ((lstFelhasznalok.getSelectedIndex() > -1) && (JOptionPane.showOptionDialog(frmFelhasznlksJogosultsgok, "Biztosan törli a kijelölt elemet?", "Törlés", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciok, opciok[1])==JOptionPane.YES_OPTION)) {
				//lusta kiértékelés: a 2.-ba csak akkor megy be, ha az 1. igaz
				//csak akkor teljesül, ha yes-szel válaszolt a kérdésre
				felhasznalok.remove(lstFelhasznalok.getSelectedIndex());
				listmodel.remove(lstFelhasznalok.getSelectedIndex()); 
		
			}
			else if(lstFelhasznalok.getSelectedIndex() == -1){
				//ha nincs kijelölt elem, akkor kell küldeni üzenetet:
				JOptionPane.showMessageDialog(frmFelhasznlksJogosultsgok, "Nincs kijelölt elem!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
			}
	}
	 
	 

	protected void modositas() {
		// TODO Auto-generated method stub
		
		//első lépés, tudni kell, h van-e kijelölt elem. Ha nincs, jelezni kell a hibát a felhasználónak
		//a JList meg tudja mondani ezt, mert meg tudja adni, h hanyadik elemre mutat, mert ha egyikre sem, akkor a -1-edik elemre
		 
		 if (lstFelhasznalok.getSelectedIndex() > -1) {
			 
				//PÉLDÁNYT KELL LÉTREHOZNI A FELUGRÓ ABLAKBÓL:
				sajatAblak_felhasznalok sajatAblakObj = new sajatAblak_felhasznalok();
				
				//most kellenek a kiválasztott elem adatai: meghivjuk a SETTERÉT a kiválasztott elemnek: megadjuk, h ezek az értékek és ezeket kell módositani:
				sajatAblakObj.setFelhasznalo((Felhasznalo_jogos)lstFelhasznalok.getSelectedValue()); //BETÖLTJÜK AZ ADATOKAT A SETTEREN KERESZTÜL, AMIT A SAJATABLAKBAN HOZTUNK LÉTRE
				sajatAblakObj.setVisible(true); //megjelenitjük a felugrót. Átkerül ide a vezérlés
				
				//ahhoz, h módositáskor rögtön lászódjon is a listában a módosult adat, az alábbi sorokra szükség van:
				if(sajatAblakObj.getDr() == DialogResult.OK) {
					listmodel.set(lstFelhasznalok.getSelectedIndex(), sajatAblakObj.getFelhasznalo()); //A LISTMODEL-T ÚGY KELL MÓDOSITANI (SET SEG.VEL), H A JLIST INDEXÉT LEKÉREM, ÉS AZT AZ INDEXET MÓDOSITOM BE A SAJATABLAK GETFELHASZNALOJÁsVAL
				}
				}
				
			else {
				JOptionPane.showMessageDialog(frmFelhasznlksJogosultsgok, "Nincs kijelölt elem!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
				
			}
		}
 
		 


	protected void adatfelvitel() {
		// TODO Auto-generated method stub
		
		// JDialog: modális és nem modális is lehet, most modálisként csinálom meg
				
		//PÉLDÁNYT KELL LÉTREHOZNI A FELUGRÓ ABLAKBÓL:
		sajatAblak_felhasznalok sajatAblakObj = new sajatAblak_felhasznalok();		
		sajatAblakObj.setVisible(true); //megjelenitjük a felugrót, és addig, amig az be nem zárul, nem lesz elérhető a főablak. Átkerül ide a vezérlés
				
		if(sajatAblakObj.getDr() == DialogResult.OK) {  //ITT ELLENŐRZÖK RÁ, H OK-T NYOMOTT-E. EZ AZ INFÓ A SAJATABLAKBOL JON
				//ha az ok-t nyomta, akkor hajtjuk végre a feltöltést
				felhasznalok.add(sajatAblakObj.getFelhasznalo());
				listmodel.addElement(sajatAblakObj.getFelhasznalo());
			}
		}
			
	

	//Az ArrayListbe betöltött adatokat hozzá kell rendelni a ListModel-hez!!!:
	private void listaModellhezRendeles() {
		// TODO Auto-generated method stub
			
			listmodel = new DefaultListModel<Felhasznalo_jogos>(); //ide hoztam át fentről
			
			//HOZZÁADOGATOM A LISTMODEL-HEZ EGYESÉVEL A LIST ELEMEIT
			for(Felhasznalo_jogos felhasznalo : felhasznalok) {
				listmodel.addElement(felhasznalo);
			}
			
			//A LISTMODEL-T HOZZÁRENDELEM A JLIST-HEZ
			lstFelhasznalok.setModel(listmodel);
	}
	}

