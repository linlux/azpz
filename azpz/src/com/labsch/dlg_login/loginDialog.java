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
public class loginDialog extends JDialog  
{ 
    private static final boolean debug = true;  
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
        
        this.setSize(300, 400);
            
     // Layout Manager ausschalten
     		this.setLayout(null);
      // GridBagConstraints cs = new GridBagConstraints();

      /// cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Username: ");
//        cs.gridx = 0;
//        cs.gridy = 0;
//        cs.gridwidth = 1;
        
        lbUsername.setBounds(10, 10, 100, 100);
        panel.add(lbUsername);

       tfUsername = new JTextField(20);
       tfUsername.setBounds(10, 110, 100, 100);
       panel.add(tfUsername);

        lbPassword = new JLabel("Password: ");     
        lbPassword.setBounds(60, 10, 100, 100);
        panel.add(lbPassword);

        pfPassword = new JPasswordField(20);

        lbPassword.setBounds(60, 110, 100, 100);
        panel.add(pfPassword);
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

    public loginDialog(Frame parent) 
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
                    JOptionPane.showMessageDialog(loginDialog.this,
                            "Moin, Moin " + getUsername() + "! Sie sind erfolgreich eingeloggt.",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    dispose();
                } else 
                {
                    JOptionPane.showMessageDialog(loginDialog.this,
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