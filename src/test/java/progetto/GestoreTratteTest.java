package progetto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


//import progetto.GestoreTratte;
//import progetto.Tratta;

public class GestoreTratteTest {

	@Test
	public void test() {
		GestoreTratte gt = new GestoreTratte();
		
		assertEquals(0, gt.getTratte().length);
		
		gt.aggiungiTratta(new Tratta("Ori", "Dest", 8, 8, 6));
		gt.aggiungiTratta(new Tratta("Bergamo", "Dalmine", 8, 8, 6));
		
		assertEquals(2, gt.getTratte().length);
		
		Tratta t = (Tratta) gt.getTratte()[0];
		assertEquals(new Tratta("Ori", "Dest", 8, 8, 6), t );
		
		// aggiungo una tratta già esistente
		gt.aggiungiTratta(new Tratta("Ori", "Dest", 3, 2));
		
		// verifico che la tratta non sia stata aggiunta
		assertEquals(2, gt.getTratte().length);
		
		// stessa cosa ma con origine e destinazione invertiti
		
		gt.aggiungiTratta(new Tratta("Dest", "Ori", 2, 1));
		
		// verifico che la tratta non sia stata aggiunta
		assertEquals(2, gt.getTratte().length);
		
		
		
		// provo a rimuovere uan tratta non esistente
		Tratta trNE = new Tratta("non", "esiste", 2, 2);
		gt.rimuoviTratta(trNE);
		
		
		// verifico rimozione
		gt.rimuoviTratta(new Tratta("Ori", "Dest", 8, 8, 6));
		assertEquals(1, gt.getTratte().length);
		
		// rimuovo tratta data l'inversa
		gt.rimuoviTratta(new Tratta("Dalmine", "Bergamo", 8, 8, 6));
		
		
		
		
	}

}
