package com.labsch.dlg_login;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.labsch.dbUtils.DBConnection;

public class login

{
    private String dbUserName;
    private String dbUserPW;
    private Long dbUser_ID = (long) -1;
    private Boolean bLogin = false;
 //   private DBConnection dbConn;
    private  static Boolean dbConnected =  false;   
   

    public login(String username, String password)
    {
        if (!username.isEmpty() && !password.isEmpty())
        {
            GetDBConnection();
            initializeComponents(username, password);
        }

    }
    /**
     *  Matthias Lüthke
     * @param  
     * @return
     */

    private boolean GetDBConnection( )
    {
        Boolean bOK = false;
        String driverClassName = "com.mysql.jdbc.Driver";

        String server = "localhost";
        String dataBase = "azpz";
        String port = ":3306/";
        String connectionStringBegin = "jdbc:mysql://";

        String userID = "root";
        String passWord = null;      
        

        if (DBConnection.connectToDatabase( driverClassName ,server, dataBase, port, connectionStringBegin, userID, passWord))
        {
            bOK = true;
            dbConnected = true;
        }

        return bOK;
    }

    private boolean  initializeComponents(String username, String password)
    {                
        return  readUser(username);

    }

    /**
     * @using * . authenticate('ML' username, String 'geheim')
     * @param username
     *            Username
     * @param password
     *            Passwort
     * @returnvalue true = User ist authentifiziert false = User ist NICHT authentifiziert oder Fehler
     */
    public boolean authenticate(String username, String password)
    {      
        boolean bOK = true;    

        if     (username == null  || password == null)
            bOK = false;
        
        if (bOK)
        {
            bOK = readUser(username);
            if (bOK)
            {
                if (password.equals(dbUserPW) && username.equals(dbUserName))
                    bOK = true;
            }
        }    
        System.out.println(" authenticate =  " + username + ' ' + password + ' ' + bOK);
        return bOK;
    }

  
    private boolean readUser(String pUserName)
    {
        Boolean bOK = false;          
        
        
        String SQL = "SELECT   `user_id`,  `user_name`,  `pw_clear`  FROM   `user` ";
        SQL += " WHERE user_name =  " +    DBConnection.dbtxt(   (pUserName.trim()) ) ;    

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
                bOK = true;
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
