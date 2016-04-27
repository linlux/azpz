package com.labsch.azpz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

/**
 * 
 * 26.04.2016
 * 
 * @author Martin Labsch
 *
 */
@SuppressWarnings("serial")
public class azpzFrame extends JFrame implements ActionListener
{

	/**
	 * 26.04.2016
	 * 
	 * @author Martin Labsch
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
			else
			{
				System.out.println(mi.getName());
			}

		}

	}

}
