package com.labsch.azpz;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.labsch.util.FileHandling;

public class azpzMain
{

	/**
	 * 
	 * 26.04.2016
	 * 
	 * @author Martin Labsch
	 *
	 */
	private enum StartupMode
	{
		NORMAL, MAXIMIZED
	}

	private static int MAIN_FRAME_WIDTH;
	private static int MAIN_FRAME_HEIGHT;
	private static String APP_LANG;

	private static String appRootDir = FileHandling.getAppPath();

	private static String settingsFilePath = appRootDir != null ? appRootDir + "\\settings\\settings.properties" : null;
	private static ImageIcon mainFrameIcon = appRootDir != null ? new ImageIcon(appRootDir + "\\pic\\Hourglass-icon-48pxl.png") : null;
	private static ImageIcon mainFrameIcon2 = appRootDir != null ? new ImageIcon(appRootDir + "\\pic\\Hourglass-icon-pxl.png") : null;

	private static int mainFrameX, mainFrameY;
	private static String titleMainFrame = "AzPz";
	// private static String mainFrameIcon = "\\pic\\Hourglass-icon-48pxl.png";

	/**
	 * 26.04.2016
	 * 
	 * @author Martin Labsch
	 * @param args
	 */
	public static void main(String[] args)
	{

		loadOrCreateSettingsFile();
		initializeComponents();

		try
		{

		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

	}

	private static void initializeComponents()
	{
		azpzFrame mainFrame = new azpzFrame();

		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu();
		JMenuItem menuItemOpen = new JMenuItem();
		JMenuItem menuItemClose = new JMenuItem();

//		ComponentListener menuBarListener = new actionper;
		
		// Titel
		mainFrame.setTitle(titleMainFrame);
		// Icon
		if (mainFrameIcon != null)
		{
			mainFrame.setIconImage(mainFrameIcon.getImage());
		}
		// settings for mainFrame
		// TODO Nachfragen, ob wirklich beendet werden soll
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
		mainFrame.setLocationRelativeTo(null);

		// settings for menu
		// TODO Mehrsprachigkeit: Bezeichner aus Datei holen (./lang)
		menuFile.setText("Datei");
		
		menuItemOpen.setText("Öffnen");
		
		menuItemClose.setText("Beenden");
		menuItemClose.setName("menuItemClose");
		
		menuItemOpen.addActionListener(mainFrame);
		menuItemClose.addActionListener(mainFrame);

		menuFile.add(menuItemOpen);
		menuFile.add(menuItemClose);

		// adding the menus to menuBar
		menuBar.add(menuFile);

		// adding the components to the mainFrame
		mainFrame.add(menuBar, BorderLayout.PAGE_START);

		mainFrame.setVisible(true);

	}

	private static void loadOrCreateSettingsFile()
	{
		// test if file exists
		// Path path = (Path) FileSystems.getDefault().getPath(".", more);

		File settingsFile = new File(settingsFilePath);

		// create settingsFile if doesnt exists
		if (!settingsFile.isFile())
		{
			FileHandling.createFile(settingsFile);
			FileHandling.setInitialPropertiesFile(settingsFile);
		}

		if (settingsFile.isFile())
		{
			Properties prop = FileHandling.getProperties(settingsFile);

			if (prop.size() > 0)
			{
				azpzMain.MAIN_FRAME_HEIGHT = new Integer(prop.getProperty("MAIN_FRAM_HEIGHT"));
				azpzMain.MAIN_FRAME_WIDTH = new Integer(prop.getProperty("MAIN_FRAME_WIDTH"));
				azpzMain.APP_LANG = prop.getProperty("APP_LANG");

			}
			else
			{
				System.out.println("Keine Einstellungen vorhanden.");
				// Status 1 because "a nonzero status code indicates abnormal termination"
				System.exit(1);
			}

		}

		// System.out.println(FileSystems.getDefault());

		// Files.walk(Path , options)

		// String propertiesFile = "F:\\PrOpErties.txt";
		// DemoPropertiesErstelle(propertiesFile);
		// DemoPropertiesLesen(propertiesFile);

	}
}
