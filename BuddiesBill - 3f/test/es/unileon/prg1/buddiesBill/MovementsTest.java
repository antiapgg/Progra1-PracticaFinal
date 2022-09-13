package es.unileon.prg1.buddiesBill;

/**
 * Clase dedicada para testear la clase Movements
 * @author Adriana Alonso Yugueros
 * @author Iván Castro Martínez
 * @author Maday Pablos Yugueros
 * @author Antía Pérez-Gorostiaga González
 *
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MovementsTest {

	Movements movements;
	Buddies buddies;
	
	@Before
	public void setUp() throws EventException {
		movements = new Movements(3);
		buddies = new Buddies(2);
		
		buddies.add(new Buddy("Rose"));
		buddies.add(new Buddy("Mike"));
		
		movements.add(new Movement("Dinner", 25.5f, new Buddy("Rose")));
		movements.add(new Movement("Brunch", 25.5f, new Buddy("Rose")));
	}

	@Test
	public void testAddOK() throws EventException{
		movements.add(new Movement("Breakfast", 25.5f, new Buddy("Rose")));
		
		String message = "Dinner - 25.5 paid by Rose\nBrunch - 25.5 paid by Rose\nBreakfast - 25.5 paid by Rose\n";
		assertEquals(message, movements.toString());
	}
	
	@Test(expected = EventException.class)
	public void testNoMoreSpace() throws EventException{
		movements = new Movements(2);
		
		movements.add(new Movement("Dinner", 25.5f, new Buddy("Rose")));
		movements.add(new Movement("Brunch", 25.5f, new Buddy("Rose")));
		movements.add(new Movement("Breakfast", 25.5f, new Buddy("Rose")));
	}
	
	@Test(expected = EventException.class)
	public void testMovementRepeated() throws EventException{
		movements.add(new Movement("Dinner", 25.5f, new Buddy("Rose")));
	}
	
	
	@Test
	public void testRemoveOK() throws EventException {
		movements.remove(new Movement("Dinner", 25.5f, new Buddy("Rose")));
		
		String message = "Brunch - 25.5 paid by Rose\n";
		assertEquals(message, movements.toString());
	}
	
	
	@Test(expected = EventException.class)
	public void testMovementNoExists() throws EventException {
		movements.remove(new Movement("Petrol", 25.5f, new Buddy("Rose")));
	}
	
	
	@Test
	public void testSearchOK() throws EventException {
		assertTrue(new Movement("Dinner", 25.5f, new Buddy("Rose")).equals(movements.search("Dinner")));
	}
	
	
	@Test
	public void testSerachFailed() throws EventException {
		assertEquals(null, movements.search("Petrol"));
	}
	
	
	@Test
	public void testIsOwnerFreeOK() throws EventException {
		assertTrue(movements.isOwnerFree(new Buddy("Mike")));
	}
	

	@Test
	public void testIsOwnerFreeExistsFailed() throws EventException {
		assertFalse(movements.isOwnerFree(new Buddy("Rose")));
	}

	
	
	@Test
	public void testGetBalance() {
		assertEquals(51, movements.getBalance(), 0);
	}
	
	@Test
	public void testToString() {
		assertEquals("Dinner - 25.5 paid by Rose\nBrunch - 25.5 paid by Rose\n", movements.toString());
	}
}
