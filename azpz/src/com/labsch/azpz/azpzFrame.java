package com.labsch.azpz;

import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import com.labsch.util.DialogHandling;
import com.labsch.util.FileHandling;
import com.labsch.dlg_login.loginDialog;

/**
 * @author Martin Labsch, 26.04.2016
 */
@SuppressWarnings("serial")
public class azpzFrame extends JFrame implements ActionListener, WindowListener, WindowStateListener
{

    private static final boolean debug = false;

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
            //  Login Dialog was choosed
            // @author Matthias Lüthke, 30.04.2016
            else if (obj instanceof JMenuItem && ((JMenuItem) e.getSource()).getName().equals("menuItemLogin"))
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
                    this.setTitle(this.getTitle().trim() + "       " + loginDlg.getUsername() + "  ist erfolgreich eingeloggt.   " + now.format(df));
                }
                else
                    this.setTitle("AzPz " + " Kein User eingeloggt");

                if (debug)
                {
                    if (e.getSource() instanceof JMenuItem)
                    {
                        System.out.println(((JMenuItem) e.getSource()).getName());
                    }
                    if (e.getSource() instanceof JButton)
                    {
                        System.out.println(((JButton) e.getSource()).getName());
                    }
                }

            }

        }

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
//        if (e.getSource() instanceof azpzFrame && ((azpzFrame) e.getSource()).getName().equals("mainFrame"))
//        {
//            azpzFrame af = (azpzFrame) e.getSource();
//            System.out.println();
//            System.out.println(af.getExtendedState());
//            System.out.println(azpzMain.getMAINFRAME_WIDTH());
//            System.out.println(azpzMain.getMAINFRAME_HEIGHT());
//            System.out.println(azpzMain.getMAINFRAME_X());
//            System.out.println(azpzMain.getMAINFRAME_Y());
//
//            if (af.getExtendedState() == Frame.NORMAL)
//            {
//            }
//        }

        if (debug)
        {
            System.out.println("windowStateChanged");
        }

    }

}
