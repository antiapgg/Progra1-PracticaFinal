package es.unileon.prg1.buddiesBill;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase dedicada para crear eventos durante la ejecución del programa.
 * @author Adriana Alonso Yugueros
 * @author Iván Castro Martínez
 * @author Maday Pablos Yugueros
 * @author Antía Pérez-Gorostiaga González
 *
 */
public class Event {
	
	private static final Logger logger = LogManager.getLogger(Event.class);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//											DECLARACION DE VARIABLES											//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private String _eventTitle;
	private Buddies _buddies;
	private Movements _movements;



	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//													CONSTRUCTOR													//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Constructor del evento. Comprueba que maxBuddies y maxMovements son numeros positivos. En el caso de no
	 * serlos, se lanza una excepción
	 * @param name Nombre del evento
	 * @param maxBuddies Numero maximo de participantes. En el caso de no ser mayor que 0, se lanza una excepcion
	 * @param maxMovements Numero maximo de movimientos. En el caso de no ser mayor que 0, se lanza una excepcion
	 * @throws EventException en el caso de la entrada no cumplir los requisitos
	 */
	public Event(String name, int maxBuddies, int maxMovements) throws EventException{


		this._eventTitle = name;
		if(maxBuddies > 0) {
			this._buddies = new Buddies(maxBuddies);
		} else {
			throw new EventException("El número de participantes debe de ser un número positivo y mayor que 0");
		}
		if(maxMovements > 0) {
			this._movements = new Movements(maxMovements);
		} else {
			throw new EventException("El número de movimientos debe de ser un número positivo y mayor que 0");
		}
		
		logger.info("Event " + name + " correctly created");
	}



	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//													METODOS														//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Metodo que añade a los movimientos del evento un nuevo movimiento.
	 * @param movement Nuevo movimiento a añadir
	 * @throws EventException En el caso de no poder añadir el movimiento correctamente
	 * 			- La excepcion puede venir de abajo
	 * 			- El movimiento que se intenta añadir no tiene dueño
	 */
	public void add(Movement movement) throws EventException {

		Buddy buddy = _buddies.search(movement.getBuddy().getName());
		if (buddy != null) {
			_movements.add(movement);
			buddy.pay(movement.getCost());
		} else {
			logger.error(movement.getBuddy().getName() + " is not in the party");
			throw new EventException(movement.getBuddy().getName() + " is not in the party");
		}
	}




	/**
	 * Metodo que elimina un movimiento especifico de los movimientos actuales del evento.
	 * @param movement Movimiento a eliminar
	 * @throws EventException En el caso de no eliminar el movimiento correctamente
	 */
	public void remove(Movement movement) throws EventException {

		movement = _movements.remove(movement);
		_buddies.search(movement.getBuddy().getName()).recieve(movement.getCost());
	}



	/**
	 * Metodo que añade a los participantes del evento un nuevo participante.
	 * @param buddy Nuevo participante a añadir
	 * @throws EventException En el caso de no poder añadir el participante correctamente
	 */
	public void add(Buddy buddy) throws EventException {

		_buddies.add(buddy);
	}



	/**
	 * Metodo que elimina un participante especifico de los participantes actuales del evento.
	 * @param buddy Participante a eliminar
	 * @throws EventException En el caso de no poder eliminar el participante correctamente
	 * 			
	 */
	public void remove(Buddy buddy) throws EventException {

		if(_movements.isOwnerFree(buddy)) {
			_buddies.remove(buddy);
		} else {
			logger.error("Remove people error: Buddy " + buddy.getName() + " has paid movements. Remove before the movements");
			throw new EventException("Remove people error: Buddy " + buddy.getName() + " has paid movements. Remove before the movements");
		}
	}



	/**
	 * Metodo que se encarga de calcular las cuentas entre los participantes segun los movimientos que hayan realizado
	 * @return Pagos que tienen que realizar para saldar las cuentas
	 * @throws EventException (NUNCA SE LANZARA)
	 */
	public Payments settleUp() throws EventException {
		
		logger.info("Starting Settle up");

		int size = 0;
		//Calculamos el numero maximo de pagos que habra en el peor caso
		String names[] = _buddies.getNames();
		String namesDebtors[];
		String namesCreditors[];
		for(int i = 1; i < names.length; i++) {
			size += i;
		}

		Payments payments = new Payments(size);
		float average = _movements.getBalance() / names.length;
		Buddies debtors = new Buddies(names.length);
		Buddies creditors = new Buddies(names.length);

		//Creamos las dos listas de Buddies y las rellenamos si deben o no dinero
		for(int i = 0; i < names.length; i++) {
			if(_buddies.search(names[i]).getPaidMoney() > average) {
				creditors.add(_buddies.search(names[i]));
			} else {
				debtors.add(_buddies.search(names[i]));
			}
		}

		//Creamos los pagos
		namesDebtors = debtors.getNames();
		namesCreditors = creditors.getNames();
		int debtorsRemaining = 0;
		int creditorsRemaining = 0;
		//while(debtors.search(namesDebtors[debtorsRemaining]) != null) {
		while(debtorsRemaining < namesDebtors.length & creditorsRemaining < namesCreditors.length) {
			//Calculamos el dinero del pago y actualizamos lo pagado de cada uno
			float money = average - debtors.search(namesDebtors[debtorsRemaining]).getPaidMoney();
			if(money != 0) {
				payments.add(new Payment(debtors.search(namesDebtors[debtorsRemaining]), creditors.search(namesCreditors[creditorsRemaining]), money));
			} 
			
			//Comprobamos si alguno de los dos buddies actuales ha saldado cuentas
			if(debtors.search(namesDebtors[debtorsRemaining]).getPaidMoney() == average) {
				debtorsRemaining++;
			}
			if(creditors.search(namesCreditors[creditorsRemaining]).getPaidMoney() == average) {
				creditorsRemaining++;
			}
		}

		//Comprobamos si quedan pagos entre los acreedores
		creditorsRemaining = 0;
		//while(creditors.search(namesCreditors[creditorsRemaining + 1]) != null) {
		while(creditorsRemaining + 1 < namesCreditors.length) {
			float money = average - creditors.search(namesCreditors[creditorsRemaining]).getPaidMoney();
			if(money != 0) {
				payments.add(new Payment(creditors.search(namesCreditors[creditorsRemaining]), creditors.search(namesCreditors[creditorsRemaining + 1]), money));
			}
			creditorsRemaining++;
		}

		return payments;
	}



	/**
	 * Metodo que devuelve el titulo (nombre) del evento
	 * @return eventTtitle Nombre/titulo del evento
	 */
	public String getEventTitle() {

		return _eventTitle;
	}



	/**
	 * Redifinición del metodo toString. Devolverá la informacion del evento de la siguiente manera
	 * Event: Weekend
	 * People:
	 * Rose : 50.0
	 * Mike : 37.6
	 * Movements:
	 * Gas - 50.0 paid by Rose
	 * Dinner - 24.0 paid by Mike
	 * Toll - 8.6 paid by Mike
	 * Drinks - 5.0 paid by Mike
	 * Balance: 87.6
	 */
	public String toString() {
		
		logger.info("Printing event information");

		StringBuilder salida = new StringBuilder();

		salida.append("Event: " + _eventTitle + "\n");
		salida.append("People:\n" + _buddies.toString());
		salida.append("Movements:\n" + _movements.toString());
		salida.append("Balance: " + _movements.getBalance());

		return salida.toString();
	}

}
