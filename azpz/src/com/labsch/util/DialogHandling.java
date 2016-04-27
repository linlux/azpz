package com.labsch.util;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * 
 * @author Martin Labsch, 27.04.2016
 *
 */
public class DialogHandling
{

	// Gibt 'true' zur�ck, wenn das Programm beendet werden soll
	/**
	 * 
	 * @author Martin Labsch, 27.04.2016
	 * @param parentComponent
	 * @return
	 */
	public static boolean queryExit(Component parentComponent)
	{

		boolean retValue = false;

		int optionValue;

		// Benutzerdefinierte Button-Texte
		String[] options =
		{ "Ja", "Nein", "Abbrechen" };

		optionValue = JOptionPane.showOptionDialog(parentComponent, "M�chten Sie das Programm beenden?", "Programm beenden", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

		if (optionValue == JOptionPane.NO_OPTION)
			retValue = false;
		else if (optionValue == JOptionPane.YES_OPTION)
			retValue = true;

		return retValue;
	}
}
