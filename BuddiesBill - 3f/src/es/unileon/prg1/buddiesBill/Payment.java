package es.unileon.prg1.buddiesBill;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase que define un pago entre 2 participantes
 * @author Adriana Alonso Yugueros
 * @author Iván Castro Martínez
 * @author Maday Pablos Yugueros
 * @author Antía Pérez-Gorostiaga González
 *
 */
public class Payment {
	
	private static final Logger logger = LogManager.getLogger(Payment.class);
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//											DECLARACION DE VARIABLES											//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private Buddy _debtor, _creditor;
	private float _money;
	
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//													CONSTRUCTOR													//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructor de la clase. Crea un pago entre dos participantes. Ademas realiza los metodos pay y recieve sobre los 
	 * participantes correspondientes
	 * @param debtor Participante deudor, es decir, participante que ha pagado MENOS dinero de que lo que corresponde
	 * @param creditor Participante acreedor, es decir, participante que ha pagado MAS dinero de lo que corresponde
	 * @param money Cantidad de dinero que tiene que pagar el participante deudor al participante acreedor
	 */
	public Payment(Buddy debtor, Buddy creditor, float money) {
		
		this._debtor = debtor;
		this._creditor = creditor;
		this._money = money;
		
		logger.info(debtor.getName() + " pay " + money + " to " + creditor.getName());
		
		this._debtor.pay(this._money);
		this._creditor.recieve(this._money);
	}
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//													METODOS														//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Redefinición del metodo toString. Devolvera la informacion del pago de la siguiente manera
	 * Payment: 15.5 from Susan to Rose
	 */
	public String toString() {
		
		StringBuilder salida = new StringBuilder();
		
		salida.append("Payment: " + _money + " from " + _debtor.getName() + " to " + _creditor.getName());
		
		return salida.toString();
	}
}
