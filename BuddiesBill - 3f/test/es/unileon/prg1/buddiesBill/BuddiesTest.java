package es.unileon.prg1.buddiesBill;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BuddiesTest {
	
	Buddies buddies;
	Movements movements;
	
	@Before
	public void setUp() throws EventException {
		buddies = new Buddies(3);

		buddies.add(new Buddy("Ben"));
	}

	@Test
	public void testAddOK() throws EventException {
		buddies.add(new Buddy("Rose"));
		
		String message = "Ben : 0.0\nRose : 0.0\n";
		assertEquals(message, buddies.toString());
	}
	
	@Test(expected = EventException.class)
	public void testAddNoMoreSpace() throws EventException {
		buddies = new Buddies(0);
		buddies.add(new Buddy("Ben"));
		
	}
	
	@Test(expected = EventException.class)
	public void testAddBuddyRepeated() throws EventException {
		buddies.add(new Buddy("Ben"));
	}

	@Test
	public void testRemoveOK() throws EventException {
		buddies.add(new Buddy("Rose"));
		buddies.remove(new Buddy("Ben"));
		
		String message = "Rose : 0.0\n";
		assertEquals(message, buddies.toString());
	}
	
	@Test
	public void testRemoveOKFull() throws EventException {
		buddies.add(new Buddy("Rose"));
		buddies.add(new Buddy("Kate"));
		buddies.remove(new Buddy("Ben"));
		
		String message = "Rose : 0.0\nKate : 0.0\n";
		assertEquals(message, buddies.toString());
	}
	
	
	@Test(expected = EventException.class)
	public void testRemoveBuddyNoExists() throws EventException {
		buddies.remove(new Buddy("Rose"));
	}

	@Test
	public void testSearchOk() throws EventException {		
		assertTrue(new Buddy("Ben").equals(buddies.search("Ben")));
	}
	
	public void testSearchFailed() throws EventException {
		assertEquals(null, buddies.search("Rose"));
	}
	
	@Test
	public void testToString() throws EventException {
		buddies.add(new Buddy("Rose"));
		buddies.add(new Buddy("Kate"));

		String message = "Ben : 0.0\nRose : 0.0\nKate : 0.0\n";
		assertEquals(message, buddies.toString());
	}

}
