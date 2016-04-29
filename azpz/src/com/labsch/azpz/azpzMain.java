package com.labsch.azpz;

import java.awt.BorderLayout;
import java.awt.Frame;
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

    private static final boolean debug = false;

    private static int MAIN_FRAME_WIDTH;
    private static int MAIN_FRAME_HEIGHT;
    private static int MAINFRAME_X;
    private static int MAINFRAME_Y;
    private static Boolean MAXIMIZED;
    private static String APP_LANG;

    private static String appRootDir = FileHandling.getAppPath();

    private static String settingsFilePath = "settings";
    private static String settingsFileName = "settings.properties";

    private static ImageIcon mainFrameIcon = appRootDir != null ? new ImageIcon(appRootDir + "\\pic\\Hourglass-icon-48pxl.png") : null;
    private static ImageIcon mainFrameIcon2 = appRootDir != null ? new ImageIcon(appRootDir + "\\pic\\Hourglass-icon-pxl.png") : null;

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
         * @author Matthias Lüthke, 27.04.2016
         */
        JMenu menuLogin = createMenuAndAddToMenuBar("Login", "menuLogin", 'L', menuBar);

        // TODO Mehrsprachigkeit: Bezeichner aus Datei holen (./lang)
        // menu-entries
        createMenuAndAddToMenuBar("Verbinden", "menuItemConnect", 'V', mainFrame, menuBar);
        // createMenuItemAndAddToMenu("Öffnen", "menuItemOpen", 'F', mainFrame, menuFile);
        createMenuItemAndAddToMenu("Beenden", "menuItemClose", 'E', mainFrame, menuFile);
        /**
         * @author Matthias Lüthke, 27.04.2016
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
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        mainFrame.setExtendedState(MAXIMIZED ? Frame.MAXIMIZED_BOTH : mainFrame.getExtendedState());
//        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLocation(MAINFRAME_X, MAINFRAME_Y);

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
     * creates an JMenu and added it to an JMenuBar.
     * 
     * @author Martin Labsch, 29.04.2016
     * @param text
     *            - will be displayed
     * @param name
     *            - for later identification e.g. listeners
     * @param mnemonic
     *            - the key to navigation via keyboard
     * @param actionListener
     *            - will be added to the item
     * @param menu
     *            - where the menu will be added
     * @return
     */
    private static JMenuItem createMenuAndAddToMenuBar(String text, String name, char mnemonic, azpzFrame actionListener, JMenuBar menu)
    {
        JMenu m = new JMenu(text);
        m.setName(name);
        m.setMnemonic(mnemonic);
        m.addActionListener(actionListener);
        
        menu.add(m);
        
        if(debug){
            System.out.println(m.getComponentZOrder(m));
        }
        
        return m;
    }

    /**
     * loads the settings from file or creates a settings-file with initial values<br>
     * if the file doesnt exists.
     * 
     * @author Martin Labsch, 26.04.2016
     */
    private static void loadOrCreateSettingsFile()
    {
        File dir = new File(appRootDir + File.separator + settingsFilePath);
        File settingsFile = new File(dir + File.separator + settingsFileName);

        // create settingsFile if doesnt exists
        if (!settingsFile.isFile())
        {
            if (FileHandling.createDir(dir))
            {
                if (FileHandling.createFile(settingsFile))
                {
                    FileHandling.setInitialPropertiesFile(settingsFile);
                }
            }
        }

        // load settings from file
        if (settingsFile.isFile())
        {
            Properties prop = FileHandling.getProperties(settingsFile);

            if (!prop.isEmpty())
            {
                azpzMain.MAIN_FRAME_HEIGHT = null != prop.getProperty("MAIN_FRAME_HEIGHT") ? new Integer(prop.getProperty("MAIN_FRAME_HEIGHT")) : 800;
                azpzMain.MAIN_FRAME_WIDTH = null != prop.getProperty("MAIN_FRAME_WIDTH") ? new Integer(prop.getProperty("MAIN_FRAME_WIDTH")) : 800;
                azpzMain.MAINFRAME_X = null != prop.getProperty("MAINFRAME_X") ? new Integer(prop.getProperty("MAINFRAME_X")) : 0;
                azpzMain.MAINFRAME_Y = null != prop.getProperty("MAINFRAME_Y") ? new Integer(prop.getProperty("MAINFRAME_Y")) : 0;
                azpzMain.MAXIMIZED = null !=  prop.getProperty("MAXIMIZED") ? new Boolean((prop.getProperty("MAXIMIZED"))) : false;
                azpzMain.APP_LANG = null !=  prop.getProperty("APP_LANG") ? prop.getProperty("APP_LANG") : "de";
            }
            else
            {
                System.out.println("Keine Einstellungen vorhanden.");
                // Status -1 because "a nonzero status code indicates abnormal termination"
                System.exit(-1);
            }

        }

    }

    /**
     * 
     * @author Martin Labsch, 27.04.2016
     * @return path of settingsfile inluding filename
     */
    public static String getSettingsFileLocation()
    {
        return appRootDir + File.separator + settingsFilePath + File.separator + settingsFileName;
    }
}
