package progetto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;



//import progetto.GestoreBiglietti;
//import progetto.GestoreTratte;
//import progetto.Tratta;

public class GestoreBigliettiTest {

	@Test
	public void test() {
		
		
		GestoreTratte gt = new GestoreTratte();
		Tratta tr = new Tratta("Casnigo", "Dalmine", 35, 10, 8);
		Tratta tr2 = new Tratta("Bergamo", "Dalmine", 8, 3);
		gt.aggiungiTratta(tr);
		gt.aggiungiTratta(tr2);
		
		GestoreBiglietti gb = new GestoreBiglietti(gt);
		
		// creo altri due GestoreBiglietti per coprire il loop (riga 14)
		// si ignorano i due warning perché per coprire il ciclo mi basta solo usare utilizzare il costruttore,
		// l'oggetto creato non mi serve
		
		// caso 1 - ciclo eseguito 0 volte
		GestoreTratte gt1 = new GestoreTratte();
		GestoreBiglietti gb1 = new GestoreBiglietti(gt1);
		
		// caso 2 - ciclo eseguito esattamente 1 volta
		GestoreTratte gt2 = new GestoreTratte();
		gt2.aggiungiTratta(new Tratta("ori", "dest", 2, 3));
		GestoreBiglietti gb2 = new GestoreBiglietti(gt2);
		
		
		
		int numFirst = gb.getBiglietti().get(tr).get("Prima Classe");
		
		assertEquals(2, numFirst);
		
		int numEco = gb.getBiglietti().get(tr).get("Economy");
		assertEquals(5, numEco);
		
		// rimuovo 4 posti dall'economy della tratta casnigo - dalmine
		assertTrue(gb.rimuoviPosto(tr, false, false, 4));
		
		// è rimasto 1 posto in economy
		int numPosti = gb.getBiglietti().get(tr).get("Economy");
		assertEquals(1, numPosti);
		
		// sono rimasti inalterati i 2 posti della prima classe
		numPosti = gb.getBiglietti().get(tr).get("Prima Classe");
		assertEquals(2, numPosti);
		
		// non ci sono abbastanza posti
		assertFalse(gb.rimuoviPosto(tr, false, false, 6));
		
		// rimuovo posti dalla prima classe
		assertTrue(gb.rimuoviPosto(tr, false, true, 2));
		
		// rimuovo altri posti dalla prima classe (non ci sono posti disponibili)
		assertFalse(gb.rimuoviPosto(tr, false, true, 2));
		
		// rimuovo posti in prima classe ad una tratta che non li ha
		assertFalse(gb.rimuoviPosto(tr2, true, true, 3));
		
		// rimuovo posti da una tratta non esistente
		assertFalse(gb.rimuoviPosto(new Tratta("a",  "b",  5, 2, 2), false, false, 2));
		
		
	}

}
