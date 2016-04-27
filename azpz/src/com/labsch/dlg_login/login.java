package com.labsch.dlg_login;

public class login

{   
   //
 
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
    public static boolean authenticate(String username, String password)
    {
	boolean breturn = false;
	if (username.equals("ML") && password.equals("geheim")) 
	    breturn = true;
	
	 return breturn;
    }
}
