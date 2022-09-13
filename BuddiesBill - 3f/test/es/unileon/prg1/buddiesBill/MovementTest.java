package es.unileon.prg1.buddiesBill;

/**
 * 
 * @author Adriana Alonso Yugueros
 * @author Iván Castro Martínez
 * @author Maday Pablos Yugueros
 * @author Antía Pérez-Gorostiaga González
 *
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MovementTest {
	
	Movement movement;
	
	@Before
	public void setUp() throws EventException{
		movement = new Movement("Regalo", 30.0f, new Buddy("Juan"));
	}

	@Test
	public void testGetName() {
		assertEquals("Regalo", movement.getName());
	}
	
	@Test
	public void testGetCost() {
		assertEquals(30,0, movement.getCost()); 
	}
	
	@Test
	public void testGetBuddy() {
		assertTrue(new Buddy("Juan").equals(movement.getBuddy()));
	}
	
	@Test
	public void testToString() {
		String message = "Regalo - 30.0 paid by Juan";
		assertEquals(message, movement.toString());
	}

}
