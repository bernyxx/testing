package progetto; 

import java.util.ArrayList;

/** Classe che rappresenta un utente*/
public class Utente {
	
	//@ public invariant nome.length() > 0;
	//@ public invariant cognome.length() > 0;

	/** Nome dell'utente */
	private final /*@ spec_public @*/ String nome;
	
	/** cognome dell'utente */
	private final /*@ spec_public @*/ String cognome;
	
	/** Lista dei biglietti acquistati dall'utente */
	private final /*@ spec_public; non_null; @*/ ArrayList<Biglietto> listaBiglietti;
	
	/** Costruttore di un utente
	 * @param nome Nome dell'utente
	 * @param cognome Cognome dell'utente
	 */
	public Utente(final String nome, final String cognome) {
		this.nome = nome;
		this.cognome = cognome;
		this.listaBiglietti = new ArrayList<Biglietto>();
	}
	
	//@ also
	//@ ensures \result.length() > 0;
	@Override
	public String toString() {
		return this.nome + " " + this.cognome;
	}
	
	// da non coprire, solo per debugging
	/** Stampa la lista dei biglietti posseduti dall'utente */
	public void stampaBiglietti() {
		System.out.println("--- Lista biglietti di " + this + "---");
		for(final Biglietto b : this.listaBiglietti) {
			
			System.out.println(b);
			System.out.println("-------------------------------------");
		}
		System.out.println("----------------------------------------------------------");
		System.out.println("----------------------------------------------------------");
	}
	
	//@ requires gbiglietti.getBiglietti().size() > 0;
	//@ requires tratta != null && tratta.getSource().length() > 0 && tratta.getDestination().length() > 0 && tratta.getDistance() > 0;
	//@ requires (\forall int i; i>= 0 && i< tratta.getPrice().length; tratta.getPrice()[i] >= 0);
	//@ requires numPersone > 0;
	//@ ensures \result ==> (listaBiglietti.size() == \old(listaBiglietti.size()) + 1);
	//@ ensures !\result ==> (listaBiglietti.size() == \old(listaBiglietti.size()));
	/**
	 * Acquista un nuovo biglietto
	 * @param gbiglietti Gestore Biglietti che registra i posti che vogliamo acquistare
	 * @param tratta	Tratta
	 * @param inverso	Il percorso è inverso rispetto alla tratta?
	 * @param primaClasse  Prima classe (true) o economy (false)
	 * @param numPersone  Numero persone
	 * @return
	 */
	public boolean compraBiglietto(final GestoreBiglietti gbiglietti, final Tratta tratta, final boolean inverso, final boolean primaClasse, final int numPersone) {
		
		assert numPersone > 0;
		
		final boolean success = gbiglietti.rimuoviPosto(tratta, inverso, primaClasse, numPersone);
		
		if(success) {
			final double costo = primaClasse ? tratta.getPrice()[0] * numPersone : tratta.getPrice()[1] * numPersone;
			listaBiglietti.add(new Biglietto(tratta, inverso, primaClasse, numPersone, costo));
		} else {
			System.out.println("Qualcosa è andato storto");
		}
		
		return success;
		
	}
	
	// versione interattiva che inserisce i parametri per comprabiglietto
	// da non coprire
	/** Versione interattiva per l'acquisto di biglietti */
	public void compra(final GestoreBiglietti gbiglietti, final GestoreTratte gtratte) {
		Interfaccia.acquista(gbiglietti, gtratte, this);
	}
	
}
