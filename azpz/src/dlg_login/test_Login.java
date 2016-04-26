package dlg_login;
import java.awt.event.*;
import javax.swing.*;
public class test_Login
{

    public static void main(String[] args)
    {

        final JFrame frame = new JFrame("JDialog Test");
        final JButton btnLogin = new JButton("Click to login");
 
        btnLogin.addActionListener    (
                new ActionListener()
                {
                    @Override
		    public void actionPerformed(ActionEvent e) 
                    {
                        LoginDialog loginDlg = new LoginDialog(frame);
                        loginDlg.setVisible(true);
                        // if logon successfully
                        if(loginDlg.isSucceeded())                        
                            btnLogin.setText("Hi alles OK " + loginDlg.getUsername() + "!");                       
                    }
                }
          ) ;
        
    }
}
