package es.unileon.prg1.buddiesBill;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase agregada para Buddy
 * @author Adriana Alonso Yugueros
 * @author Iván Castro Martínez
 * @author Maday Pablos Yugueros
 * @author Antía Pérez-Gorostiaga González
 *
 */
public class Buddies {
	
	private static final Logger logger = LogManager.getLogger(Buddies.class);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//											DECLARACION DE VARIABLES											//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private int _next;
	private Buddy[] _buddies;



	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//													CONSTRUCTOR													//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Constructor de la clase
	 * @param size tamaño maximo de participantes en el evento
	 */
	public Buddies(int size) {

		this._next = 0;
		this._buddies = new Buddy[size];
	}



	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//													METODOS														//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Metodo que añade un participante a los participantes actuales del evento
	 * @param buddy participante a añadir
	 * @throws EventExcepcion en el caso de no añadir correctamente el participante. Esto sucede cuando
	 * 			- No queda espacio sufiente
	 * 			- El participante ya existe (Mismo nombre)
	 */
	public void add(Buddy buddy) throws EventException {

		// Comprobamos si queda espacio
		if (_next != _buddies.length){
			// Comprobamos que el buddy no existe
			if(this.indexOf(buddy) == -1) {
				_buddies[_next] = buddy;
				_next++;
				logger.info("Buddy " + buddy.getName() + " correctly created");
			} else {
				logger.error("Add people error: Buddy " + buddy.getName() + " already in the party");
				throw new EventException("Add people error: Buddy " + buddy.getName() + " already in the party");
			}	
		} else {
			logger.error("People Error: No more room left");
			throw new EventException("People Error: No more room left");
		}
	}


	/**
	 * Metodo que elimina un participante de los participantes actuales del evento. Ordena
	 *  el array de participantes despues de haberlo eliminado
	 * @param buddy participante a eliminar
	 * @throws EventExcepcion en el caso de no eliminar correctamente el participante. Esto sucede cuando
	 * 			- No existe el participante a eliminar
	 * 			- El participante a eliminar tiene movimientos a su nombre
	 */
	public void remove(Buddy buddy) throws EventException {

		int index = this.indexOf(buddy);
		// Comprobamos que el participante exixte
		if(index != -1) {
			// Comprobamos que el participante no tiene ningun movimiento
			_buddies[index] = null;
			index++;
			while(index < _next){
				_buddies[index - 1] = _buddies[index];
				_buddies[index] = null;
				index++;
			}
			_next--;
			logger.info("Buddy " + buddy.getName() + " correctly removed");
		} else {
			logger.error("Remove people error: Buddy " + buddy.getName() + " is not in the party");
			throw new EventException("Remove people error: Buddy " + buddy.getName() + " is not in the party");
		}
	}


	/**
	 * Metodo indexOf, devuelve la posicion de un participante en el caso de existir. 
	 * Las posiciones devueltas van del 0 en adelante, es decir, como en las posiciones
	 * de los array
	 * @param buddy participante buscado
	 * @return indexOf posicion del participante buscado. En caso de no existir devuelve -1
	 */
	private int indexOf(Buddy buddy) {

		boolean isContained = false;
		int index = 0;
		//Recorre el array mientras no haya nulls o no se haya encontrado el mismo Buddy
		while((isContained == false) & (index < _next)) {
			if (_buddies[index].equals(buddy)){
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
	 * Metodo que devuelve todos los nombres en orden de los participantes actuales
	 * @return todos los nombres de los participantes
	 */
	public String[] getNames() {
		
		String[] names = new String[_next];
		for(int i = 0; i < _next; i++) {
			names[i] = _buddies[i].getName();
		}
		
		return names;
	}



	/**
	 * Metodo que devuelve un participante pedido a traves de su nombre
	 * @param name nombre del participante
	 * @return participante pedido. En caso de no existir devuelve null
	 */
	public Buddy search(String name) {

		int index = indexOf(new Buddy(name));
		if(index != -1) {
			return _buddies[index];
		} else {
			return null;
		}
	}



	/**
	 * Redifinición del metodo toString. Devolverá la informacion de los participante del evento de la siguiente manera
	 * Rose : 50.0
	 * Mike : 24.0
	 * Susan : 8.0
	 * Ben : 12.0
	 */
	public String toString() {

		StringBuilder salida = new StringBuilder();

		for(int i = 0; i < _next; i++) {
			salida.append(this._buddies[i].toString() + "\n");
		}

		return salida.toString();
	}
}
