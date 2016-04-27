package com.labsch.dbUtils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import util2.StatusBar;
import util2.WinUtil;

//Verwenden von mySQL als Datenbank:
//1. 	Herunterladen des mySQL-Datenbank-Treibers (Connector/J) von
//		http://www.mysql.de/downloads/connector/j/ 
//		Plattformunabhängiges ZIP-Archiv (mysql-connector-java-5.1.xx.zip)  
//		Nach betätigen des Download-Buttons keine Eingabe von Zugangsdaten
//		sondern Auswahl von [» No thanks, just take me to the downloads!]
//2. 	Nach dem Herunterladen entpacken und das z.B. in das Verzeichnis 
//		C:\Temp	kopieren (es werden nicht alle Dateien aus der ZIP-Datei benötigt).
//3. 	Innerhalb des Eclipse Workspaces ein Verzeichnis 'lib' anlegen und
//		das Java-Archiv mysql-connector-java-5.1.xx-bin.jar aus dem heruntergeladenen
//		Verzeichnis in dieses Verzeichnis kopieren.
//4. 	In Eclipse das Menü Project->Properties aufrufen. In den Properties 'Java Build Path'
//		auswählen und dort die Registerkarte 'Libraries'. Über den Button
//		[Add External JARs...] das Java-Archiv im Verzeichnis 'lib' hinzufügen.     
//		Alternativ:
//		Verzeichnis 'lib' im Package Explorer öffnen, mit rechter Maustaste auf den Connector
//		klicken und  anschliessend 'Build Path' -> Add to Build Path' auswählen. 

//Anlegen einer Datenbank/Tabelle mit XAMPP.
//1. 	Download vom XAMPP mit Installer -> http://www.apachefriends.org/de/xampp-windows.html
//2. 	Installation direkt in C:\XAMPP (sicherheitshalber wg. Zugriffsberechtigungen etc.)
//3. 	Starten des Programmes C:\xampp\xampp\xampp-control.exe.
//4. 	Starten des Apache-Servers.
//5. 	Starten der Datenbank. 
//6. 	Aufruf von XAMPP Für Windows über den Browser (Firefox) mit localhost.
//7. 	Auswahl von Tools/phpAdmin auf der linken Seite.  

public class DatenbankZugriffe extends JFrame implements WindowListener, ActionListener
{

	private JMenuBar menuBar;
	private JMenu menuDatei, menuStammdaten, menuExtras, menuLAF;
	private JMenuItem miBeenden, miPostleitzahlen, miPostleitzahlenImportieren;
	private JProgressBar progressBar;
	
	private StatusBar statusBar;
	private File fcFile;
	
	public DatenbankZugriffe()
	{
		initializeComponents();
	}
	
	private void initializeComponents()
	{
		
		this.setTitle("Datenbankzugriffe");
		
		// getResource() erfragt beim Class Loader den Bereich, aus dem die Klasse geladen wurde
		// (Dateisystem oder JAR-Datei).
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/Server.png")));
		
		
		this.setSize(800,  480);
		
		// Das Schließen des Frames wird vom WindowListener überwacht
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		
		// Das nachträgliche Ändern der Grösse ist erlaubt.
		
		// Das Standard-Layout des Frames ist das BorderLayout
	
		menuBar = new JMenuBar();
		
		
		menuDatei = WinUtil.createMenu(menuBar, "Datei", null, 'D');
		
//		menuDatei = new JMenu("Datei");
//		menuDatei.setMnemonic('D');
//		menuBar.add(menuDatei);
		
		
		miBeenden = WinUtil.createMenuItem(menuDatei, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Beenden", null, 'e', "Programm beenden");
		
//		miBeenden = new JMenuItem("Beenden");
//		miBeenden.addActionListener(this);
//		miBeenden.setMnemonic('e');
//		menuDatei.add(miBeenden);
		
		
		menuStammdaten = WinUtil.createMenu(menuBar, "Stammdaten", null, 'S');
		miPostleitzahlen = WinUtil.createMenuItem(menuStammdaten, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Postleitzahlen", null, 'P', null);
		
		menuExtras = WinUtil.createMenu(menuBar, "Extras", null, 'x');
		miPostleitzahlenImportieren = WinUtil.createMenuItem(menuExtras, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Postleitzahlen importieren...", null, 'i', null);
		
		
		createLAFMenu(menuExtras);
		
		this.setJMenuBar(menuBar);
		
		
		// Statusleiste am unteren Rand des Frames erzeugen
		statusBar = new StatusBar();
		// Die Breite der Statusbar passt sich autom. an die Breite des Frames an.
		statusBar.setPreferredSize(new Dimension(0, 30));
		statusBar.setStatusLabelFont(statusBar.getStatusLabelFont().deriveFont(Font.PLAIN));
		this.add(statusBar, BorderLayout.PAGE_END);
		
		// Fortschrittsanzeige innerhalb der Statusleiste erzeugen
		
		// Wenn die Fortschrittsanzeige am rechten Rand des Frames erscheinen soll.
		//progressBar = new JProgressBar(JProgressBar.VERTICAL);
		
		progressBar = new JProgressBar(JProgressBar.HORIZONTAL);
		
		// Eine Umrandung anzeigen
		progressBar.setBorderPainted(true);
		
		// Wenn die Fortschrittsanzeige am rechten Rand des Frames erscheinen soll.
		// progressBar.setPreferredSize(new Dimension(30, statusBar.getHeight()));
		
		progressBar.setPreferredSize(new Dimension(300, statusBar.getHeight()));
		// Farbe für die Fortschrittsanzeige ändern
		progressBar.setForeground(Color.GREEN);
		// Prozentsatz anzeigen
		progressBar.setStringPainted(true);
		// Initial unsichtbar
		progressBar.setVisible(false);
		
		// Wenn die Fortschrittsanzeige am rechten Rand des Frames erscheinen soll.
		//this.add(progressBar, BorderLayout.LINE_END);
		
		statusBar.add(progressBar, BorderLayout.LINE_END);
		
	}
	
	private void createLAFMenu(JMenu menu)
	{
		
		String currentLAFName;
		
		int i = Globals.getLookAndFeels();
		if (i == 0) return;
		
		// Umwandlung der Schlüsselwerte der Hastable über einen 'Set' in ein Array.
		// Im Schlüssel steht der LookAnFeel-Name.
		Object[] array = Globals.getLAFTable().keySet().toArray();
		// Aufsteigende Sortierung
		Arrays.sort(array);
				
		menu.addSeparator();
		
		menuLAF = WinUtil.createSubMenu(menu, "Look and Feel", null, 'L');
		
		// Den akuellen LookAndFeel-Name des Systems ermitteln.
		currentLAFName = Globals.getSystemLookAndFeelName();	
				
		// LookAndFeel-Menü mit RadioButtons erstellen
		ButtonGroup bg = new ButtonGroup();
		
		for (Object obj : array)
		{
			JMenuItem mi = WinUtil.createMenuItem(menuLAF, null, WinUtil.MenuItemType.ITEM_RADIO, this, obj.toString(), null, 0, null);
			bg.add(mi);
			
			// Den aktuellen LookAndFeel-Namen im Menü auswählen. 
			if (obj.toString().equals(currentLAFName))
				mi.setSelected(true);

		}
		
	}
	
	
	
	private void initFrame()
	{
		this.setLocationRelativeTo(null);
		
		openMySQLDatabase();
		
	}
	
	
	
	private void openMySQLDatabase()
	{
		
		String connectionString, classForName;
		String server = "localhost";
		String dataBase = "alfatraining";
		
		classForName = "com.mysql.jdbc.Driver";
		
		connectionString = "jdbc:mysql://" + server + ":3306/";
		connectionString += dataBase;
		
		dbEnabled(DBConnection.connectToDatabase(classForName, connectionString, "root", null));
		
		
	}
	
	
	
	private void dbEnabled(boolean enabled)
	{
		menuStammdaten.setEnabled(enabled);
		menuExtras.setEnabled(enabled);
		
		if (!enabled)
			statusBar.setMessage("Datenbank: keine");
		else
			statusBar.setMessage("Datenbank: " + DBConnection.getCatalog());
		
		
	}
	
	
	private void showFrame()
	{
		initFrame();
		this.setVisible(true);
		
	}
	
	
	private void openFileDialog()
	{
		
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(fcFile);
		fc.setFileFilter(new FileNameExtensionFilter("Textdokument (*.txt)", "txt"));
		// Einen weiteren Filter hinzufügen 
		fc.addChoosableFileFilter(new FileNameExtensionFilter("CSV-Datei (*.csv)", "csv"));
		
		if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
			return;
		
		// Speichern der zuletzt ausgewählten Datei
		fcFile = fc.getSelectedFile();
		
		//readFile(fc.getSelectedFile().toString());
		
		readFileAsThread(fc.getSelectedFile().toString());
		
		
	}
	
	private void readFileAsThread(String Dateiname)
	{
		
		// Thread zum Einlesen der Postleitzahlen verwenden.
		// Nur so kann die Statuszeile sinnvoll aktualisiert werden.
		
		Thread t = new Thread(new ReadFileIntoDatabase(Dateiname));
		t.start();
	}
	
	
	private void readFile(String Dateiname)
	{
		
		String zeile;
		int readCounter = 0;
		int addCounter = 0;
		String tempString;
		String[] split;
		long lngKey;
		
		// Aktuellen Inhalt der Statuszeile sichern
		tempString = statusBar.getText();
		
		
		// Fortschrittsanzeige vorbereiten und sichtbar machen
		long fileLength =  new File(Dateiname).length();
		if (fileLength <= Integer.MAX_VALUE)
			progressBar.setMaximum((int)fileLength);
		else
		{		
			progressBar.setMaximum(getRecordCount(Dateiname));
			fileLength = -1;
		}
		
		progressBar.setMinimum(0);
		progressBar.setValue(0);
		progressBar.setVisible(true);
		
		lngKey = Globals.getNextKey();
		
		try (Scanner scanner = new Scanner(new FileInputStream(Dateiname)))
		{
			
			while (scanner.hasNextLine())
			{
				zeile = scanner.nextLine();
				readCounter++;
				statusBar.setMessage(String.format("Datensätze werden gelesen ... [%s]", NumberFormat.getInstance().format(readCounter)));		
				
				if (fileLength < 0)
					progressBar.setValue(readCounter);
				else
					progressBar.setValue(progressBar.getValue() + zeile.length() + System.lineSeparator().length());;
				
				// statusBar.setMessage(zeile);
				// Nur wenn die Methode nicht als nicht als Thread läuft.
				// Aktualisiert den angebenen Bereich.
				// Muss angegeben werden, wenn der Inhalt eines Steuerlements in sehr kurzen Abständen vom eigenen Prozess geändert werden soll.
				// Funktioniert aber nicht mehr, wenn z.B. während der Laufzeit auch Grössenänderungen am Frame vorgenommen werden und sich
				// damit auch die Grösse des zu aktualisierenden Steuerlements ändert. 
				//statusBar.paintImmediately(statusBar.getVisibleRect());
				
				// Methode split mit maximaler Anzahl der zurückzuliefernden Zeichenketten.
				split = zeile.split(";", 2);
				if (split.length == 2)
				{
					if (Globals.istPLZOrtVohanden(split[0], split[1]))
						continue;
					
					if (Globals.insertPLZOrt(lngKey, split[0], split[1]))
					{
						addCounter++;
						lngKey++;
					}
							
				}
				
			}
				
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Fehler beim Einlesen der Datei: " + ex.getMessage(), "E/A Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		
		// Fortschrittsanzeige wieder unsichtbar machen
		progressBar.setVisible(false);
		
		
		// Original Inhalt der Statusanzeige wiederherstellen.
		statusBar.setText(tempString);
		
		JOptionPane.showMessageDialog(this,  String.format("Es wurden %s Datensätze erfolgreich eingelesen", NumberFormat.getInstance().format(addCounter)),
				                      "Importieren Postleitzahlen", JOptionPane.INFORMATION_MESSAGE);
		
		
	}
	
	
	private int getRecordCount(String Dateiname)
	{
		int retValue = 0;
		
		try(Scanner scanner = new Scanner(new FileInputStream(Dateiname)))
		{
			while(scanner.hasNextLine())
			{
				scanner.nextLine();
				retValue++;
			}
			
		}
		catch (Exception ex) {}
		
		
		return retValue;
	}
	
	
//	private void showPLZTable()
//	{
//		
//		PLZTable dlg = new PLZTable();
//		dlg.showDialog(this);
//		
//	}
	
	
	public static void main(String[] args)
	{
		
		DatenbankZugriffe f = new DatenbankZugriffe();
		f.showFrame();
	
	}


	private class ReadFileIntoDatabase implements Runnable
	{
		
		private String Dateiname;
		
		public ReadFileIntoDatabase(String Dateiname)
		{
			this.Dateiname = Dateiname;
		}
		

		@Override
		public void run()
		{
			
			// Verhindern dass während des Imports Benutzermenü-Funktionen
			// aufgerufen werden können.
			for (int i = 0; i < menuBar.getMenuCount(); i++)
				menuBar.getMenu(i).setEnabled(false);
			
			readFile(Dateiname);
			
			// Benutzermenü wieder aktivieren
			for (int i = 0; i < menuBar.getMenuCount(); i++)
				menuBar.getMenu(i).setEnabled(true);
		}
		
	}
	
	
	
	
	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent e)
	{
		
		DBConnection.closeConnection();
		
	}


	@Override
	public void windowClosing(WindowEvent e)
	{
		
		// Nur wenn der Import abgeschlossen wurde.
		if (menuDatei.isEnabled())
			this.dispose();
		
	}


	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		
//		if (e.getSource() == miBeenden)
//			windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
//		else if (e.getSource() == miPostleitzahlenImportieren)
//			openFileDialog();
//		else if (e.getSource() == miPostleitzahlen)
//			showPLZTable();
//		else if (e.getActionCommand() != null)
//			Globals.setLookAndFeel(e.getActionCommand(), this);
		
	}

}
