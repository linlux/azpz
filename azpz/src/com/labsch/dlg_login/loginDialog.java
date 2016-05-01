package com.labsch.dlg_login;

/**
 * 
 * @author Matthias Lüthke,   27.04.2016
 *
 */
import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


@SuppressWarnings("serial")
public class loginDialog extends JDialog implements ActionListener, WindowListener
{
    private final boolean debug = true;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded = false;
    private Frame parentFrame;
    
    private String dbUserName ;
    private String dbUserPW ;
    private long  dbUser_ID = (long)-1 ;
    private Boolean bLogin = false;
    
    private  login mlog;
    
       
    private void initDialog()
    {
        this.setModal(true);
        // this.setLocationRelativeTo(owner);
    }

    private void initializeComponents(Frame parent)
    {
        parentFrame = parent;
        this.setBounds(100, 100, 300, 200);

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]
        { 30, 44, 57, 45, 65, 0 };
        gridBagLayout.rowHeights = new int[]
        { 59, 20, 20, 23, 0 };
        gridBagLayout.columnWeights = new double[]
        { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[]
        { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        this.getContentPane().setLayout(gridBagLayout);

        JLabel lblUser = new JLabel("User");
        GridBagConstraints gbc_lblUser = new GridBagConstraints();
        gbc_lblUser.anchor = GridBagConstraints.EAST;
        gbc_lblUser.insets = new Insets(0, 0, 5, 5);
        gbc_lblUser.gridx = 2;
        gbc_lblUser.gridy = 1;
        this.getContentPane().add(lblUser, gbc_lblUser);

        tfUsername = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.gridx = 3;
        gbc_textField.gridy = 1;
        this.getContentPane().add(tfUsername, gbc_textField);
        tfUsername.setColumns(10);

        JButton btnCancel = new JButton("Cancel");

        btnCancel.addActionListener(this);

        JButton btnLogin = new JButton("Login");

        btnLogin.addActionListener(this);

        JLabel lblPw = new JLabel("Passwort");
        GridBagConstraints gbc_lblPw = new GridBagConstraints();
        gbc_lblPw.anchor = GridBagConstraints.EAST;
        gbc_lblPw.insets = new Insets(0, 0, 5, 5);
        gbc_lblPw.gridx = 2;
        gbc_lblPw.gridy = 2;
        this.getContentPane().add(lblPw, gbc_lblPw);

        pfPassword = new JPasswordField();
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField.insets = new Insets(0, 0, 5, 5);
        gbc_passwordField.gridx = 3;
        gbc_passwordField.gridy = 2;
        this.getContentPane().add(pfPassword, gbc_passwordField);
        GridBagConstraints gbc_btnCancel = new GridBagConstraints();
        gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
        gbc_btnCancel.gridx = 3;
        gbc_btnCancel.gridy = 3;
        this.getContentPane().add(btnCancel, gbc_btnCancel);
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.gridx = 4;
        gbc_button.gridy = 3;
        this.getContentPane().add(btnLogin, gbc_button);
    }

    public loginDialog(Frame parent)
    {
        super(parent, true);
        //
        initializeComponents(parent);
    }

    public String getUsername()
    {
        return tfUsername.getText().trim();
    }

    public String getPassword()
    {
        return new String(pfPassword.getPassword());
    }

    public boolean isSucceeded()
    {
        return succeeded;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object obj = e.getSource();
        if (debug)
            System.out.println("actionPerformed event :" + e.getActionCommand());

        if (obj instanceof JButton)
        {
            JButton btn = (JButton) obj;
            if (debug)
                System.out.println(" ActinCommand  : " + btn.getActionCommand() + btn.getName());
            // + btnCancel.getActionCommand().toString() +
            // btnLogin.getActionCommand().toString());

            switch (btn.getActionCommand())
            {
            case "Login":
                if (debug)
                    System.out.println(" ActionCommand = " + "Login");

                if (getUsername() == null || getPassword() == null)
                {
                    JOptionPane.showMessageDialog(loginDialog.this, "Bitte Passwort und Username eingeben", "Login", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                
                
                login mlog = new login(getUsername(), getPassword());

                if (mlog.authenticate(getUsername(), getPassword()))
                {
                    System.out.println(" login.authenticate =  " + getUsername() + getPassword());

                    if (debug)
                    {
                        JOptionPane.showMessageDialog(loginDialog.this, "Moin, Moin " + getUsername() + "! Sie sind erfolgreich eingeloggt.", "Login",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                    succeeded = true;
                    windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                }
                else
                {
                    JOptionPane.showMessageDialog(loginDialog.this, "username oder passwort falsch", "Login", JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    tfUsername.setText("");
                    pfPassword.setText("");
                    succeeded = false;
                }

                break;
            case "Cancel":
                windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                break;
            default:
                break;
            }
        }
    }

    // if (btn.getActionCommand().equals("Login"))
    // {
    // // login
    // System.out.println(" ActionCommand = " + "Login" );
    // System.out.println("actionPerformed event :" + " LogIN");
    // if (login.authenticate(getName(), getPassword())) {
    // System.out.println("Erfolgreich eingeloggt " + obj.toString());
    // JOptionPane.showMessageDialog(loginDialog.this,
    // "Moin, Moin " + getUsername() + "! Sie sind erfolgreich eingeloggt.",
    // "Login",
    // JOptionPane.INFORMATION_MESSAGE);
    // succeeded = true;
    // // TODO Lüthke Erfolgsmeldung auf dem Titel des aufrufenden
    // // frame schreiben
    // dispose();
    // } else {
    // System.out.println("nicht eingeloggt " + obj.toString());
    // JOptionPane.showMessageDialog(loginDialog.this, "falscher username der
    // falsches password", "Login",
    // JOptionPane.ERROR_MESSAGE);
    // // reset username and password
    // tfUsername.setText("");
    // pfPassword.setText("");
    // succeeded = false;
    // }
    //
    //
    // if (btn.getActionCommand().equals("Cancel"))
    // System.out.println(" ActionCommand = " + "Cancel" );
    //
    //
    // if ( btn.getActionCommand().equals("Cancel") )
    // if (debug)
    // {
    // System.out.println("WindowClosing event setzen :" +
    // e.getActionCommand());
    // windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    // }
    // else {
    // // login
    // System.out.println("actionPerformed event :" + " LogIN");
    // if (login.authenticate(getName(), getPassword())) {
    // System.out.println("Erfolgreich eingeloggt " + obj.toString());
    // JOptionPane.showMessageDialog(loginDialog.this,
    // "Moin, Moin " + getUsername() + "! Sie sind erfolgreich eingeloggt.",
    // "Login",
    // JOptionPane.INFORMATION_MESSAGE);
    // succeeded = true;
    // // TODO Lüthke Erfolgsmeldung auf dem Titel des aufrufenden
    // // frame schreiben
    // dispose();
    // } else {
    // System.out.println("nicht eingeloggt " + obj.toString());
    // JOptionPane.showMessageDialog(loginDialog.this, "falscher username der
    // falsches password", "Login",
    // JOptionPane.ERROR_MESSAGE);
    // // reset username and password
    // tfUsername.setText("");
    // pfPassword.setText("");
    // succeeded = false;
    // }
    // }

    @Override
    public void windowActivated(WindowEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosed(WindowEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosing(WindowEvent arg0)
    {

        // Ruft die Methode windowClosed() auf
        this.dispose();

    }

    @Override
    public void windowDeactivated(WindowEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowOpened(WindowEvent arg0)
    {
        // TODO Auto-generated method stub

    }
}
