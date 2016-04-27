package util2;

import java.text.Collator;
import java.util.Locale;

// Sprachabhängiges Vergleichen.
// Klasse LocaleString um die Sortierung nach deutschen Sprachregeln durchzuführen.
// (Berücksichtigung der Umlaute und der s-Ligatur [ß]).

// Für die deutsche Sprache gilt, dass »ä« zwischen »a« und »b« äquivalent zu »ae« einsortiert wird
// und nicht so, wie Unicode das Zeichen einordnet: hinter dem »z«. Ähnliches gilt für das »ß«. 
// Auch das Spanische hat seine Besonderheiten im Alphabet: Hier gelten das »ch« und das »ll« als einzelner
// Buchstabe, die passend einsortiert werden müssen.

// Damit Java für alle Landessprachen die String-Vergleiche korrekt durchführen kann, bietet die Bibliothek 
// Collator-Klassen. 
public class LocaleString implements Comparable<LocaleString>
{

	private String strValue;
	
	// Collator Klasse für den Vergleich von Zeichenketten nach landesüblichen Kriterien.
	private Collator collator;
	
	/*
	 * In Java repräsentieren Locale-Objekte geografische, politische oder kulturelle Regionen.
	 * Die Sprache und die Region müssen getrennt werden, denn nicht immer gibt eine Region oder ein Land die Sprache eindeutig vor.
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

	// Wenn equals() nicht sinnvoll überschrieben wird verhält es sich wie ==
	// also werden Referenzen verglichen und keine Werte.
	
	@Override
	public boolean equals(Object that)
	{
		// Wenn auf das gleiche Objekt verwiesen wird
		if (this == that)
			return true;

		// Wenn der Argumenttyp ungültig (kein Vergleich von Äpfel mit Birnen)
		if (!(that instanceof LocaleString))
			return false;

		return (compareTo((LocaleString)that) == 0);
	}
	
	// Eine Klasse die 'equals() überschreibt muss auch die Methode
	// hashCode() überschreiben.
	// Das Überschreiben der Methode 'equals()' bedeuted, dass ein bestimmtes
	// Kriterium
	// für den Vergleich des Objekts verwendet wird.
	// Diese Kriterium sollte auch für Methode 'hashCode()' verwendet werden.

	// Die Methode hashCode() soll zu jedem Objekt eine möglichst eindeutige
	// Integerzahl (sowohl positiv als auch negativ)
	// liefern, die das Objekt identifiziert. Die Ganzzahl heißt Hashcode
	// beziehungsweise Hash-Wert, und hashCode() ist die
	// Implementierung einer Hash-Funktion. Nötig sind Hashcodes, wenn die
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
