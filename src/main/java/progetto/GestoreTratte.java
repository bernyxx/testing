package progetto;

import java.util.ArrayList;

/** Classe per la gestione delle tratte */
public class GestoreTratte {
	
	/** Vettore delle tratte */
	final private /*@ non_null; spec_public; @*/ ArrayList<Tratta> tratte;
	
	/** Costruttore */
	public GestoreTratte() {		
		tratte = new ArrayList<Tratta>();
	}
	
	// da non coprire, serve solo per debugging
	/** Stampa la lista delle tratte (quelle presenti nel vettore "tratte")*/
	public void stampaListaTratte() {
		System.out.println();
		System.out.println();
		System.out.println("--- Lista tratte ---");
		for(final Tratta tr : this.tratte) {
			
			System.out.println(tr);
			System.out.println("-------------------------------------");
		}
		System.out.println("-------------------------------------");
		System.out.println("----- FINE LISTA TRATTE ----");
		System.out.println("-------------------------------------");
	}
	
	
	//@ requires newTratta != null && newTratta.getSource().length() > 0 && newTratta.getDestination().length() > 0 && newTratta.getDistance() > 0;
	//@ requires (\forall int i; i>= 0 && i< newTratta.getPrice().length; newTratta.getPrice()[i] >= 0);
	//@ ensures tratte.size() >= \old(tratte.size());
	
	/** Aggiungi una tratta al vettore tratte 
	 * @param newTratta Nuova tratta da aggiungere 
	*/
	public void aggiungiTratta(final Tratta newTratta) {
		final Tratta trattaInv = new Tratta(newTratta.getDestination(), newTratta.getSource(), 2, 1, 1);
		
		// verifico se esiste già la tratta nel vettore
		if(this.tratte.contains(newTratta) || this.tratte.contains(trattaInv)) {
			System.out.println("La tratta da inserire è già presente");
			return;
		}
		
		this.tratte.add(newTratta);
		System.out.println("Tratta " + newTratta + " inserita correttamente!");
	}
	
	//@ requires tratta != null && tratta.getSource().length() > 0 && tratta.getDestination().length() > 0 && tratta.getDistance() > 0;
	//@ requires (\forall int i; i>= 0 && i< tratta.getPrice().length; tratta.getPrice()[i] >= 0);
	//@ ensures tratte.size() <= \old(tratte.size());
	
	/** Rimuovi una tratta
	 * @param tratta Tratta da rimuovere
	*/
	public void rimuoviTratta(final Tratta tratta) {
		final Tratta trattaInv = new Tratta(tratta.getDestination(), tratta.getSource(), 2, 1, 1);
		if(this.tratte.contains(tratta)) {
			this.tratte.remove(tratta);
			System.out.println("Tratta rimossa con successo!");
		} else if(this.tratte.contains(trattaInv)) {
			this.tratte.remove(trattaInv);
			System.out.println("Tratta inversa rimossa con successo!");
		}else {
			System.out.println("La tratta da rimuovere non è presente!");
		}
		
	}
	
	//@ ensures \result != null;
	/** @return vettore tratte */
	public Object[] getTratte() {
		return this.tratte.toArray();
	}
	
	

}
