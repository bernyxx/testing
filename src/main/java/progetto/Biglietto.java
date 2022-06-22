package progetto;

/**
 * Biglietto acquistato da un utente
 */
public class Biglietto {
	
	//@ public invariant numPersone > 0;
	//@ public invariant costoTotale >= 0;
	
	/** tratta per cui il biglietto è valido */
	final private  Tratta tratta;
	
	/** il percorso è inverso rispetto alla tratta? */
	final private /*@ spec_public @*/ boolean inverso;
	
	/** prima classe (true) o economy (false)? */
	final private  /*@ spec_public @*/ boolean primaClasse;  
	
	/** numero di persone incluso nel biglietto */
	final private /*@ spec_public @*/ int numPersone;  
	
	/** prezzo totale pagato per il biglietto */
	final private /*@ spec_public @*/ double costoTotale;  
	
	//@ requires numPersone > 0;
	//@ requires costo >= 0;
	/** Crea un nuovo biglietto
	 * @param tratta Tratta
	 * @param inverso  Il percorso è inverso rispetto alla tratta?
	 * @param primaClasse Prima classe (true) o economy (false)
	 * @param numPersone Numero persone
	 * @param costo  Costo totale del biglietto
	*/
	public Biglietto(final Tratta tratta, final boolean inverso, final boolean primaClasse, final int numPersone, final double costo) {
		
		
		assert numPersone > 0 : "Numero di persone non valido";
		assert costo > 0 : "Costo non valido";
		
		this.tratta = tratta;
		this.inverso = inverso;
		this.primaClasse = primaClasse;
		this.numPersone = numPersone;
		this.costoTotale = costo;
	}
	
	//@ also
	//@ requires true;
	//@ ensures \result.length() > 0;

	@Override
	public String toString() {
		final String tratta1 = inverso ? tratta.getInverso().toString() : tratta.toString();
		final String sistemazione = primaClasse ? "Prima Classe" : "Economy";
		return String.format("Tratta: %s -- Num. Persone: %o -- Sistemazione: %s -- Costo € %.2f", tratta1,
				this.numPersone, sistemazione, this.costoTotale);
	}

	//@ ensures \result != null;
	/** @return tratta */
	public Tratta getTratta() {
		return this.tratta;
	}

	//@ ensures \result == inverso;
	/** @return Il biglietto è per il percorso inverso? */
	public boolean isInverso() {
		return this.inverso;
	}

	//@ ensures \result == primaClasse;
	/** @return Il biglietto è per una prima classe? */
	public boolean isFirstClass() {
		return this.primaClasse;
	}
	
	//@ ensures \result == numPersone;
	/** @return Numero di persone */
	public int getNumPersone() {
		return this.numPersone;
	}
	
	//@ ensures \result == costoTotale;
	/** @return Costo totale */
	public double getCostoTotale() {
		return this.costoTotale;
	}
	
}
