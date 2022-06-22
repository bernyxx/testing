package progetto;

/** Classe di prova */
public class MainClass {

	/** Classe main */
	public static void main(String[] args) {
		
		final GestoreTratte gtratte = new GestoreTratte();
		
		gtratte.aggiungiTratta(new Tratta("Albino", "Dalmine", 2, 6));
		gtratte.aggiungiTratta(new Tratta("Casnigo", "Dalmine", 3, 8, 4));
		
		gtratte.stampaListaTratte();
		
		final GestoreBiglietti gbiglietti = new GestoreBiglietti(gtratte);
		
		final Utente utente1 = new Utente("gino", "clown");
		
//		utente1.compraBiglietto(gbiglietti, new Tratta("Albino", "Dalmine", 50, 6), false, false, 2);
//		utente1.compraBiglietto(gbiglietti, new Tratta("Casnigo", "Dalmine", 2, 2), false, false, 3);
		
		utente1.compra(gbiglietti, gtratte);
		
		utente1.stampaBiglietti();
		
		System.out.println(gbiglietti.getBiglietti());
		
		
		
		
		
		

	}

}
