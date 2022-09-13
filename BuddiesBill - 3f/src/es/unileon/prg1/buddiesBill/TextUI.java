package es.unileon.prg1.buddiesBill;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase dedicada a la interfaz de texto del usuario
 * @author Adriana Alonso Yugueros
 * @author Iván Castro Martínez
 * @author Maday Pablos Yugueros
 * @author Antía Pérez-Gorostiaga González
 */
public class TextUI {
	
	
	private static final Logger logger = LogManager.getLogger(TextUI.class);

	/**
	 * Metodo encargado de imprimir el menu e interactuar con el usuario. Dependiendo de la opcion seleccionada, se realizara un accion u otra
	 * Este metodo imprime ademas en el LOG lo que va sucediendo durante la ejecucion de la aplicacion
	 * @param event Evento de esa ejecucion
	 * @throws EventException En el caso de que alguna de las acciones a realizar no se realicen correctamente
	 */
	public static void start(Event event) {

		int option;
		System.out.println(event.getEventTitle() + " event");

		do {
			
			logger.info("Printing the menu and waiting for user's choice");

			System.out.println("---\n" + event.toString() + "\n---");
			System.out.println("Select an option:");
			System.out.println("1 - Add buddy\n2 - Remove buddy\n3 - Add movement\n4 - Remove movement\n5 - Show summary\n6 - Settle up\n7 - Exit");

			option = Teclado.readInteger();

			logger.info("User has choosen the option: " + option);
			
			try{

				switch (option) {
					// Añadir Buddy al evento
					case 1: System.out.println("Name of the buddy to add:");
					 String nameBuddyAdd = Teclado.readString(); event.add(new Buddy(nameBuddyAdd)); break;
					// Eliminar Buddy del evento
					case 2: System.out.println("Name of the buddy to remove:");
					String nameBuddyRemove = Teclado.readString(); event.remove(new Buddy(nameBuddyRemove)); break;
					// Añadir movimiento al evento
					case 3: System.out.println("What:"); String nameMovementAdd = Teclado.readString();
					System.out.println("How much:"); float costMovementAdd = Teclado.readFloat();
					System.out.println("Who:"); String buddyAdd = Teclado.readString();
					event.add(new Movement(nameMovementAdd, costMovementAdd, new Buddy(buddyAdd))); break;
					// Eliminar movimiento del evento
					case 4: System.out.println("Name of the movement to remove:"); String nameMovementRemove = Teclado.readString();
					event.remove(new Movement(nameMovementRemove, 0.0f, new Buddy(""))); break;
					// Mostrar informacion del evento
					case 5: System.out.println(event.toString()); break;
					// Echar cuentas entre los participantes del evento
					case 6: System.out.println(event.settleUp()); break;
					// Salir de la aplicacion
					case 7: System.out.println("Bye!"); break;
					// Opcion invalida
					default: System.out.println("Invalid option"); 
					logger.error("User's option is invalid"); break;
				}
			} catch (EventException e) {
				System.err.println(e.getMessage());
				logger.error(e.getMessage());
			}
		} while (option != 7);
		
		logger.info("Exiting of BuddiesBill aplication");
	}

}
