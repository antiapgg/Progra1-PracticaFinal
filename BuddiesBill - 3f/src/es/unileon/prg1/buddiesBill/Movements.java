package es.unileon.prg1.buddiesBill;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase agregada para Movement
 * @author Adriana Alonso Yugueros
 * @author Iván Castro Martínez
 * @author Maday Pablos Yugueros
 * @author Antía Pérez-Gorostiaga González
 *
 */
public class Movements {
	
	private static final Logger logger = LogManager.getLogger(Movements.class);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//											DECLARACION DE VARIABLES											//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private int _next;
	private Movement[] _movements;



	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//													CONSTRUCTOR													//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Constructor de la clase
	 * @param size tamaño maximo de movimientos del evento
	 */
	public Movements(int size) {

		this._next = 0;
		this._movements = new Movement[size];
	}



	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//													METODOS														//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Metodo que añade un movimiento a los movimientos actuales del evento
	 * @param movement movimiento a añadir
	 * @throws EventExcepcion en el caso de no añadir correctamente el movimiento. Esto sucede cuando
	 * 			- No queda espacio sufiente
	 * 			- El movimiento ya existe (Mismo nombre)
	 */
	public void add (Movement movement) throws EventException{

		// Comprobamos si queda espacio
		if(_next != _movements.length) {
			// Comprobamos que el movimiento no existe
			if(this.indexOf(movement) == -1) {
				// Comprobamos que el dueño esta en el evento
				_movements[_next] = movement;
				_next++;
				logger.info("Movement " + movement.getName() + " correctly added");
			} else {
				logger.error("Add payment error: Payment " + movement.getName() + " already included");
				throw new EventException("Add payment error: Payment " + movement.getName() + " already included");
			}
		} else {
			logger.error("Movements Error: No more room left");
			throw new EventException("Movements Error: No more room left");
		}
	}



	/**
	 * Metodo que elimina un movimiento de los movimientos actuales del evento. Ordena
	 *  el array de movimientos despues de haberlo eliminado
	 * @param movement movimiento a eliminar
	 * @throws EventExcepcion en el caso de no eliminar correctamente el movimiento. Esto sucede cuando
	 * 			- No existe el movimiento a eliminar
	 */
	public Movement remove(Movement movement) throws EventException {

		int index = this.indexOf(movement);
		Movement movementErase;
		// Comprobamos que el movimiento a eliminar existe
		if(index != -1) {
			movementErase = _movements[index];
			_movements[index] = null;
			index++;
			while(index < _next){
				_movements[index - 1] = _movements[index];
				_movements[index] = null;
				index++;
			}
			_next--;
			logger.info("Movement " + movement.getName() + " correctly removed");
			return movementErase;
		} else {
			logger.error("Movement " + movement.getName() + " not included");
			throw new EventException("Movement " + movement.getName() + " not included");
		}
	}



	/**
	 * Metodo indexOf, devuelve la posicion de un movimiento en el caso de existir
	 * Las posiciones devueltas van del 0 en adelante, es decir, como en las posiciones
	 * de los array
	 * @param movement movimiento buscado
	 * @return indexOf posicion del movimiento buscado. En caso de no existir devuelve -1
	 */
	private int indexOf(Movement movement) {

		boolean isContained = false;
		int index = 0;
		//Recorre el array mientras no haya nulls o no se haya encontrado el mismo Movement
		while((isContained == false) & (index < _next)) {
			if (_movements[index].equals(movement)){
				isContained = true;
			} else {
				index++;
			}
		}

		if(index != _next) {
			return index;
		} else {
			return -1;
		}
	}
	
	
	/**
	 * Metodo que devuelve un movimiento pedido a traves de su nombre
	 * @param name nombre delo movimiento
	 * @return movimiento pedido. En caso de no existir devuelve null
	 */
	public Movement search(String name) {

		int index = indexOf(new Movement(name, 0, new Buddy("")));
		if(index != -1) {
			return _movements[index];
		} else {
			return null;
		}
	}



	/**
	 * Metodo que comprueba si existen movimientos hechos por un participante especifico
	 * @param buddy participante sobre el que determinar si tiene movimientos a su nombre
	 * @return true si no tiene movimientos a su nombre; en el caso contrario se devueve false
	 */
	public boolean isOwnerFree(Buddy buddy) {

		boolean ownerFree = true;
		int i = 0;
		while((i < _next) & (ownerFree == true)) {
			if(_movements[i].getBuddy().getName().equals(buddy.getName())) {
				ownerFree = false;
			}
			i++;
		}

		return ownerFree;
	}




	/**
	 * Metodo que devuelve el dinero total de todos los movimientos del evento
	 * @return balance, dinero total del evento
	 */
	public float getBalance() {

		float balance = 0;
		for(int i = 0; i < _next; i++) {
			balance += _movements[i].getCost();
		}

		return balance;
	}



	/**
	 * Redifinición del metodo toString. Devolverá la informacion de los movimientos del evento de la siguiente manera
	 * Gas - 50.0 paid by Rose
	 * Dinner - 24.0 paid by Mike
	 * Toll - 8.6 paid by Mike
	 * Drinks - 5.0 paid by Mike
	 */
	public String toString() {

		StringBuilder salida = new StringBuilder();

		for(int i = 0; i < _next; i++) {
			salida.append(this._movements[i].toString() + "\n");
		}

		return salida.toString();
	}
}

