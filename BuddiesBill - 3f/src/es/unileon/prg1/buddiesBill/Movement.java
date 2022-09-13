package es.unileon.prg1.buddiesBill;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Movement {
	
	private static final Logger logger = LogManager.getLogger(Movement.class);
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//											DECLARACION DE VARIABLES											//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private String _name;
	private float _cost;
	private Buddy _buddy;
	
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//													CONSTRUCTOR													//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructor de la clase Movement
	 * @param name Nombre del movimiento (UNICO)
	 * @param cost Coste del movimiento
	 * @param buddy Creador y acreedor del movimiento
	 */
	public Movement(String name, float cost, Buddy buddy) {
		
		this._name = name;
		this._cost = cost;
		this._buddy = buddy;
		
		logger.info("Movement " + name + " created");
	}
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//													METODOS														//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Metodo que devuelve el nombre del movimiento
	 * @return _name Nombre del movimiento
	 */
	public String getName() {
		
		return _name;
	}



	/**
	 * Metodo que devuelve el coste del movimiento
	 * @return _cost Coste del movimiento
	 */
	public float getCost() {
		
		return _cost;
	}



	/**
	 * Metodo que devuelve Buddy el creador y acreedor del movimiento
	 * @return _buddy Buddy creador y acreedor del movimento
	 */
	public Buddy getBuddy() {
		
		return _buddy;
	}
	
	
	/**
	 * Redefinicion del metodo equals. Devuelve un boolean tras comparar dos movimientos
	 * @param movement movimiento a comparar con el movimienti que realiza la llamada
	 * @return true en el caso de ser el mismo movimiento (mismo nombre); devolvera false en el caso contrario
	 */
	public boolean equals(Movement movement) {
		
		return this._name.equals(movement.getName());
	}
	
	
	
	/**
	 * Redifinición del metodo toString. Devolverá la informacion del movimiento del evento de la siguiente manera
	 * Gas - 50.0 paid by Rose
	 */
	public String toString() {
		
		StringBuilder salida = new StringBuilder();
		
		salida.append(_name + " - " + _cost + " paid by " + _buddy.getName());
		
		return salida.toString();
	}
}
