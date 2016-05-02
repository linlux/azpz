package com.labsch.util;

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

    /**
     * @author Martin Labsch, 27.04.2016
     */
    public static void showExitConfirmationDialog()
    {

        /*
         * get the listeners from the mainFrame
         */
        azpzFrame mainFrame = null;

        mainFrame = (azpzFrame) FrameHandling.getAnAzpzFrameByName("mainFrame");

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
            b1.addActionListener(mainFrame);
            b2.addActionListener(mainFrame);
            b3.addActionListener(mainFrame);
        }
        JButton[] options =
        { b1, b2, b3 };

        /**
         * @author Martin Labsch, 29.04.2016
         * Hiernach geht's NICHT weiter, wenn 'Ja' gewaehlt wurde. Der Grund ist mir nicht bekannt.
         * Wurde etwas anderes gewaehlt, werden auch die Anweisungen darunter (falls vorhanden) ausgefuehrt.
         */
        JOptionPane.showOptionDialog(mainFrame, "Möchten Sie das Programm beenden?", "Programm beenden", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
    }
}
