package com.labsch.util;

import java.awt.Component;
import java.awt.Frame;
import javax.swing.JButton;
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
        /*
         * to get the actual properties and listeners from the mainFrame
         */
        Frame[] allFrames = azpzFrame.getFrames();
        Frame mainFrame = null;

        if (allFrames.length > 0)
        {
            for (int i = 0; i < allFrames.length; i++)
            {
                if (allFrames[i].getName().equals("mainFrame"))
                {
                    mainFrame = allFrames[i];
                }
            }
        }

        int optionValue;

        // Benutzerdefinierte Button-Texte, Mnemonic und ActionListener
        JButton b1 = new JButton("Ja");
        JButton b2 = new JButton("Nein");
        JButton b3 = new JButton("Abbrechen");
        b1.setName("appCloseOptionYes");
        b2.setName("appCloseOptionNo");
        b3.setName("appCloseOptionCancel");
        b1.setMnemonic('J');
        b2.setMnemonic('N');
        b3.setMnemonic('A');
        if (mainFrame != null)
        {
            b1.addActionListener((azpzFrame) mainFrame);
            b2.addActionListener((azpzFrame) mainFrame);
            b3.addActionListener((azpzFrame) mainFrame);
        }
        JButton[] options =
        { b1, b2, b3 };

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
            if(mainFrame != null){
                FileHandling.safeActualProperties(mainFrame);
            }
        }

        return retValue;
    }
}
