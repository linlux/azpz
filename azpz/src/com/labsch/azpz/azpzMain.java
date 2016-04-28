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
     * @author Martin Labsch, 26.04.2016
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

    /**
     * @author Martin Labsch, 26.04.2016
     * @param args
     */
    public static void main(String[] args)
    {
        loadOrCreateSettingsFile();
        initializeComponents();
    }

	
	
    /**
     * @author Martin Labsch, 26.04.2016
     */
    private static void initializeComponents()
    {
        azpzFrame mainFrame = new azpzFrame();
        mainFrame.setName("mainFrame");
        mainFrame.addWindowListener(mainFrame);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = createMenuAndAddToMenuBar("Datei", "menuFile", 'D', menuBar);
		/**
		 *    @author Matthias Lüthke, 27.04.2016
		 */
	JMenu menuLogin = createMenuAndAddToMenuBar("Login", "menuLogin", 'L', menuBar);

        // TODO Mehrsprachigkeit: Bezeichner aus Datei holen (./lang)
        // menu-entries
        createMenuItemAndAddToMenu("Öffnen", "menuItemOpen", 'F', mainFrame, menuFile);
        createMenuItemAndAddToMenu("Beenden", "menuItemClose", 'E', mainFrame, menuFile);
		/**
		 *    @author Matthias Lüthke, 27.04.2016
		 */
		createMenuItemAndAddToMenu("LogIn", "menuItemLogin", 'L', mainFrame, menuLogin);

        // Titel
        mainFrame.setTitle(titleMainFrame);
        // Icon
        if (mainFrameIcon != null)
        {
            mainFrame.setIconImage(mainFrameIcon.getImage());
        }
        // settings for mainFrame
        // TODO Nachfragen, ob wirklich beendet werden soll
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        mainFrame.setLocationRelativeTo(null);

        // adding the components to the mainFrame
        mainFrame.add(menuBar, BorderLayout.PAGE_START);

        mainFrame.setVisible(true);

    }

    /**
     * creates an JMenu and added it to an JMenuBar.
     * 
     * @author Martin Labsch, 26.04.2016
     * @param text
     *            - will be displayed
     * @param name
     *            - for later identification e.g. listeners
     * @param mnemonic
     *            - the key to navigation via keyboard
     * @param menuBar
     *            - where the menu will be added
     * @return
     */
    private static JMenu createMenuAndAddToMenuBar(String text, String name, char mnemonic, JMenuBar menuBar)
    {
        JMenu menu = new JMenu();

        menu.setText(text);
        menu.setName(name);
        menu.setMnemonic('D');

        menuBar.add(menu);

        return menu;
    }

    /**
     * creates an JMenuItem and added it to an JMenu.
     * 
     * @author Martin Labsch, 26.04.2016
     * @param text
     *            - will be displayed
     * @param name
     *            - for later identification e.g. listeners
     * @param mnemonic
     *            - the key to navigation via keyboard
     * @param actionListener
     *            - will be added to the item
     * @param menu
     *            - where the menu-item will be added
     * @return
     */
    private static JMenuItem createMenuItemAndAddToMenu(String text, String name, char mnemonic, azpzFrame actionListener, JMenu menu)
    {
        JMenuItem mi = new JMenuItem(text);
        mi.setName(name);
        mi.setMnemonic(mnemonic);
        mi.addActionListener(actionListener);

        menu.add(mi);

        return mi;
    }

    /**
     * loads the settings from file or creates a settings-file with initial values<br>
     * if the file doesnt exists.
     * 
     * @author Martin Labsch, 26.04.2016
     */
    private static void loadOrCreateSettingsFile()
    {
        // test if file exists
        // Path path = (Path) FileSystems.getDefault().getPath(".", more);
	
	
	/**
	 * ML 20160428 ToDo es muss überprüft werden ob Pfad vorhanden ist
	 * sonst Fehler   C:\Users\Alfa\git\azpz\azpz\settings
	 */
	

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
                azpzMain.MAIN_FRAME_HEIGHT = new Integer(prop.getProperty("MAIN_FRAME_HEIGHT"));
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

    /**
     * 
     * @author Martin Labsch, 27.04.2016
     * @return path of File 'setting.properties'
     */
    public static String getSettingsFilePath()
    {
        return settingsFilePath;
    }
}
