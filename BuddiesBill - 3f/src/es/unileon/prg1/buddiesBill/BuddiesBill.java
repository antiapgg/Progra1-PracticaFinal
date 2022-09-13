package es.unileon.prg1.buddiesBill;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Clase dedicada para iniciar la aplicación..
 * @author Adriana Alonso Yugueros
 * @author Iván Castro Martínez
 * @author Maday Pablos Yugueros
 * @author Antía Pérez-Gorostiaga González
 *
 */
public class BuddiesBill {
	
	private static final Logger logger = LogManager.getLogger(BuddiesBill.class);

	/**
	 * Main de la practica, primer metodo en ejecutarse de la aplicacion. Crea el evento sobre el que se va a trabajar.
	 * A continuacion llama a la interfaz de texto del usuario. Ademas comprueba que tanto el numero de argumentos
	 * como el tipo de estos son correctos
	 * @param args Array de Strings con los datos del evento
	 * 			args[0] = Nombre del evento
	 * 			args[1] = Numero maximo de participantes. Debe de ser un numero positivo mayor que 0
	 * 			args[2] = Numero maximo de movimientos. Debe de ser un numero positivo mayor que 0
	 * Tambien imprime en el LOG las primeras trazas
	 * @throws EventException en el caso de que los datos del evento no sean correctos
	 */
	public static void main(String[] args) {
		
		logger.info("Entering to BuddiesBill aplication");
		
		try {
			if(args.length == 3) {
				String name = args[0];
				int maxBuddies = Integer.parseInt(args[1]);
				int maxMovements = Integer.parseInt(args[2]);
				Event evento = new Event(name, maxBuddies, maxMovements);
				logger.info("Correct creation of the event: " + args[0] + " " + args[1] + " " + args[2]);
				TextUI.start(evento);
			} else {
				throw new EventException("Error con el número de argumentos recibidos.");
			}
		} catch (EventException| NumberFormatException e) {
			System.err.println(e.getMessage() + "\nSintaxis del programa:\nBuddiesBill eventTitle maxBuddies maxMovements");
			logger.fatal("Error creting the event");
			logger.fatal("Exiting of the execution of the aplication");
		}
	}
}
