package es.unileon.prg1.buddiesBill;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Clase dedicada para testear la clase Payment
 * @author Adriana Alonso Yugueros
 * @author Iván Castro Martínez
 * @author Maday Pablos Yugueros
 * @author Antía Pérez-Gorostiaga González
 *
 */
public class PaymentTest {

	Payment payment;
	
	@Test
	public void testToString() {
		payment = new Payment(new Buddy("Susan"), new Buddy("Rose"), 15.5f);
		
		String message = "Payment: 15.5 from Susan to Rose";
		assertEquals(message, payment.toString());
	}

}
