package com.labsch.util;

import java.awt.Component;
import java.awt.Frame;
import javax.swing.JOptionPane;

import com.labsch.azpz.azpzFrame;

/**
 * 
 * @author Martin Labsch, 27.04.2016
 *
 */
public class DialogHandling
{

    // Gibt 'true' zurück, wenn das Programm beendet werden soll
    /**
     * 
     * @author Martin Labsch, 27.04.2016
     * @param comp
     * @return
     */
    public static boolean queryExit(Component comp)
    {

        boolean retValue = false;

        int optionValue;

        // Benutzerdefinierte Button-Texte
        String[] options =
        { "Ja", "Nein", "Abbrechen" };

        optionValue = JOptionPane.showOptionDialog(comp, "Möchten Sie das Programm beenden?", "Programm beenden", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

        if (optionValue == JOptionPane.NO_OPTION)
            retValue = false;
        else if (optionValue == JOptionPane.YES_OPTION)
            retValue = true;

        // storing the actual mainFrame properties
        if (comp.getName().equals("mainFrame"))
        {
            FileHandling.safeActualProperties(comp);
        }
        if (comp.getName().equals("menuItemClose"))
        {
            /*
             * to get the actual properties from the mainFrame
             */
            Frame[] allFrames = azpzFrame.getFrames();

            if (allFrames.length > 0)
            {
                for (int i = 0; i < allFrames.length; i++)
                {
                    if (allFrames[i].getName().equals("mainFrame"))
                    {
                        FileHandling.safeActualProperties(allFrames[i]);
                    }
                }
            }
        }

        return retValue;
    }
}
