package util2;

public class ListItem
{

	private Object valueMember;
	private Object displayMember;
	
	
	public ListItem(Object valueMember, Object displayMember)
	{
		this.valueMember = valueMember;
		this.displayMember = displayMember;
		
	}


	public Object getValueMember()
	{
		return valueMember;
	}


	public Object getDisplayMember()
	{
		return displayMember;
	}

	
	// Sinnvolles Überschreiben der Methode toString():
	// Die Listbox wird autom. immer versuchen, jeden Eintrag
	// als Zeichenkette anzuzeigen und deshalb auch immer
	// die Methode toString() verwenden.
	// Wenn toString() nicht sinnvoll überschrieben wird,
	// wird nur der Name des Packets, der Name der Klasse und die
	// hexadezimale Adresse des Objekt auf dem Heap zurückgeliefert.
	@Override
	public String toString()
	{
		return getDisplayMember().toString();
	}
	
	
	
	
	
}
