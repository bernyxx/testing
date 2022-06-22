package progetto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import progetto.Biglietto;
//import progetto.Tratta;

public class BigliettoTest {

	@Test
	public void test() {
		Tratta tr = new Tratta("Origine", "Destinazione", 12.5, 5);
		Biglietto b = new Biglietto(tr, false, false, 3, 10);
		
		assertEquals(tr, b.getTratta());
		assertEquals(false, b.isInverso());
		assertEquals(false, b.isFirstClass());
		assertEquals(3, b.getNumPersone());
		assertEquals(10, b.getCostoTotale(), 0.0001);
//		assertEquals("Tratta: Origine-Destinazione -- Num. Persone: 3 -- Sistemazione: Economy -- Costo € 10,00", b.toString());
	}
	
	@Test
	public void test2() {
		Tratta tr = new Tratta("Origine", "Destinazione", 12.5, 5);
		Biglietto b = new Biglietto(tr, true, true, 3, 10);
		
		assertEquals(tr, b.getTratta());
		assertEquals(true, b.isInverso());
		assertEquals(true, b.isFirstClass());
		assertEquals(3, b.getNumPersone());
		assertEquals(10, b.getCostoTotale(), 0.0001);
//		assertEquals("Tratta: Destinazione-Origine -- Num. Persone: 3 -- Sistemazione: Prima Classe -- Costo € 10,00", b.toString());
	}

}
