package restful.prime.number.model.impl;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import restful.prime.number.model.impl.PrimeNumberGeneratorFactory.GeneratingStrategy;


public class PrimeNumberGeneratorFactoryTest {

	private static PrimeNumberGeneratorFactory factory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = new PrimeNumberGeneratorFactory();
	}

	@Test
	public void testDefaultStrategy() {
		assertEquals(GeneratingStrategy.STREAM, factory.getGeneratingStrategy());
	}

	@Test
	public void testSetProperties() {
		final String oldValue = System.getProperty(PrimeNumberGeneratorFactory.GENERATING_STRATEGY_PROPERTY_NAME);

		System.setProperty(PrimeNumberGeneratorFactory.GENERATING_STRATEGY_PROPERTY_NAME, "STREAM");
		assertEquals(GeneratingStrategy.STREAM, factory.getGeneratingStrategy());
		
		System.setProperty(PrimeNumberGeneratorFactory.GENERATING_STRATEGY_PROPERTY_NAME, "PARALLEL_STREAM");
		assertEquals(GeneratingStrategy.PARALLEL_STREAM, factory.getGeneratingStrategy());

		TestUtils.restoreSystemProperty(PrimeNumberGeneratorFactory.GENERATING_STRATEGY_PROPERTY_NAME, oldValue);
	}

	@Test
	public void testSetNotRecognisedPropertyValue() {
		final String oldValue = System.getProperty(PrimeNumberGeneratorFactory.GENERATING_STRATEGY_PROPERTY_NAME);
		
		System.setProperty(PrimeNumberGeneratorFactory.GENERATING_STRATEGY_PROPERTY_NAME, "abcd");
		assertEquals(GeneratingStrategy.STREAM, factory.getGeneratingStrategy());

		TestUtils.restoreSystemProperty(PrimeNumberGeneratorFactory.GENERATING_STRATEGY_PROPERTY_NAME, oldValue);
	}

	@Test
	public void testCreatePrimeNumberChecker() {
		assertTrue(factory.createPrimeNumberGenerator() instanceof StreamPrimeNumberGenerator);
		
		final String oldValue = System.getProperty(PrimeNumberGeneratorFactory.GENERATING_STRATEGY_PROPERTY_NAME);
		System.setProperty(PrimeNumberGeneratorFactory.GENERATING_STRATEGY_PROPERTY_NAME, "STREAM");
		assertTrue(factory.createPrimeNumberGenerator() instanceof StreamPrimeNumberGenerator);
		
		System.setProperty(PrimeNumberGeneratorFactory.GENERATING_STRATEGY_PROPERTY_NAME, "PARALLEL_STREAM");
		assertTrue(factory.createPrimeNumberGenerator() instanceof ParallelStreamPrimesNumberGenerator);
		
		TestUtils.restoreSystemProperty(PrimeNumberGeneratorFactory.GENERATING_STRATEGY_PROPERTY_NAME, oldValue);
	}
	
}
