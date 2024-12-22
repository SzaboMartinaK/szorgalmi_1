package szorgalmi_felhasznalok_es_jogosultsagok;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class sajatAblak_felhasznalok extends JDialog {
	
	private Felhasznalo_jogos felhasznalo; //A Felhasznalo_jogos OSZTÁLYBÓL LÉTREHOZUNK EGY OBJEKTUMOT. ezzel az objektummal fogunk dolgozni a felvitelkor és módositáskor
	//ITT MÉG CSAK DEKLARÁLJUK, EZZEL A NÉVVEL FOGUNK TUDNI MAJD RÁ HIVATKOZNI
	//ha módositás fog történni, akkor majd ezen a néven keresztül kap értékeket
	//ha felvitelben vagyunk, akkor semmilyen értékkel nem rendelkezik (null értéke van), és mi fogunk hozzárendelni adatokat

	private DialogResult dr = DialogResult.CANCEL; //alapból cancelre van állitva. csak akkor fog megváltozni, ha az ok gombot nyomta meg a felhasználó a felvitel végén --> a dispose elé még betesszük ennek a megváltoztatását (l. lejjebb)
	
	public  Felhasznalo_jogos getFelhasznalo() {
		return felhasznalo;
	}
	
	
	
	public void setFelhasznalo(Felhasznalo_jogos felhasznalo) { //EZÉRT HOZZUK LÉTRE, H ADATOT LEHESSEN MÓDOSITANI: A FELVITT ADATOKAT TUDJA MÓDOSITANI A FELHASZNÁLÓ 
		this.felhasznalo = felhasznalo;
		txtID.setText(felhasznalo.getFelhasznaloiAzonosito());
		cmbJogosultsag.setSelectedItem(felhasznalo.getJogosultsag());
		txtNev.setText(felhasznalo.getNev());
		
	}



	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JTextField txtNev;
	private JComboBox<Integer> cmbJogosultsag;

	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			sajatAblak_felhasznalok dialog = new sajatAblak_felhasznalok();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public sajatAblak_felhasznalok() {
		
		setModal(true); //magunk irjuk be, ezzel beállitjuk a modális működést
		
		setTitle("Felhasználók felviteli és módositási ablaka");
		setBounds(100, 100, 624, 451);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblID = new JLabel("ID:");
			lblID.setBounds(20, 22, 78, 26);
			contentPanel.add(lblID);
		}
		{
			JLabel lblJogosultsag = new JLabel("Jogosultság:");
			lblJogosultsag.setBounds(20, 74, 78, 26);
			contentPanel.add(lblJogosultsag);
		}
		{
			JLabel lblNev = new JLabel("Név:");
			lblNev.setBounds(20, 129, 78, 26);
			contentPanel.add(lblNev);
		}
		{
			txtID = new JTextField();
			txtID.setBounds(183, 25, 86, 20);
			contentPanel.add(txtID);
			txtID.setColumns(10);
		}
		{
			txtNev = new JTextField();
			txtNev.setColumns(10);
			txtNev.setBounds(183, 132, 86, 20);
			contentPanel.add(txtNev);
		}
		
		cmbJogosultsag = new JComboBox<Integer>();
		cmbJogosultsag.setModel(new DefaultComboBoxModel<>(new Integer[] {1, 2, 3})); //HOZZÁ KELL RENDELNI A COMBOBOX-HOZ A VÁLASZTÁSI LEHETŐSÉGEKET. vISZONT EZEKET INTEGER TIPUSKÉNT, MERT A COMBOBOX EZT TUDJA KEZELNI (int nem lehet)
		cmbJogosultsag.setBounds(183, 76, 86, 26);
		contentPanel.add(cmbJogosultsag);
		{
			JLabel lblKod1 = new JLabel("Vendég=1");
			lblKod1.setBounds(396, 61, 78, 26);
			contentPanel.add(lblKod1);
		}
		{
			JLabel lblJogosultsagMagyarazat = new JLabel("Jogosultság:");
			lblJogosultsagMagyarazat.setBounds(308, 61, 78, 26);
			contentPanel.add(lblJogosultsagMagyarazat);
		}
		{
			JLabel lblKod2 = new JLabel("Felhasználó=2");
			lblKod2.setBounds(396, 89, 88, 26);
			contentPanel.add(lblKod2);
		}
		{
			JLabel lblKod3 = new JLabel("Admin=3");
			lblKod3.setBounds(396, 115, 78, 26);
			contentPanel.add(lblKod3);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnMentes = new JButton("Mentés");
				btnMentes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mentes();
					}
				});
				btnMentes.setActionCommand("OK");
				buttonPane.add(btnMentes);
				getRootPane().setDefaultButton(btnMentes);
			}
			{
				JButton btnMegsem = new JButton("Mégsem");
				btnMegsem.setActionCommand("Cancel");
				buttonPane.add(btnMegsem);
			}
		}
	}

	protected void mentes() {
		// TODO Auto-generated method stub
		
		if(!txtID.getText().isBlank() && !txtNev.getText().isBlank()) { //ellenőrzés, h jó adatot vitt-e fel
			felhasznalo = new Felhasznalo_jogos(txtID.getText(), (Integer)cmbJogosultsag.getSelectedItem(), txtNev.getText()); //INTEGER TIPUSBA KELL KÉNYSZERITENI A KIOLVASÁSKOR
			dr = DialogResult.OK; //MENTES GOMBOT NYOMOTT. EZT AZ INFÓT TOVÁBBADOM A FŐABLAKNAK; ha sikerült példányositani a felhasznalo-t, minden adat megvan, akkor ok-ra állitom a dr-t
			dispose(); //magát az ablakot tünteti el, de az objektum(JDialog) attól nem szűnik meg, továbbra is elérhető. Ezzel csak azt érjük el, h visszakerüljön a vezérlés a főablakba 

		}
		else {
		//amikor a felhasználó üresen hagyta a a betegség megnevezését vagy csak space-eket ütött bele:
			JOptionPane.showMessageDialog(this, "Az ID és/vagy a név mező nem maradhat üresen!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
		}
	}

	public DialogResult getDr() {
		// TODO Auto-generated method stub
		return dr;
	}
}
