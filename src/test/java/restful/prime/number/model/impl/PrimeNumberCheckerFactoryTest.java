package restful.prime.number.model.impl;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import restful.prime.number.model.impl.PrimeNumberCheckerFactory.CheckingStrategy;

public class PrimeNumberCheckerFactoryTest {

	private static PrimeNumberCheckerFactory factory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = new PrimeNumberCheckerFactory();
	}

	@Test
	public void testDefaultStrategy() {
		assertEquals(CheckingStrategy.STREAM, factory.getCheckingStrategy());
	}

	@Test
	public void testSetProperties() {
		final String oldValue = System.getProperty(PrimeNumberCheckerFactory.CHECKING_STRATEGY_PROPERTY_NAME);

		System.setProperty(PrimeNumberCheckerFactory.CHECKING_STRATEGY_PROPERTY_NAME, "STREAM");
		assertEquals(CheckingStrategy.STREAM, factory.getCheckingStrategy());
		
		System.setProperty(PrimeNumberCheckerFactory.CHECKING_STRATEGY_PROPERTY_NAME, "ITERATIVE");
		assertEquals(CheckingStrategy.ITERATIVE, factory.getCheckingStrategy());

		TestUtils.restoreSystemProperty(PrimeNumberCheckerFactory.CHECKING_STRATEGY_PROPERTY_NAME, oldValue);
	}

	@Test
	public void testSetNotRecognisedPropertyValue() {
		final String oldValue = System.getProperty(PrimeNumberCheckerFactory.CHECKING_STRATEGY_PROPERTY_NAME);
		
		System.setProperty(PrimeNumberCheckerFactory.CHECKING_STRATEGY_PROPERTY_NAME, "abcd");
		assertEquals(CheckingStrategy.STREAM, factory.getCheckingStrategy());

		TestUtils.restoreSystemProperty(PrimeNumberCheckerFactory.CHECKING_STRATEGY_PROPERTY_NAME, oldValue);
	}

	@Test
	public void testCreatePrimeNumberChecker() {
		assertTrue(factory.createPrimeNumberChecker() instanceof StreamPrimeNumberChecker);
		
		final String oldValue = System.getProperty(PrimeNumberCheckerFactory.CHECKING_STRATEGY_PROPERTY_NAME);
		System.setProperty(PrimeNumberCheckerFactory.CHECKING_STRATEGY_PROPERTY_NAME, "STREAM");
		assertTrue(factory.createPrimeNumberChecker() instanceof StreamPrimeNumberChecker);
		
		System.setProperty(PrimeNumberCheckerFactory.CHECKING_STRATEGY_PROPERTY_NAME, "ITERATIVE");
		assertTrue(factory.createPrimeNumberChecker() instanceof IterativePrimeNumberChecker);
		
		TestUtils.restoreSystemProperty(PrimeNumberCheckerFactory.CHECKING_STRATEGY_PROPERTY_NAME, oldValue);
	}
}
