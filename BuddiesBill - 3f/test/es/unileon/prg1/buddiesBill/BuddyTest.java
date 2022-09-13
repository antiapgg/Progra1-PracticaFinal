package es.unileon.prg1.buddiesBill;

import static org.junit.Assert.*;
import es.unileon.prg1.buddiesBill.EventException;
import es.unileon.prg1.buddiesBill.Buddy;
import org.junit.Before;
import org.junit.Test;

public class BuddyTest {
	Buddy Rosa;
	
	@Before
		public void setup()throws EventException{
			Rosa = new Buddy("Rosa");
			
		}
		@Test
		public void testGetName() throws EventException{
			assertEquals("Rosa", Rosa.getName());
		}
		
		@Test 
		public void testGetPaidMoney() throws EventException{
			assertEquals(0, Rosa.getPaidMoney(), 0);	
		}
		
		@Test
		public void testPay()throws EventException{
			Rosa.pay(20);
			assertEquals(20, Rosa.getPaidMoney(), 0);
		}
		
		@Test
		public void testReceive() throws EventException{
			Rosa.pay(25);
			Rosa.recieve(15);
			assertEquals(10, Rosa.getPaidMoney(), 0);
		}
		
		@Test
		public void testtoString() throws EventException{
			Rosa.pay(25);
			assertEquals("Rosa : 25.0", Rosa.toString());
		}
		
}		
