package es.unileon.prg1.buddiesBill;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase que un participante del evento
 * @author Adriana Alonso Yugueros
 * @author Iván Castro Martínez
 * @author Maday Pablos Yugueros
 * @author Antía Pérez-Gorostiaga González
 *
 */
public class Buddy {
	
	private static final Logger logger = LogManager.getLogger(Buddy.class);
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//											DECLARACION DE VARIABLES											//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private String _name;
	private float _paidMoney;
	
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//													CONSTRUCTOR													//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructor de la clase, crea un participante
	 * @param name Nombre del participante (UNICO)
	 */
	public Buddy (String name){
		
		this._name = name;
		this._paidMoney = 0;
		
		logger.info("Buddy " + name + " created");
	}
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//													METODOS														//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Metodo que devuelve el nombre del participante
	 * @return _name Nombre del participante
	 */
	public String getName (){
		
		return _name;
	}
	
	
	/**
	 * Metodo que devuelve la cantidad pagada por el participante
	 * @return _paidMoney Dinero pagado en el evento por el participante
	 */
	public float getPaidMoney (){
		
		return _paidMoney;
	}
	
	
	/**
	 * Metodo que suma una cantidad de dinero al dinero total de un participante. Usado cuando este
	 * participante crea un movimiento o paga a otro participante
	 * @param money Cantidad de dinero que hay que sumar a al dinero total pagado por el participante
	 */
	public void pay (float money){
		
		_paidMoney += money;
		
		logger.info("Buddy " + _name + " pay " + money);
	}
	
	
	/**
	 * Metodo que resta una cantidad de dinero al dinero total de un participante. Usado cuando este
	 * participante elimina un movimiento o recibe dinero de otro participante
	 * @param money Cantidad de dinero que hay que restar a al dinero total pagado por el participante
	 */
	public void recieve (float money){
		
		_paidMoney -= money;
		logger.info("Buddy " + _name + " recieve " + money);
	}
	
	
	
	
	/**
	 * Redefinicion del metodo equals. Devuelve un boolean tras comparar dos participantes
	 * @param buddy participante a comparar con el participante que realiza la llamada
	 * @return true en el caso de ser el mismo participante (mismo nombre); devolvera false en el caso contrario
	 */
	public boolean equals(Buddy buddy) {
		
		return this._name.equals(buddy.getName());
	}
	
	
	
	
	/**
	 * Redefinición del metodo toString. Devolvera la informacion del participante de la siguiente manera
	 * Rose : 50.0
	 */
	public String toString (){
		
		StringBuilder salida = new StringBuilder();
		
		salida.append(_name + " : " + _paidMoney);
		
		return salida.toString();
	}
}
