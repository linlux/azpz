package com.labsch.dlg_login;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.labsch.dbUtils.DBConnection;



public class login

{        
    private String dbUserName  ;
    private String dbUserPW ;
    private Long  dbUser_ID = (long) -1 ;
    private Boolean bLogin = false;
   
   //
    
    public login(String username, String password)
    {
        if ( !username.isEmpty() &&  !password.isEmpty()) 
          initializeComponents( username,  password);
    }
     
    private void initializeComponents(String username, String password)
    {
        boolean bOK = false;     
        bOK =  readUser(username) ;        
        
    }

    /**
	 * @using	 * 
	 *   . authenticate('ML' username, String 'geheim')
	 * @param username
	 *  Username
	 * @param password
	 * Passwort 	
	 * @returnvalue
	 *    true = User ist authentifiziert
	 *    false = User ist NICHT authentifiziert oder Fehler
	 */
    public  boolean authenticate(String username, String password )
    {
	boolean breturn = true;
	boolean bOK = false;
	if ( !username.isEmpty() &&  !password.isEmpty()) 
	    breturn = false;
	
	    bOK =  readUser(username) ;
	    if (bOK)
	    {
	        if ( password.equals(dbUserPW) && username.equals(dbUserName))
	            breturn = true;
	    }
	    
    	 System.out.println(" authenticate =  " + username + ' ' + password + ' ' + breturn);
    	 return breturn;
    }
    
    
    
    private   boolean readUser(String pUserName)
    {
        Boolean bOK =  false ;

        String SQL = "SELECT   `user_id`,  `user_name`,  `pw_clear`  FROM   `user`";
               SQL +=  "WHERE user_name =  "  + (pUserName.trim());        

        ResultSet rSet = DBConnection.executeQuery(SQL);
        if (rSet == null)
            return bOK;

        try
        {
            if (rSet.next())
            {
                dbUserName = rSet.getString("user_name");
                dbUser_ID = rSet.getLong("user_id");
                dbUserPW = rSet.getString("pw_clear");                               
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Der Datensatz konnte nicht gefunden werden");
                dbUser_ID = (long) -1;
            }

            rSet.close();

        }
        catch (Exception ex)
        {
        }
        return bOK;        
    }
    
    
    
}
