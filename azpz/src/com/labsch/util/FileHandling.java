package com.labsch.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * 26.04.2016
 * 
 * @author Martin Labsch, 26.04.2016
 *
 */
public class FileHandling
{
	/**
	 * 
	 * Creates a File in the Filesystem.
	 * 
	 * @author Martin Labsch, 26.04.2016<br>
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
	 * @author Martin Labsch, 26.04.2016<br>
	 *         26.04.2016
	 * @param settingsFile
	 */
	public static void setInitialPropertiesFile(File settingsFile)
	{

		Properties prop = new Properties();

		prop.setProperty("MAIN_FRAME_WIDTH", "800");
		prop.setProperty("MAIN_FRAME_HEIGHT", "600");
		prop.setProperty("language", "de");

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

	 * @author Martin Labsch, 26.04.2016
	 * @param propFile
	 * @return
	 */
	public static Properties getProperties(File propFile)
	{
		Properties prop = new Properties();
		FileInputStream inStream;
		try
		{
			inStream = new FileInputStream(propFile);
			if (inStream != null)
			{
				prop.load(inStream);
				// prop.list(System.out);
			}
		}
		catch (FileNotFoundException e)
		{
			// TODO log: settingsFile wurde nicht gefunden.
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO log: Properties konnten nicht geladen werden.
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * 
	 * @author Martin Labsch, 26.04.2016<br>
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
