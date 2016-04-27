package util2;

import java.awt.event.ActionListener;

import javax.swing.*;

public class WinUtil
{
	
	
	
	public static enum MenuItemType
	{
		ITEM_PLAIN, ITEM_CHECK, ITEM_RADIO
	}
	

	/**
	 * <li><b><i>createMenu</i></b>
	 * <br>
	 * <br>
	 * public JMenu createMenu(JMenuBar menuBar, String menuText, String menuName, int shortKey)
	 * <br>
	 * <br>
	 * Erstellt einen Men�.
	 * <br>
	 * <br>
	 * @param menuBar
	 * - Die Men�leiste, zu dem dieses Men� geh�rt.
	 * @param menuText
	 * - Der Text des Men�s.
	 * @param menuName
	 * - Optionaler Name des Men�s oder <b>null</b>.
	 * @param shortKey
	 * - Optionales Tastaturk�rzel oder <b>0</b>.
	 * @return
	 * 	Men�.
	 */
	public static JMenu createMenu(JMenuBar menuBar, String menuText, String menuName, int shortKey)
	{
		JMenu menu = null;
		
		// Hinzuf�gen des Men�s
		menu = new JMenu();
		menu.setText(menuText);
		menu.setName(menuName);
		
		// Optionales Tastaturk�rzel hinzuf�gen
		if (shortKey > 0)
			menu.setMnemonic(shortKey);
		
		
		menuBar.add(menu);	

		return menu;
	}
	
	/**
	 * <li><b><i>createSubMenu</i></b>
	 * <br>
	 * <br>
	 * public JMenu createSubMenu(JMenu mainMenu, String menuText, String menuName, int shortKey)
	 * <br>
	 * <br>
	 * Erstellt ein Untermen�.
	 * <br>
	 * <br>
	 * @param mainMenu
	 * - Das Men�, zu dem das Untermen� hinzugef�gt werden soll.
	 * @param menuText
	 * - Der Text des Men�s.
	 * @param menuName
	 * - Optionaler Name des Untermen�s oder <b>null</b>.
	 * @param shortKey
	 * - Optionales Tastaturk�rzel oder <b>0</b>.
	 * @return
	 * - Untermen�.
	 */
	public static JMenu createSubMenu(JMenu mainMenu, String menuText, String menuName, int shortKey)
	{
		JMenu menu = null;

		// Hinzuf�gen des Men�s als Untermen� des Hauptmen�s
		menu = new JMenu();
		menu.setText(menuText);
		menu.setName(menuName);

		// Optionales Tastaturk�rzel hinzuf�gen
		if (shortKey > 0)
			menu.setMnemonic(shortKey);

		mainMenu.add(menu);

		return menu;
	}
	
	
	/**
	 * <li><b><i>createMenuItem</i></b>
	 * <br>
	 * <br>
	 * public JMenuItem createMenuItem(JMenu menu, String miName, MenuItemType miType, ActionListener actionListener,<br>&nbsp String miText, ImageIcon icon, int shortKey, String miToolTip)
	 * <br>
	 * <br>
	 * Erstellt einen Men�eintrag.
	 * <br>
	 * <br>
	 * @param menu
	 * 	- Das Men�, zu dem dieser Men�eintrag geh�rt.
	 * @param miName
	 * - Optionaler Name des Men�eintrags oder <b>null</b>.
	 * @param miType
	 * 	- Der Typ des Men�eintrags <b>MenuItemType</b>.
	 * @param actionListener
	 *  - Der ActionListener, der verwendet werden soll, wenn der Men�eintrag ausgew�hlt wurde oder <b>null</b>.
	 * @param miText
	 * 	- Der Text des Men�eintrags.
	 * @param icon
	 * 	- Symbol, welches links vor dem Text angezeigt werden soll oder <b>null</b>.
	 * @param shortKey
	 * 	- Optionales Tastaturk�rzel oder <b>0</b>.
	 * @param miToolTip
	 * 	- Optionaler Text f�r den Tooltip oder <b>null</b>.
	 * @return
	 * 	  Men�eintrag.
	 */
	public static JMenuItem createMenuItem(JMenu menu, String miName, MenuItemType miType, 
			                               ActionListener actionListener, String miText, ImageIcon icon, int shortKey, String miToolTip) 
	{
		JMenuItem menuItem = new JMenuItem(); 
		
		switch(miType)
		{
		case ITEM_RADIO:
			 menuItem = new JRadioButtonMenuItem();
			 break;
			 
		case ITEM_CHECK:
			menuItem = new JCheckBoxMenuItem();
			break;
		}
		
		// Name des Men�eintrags
		menuItem.setName(miName);
		
		// Menu Text hinzuf�gen
		menuItem.setText(miText);
		
		// Optionales Image hinzuf�gen
		menuItem.setIcon(icon);
		
		// Optionales Tastaturk�rzel hinzuf�gen
		if (shortKey > 0)
			menuItem.setMnemonic(shortKey);

		// Optionalen Toltip hinzuf�gen
		menuItem.setToolTipText(miToolTip);
		
		// ActionListener hinzuf�gen
		menuItem.addActionListener(actionListener);
		
		// Men�eintrag zum Menu hinzuf�gen
		menu.add(menuItem);
		
		// R�ckgabe des Men�eintrags
		return menuItem;
		
	}
	
	
	
}
