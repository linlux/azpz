package util2;

import java.text.Collator;
import java.util.Locale;

// Sprachabh�ngiges Vergleichen.
// Klasse LocaleString um die Sortierung nach deutschen Sprachregeln durchzuf�hren.
// (Ber�cksichtigung der Umlaute und der s-Ligatur [�]).

// F�r die deutsche Sprache gilt, dass �� zwischen �a� und �b� �quivalent zu �ae� einsortiert wird
// und nicht so, wie Unicode das Zeichen einordnet: hinter dem �z�. �hnliches gilt f�r das �߫. 
// Auch das Spanische hat seine Besonderheiten im Alphabet: Hier gelten das �ch� und das �ll� als einzelner
// Buchstabe, die passend einsortiert werden m�ssen.

// Damit Java f�r alle Landessprachen die String-Vergleiche korrekt durchf�hren kann, bietet die Bibliothek 
// Collator-Klassen. 
public class LocaleString implements Comparable<LocaleString>
{

	private String strValue;
	
	// Collator Klasse f�r den Vergleich von Zeichenketten nach landes�blichen Kriterien.
	private Collator collator;
	
	/*
	 * In Java repr�sentieren Locale-Objekte geografische, politische oder kulturelle Regionen.
	 * Die Sprache und die Region m�ssen getrennt werden, denn nicht immer gibt eine Region oder ein Land die Sprache eindeutig vor.
	 * z. B. (en, GB), (en, AUS), (en, US) oder (de, DE), (de, LU), (de, CH), (de, AT).
	 */
	private Locale loc;
	
	public LocaleString(String strValue)
	{
		this.strValue = strValue;
	}


	@Override
	public int compareTo(LocaleString that)
	{
		
		if (collator == null)
		{
			// Ermittlung der Default-Locale (Lokale.GERMAN)
			loc = Locale.getDefault();
			setLocale(loc);
		}
		
		
		return collator.compare(this.toString(), that.strValue);
	}

	// Wenn equals() nicht sinnvoll �berschrieben wird verh�lt es sich wie ==
	// also werden Referenzen verglichen und keine Werte.
	
	@Override
	public boolean equals(Object that)
	{
		// Wenn auf das gleiche Objekt verwiesen wird
		if (this == that)
			return true;

		// Wenn der Argumenttyp ung�ltig (kein Vergleich von �pfel mit Birnen)
		if (!(that instanceof LocaleString))
			return false;

		return (compareTo((LocaleString)that) == 0);
	}
	
	// Eine Klasse die 'equals() �berschreibt muss auch die Methode
	// hashCode() �berschreiben.
	// Das �berschreiben der Methode 'equals()' bedeuted, dass ein bestimmtes
	// Kriterium
	// f�r den Vergleich des Objekts verwendet wird.
	// Diese Kriterium sollte auch f�r Methode 'hashCode()' verwendet werden.

	// Die Methode hashCode() soll zu jedem Objekt eine m�glichst eindeutige
	// Integerzahl (sowohl positiv als auch negativ)
	// liefern, die das Objekt identifiziert. Die Ganzzahl hei�t Hashcode
	// beziehungsweise Hash-Wert, und hashCode() ist die
	// Implementierung einer Hash-Funktion. N�tig sind Hashcodes, wenn die
	// Objekte in speziellen Datenstrukturen untergebracht werden,
	// die nach dem Hashing-Verfahren arbeiten (Assoziativspeicher HashMap,
	// TreeMap).
	// Datenstrukturen mit Hashing-Algorithmen bieten effizienten Zugriff auf
	// ihre Elemente.
	@Override
	public int hashCode()
	{
		if (toString() == null)
			return 0;

		return toString().hashCode();
	}
	
	

	public void setLocale(Locale loc)
	{
		collator = Collator.getInstance(loc);	
	}
	
	
	@Override
	public String toString()
	{
		return strValue;
	}
	
	
	
	
	
}
