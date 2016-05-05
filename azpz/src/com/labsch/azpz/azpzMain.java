package com.labsch.azpz;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.File;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.labsch.util.FileHandling;

public class azpzMain
{

    /**
     * @author Martin Labsch, 26.04.2016
     */
    // private enum StartupMode
    // {
    // NORMAL, MAXIMIZED
    // }

    private static final boolean debug = false;

    private static int MAINFRAME_WIDTH = 800,
            MAINFRAME_HEIGHT = 600,
            MAINFRAME_X,
            MAINFRAME_Y;
    private static Boolean MAINFRAME_MAXIMIZED_BOTH;

    private static String appRootDir = FileHandling.getAppPath(),
            settingsFilePath = "settings",
            settingsFileName = "settings.properties",
            titleMainFrame = "AzPz",
            APP_LANG;

    private static ImageIcon mainFrameIcon = appRootDir != null ? new ImageIcon(appRootDir + "\\pic\\Hourglass-icon-48pxl.png") : null,
            mainFrameIcon2 = appRootDir != null ? new ImageIcon(appRootDir + "\\pic\\Hourglass-icon-pxl.png") : null;

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

        JMenuBar menuBar = new JMenuBar();
        JMenuBar statusBar = new JMenuBar();

        JMenu menuFile = createMenuAndAddToMenuBar("Datei", "menuFile", 'D', menuBar);

        /**
         * @author Matthias Lüthke, 27.04.2016
         */
        JMenu menuLogin = createMenuAndAddToMenuBar("Login", "menuLogin", 'L', menuBar);

        /**
         * @author Matthias Lüthke, 27.04.2016
         */
        JMenu menuLogOut = createMenuAndAddToMenuBar("Logout", "menuLogout", 'O', menuBar);

        // TODO Mehrsprachigkeit: Bezeichner aus Datei holen (./lang)
        // menu-entries
        createMenuAndAddToMenuBar("Verbinden", "menuConnect", 'V', mainFrame, menuBar);
        JMenu menuProjects = (JMenu) createMenuAndAddToMenuBar("Projekte", "menuProjects", 'P', mainFrame, menuBar);
        menuProjects.setEnabled(false);

        // createMenuItemAndAddToMenu("Öffnen", "menuItemOpen", 'F', mainFrame, menuFile);
        createMenuItemAndAddToMenu("Beenden", "menuItemClose", 'E', mainFrame, menuFile);
        /**
         * @author Matthias Lüthke, 27.04.2016
         */
        createMenuItemAndAddToMenu("LogIn", "menuItemLogin", 'L', mainFrame, menuLogin);
        createMenuItemAndAddToMenu("LogOut", "menuItemLogout", 'O', mainFrame, menuLogOut);

        mainFrame.setName("mainFrame");
        mainFrame.setTitle(titleMainFrame);
        mainFrame.addWindowListener(mainFrame);
        mainFrame.addWindowStateListener(mainFrame);
        if (mainFrameIcon != null)
        {
            mainFrame.setIconImage(mainFrameIcon.getImage());
        }
        // settings for mainFrame
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setSize(MAINFRAME_WIDTH, MAINFRAME_HEIGHT);
        mainFrame.setExtendedState(MAINFRAME_MAXIMIZED_BOTH ? Frame.MAXIMIZED_BOTH : mainFrame.getExtendedState());
        // mainFrame.setLocationRelativeTo(null);
        mainFrame.setLocation(MAINFRAME_X, MAINFRAME_Y);
        mainFrame.addComponentListener(mainFrame);

        JLabel statusBarLabel = new JLabel("statusbar");
        statusBarLabel.setName("statusBarLabel");

        // adding components to statusBar
        statusBar.add(statusBarLabel);

        // adding the components to the mainFrame
        // mainFrame.add(menuBar, BorderLayout.PAGE_END);
        mainFrame.setJMenuBar(menuBar);
        mainFrame.getContentPane().add(statusBar, BorderLayout.PAGE_END);

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
        menu.setMnemonic(mnemonic);

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
     * @param listener
     *            - will be added to the item
     * @param menu
     *            - where the menu will be added
     * @return
     */
    private static JMenuItem createMenuAndAddToMenuBar(String text, String name, char mnemonic, azpzFrame listener, JMenuBar menu)
    {
        JMenu m = new JMenu(text);
        m.setName(name);
        m.setMnemonic(mnemonic);
        m.addActionListener(listener);
        m.addMouseListener(listener);
        m.addMenuListener(listener);

        menu.add(m);

        if (debug)
        {
            System.out.println("getComponentZOrder(m): " + m.getComponentZOrder(m));
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
                azpzMain.MAINFRAME_HEIGHT = null != prop.getProperty("MAINFRAME_HEIGHT") ? new Integer(prop.getProperty("MAINFRAME_HEIGHT")) : 800;
                azpzMain.MAINFRAME_WIDTH = null != prop.getProperty("MAINFRAME_WIDTH") ? new Integer(prop.getProperty("MAINFRAME_WIDTH")) : 800;
                azpzMain.MAINFRAME_X = null != prop.getProperty("MAINFRAME_X") ? new Integer(prop.getProperty("MAINFRAME_X")) : 0;
                azpzMain.MAINFRAME_Y = null != prop.getProperty("MAINFRAME_Y") ? new Integer(prop.getProperty("MAINFRAME_Y")) : 0;
                azpzMain.MAINFRAME_MAXIMIZED_BOTH = null != prop.getProperty("MAXIMIZED") ? new Boolean((prop.getProperty("MAXIMIZED"))) : false;
                azpzMain.APP_LANG = null != prop.getProperty("APP_LANG") ? prop.getProperty("APP_LANG") : "de";
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

    /**
     * @return the mAINFRAME_WIDTH
     */
    public static int getMAINFRAME_WIDTH()
    {
        return MAINFRAME_WIDTH;
    }

    /**
     * @param mAINFRAME_WIDTH
     *            the mAINFRAME_WIDTH to set
     */

    public static void setMAINFRAME_WIDTH(int mAINFRAME_WIDTH)
    {
        MAINFRAME_WIDTH = mAINFRAME_WIDTH;
    }

    /**
     * @return the mAINFRAME_HEIGHT
     */
    public static int getMAINFRAME_HEIGHT()
    {
        return MAINFRAME_HEIGHT;
    }

    /**
     * @param mAINFRAME_HEIGHT
     *            the mAINFRAME_HEIGHT to set
     */

    public static void setMAINFRAME_HEIGHT(int mAINFRAME_HEIGHT)
    {
        MAINFRAME_HEIGHT = mAINFRAME_HEIGHT;
    }

    /**
     * @return the mAINFRAME_X
     */
    public static int getMAINFRAME_X()
    {
        return MAINFRAME_X;
    }

    /**
     * @param mAINFRAME_X
     *            the mAINFRAME_X to set
     */

    public static void setMAINFRAME_X(int mAINFRAME_X)
    {
        MAINFRAME_X = mAINFRAME_X;
    }

    /**
     * @return the mAINFRAME_Y
     */
    public static int getMAINFRAME_Y()
    {
        return MAINFRAME_Y;
    }

    /**
     * @param mAINFRAME_Y
     *            the mAINFRAME_Y to set
     */

    public static void setMAINFRAME_Y(int mAINFRAME_Y)
    {
        MAINFRAME_Y = mAINFRAME_Y;
    }

    /**
     * @return the mAINFRAME_MAXIMIZED_BOTH
     */
    public static Boolean getMAINFRAME_MAXIMIZED_BOTH()
    {
        return MAINFRAME_MAXIMIZED_BOTH;
    }

    /**
     * @param mAINFRAME_MAXIMIZED_BOTH
     *            the mAINFRAME_MAXIMIZED_BOTH to set
     */

    public static void setMAINFRAME_MAXIMIZED_BOTH(Boolean mAINFRAME_MAXIMIZED_BOTH)
    {
        MAINFRAME_MAXIMIZED_BOTH = mAINFRAME_MAXIMIZED_BOTH;
    }
}
