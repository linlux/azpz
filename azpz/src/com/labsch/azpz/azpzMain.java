package com.labsch.azpz;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

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

	private static final int MAIN_FRAME_WIDTH = 800;
	private static final int MAIN_FRAM_HEIGHT = 600;

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
		JMenu menuClose = new JMenu();

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
		mainFrame.setJMenuBar(menuBar);
		mainFrame.setSize(MAIN_FRAME_WIDTH, MAIN_FRAM_HEIGHT);
		mainFrame.setLocationRelativeTo(null);
		
		// settings for menu
		// TODO Mehrsprachigkeit: Bezeichner aus Datei holen (./lang)
		menuFile.setText("Datei");
		menuClose.setText("Beenden");
		
		menuFile.add(menuClose);
		
		//adding the menus to menuBar
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

		// load or create settings-property-file
		if (settingsFile.isFile())
		{

		}
		else
		{
			FileHandling.createFile(settingsFile);
			FileHandling.setInitialProperties(settingsFile);
		}

		// System.out.println(FileSystems.getDefault());

		// Files.walk(Path , options)

		// String propertiesFile = "F:\\PrOpErties.txt";
		// DemoPropertiesErstelle(propertiesFile);
		// DemoPropertiesLesen(propertiesFile);

	}

}
