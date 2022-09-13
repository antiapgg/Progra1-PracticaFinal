package es.unileon.prg1.buddiesBill;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EventTest.class, PaymentsTest.class, PaymentTest.class, MovementTest.class, MovementsTest.class, 
	BuddyTest.class,  BuddiesTest.class})
public class AllTests {

}
