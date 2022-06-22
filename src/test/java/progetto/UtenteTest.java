package progetto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

//import progetto.GestoreBiglietti;
//import progetto.GestoreTratte;
//import progetto.Tratta;
//import progetto.Utente;

public class UtenteTest {

	@Test
	public void test() {
		// inizializzazione
		GestoreTratte gt = new GestoreTratte();
		Tratta tr = new Tratta("Casnigo", "Dalmine", 35, 10, 8);
		Tratta tr2 = new Tratta("Bergamo", "Dalmine", 8, 3);
		gt.aggiungiTratta(tr);
		gt.aggiungiTratta(tr2);
		
		Utente u = new Utente("Pino", "Ballerino");
		assertEquals("Pino Ballerino", u.toString());
		
		GestoreBiglietti gb = new GestoreBiglietti(gt);
		
		// compro 3 biglietti in economy
		assertTrue(u.compraBiglietto(gb, tr, false, false, 3));
		
		// compro 1 biglietti in prima classe
		assertTrue(u.compraBiglietto(gb, tr, false, true, 1));
		
		// compro altri 2 biglietti in prima classe (fallisce perché ne resta 1 disp.)
		assertFalse(u.compraBiglietto(gb, tr, false, true, 2));
		
		
	}

}
