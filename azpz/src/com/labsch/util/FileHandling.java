package com.labsch.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileHandling
{
	/**
	 * 
	 * Creates a File in the Filesystem.
	 * 
	 * @author Martin Labsch<br>
	 *         26.04.2016
	 * @param file
	 *            full path including filename.
	 */
	public static void createFile(File file)
	{
		try
		{
			file.createNewFile();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @author Martin Labsch<br>
	 *         26.04.2016
	 * @param settingsFile
	 */
	public static void setInitialProperties(File settingsFile)
	{

		Properties prop = new Properties();

		prop.setProperty("Benutzer", "alfa");
		prop.setProperty("Kennwort", "geheim");
		prop.setProperty("Sprache", "de");
		prop.setProperty("Anfangswert", "42");

		try
		{
			prop.store(new FileOutputStream(settingsFile), "");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @author Martin Labsch<br>
	 *         26.04.2016
	 * @return null or the actual app-path
	 */
	public static String getAppPath()
	{
		String appPath = null;

		try
		{
			appPath = new File(".").getCanonicalPath();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appPath;
	}

}
