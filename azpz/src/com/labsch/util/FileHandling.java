package com.labsch.util;

import java.awt.Component;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.JFrame;

import com.labsch.azpz.azpzFrame;
import com.labsch.azpz.azpzMain;

/**
 * 
 * 26.04.2016
 * 
 * @author Martin Labsch, 26.04.2016
 *
 */
public class FileHandling
{
    private static final boolean debug = false;

    /**
     * 
     * Creates a File in the Filesystem.
     * 
     * @author Martin Labsch, 26.04.2016
     * @param file
     *            full path including filename.
     */
    public static boolean createFile(File file)
    {
        boolean retValue = false;

        try
        {
            retValue = file.createNewFile();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return retValue;

    }

    /**
     * 
     * Creates a directory including any necessary but nonexistent parent directories in the Filesystem.
     * 
     * @author Martin Labsch, 28.04.2016
     * @param dir
     *            full path including filename.
     */
    public static boolean createDir(File dir)
    {
        boolean retValue = false;

        if (!dir.isDirectory())
        {
            retValue = dir.mkdir();
        }
        else
        {
            retValue = true;
        }

        return retValue;

    }

    /**
     * intial properties of mainFrame.
     * 
     * @author Martin Labsch, 26.04.2016
     * @param settingsFile
     */
    public static void setInitialPropertiesFile(File propFile)
    {

        HashMap<String, String> props = new HashMap<String, String>();

        props.put("MAINFRAME_WIDTH", "800");
        props.put("MAINFRAME_HEIGHT", "600");
        props.put("MAINFRAME_X", "0");
        props.put("MAINFRAME_Y", "0");
        props.put("MAXIMIZED", "false");
        props.put("language", "de");

        setPropertyFile(propFile, props);
    }

    /**
     * stores properties from HashMap in property-File.
     * 
     * @author Martin Labsch, 26.04.2016
     * @param settingsFile
     */
    public static void setPropertyFile(File propFile, HashMap<String, String> props)
    {

        Properties prop = new Properties();

        for (String p : props.keySet())
        {
            if (debug)
            {
                System.out.println(p + " " + props.get(p));
            }
            prop.setProperty(p, props.get(p));
        }

        try
        {
            prop.store(new FileOutputStream(propFile), "comment");
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

    /**
     * safes the actual size and position of mainFrame.
     * 
     * @author Martin Labsch, 27.04.2016
     */
    public static void safeActualProperties()
    {

        azpzFrame mainFrame = (azpzFrame) FrameHandling.getAnAzpzFrameByName("mainFrame");
        HashMap<String, String> props = new HashMap<String, String>();
        boolean isMaximizedBoth = ((JFrame) mainFrame).getExtendedState() == Frame.MAXIMIZED_BOTH ? true : false;

            props.put("MAINFRAME_WIDTH", Integer.toString(azpzMain.getMAINFRAME_WIDTH()));
            props.put("MAINFRAME_HEIGHT", Integer.toString(azpzMain.getMAINFRAME_HEIGHT()));
            props.put("MAINFRAME_X", Integer.toString(azpzMain.getMAINFRAME_X()));
            props.put("MAINFRAME_Y", Integer.toString(azpzMain.getMAINFRAME_Y()));

        props.put("MAXIMIZED", isMaximizedBoth ? "true" : "false");
        props.put("language", "fr");

        setPropertyFile(new File(azpzMain.getSettingsFileLocation()), props);

        if (debug)
        {
            System.out.println();
            System.out.println(java.awt.Frame.MAXIMIZED_BOTH);
            System.out.println(((Frame) mainFrame).getExtendedState());
            System.out.println();
        }

    }

    /**
     * creates an directory in Filesystem when it not exists
     * 
     * @author Martin Labsch, 28.04.2016
     * @param dir
     * @return true if directory was created or exists
     */
    public static boolean createDirIfNotExists(String dir)
    {
        boolean retValue = false;

        File file = new File(dir);

        if (file.isDirectory())
        {
            System.out.println("jo");
            retValue = true;
        }
        else
        {
            // file.mk
            // System.out.println("nö");

        }

        return retValue;

    }

}
