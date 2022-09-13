package es.unileon.prg1.buddiesBill;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * Clase dedicada para testear la clase Payments
 * @author Adriana Alonso Yugueros
 * @author Iván Castro Martínez
 * @author Maday Pablos Yugueros
 * @author Antía Pérez-Gorostiaga González
 *
 */
public class PaymentsTest {
	
	Payments payments;
	
	@Before
	public void setUp() {
		payments = new Payments(5);
	}
	
	
	@Test
	public void testAdd() {
		payments.add(new Payment(new Buddy("Susan"), new Buddy("Rose"), 15.5f));
		payments.add(new Payment(new Buddy("Ben"), new Buddy("Rose"), 11.5f));
		payments.add(new Payment(new Buddy("Rose"), new Buddy("Mike"), 0.5f));
	}
	
	
	@Test
	public void testToString() {
		payments.add(new Payment(new Buddy("Susan"), new Buddy("Rose"), 15.5f));
		payments.add(new Payment(new Buddy("Ben"), new Buddy("Rose"), 11.5f));
		payments.add(new Payment(new Buddy("Rose"), new Buddy("Mike"), 0.5f));
		
		String message = "Payment: 15.5 from Susan to Rose\nPayment: 11.5 from Ben to Rose\nPayment: 0.5 from Rose to Mike\n";
		assertEquals(message, payments.toString());
	}

}
