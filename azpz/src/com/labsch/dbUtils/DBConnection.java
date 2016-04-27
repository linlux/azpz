package com.labsch.dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBConnection
{

	private static Connection dbConn;
	private static String connectionString;
	
	
	public static boolean connectToDatabase(String classForName, String connectionString, String userID, String passWord) 
	{
		
		boolean retValue = false;
		
		
		try
		{
			// Erstellen und Registrieren der als Zeichenkette 'classForName'
			// übergebenen Klasse für den DriverManager.
			// (statische Initialisierung).
			Class.forName(classForName).newInstance();
			
			dbConn = DriverManager.getConnection(connectionString, userID, passWord);
			DBConnection.connectionString = connectionString;
			retValue = true;
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			dbConn = null;
			DBConnection.connectionString = null;
		}
		
		
		return retValue;
		
	}
	
	
	public static void closeConnection()
	{
		
		if (dbConn == null)
			return;
		
		try
		{
			dbConn.close();
		}
		catch (Exception ex) {}
	
		dbConn = null;
		connectionString = null;
		
	}


	public static Connection getConnection()
	{
		return dbConn;
	}


	public static String getConnectionString()
	{
		return connectionString;
	}
	
	
	public static String getCatalog()
	{
		
		String retValue = "";
		
		if (dbConn == null)
			return retValue;
		
		try
		{
			retValue = dbConn.getCatalog();
		}
		catch (Exception ex) {}
		
		
		return retValue;
	}
	
	
	public static int executeNonQuery(String SQL)
	{
		
		Statement stmt;

		int retValue = 0;
		
		if (dbConn == null)
			return retValue;
		
		try
		{
			stmt = dbConn.createStatement();
			retValue = stmt.executeUpdate(SQL);
			stmt.close();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		
		return retValue;
		
	}
	
	
	public static Object executeScalar(String SQL)
	{
		Statement stmt;
		Object retValue = null;
		
		if (dbConn == null)
			return retValue;
		
		
		try
		{
			stmt = dbConn.createStatement();
			ResultSet rSet  = stmt.executeQuery(SQL);
			
			// Auf den ersten Datensatz positionieren
			rSet.next();
			// Den Inhalt der ersten Spalte als Objekt übernehmen 
			retValue = rSet.getObject(1);
			rSet.close();
			stmt.close();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		
		return retValue;
	}
	
	public static ResultSet executeQuery(String SQL)
	{
		Statement stmt;
		ResultSet rSet = null;
		
		if (dbConn == null)
			return rSet;
		
		
		try
		{
			stmt = dbConn.createStatement();
			rSet  = stmt.executeQuery(SQL);
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		
		return rSet;
	}
	
	
}
