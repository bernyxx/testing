package progetto;

import java.util.HashMap;

/**
 * Classe che gestisce il numero di posti disponibili per ciascuna tratta
 */
public class GestoreBiglietti {
	
	/** hashmap che contiene i posti disponibili per 
	 * ciascuna tratta per prima classe e economy 
	*/
	private final /*@ non_null; spec_public; @*/ HashMap<Tratta, HashMap<String, Integer>>   biglietti;
	
	//@ requires gtratte.tratte.size() > 0;
	//@ ensures biglietti.size() == 2 * gtratte.tratte.size();   
	
	/** Crea un nuovo gestore biglietti dato un GestoreTratte
	 * @param gtratte Gestore Tratte 
	*/
	public GestoreBiglietti(GestoreTratte gtratte) {
		
		// non posso usare il diamond operator, problemi con codecover
		this.biglietti = new HashMap<Tratta, HashMap<String, Integer>>();
		
		for(final Object t : gtratte.getTratte()) {
			final Tratta trattaLoop = (Tratta) t;
			
			final Tratta trInv = trattaLoop.getInverso();
			
			final HashMap<String, Integer> numBiglietti = new HashMap<String, Integer>();
			final HashMap<String, Integer> numBiglietti2 = new HashMap<String, Integer>();
			
			
			if(trattaLoop.getPrice()[0] != 0) {
				numBiglietti.put("Prima Classe", 2);
				numBiglietti2.put("Prima Classe", 2);
			} 
			
			numBiglietti.put("Economy", 5);
			numBiglietti2.put("Economy", 5);
			
			biglietti.put(trattaLoop, numBiglietti);
			biglietti.put(trInv, numBiglietti2);
		}
	
	}
	
	//@ requires tratta != null && tratta.getSource().length() > 0 && tratta.getDestination().length() > 0 && tratta.getDistance() > 0;
	//@ requires (\forall int i; i>= 0 && i< tratta.getPrice().length; tratta.getPrice()[i] >= 0);
	//@ requires primaClasse ==> tratta.getPrice()[0] > 0;
	
	//@ ensures (\result && primaClasse && !inverso) ==> (biglietti.get(tratta).get("Prima Classe") == \old(biglietti.get(tratta).get("Prima Classe")) - numPersone);
	//@ ensures (\result && primaClasse && inverso) ==> (biglietti.get(tratta.getInverso()).get("Prima Classe") == \old(biglietti.get(tratta.getInverso()).get("Prima Classe")) - numPersone);
	//@ ensures (\result && !primaClasse && !inverso) ==> (biglietti.get(tratta).get("Economy") == \old(biglietti.get(tratta).get("Economy")) - numPersone);
	//@ ensures (\result && !primaClasse && inverso) ==> (biglietti.get(tratta.getInverso()).get("Economy") == \old(biglietti.get(tratta.getInverso()).get("Economy")) - numPersone);
	
	//@ ensures !\result ==> (biglietti.get(tratta).get("Prima Classe") == \old(biglietti.get(tratta).get("Prima Classe")));
	//@ ensures !\result ==> (biglietti.get(tratta).get("Economy") == \old(biglietti.get(tratta).get("Economy")));
	
	/** Rimuovi un posto
	 * @param tratta Tratta
	 * @param inverso Percorso diretto (false) o inverso (true)
	 * @param primaClasse Economy (false) o Prima Classe (true)
	 * @param numPersone Numero di posti da rimuovere
	 */
	public boolean rimuoviPosto(final Tratta tratta, final boolean inverso, final boolean primaClasse, final int numPersone) {
		
		assert numPersone > 0 : "numPersone non valido";
		
		Tratta tratta1;
		if(inverso) {
			tratta1 = tratta.getInverso();
		} else {
			tratta1 = tratta;
		}
		
		if(biglietti.containsKey(tratta1)) {
			final HashMap<String, Integer> map = biglietti.get(tratta1);
			// System.out.println(map);
			// System.out.println("Tolgo dai posti disponibili");
			
			if(primaClasse && !map.containsKey("Prima Classe")) {
				System.out.println("Per la tratta scelta non esiste la Prima Classe");
				return false;
			}
			
			if(primaClasse &&  map.get("Prima Classe") >= numPersone) {
				map.put("Prima Classe", map.get("Prima Classe") - numPersone);
				biglietti.put(tratta1, map);
				System.out.println("Rimossi " + numPersone + " dalla prima classe");
				return true;
			} else if (!primaClasse && map.get("Economy") >= numPersone) {
				map.put("Economy", map.get("Economy") - numPersone);
				biglietti.put(tratta1, map);
				System.out.println("Rimossi " + numPersone + " dall'economy");
				return true;
			} else {
				System.out.println("Non ci sono posti disponibili");
				return false;
			}
			
		} else {
			System.out.println("Tratta non trovata!");
			return false;
		}
		
	}
	
	//@ ensures \result != null;
	/** Ottieni l'hashmap che contiene i posti disponibili 
	 * per ciascuna tratta per prima classe e economy */
	public /*@ pure @*/HashMap<Tratta, HashMap<String, Integer>> getBiglietti(){
		return this.biglietti;
	}

}
