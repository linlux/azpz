package com.labsch.azpz;

import java.awt.Component;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import com.labsch.util.DialogHandling;

import com.labsch.dlg_login.loginDialog;

/**
 * @author Martin Labsch, 26.04.2016
 */
@SuppressWarnings("serial")
public class azpzFrame extends JFrame implements ActionListener, WindowListener
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
            DialogHandling.queryExit((Component) e.getSource());
        }

        if (obj instanceof JButton && ((JButton) obj).getName().equals("appCloseOptionYes"))
        {
            System.exit(0);
        }

        // @author Matthias L�thke, 27.04.2016
        if (obj instanceof JMenuItem && ((JMenuItem) e.getSource()).getName().equals("menuItemLogin"))
        {

            final JFrame frame = new JFrame("JDialog Test");

            final JButton btnLogin = new JButton("Click to login");

            frame.setLocationRelativeTo(this);

            loginDialog loginDlg = new loginDialog(frame);
            frame.setLocationRelativeTo(this);
            loginDlg.setVisible(true);
            // if logon successfully
            if (loginDlg.isSucceeded())
                btnLogin.setText("Hi alles OK " + loginDlg.getUsername() + "!");

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

        if (DialogHandling.queryExit((Component) e.getSource()))
        {
            if (((azpzFrame) e.getSource()).getName().equals("mainFrame"))
            {
                System.exit(0);
            }
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

}
