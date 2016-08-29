package restful.prime.number.model.impl;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import restful.prime.number.response.PrimeNumberCheckResult;
import restful.prime.number.response.PrimeNumberGenerateResult;

public class AbstractPrimeNumberServiceTest {

	private static AbstractPrimeNumberService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new AbstractPrimeNumberService() {

			@Override
			public PrimeNumberCheckResult checkPrime(long number) {
				return null;
			}

			@Override
			public PrimeNumberGenerateResult generatePrimes(long lower, long upper) {
				return null;
			}
			
		};
	}

	@Test
	public void testGetMaxAllowedNumber() {
		testGetMaxAllowedNumber(AbstractPrimeNumberService.MAX_NUMBER_PROPERTY_NAME);
	}

	@Test
	public void testGetMaxUpperBound() {
		testGetMaxAllowedNumber(AbstractPrimeNumberService.MAX_UPPER_BOUND_PROPERTY_NAME);
	}

	@Test
	public void testGetMaxTaskTimeOut() {
		testGetMaxAllowedNumber(AbstractPrimeNumberService.MAX_TASK_TIMEOUT_PROPERTY_NAME);
	}

	private void testGetMaxAllowedNumber(final String property) {
		assertEquals(-1L, service.getConfiguredValue(property));
		
		final String oldValue = System.getProperty(property);
		
		System.setProperty(property, "100");
		assertEquals(100L, service.getConfiguredValue(property));
		
		System.setProperty(property, "abcd");
		assertEquals(-1L, service.getConfiguredValue(property));
		
		TestUtils.restoreSystemProperty(property, oldValue);
	}
	
}
