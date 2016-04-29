package com.labsch.dlg_login;
/**
 * 
 * @author Matthias Lüthke,   27.04.2016
 *
 */
import java.awt.*;
import java.awt.Window.Type;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class loginDialog extends JDialog  implements  ActionListener
{ 
   // private JFrame frameLogin;
    private static final boolean debug = true;  
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    private Frame parentFrame;     
    
    // Grösse der Componenten festlegen
    
    private int  dlg_width= 325;
    private int  dlg_height = 210;
    private int  btn_width= 80;
    private int  btn_height = 20;
    private int  lbl_width= 60;
    private int  lbl_height = 20;
    private int  tf_width= 60;
    private int  tf_height = 20;   
        
       
    private void initDialog ()
	{
	        
	        this.setModal(true);
		//this.setLocationRelativeTo(owner); 	

	}
       private void initializeComponents(Frame parent)
	{
	   
	 parentFrame = parent;
        
        
        this.setSize(dlg_width, dlg_height);
        this.setLocationRelativeTo(parentFrame);        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);   
        
        this.setTitle(" Login Dialog. Bitte User und Password eingeben");  
        
	this.setType(Type.UTILITY);
	this.setBounds(100, 100, 325, 210);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.getContentPane().setLayout(null);
	
	JButton btnNewButton = new JButton("Cancel");
	btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
	btnNewButton.setBounds(204, 112, btn_width, btn_height);
	this.getContentPane().add(btnNewButton);
	
	JButton btnCa = new JButton("Login");
	btnCa.setFont(new Font("Tahoma", Font.PLAIN, 12));
	btnCa.setBounds(115, 112, btn_width, btn_height);
	this.getContentPane().add(btnCa);
	
	JLabel lblUser = new JLabel("User:");
	lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblUser.setBounds(37, 34, lbl_width, lbl_height);
	this.getContentPane().add(lblUser);
	
	JLabel lblPasswort = new JLabel("Passwort:");
	lblPasswort.setToolTipText("Bitte Passwort eingeben und einloggen");
	lblPasswort.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblPasswort.setBounds(37, 69, lbl_width, lbl_height);
	this.getContentPane().add(lblPasswort);
	
	tfUsername = new JTextField();
	tfUsername.setBounds(115, 28, tf_width, tf_height);
	
	this.getContentPane().add(tfUsername);
	
	pfPassword = new JPasswordField();
	pfPassword.setBounds(115, 66, tf_width, tf_height);
	this.getContentPane().add(pfPassword);              
                  

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
        	Object obj = e.getSource();
        	
        	  if (obj instanceof JButton)
        	  {
        	      JButton btn = (JButton) obj;
        	            	      
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
        }
        )   ;
      
      
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
    @Override
    public void actionPerformed(ActionEvent e)
    {
	// TODO Auto-generated method stub
	
    }
}