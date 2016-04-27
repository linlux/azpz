package com.labsch.dbUtils;

import java.awt.Component;
import java.util.Hashtable;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Globals
{

	// Privater Standardkonstruktor.
	// Alle Methoden dieser Klasse sind statisch. Durch die Deklaration eines
	// eigenen Standardkonstruktors wird verhindert, dass Java einen Standardkonstruktor
	// erstellt.
	// Die Änderung des Zugriffsmodifizierers von 'public' in 'private'
	// verhindert, dass eine Instanz dieser Klasse erstellt werden kann.
	
	static private Hashtable<String, String> htLAF = new Hashtable<String, String>();
	
	private Globals() {}
	
	
	public static String quote(String value)
	{
		return "'" + value.replaceAll("'", "''") + "'";
	}
	
	
	public static boolean insertPLZOrt(long PrimaryKey, String PLZ, String Ort)
	{
		
		String SQL = "INSERT INTO POSTLEITZAHLEN ";
		SQL += " (PRIMARYKEY, PLZ, ORT) ";
		SQL += " VALUES (";
		SQL += Long.toString(PrimaryKey) + ", ";
		SQL += quote(PLZ) + ", ";
		SQL += quote(Ort) + ")";
		
		return DBConnection.executeNonQuery(SQL) > 0;
		
	}
	
	public static boolean insertPLZOrt1(long PrimaryKey, String PLZ, String Ort)
	{
		
		String SQL = "INSERT INTO POSTLEITZAHLEN ";
		SQL += " (PRIMARYKEY, PLZ, ORT) ";
		SQL += " VALUES (";
		SQL += Long.toString(PrimaryKey) + ", ";
		SQL += quote(PLZ) + ", ";
		SQL += quote(Ort) + ") ";
		SQL += " ON DUPLICATE KEY PLZ = " + quote(PLZ) + ", ORT = " + quote(Ort);
		
		return DBConnection.executeNonQuery(SQL) > 0;
		
	}
	
	
	public static long getNextKey()
	{
		long retValue = 0;
		
		
		String SQL = "SELECT MAX(PRIMARYKEY) FROM POSTLEITZAHLEN";
		Object obj = DBConnection.executeScalar(SQL);
		if (obj != null)
			retValue = (long)obj;
		
		
		return ++retValue;
	}
	
	public static boolean istPLZOrtVohanden(String PLZ, String Ort)
	{
		
		boolean retValue = false;
		
		
		String SQL = "SELECT COUNT(*) FROM POSTLEITZAHLEN ";
		SQL += " WHERE PLZ = " + quote(PLZ);
		SQL += " AND ORT = " + quote(Ort);
		
		Object obj = DBConnection.executeScalar(SQL);
		
		if (obj != null)
			retValue = (long)obj > 0;
			
		return retValue;

	}
	
	public static int getLookAndFeels()
	{
		
		LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
		
		for (LookAndFeelInfo laf : lafInfo)
			htLAF.put(laf.getName(), laf.getClassName());
		
		
		return htLAF.size();
		
	}
	
	public static Hashtable<String, String> getLAFTable()
	{
		return htLAF;
	}

	public static String getSystemLookAndFeelName()
	{

		return UIManager.getLookAndFeel().getName();

	}
	
	public static void setLookAndFeel(String lafName, Component component)
	{
		
		
		if (!htLAF.containsKey(lafName))
			return;
		
		
		String lafClass = htLAF.get(lafName);
		
		try
		{
			UIManager.setLookAndFeel(lafClass);
			SwingUtilities.updateComponentTreeUI(component);
		}
		catch (Exception ex)
		{}
		
		
	}
	
}
