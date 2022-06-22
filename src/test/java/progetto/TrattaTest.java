package progetto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

//import progetto.Tratta;
//import progetto.Utente;

public class TrattaTest {

	@Test
	public void test() {
		// test costruttore con prezzo prima classe ed economy
		Tratta tr = new Tratta("Ori", "Dest", 9.5, 12, 8);
		
		assertEquals("Ori", tr.getSource());
		assertEquals("Dest", tr.getDestination());
		assertEquals(9.5, tr.getDistance(), 0.001);
		assertEquals(12.0, tr.getPrice()[0], 0.001);
		assertEquals(8.0, tr.getPrice()[1], 0.001);
		assertEquals(new Tratta("Dest", "Ori", 9.5, 12, 8), tr.getInverso());
		assertEquals("Ori-Dest", tr.toString());
		assertEquals("Tratta: Ori - Dest\nDistanza: 9.5\nPrezzo Prima Classe: 12.0\nPrezzo Economy: 8.0\n", tr.toStringCompleto());
	}
	
	@Test
	public void test2() {
		// test costruttore senza prima classe
		Tratta tr = new Tratta("Ori", "Dest", 9.5, 8);
		
		assertEquals("Ori", tr.getSource());
		assertEquals("Dest", tr.getDestination());
		assertEquals(9.5, tr.getDistance(), 0.001);
		assertEquals(0, tr.getPrice()[0], 0.001);
		assertEquals(8, tr.getPrice()[1], 0.001);
		assertEquals(new Tratta("Dest", "Ori", 9.5, 8), tr.getInverso());
		assertEquals("Ori-Dest", tr.toString());
		assertEquals("Tratta: Ori - Dest\nDistanza: 9.5\nPrezzo Prima Classe: 0.0\nPrezzo Economy: 8.0\n", tr.toStringCompleto());
	}
	
	@Test
	public void test3() {
		// test di equals (branch mancanti)
		Utente u = new Utente("gino", "ginetti");
		Tratta t = new Tratta("ori", "dest", 2, 2);
		
		// equals con un oggetto non tratta
		assertFalse(t.equals(u));
		
		// equals con origine corretta ma destinazione errata
		Tratta tr = new Tratta("ori", "dest_err", 2, 2);
		assertFalse(t.equals(tr));
		
		
	}

}
