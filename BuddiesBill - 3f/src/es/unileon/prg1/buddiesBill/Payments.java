package es.unileon.prg1.buddiesBill;

/**
 * Clase que define el agregado de todos los pagos del evento
 * @author Adriana Alonso Yugueros
 * @author Iván Castro Martínez
 * @author Maday Pablos Yugueros
 * @author Antía Pérez-Gorostiaga González
 *
 */
public class Payments {
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//											DECLARACION DE VARIABLES											//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private Payment[] _payments;
	private int _next;
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//													CONSTRUCTOR													//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructor de la clase. Crea el agregado para los pagos del evento
	 * @param size Numero total de todos los pagos del evento 
	 */
	public Payments(int size) {
		
		_payments = new Payment[size];
		_next = 0;
	}
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//													METODOS														//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Metodo que añade un pago a los pagos actuales. 
	 * @param payment Pago a añadir
	 */
	public void add(Payment payment) {
		
		if(_next < _payments.length){
			_payments[_next] = payment;
			_next++;
		}
	}
	
	

	/**
	 * Redefinición del metodo toString. Devolvera la informacion de todos los pagos de la siguiente manera
	 * Payment: 15.5 from Susan to Rose
	 * Payment: 11.5 from Ben to Rose
	 * Payment: 0.5 from Rose to Mike
	 */
	public String toString() {
		
		StringBuilder salida = new StringBuilder();
		
		for(int i = 0; i <  _next; i++) {
			salida.append(_payments[i] + "\n");
		}
		
		return salida.toString();
	}
	
}
