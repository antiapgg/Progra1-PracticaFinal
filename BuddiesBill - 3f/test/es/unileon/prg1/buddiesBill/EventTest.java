package es.unileon.prg1.buddiesBill;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * Clase dedicada para testear la clase Event
 * @author Adriana Alonso Yugueros
 * @author Iván Castro Martínez
 * @author Maday Pablos Yugueros
 * @author Antía Pérez-Gorostiaga González
 *
 */
public class EventTest {

	Event event;
	
	@Before
	public void setUp() throws Exception {
		event = new Event("Prueba", 5, 5);

		event.add(new Buddy("Rose"));
		event.add(new Buddy("Mike"));
		event.add(new Buddy("Susan"));
		event.add(new Buddy("Ben"));
		
		event.add(new Movement("Dinner", 24, new Buddy("Mike")));
		event.add(new Movement("Gas", 50, new Buddy("Rose")));
		event.add(new Movement("Toll", 8, new Buddy("Susan")));
		event.add(new Movement("Drinks", 12, new Buddy("Ben")));
	}
	

	@Test(expected = EventException.class)
	public void testEventBuddiesParameter() throws EventException{
		Event eventFail = new Event("Prueba", -2, 5);
	}
	
	

	@Test(expected = EventException.class)
	public void testEventMovementsParameter() throws EventException{
		Event eventFail = new Event("Prueba", 5, 0);
	}
	

	@Test
	public void testAddBuddyOK() throws EventException{
		event.add(new Buddy("Lauren"));
		
		String message = "Event: Prueba\nPeople:\nRose : 50.0\nMike : 24.0\nSusan : 8.0\nBen : 12.0\n" + 
		"Lauren : 0.0\nMovements:\nDinner - 24.0 paid by Mike\nGas - 50.0 paid by Rose\nToll - 8.0 paid by Susan" + 
				"\nDrinks - 12.0 paid by Ben\nBalance: 94.0";
		assertEquals(message, event.toString());
	}

	@Test
	public void testRemoveBuddyOK() throws EventException{
		event.remove(new Movement("Dinner", 0, new Buddy("Mike")));
		event.remove(new Buddy("Mike"));
		
		String message = "Event: Prueba\nPeople:\nRose : 50.0\nSusan : 8.0\nBen : 12.0\n" + 
				"Movements:\nGas - 50.0 paid by Rose\nToll - 8.0 paid by Susan" + 
						"\nDrinks - 12.0 paid by Ben\nBalance: 70.0";
				assertEquals(message, event.toString());
	}
	

	@Test(expected = EventException.class)
	public void testRemoveBuddyHasMovements() throws EventException{
		event.remove(new Buddy("Susan"));
	}
	
	@Test
	public void testAddMovementOK() throws EventException{
		event.add(new Movement("Petrol", 25.0f, new Buddy("Rose")));
		
		String message = "Event: Prueba\nPeople:\nRose : 75.0\nMike : 24.0\nSusan : 8.0\nBen : 12.0\n" + 
		"Movements:\nDinner - 24.0 paid by Mike\nGas - 50.0 paid by Rose\nToll - 8.0 paid by Susan" + 
				"\nDrinks - 12.0 paid by Ben\nPetrol - 25.0 paid by Rose\nBalance: 119.0";
		assertEquals(message, event.toString());
	}
	
	
	@Test(expected = EventException.class)
	public void testAddMovementFailed() throws EventException{
		event.add(new Movement("Petrol", 25, new Buddy("Lauren")));
	}

	@Test
	public void testRemoveMovement() throws EventException{
		event.remove(new Movement("Dinner", 0, new Buddy("Mike")));
		
		String message = "Event: Prueba\nPeople:\nRose : 50.0\nMike : 0.0\nSusan : 8.0\nBen : 12.0\n" + 
		"Movements:\nGas - 50.0 paid by Rose\nToll - 8.0 paid by Susan" + 
				"\nDrinks - 12.0 paid by Ben\nBalance: 70.0";
		assertEquals(message, event.toString());
	}

	@Test
	public void testSettleUp() throws EventException{
		String message = "Payment: 15.5 from Susan to Rose\nPayment: 11.5 from Ben to Rose\nPayment: 0.5 from Rose to Mike\n";
	
		assertEquals(message, event.settleUp().toString());
	}
	
	@Test
	public void testSettleUpSpecialConditions() throws EventException {
		event = new Event("Prueba", 2, 2);
		
		event.add(new Buddy("Mike"));
		event.add(new Buddy("Ben"));
		
		event.add(new Movement("Dinner", 20.0f, new Buddy("Mike")));
		event.add(new Movement("Gas", 30.0f, new Buddy("Ben")));
		
		String message = "Payment: 5.0 from Mike to Ben\n";
		
		assertEquals(message, event.settleUp().toString());
	}
	
	@Test
	public void testGetEventTitle() {
		assertEquals("Prueba", event.getEventTitle());
	}
	
	
	@Test
	public void testToString() {
		String message = "Event: Prueba\nPeople:\nRose : 50.0\nMike : 24.0\nSusan : 8.0\nBen : 12.0\n" + 
		"Movements:\nDinner - 24.0 paid by Mike\nGas - 50.0 paid by Rose\nToll - 8.0 paid by Susan" + 
				"\nDrinks - 12.0 paid by Ben\nBalance: 94.0";
		assertEquals(message, event.toString());	
	}
	
	
	

}
