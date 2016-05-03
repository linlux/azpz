package com.labsch.util;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.labsch.azpz.azpzFrame;

public class MenuHandling
{
    private static final boolean debug = false;

    /**
     * 
     * @author Martin Labsch, 01.05.2016
     * @param menuName
     * @param frameName
     * @return null or the JMenu if exists.
     */
    public static JMenu getAnMenuByNameFromFramesMenuBar(String menuName, String frameName)
    {
        JMenu m = null;
        azpzFrame frame = (azpzFrame) FrameHandling.getAnAzpzFrameByName(frameName);

        if (frame != null)
        {
            JMenuBar mb = frame.getJMenuBar();

            if (mb != null)
            {
                if (mb.getMenuCount() > 0)
                {
                    for (int i = 0; i < mb.getMenuCount(); i++)
                    {
                        if (mb.getMenu(i).getName().equals(menuName))
                        {
                            m = mb.getMenu(i);
                        }

                        if (debug)
                        {
                            System.out.println(mb.getMenu(i));
                        }
                    }
                }
            }
        }

        return m;
    }

    /**
     * 
     * @author Martin Labsch, 01.05.2016
     * @param menuItemName
     * @param frameName
     * @return null or the JMenuItem if exists.
     */
    public static JMenuItem getAnMenuItemByNameFromFramesMenuBar(String menuItemName, String frameName)
    {
        JMenuItem mi = null;
        azpzFrame frame = (azpzFrame) FrameHandling.getAnAzpzFrameByName(frameName);

        if (frame != null)
        {
            JMenuBar mb = frame.getJMenuBar();

            if (mb != null)
            {
                if (mb.getMenuCount() > 0)
                {
                    for (int i = 0; i < mb.getMenuCount(); i++)
                    {
                        JMenu m = mb.getMenu(i);

                        if (m.getItemCount() > 0)
                        {
                            for (int j = 0; j < m.getItemCount(); j++)
                            {
                                if (m.getItem(i).getName().equals(menuItemName))
                                {
                                    mi = m.getItem(i);
                                }

                                if (debug)
                                {
                                    System.out.println(m.getItem(i));
                                }
                            }
                        }
                    }
                }
            }
        }

        return mi;
    }

    void getMenuElementPath()
    {
//        MenuElement elem[] = MenuSelectionManager.defaultManager().set
        
//        MenuSelectionManager.defaultManager().setSelectedPath(arg0);
    }
}
