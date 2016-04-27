package com.labsch.azpz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import com.labsch.dlg_login.loginDialog;

/**
 @author Martin Labsch, 26.04.2016
 */
@SuppressWarnings("serial")
public class azpzFrame extends JFrame implements ActionListener, WindowListener
{

	private static final boolean debug = true;

	/**
	 * @author Martin Labsch, 26.04.2016
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("actionPerformed event from:");

		Object obj = e.getSource();

		if (obj instanceof JMenuItem)
		{
			JMenuItem mi = (JMenuItem) obj;   
			
			
			if (mi.getName() != null && mi.getName().equals("menuItemClose"))
			{
				System.out.println(mi.getName());
			}
			//     @author Matthias Lüthke, 27.04.2016
			else 	if (mi.getName() != null && mi.getName().equals("menuItemLogin"))		
			{
			       System.out.println(mi.getName()); 
			       final JFrame frame = new JFrame("JDialog Test");
			       
			       final JButton btnLogin = new JButton("Click to login");		             
				
				
			       frame.setLocationRelativeTo(this);						
			   
			       
			       loginDialog loginDlg = new loginDialog(frame);
			       frame.setLocationRelativeTo(this);	
	                       loginDlg.setVisible(true);
	                        // if logon successfully
	                        if(loginDlg.isSucceeded())                        
	                            btnLogin.setText("Hi alles OK " + loginDlg.getUsername() + "!");   
			       
			}
			else 			
			{
				System.out.println(mi.getName());
			}

		}

	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		if(debug)
		{
			System.out.println("windowActivated");
		}		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		if(debug)
		{
			System.out.println("windowClosed");
		}		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		if(debug)
		{
			System.out.println("windowClosing");
		}		
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		if(debug)
		{
			System.out.println("windowDeactivated");
		}		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		if(debug)
		{
			System.out.println("windowDeiconified");
		}		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		if(debug)
		{
			System.out.println("windowIconified");
		}		
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		if(debug)
		{
			System.out.println("windowOpened");
		}				
	}

}
