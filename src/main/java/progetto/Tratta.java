package progetto;

import java.util.Objects;

/** Tratta del treno */
public class Tratta {
	
	//@ public invariant origine.length() > 0;
	//@ public invariant destinazione.length() > 0;
	//@ public invariant distanza > 0;
	//@ public invariant prezzoPrimaClasse >= 0;
	//@ public invariant prezzoEconomy > 0;
	
	/** Tratta: origine*/
	private final /*@ spec_public @*/ String origine;
	
	/** Tratta: destinazione */
	private final /*@ spec_public @*/ String destinazione;
	
	/** Tratta: distanza in km */
	private final /*@ spec_public @*/ double distanza;
	
	/** Tratta: prezzo unitario prima classe */
	private final /*@ spec_public @*/ double prezzoPrimaClasse;
	
	/** Tratta: prezzo unitario classe economy */
	private final /*@ spec_public @*/ double prezzoEconomy;
	
	/** Hashcode */
	private final int hashcode;
	
	/** Costruttore tratta con prima classe 
	 * @param ori	Origine
	 * @param dest	Destinazione
	 * @param dist	Distanza
	 * @param prPrimaClasse	Prezzo Unitario Prima Classe
	 * @param prEconomy		Prezzo Unitario Economy
	 */
	public Tratta(final String ori, final String dest, final double dist, final double prPrimaClasse, final double prEconomy) {
		assert dist > 0;
		assert prPrimaClasse >= 0;
		assert prEconomy > 0;
		
		this.origine = ori;
		this.destinazione = dest;
		this.distanza = dist;
		this.prezzoPrimaClasse = prPrimaClasse;
		this.prezzoEconomy = prEconomy;
		this.hashcode = Objects.hash(this.origine, this.destinazione);
	}
	
	/** (Overloading) Costruttore tratta senza prima classe
	 * @param ori	Origine
	 * @param dest	Destinazione
	 * @param dist	Distanza
	 * @param prEconomy		Prezzo Unitario Economy
	 */
	public Tratta(final String ori, final String dest, final double dist, final double prEconomy) {
		assert dist > 0;
		assert prEconomy >= 0;
		
		this.origine = ori;
		this.destinazione = dest;
		this.distanza = dist;
		this.prezzoPrimaClasse = 0;
		this.prezzoEconomy = prEconomy;
		
		// una tratta è uguale all'altra se ha la stessa origine e la stessa destinazione (non importa il costo unitario e la distanza)
		this.hashcode = Objects.hash(this.origine, this.destinazione);
	}

	//@ ensures \result.equals(origine);
	/** @return origine */
	public /*@ pure @*/ String getSource() {
		return this.origine;
	}

	//@ ensures \result.equals(destinazione);
	/** @return destinazione */
	public /*@ pure @*/ String getDestination() {
		return this.destinazione;
	}
	
	//@ ensures \result == distanza;
	/** @return distanza */
	public /*@ pure @*/ double getDistance() {
		return this.distanza;
	}
	
	
	//@ ensures \result[0] == prezzoPrimaClasse;
	//@ ensures \result[1] == prezzoEconomy;
	/** @return array a 2 celle 
	 *  [0] prezzo prima classe (pari a 0 se non è presente)
	 *  [1] prezzo economy
	 */
	public /*@ pure @*/ double[] getPrice() {
		return new double[] {this.prezzoPrimaClasse, this.prezzoEconomy} ;
	}
	
	//@ ensures \result.getSource() == destinazione;
	//@ ensures \result.getDestination() == origine;
	//@ ensures \result.getDistance() == distanza;
	//@ ensures \result.getPrice()[0] == prezzoPrimaClasse;
	//@ ensures \result.getPrice()[1] == prezzoEconomy;
	/** @return tratta con origine e destinazione invertiti */
	public /*@ pure @*/ Tratta getInverso() {
		return new Tratta(this.destinazione, this.origine, this.distanza, this.prezzoPrimaClasse, this.prezzoEconomy);
	}

	//@ ensures \result.length() > 0;
	/** @return stringa completa di tutte le info */
	public String toStringCompleto() {
		return "Tratta: " + this.origine + " - " + this.destinazione + '\n' + "Distanza: " 
				+ this.distanza + '\n' + "Prezzo Prima Classe: " + this.prezzoPrimaClasse + '\n' + "Prezzo Economy: " + this.prezzoEconomy + '\n';
	}
	
	//@ also
	//@ ensures \result.length() > 0;
	@Override
	public String toString() {
		return this.origine + "-" + this.destinazione;
	}

	// una tratta è uguale all'altra se ha la stessa origine e la stessa destinazione (non importa il prezzo unitario e la distanza)
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) return true;

		if (obj instanceof Tratta) {
			final Tratta tra = (Tratta) obj;
			if (tra.getSource().equals(this.origine) && tra.getDestination().equals(this.destinazione)) {
				return true;
			}
			return false;
		}else {
			return false;
		}
		
	}
	
	@Override
	public int hashCode() {
		return this.hashcode;
	}
}
