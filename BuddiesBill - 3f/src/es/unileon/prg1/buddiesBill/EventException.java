package es.unileon.prg1.buddiesBill;

/**
 * Clase que define la excepcion usada en toda la aplicacion. Cada vez que se produzca un error, se lanzara
 * esta excepcion
 * @author Adriana Alonso Yugueros
 * @author Iván Castro Martínez
 * @author Maday Pablos Yugueros
 * @author Antía Pérez-Gorostiaga González
 *
 */
@SuppressWarnings("serial")
public class EventException extends Exception {
		
	/**
	 * Constructor de la excepcion
	 * @param message Mensaje que imprimira la expcepcion
	 */
	public EventException(String message){
		super(message);
	}
}