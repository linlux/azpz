package com.labsch.dlg_login;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class LoginDialog extends JDialog  
{ 
    private boolean debug = false;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    private Frame parentFrame; 
    
    private void initDialog ()
	{
	        
	        this.setModal(true);
		//this.setLocationRelativeTo(owner); 	

	}
       private void initializeComponents(Frame parent)
	{
	   
	 parentFrame = parent;
         JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);

        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("Login");
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {

        @Override
	public void actionPerformed(ActionEvent e)
             {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);

        getContentPane().add(bp, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

  }

    public LoginDialog(Frame parent) 
    {
        super(parent, "Login", true);
        //
        initializeComponents(parent)  ;

        btnLogin.addActionListener(new ActionListener() 
        {

            @Override
	    public void actionPerformed(ActionEvent e)
             {
                if (login.authenticate(getUsername(), getPassword()))
                 {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Moin, Moin " + getUsername() + "! Sie sind erfolgreich eingeloggt.",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    dispose();
                } else 
                {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "falscher username der falsches password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    tfUsername.setText("");
                    pfPassword.setText("");
                    succeeded = false;

                }
            }
        }
        );
        
      
      
    }

    public String getUsername() {
        return tfUsername.getText().trim();
    }

    public String getPassword() {
        return new String(pfPassword.getPassword());
    }

    public boolean isSucceeded() {
        return succeeded;
    }
}