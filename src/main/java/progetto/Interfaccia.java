package progetto;

import java.util.Scanner;

/** Classe per creare un'interazione con l'utente che vuole acquistare i biglietti */
public class Interfaccia {
	
	private static int inputInteger(final Scanner input, final int max) {
		
		assert max > 0 : "Valore di max in inputInteger non valido";
		
		int indice;
		
		while (true) {
			final String inputStr = input.next();
			try {
				indice = Integer.parseInt(inputStr);
				
				if(indice <= max && indice >=1) {
					
					return indice;
				}
				
				System.out.println("Tratta non valida. Riprovare.");
			} catch (NumberFormatException e) {
				System.out.println("Numero inserito non corretto");
			}
		}
	}

	private static int inputNumPersone(final Scanner input) {

		int indice;

		while (true) {
			final String inputStr = input.next();
			try {
				indice = Integer.parseInt(inputStr);
				if(indice > 0) {
					return indice;
				}
				
				System.out.println("Ïl numero inserito non è valido!");
				
			} catch (NumberFormatException e) {
				System.out.println("Numero inserito non corretto");
			}
		}
	}
		
	
	/** Acquista un biglietto in modo interattivo 
	 * @param gbiglietti Gestore Biglietti
	 * @param gtratte	Gestore Tratte
	 * @param utente 	Utente che acquista il biglietto
	 */
	public static void acquista(final GestoreBiglietti gbiglietti, final GestoreTratte gtratte, final Utente utente) {

		final Scanner input = new Scanner(System.in);
		
		Tratta tScelta;
		boolean inverso = false;
		boolean primaClasse = false;
		int numPersone = 0;
		
		System.out.println("**********************");
		System.out.println("Acquisto di un nuovo biglietto");
		System.out.println("Selezionare la tratta desiderata");
		
		final Object[] tratte = gtratte.getTratte();
		
		System.out.println();
		for(int i=0; i < tratte.length; i++) {
			final Tratta tra = (Tratta) tratte[i];
			System.out.println((i+1) + ")  " + tra );
		}

		
		final int indice = inputInteger(input, tratte.length);
		
		assert indice > 0;
		
		System.out.println("Tratta numero "+ indice + " selezionata!");
		
		// selezione direzione
		
		System.out.println("Percorso diretto o inverso?");
		
		tScelta = (Tratta) tratte[indice - 1];
		
		System.out.println(String.format("1) %s - %s", tScelta.getSource(), tScelta.getDestination()));
		System.out.println(String.format("2) %s - %s", tScelta.getDestination(), tScelta.getSource()));
		
		final int inversoInt = inputInteger(input, 2);
		
		assert inversoInt > 0;
		
		if (inversoInt == 1) {
			inverso = false;
			System.out.println("Selezionato diretto");

		} else if (inversoInt == 2) {
			inverso = true;
			System.out.println("Selezionato inverso");
		}
		
		// selezione prima classe / economy solo se esiste anche la prima classe
		
		int sceltaTrattamento = 2;
		
		if(tScelta.getPrice()[0] != 0) {
			System.out.println("Scelta sistemazione");
			System.out.println("1) Prima Classe");
			System.out.println("2) Economy");
			
			sceltaTrattamento = inputInteger(input, 2);
		}
		
		assert sceltaTrattamento > 0;
		
		if (sceltaTrattamento == 1) {
			primaClasse = true;
			System.out.println("Selezionato Prima Classe");

		} else if (inversoInt == 2) {
			primaClasse = false;
			System.out.println("Selezionato Economy");
		}
		
		
		// inserimento numero biglietti
		
		System.out.println("Inserire il numero di biglietti");
		
		numPersone = inputNumPersone(input);
		
		assert numPersone > 0;
		
		input.close();
		
		// ora abbiamo tutti i dati necessari per chiamare il metodo
		utente.compraBiglietto(gbiglietti, tScelta, inverso, primaClasse, numPersone);
		
	}
}
