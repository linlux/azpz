package com.labsch.azpz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.labsch.util.DialogHandling;
import com.labsch.util.FileHandling;
import com.labsch.util.MenuHandling;
import com.labsch.dbUtils.DBConnection;
import com.labsch.dlg_login.loginDialog;

/**
 * @author Martin Labsch, 26.04.2016
 */
@SuppressWarnings("serial")
public class azpzFrame extends JFrame implements ActionListener, WindowListener, WindowStateListener, MouseListener, MenuListener
{

    private static final boolean debug = false;
    private boolean bLogin = false; // Matthias Lüthke 01.05.2016

    /**
     * @author Martin Labsch, 26.04.2016
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (debug)
        {
            System.out.println("actionPerformed event from:");
        }

        Object obj = e.getSource();

        if ((obj instanceof JMenuItem && ((JMenuItem) obj).getName().equals("menuItemClose")))
        {
            DialogHandling.showExitConfirmationDialog();
        }
        else if (obj instanceof JMenuItem && ((JMenuItem) e.getSource()).getName().equals("menuItemLogout"))
        {
            if (bLogin)
                logOut();
        }
        else if (obj instanceof JMenuItem && ((JMenuItem) obj).getName().equals("menuItemLogin"))
        {
            if (!bLogin)
            {
                logIn();
            }
            else
            {
                JOptionPane(this, "Sie sind bereits eingeloggt. ", "Login", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (obj instanceof JMenuItem && ((JMenuItem) obj).getName().equals("menuItemLogin"))
        {
            if (!bLogin)
            {
                logIn();
            }
            else
            {
                JOptionPane(this, "Sie sind bereits eingeloggt. ", "Login", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (obj instanceof JButton && ((JButton) obj).getName().equals("appCloseOptionYes"))
        {
            FileHandling.safeActualProperties();
            System.exit(0);
        }

        // close Dialog if No or Cancel was choosed
        else if (obj instanceof JButton && (((JButton) obj).getName().equals("appCloseOptionNo") || ((JButton) obj).getName().equals("appCloseOptionCancel")))
        {
            Window[] allWindows = azpzFrame.getWindows();

            if (allWindows.length > 0)
            {
                for (int i = 0; i < allWindows.length; i++)
                {
                    if (allWindows[i] instanceof JDialog)
                    {
                        allWindows[i].dispose();
                    }
                }
            }

            if (debug)
            {
                if (obj instanceof JMenuItem)
                {
                    System.out.println(((JMenuItem) obj).getName());
                }
                else if (obj instanceof JMenu)
                {
                    System.out.println(((JMenu) obj).getName());
                }
                else if (obj instanceof JButton)
                {
                    System.out.println(((JButton) obj).getName());
                }
            }

        }

    }

    /**
     * TODO noch beschreiben
     */
    private void logIn()
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df;
        df = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");

        loginDialog loginDlg = new loginDialog(this);
        loginDlg.setLocationRelativeTo(this);
        loginDlg.setVisible(true);
        // if logon successfully
        if (loginDlg.isSucceeded())
        {
            setbLogin(true); // Matthias Lüthke 01.05.2016

            this.setTitle(this.getTitle().trim() + "       " + loginDlg.getUsername() + "  ist erfolgreich eingeloggt.   " + now.format(df));
        }
        else
        {
            setbLogin(false); // Matthias Lüthke 01.05.2016
            this.setTitle("AzPz " + " Kein User eingeloggt");
        }
    }

    /**
     * TODO Matthias noch beschreiben
     */
    private void logOut()
    {
        setbLogin(false); // Matthias Lüthke 01.05.2016
        this.setTitle("AzPz " + " Kein User eingeloggt");
    }

    private void JOptionPane(azpzFrame azpzFrame, String string, String string2, int informationMessage)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowActivated(WindowEvent e)
    {
        if (debug)
        {
            System.out.println("windowActivated");
        }
    }

    @Override
    public void windowClosed(WindowEvent e)
    {
        if (debug)
        {
            System.out.println("windowClosed");
        }
    }

    @Override
    public void windowClosing(WindowEvent e)
    {

        // Object obj = e.getSource();

        if (((azpzFrame) e.getSource()).getName().equals("mainFrame"))
        {
            DialogHandling.showExitConfirmationDialog();
        }

        if (debug)
        {
            System.out.println("windowClosing");
        }
    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {
        if (debug)
        {
            System.out.println("windowDeactivated");
        }
    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {
        if (debug)
        {
            System.out.println("windowDeiconified");
        }
    }

    @Override
    public void windowIconified(WindowEvent e)
    {
        if (debug)
        {
            System.out.println("windowIconified");
        }
    }

    @Override
    public void windowOpened(WindowEvent e)
    {
        if (debug)
        {
            System.out.println("windowOpened");
        }
    }

    @Override
    public void windowStateChanged(WindowEvent e)
    {
        // if (e.getSource() instanceof azpzFrame && ((azpzFrame) e.getSource()).getName().equals("mainFrame"))
        // {
        // azpzFrame af = (azpzFrame) e.getSource();
        // System.out.println();
        // System.out.println(af.getExtendedState());
        // System.out.println(azpzMain.getMAINFRAME_WIDTH());
        // System.out.println(azpzMain.getMAINFRAME_HEIGHT());
        // System.out.println(azpzMain.getMAINFRAME_X());
        // System.out.println(azpzMain.getMAINFRAME_Y());
        //
        // if (af.getExtendedState() == Frame.NORMAL)
        // {
        // }
        // }

        if (debug)
        {
            System.out.println("windowStateChanged");
        }
    }

    /**
     * @return the bLogin
     */
    public boolean isbLogin()
    {
        return bLogin;
    }

    /**
     * @param bLogin
     *            the bLogin to set
     */
    public void setbLogin(boolean bLogin)
    {
        this.bLogin = bLogin;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        Object obj = e.getSource();

        /**
         * @author Martin Labsch, 01.05.2016 connect to db
         */
        // if ((obj instanceof JMenu && ((JMenu) obj).getName().equals("menuConnect")))
        // {
        //
        // }

        // if (DBConnection.connectToDatabase())
        // {
        // JMenu m = MenuHandling.getAnMenuByNameFromFramesMenuBar("menuConnect", "mainFrame");
        // // JMenuItem mi = MenuHandling.getAnMenuItemByNameFromFramesMenuBar("menuConnect", "mainFrame");
        // if (m != null)
        // {
        //
        // m.setText("Verbunden");
        // Font font = m.getFont().deriveFont(Font.BOLD);
        // m.setFont(font);
        // m.setForeground(Color.RED);
        // // deselect the selected element
        // MenuSelectionManager.defaultManager().clearSelectedPath();
        // }
        //
        // }
        // else
        // {
        // System.out.println(MenuSelectionManager.defaultManager().getSelectedPath());
        // MenuElement[] elems = MenuSelectionManager.defaultManager().getSelectedPath();
        //
        // if (elems.length > 0)
        // {
        // for (int i = 0; i < elems.length; i++)
        // {
        // elems[i].getSubElements();
        // }
        // }
        //
        // System.out.println();
        //
        // }

        if (debug)
        {
            System.out.println("mouseClicked");
            System.out.println(obj.getClass());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        if (debug)
        {
            System.out.println("mouseEntered");
        }
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        if (debug)
        {
            System.out.println("mouseExited");
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (debug)
        {
            System.out.println("mousePressed");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (debug)
        {
            System.out.println("mouseReleased");
        }
    }

    @Override
    public void menuCanceled(MenuEvent e)
    {
        if (debug)
        {
            System.out.println("menuCanceled");
        }

    }

    @Override
    public void menuDeselected(MenuEvent e)
    {
        if (debug)
        {
            System.out.println("menuDeselected");
        }

    }

    @Override
    public void menuSelected(MenuEvent e)
    {
        Object obj = e.getSource();

        if (obj != null && ((JMenu) obj).getName().equals("menuConnect"))
        {
            if (DBConnection.connectToDatabase())
            {
                JMenu m = MenuHandling.getAnMenuByName("menuConnect", "mainFrame");
                // JMenuItem mi = MenuHandling.getAnMenuItemByNameFromFramesMenuBar("menuConnect", "mainFrame");
                if (m != null)
                {

                    m.setText("Verbunden");
                    Font font = m.getFont().deriveFont(Font.BOLD);
                    m.setFont(font);
                    m.setForeground(Color.RED);
                    // deselect the selected element
                    MenuSelectionManager.defaultManager().clearSelectedPath();
                    Projects.initProjects();
                }

            }
            else
            {
                System.out.println(MenuSelectionManager.defaultManager().getSelectedPath());
                MenuElement[] elems = MenuSelectionManager.defaultManager().getSelectedPath();

                if (elems.length > 0)
                {
                    for (int i = 0; i < elems.length; i++)
                    {
                        elems[i].getSubElements();
                    }
                }

                System.out.println();

            }
        }

        if (obj != null && ((JMenu) obj).getName().equals("menuProjects"))
        {
            System.out.println("Do something");
        }

        if (debug)
        {
            System.out.println("menuSelected");
        }

    }
}
