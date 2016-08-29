package restful.prime.number.model.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import restful.prime.number.model.impl.AbstractPrimeNumberService;
import restful.prime.number.model.impl.PrimeNumberService;
import restful.prime.number.model.spi.IPrimeNumberChecker;
import restful.prime.number.model.spi.IPrimeNumberGenerator;
import restful.prime.number.response.PrimeNumberCheckResult;
import restful.prime.number.response.PrimeNumberGenerateResult;

public class PrimeNumberServiceTest {

	private static PrimeNumberService service;
	private static IPrimeNumberChecker primeNumberChecker;
	private static IPrimeNumberGenerator primeNumberGenerator;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new PrimeNumberService();
		primeNumberChecker = Mockito.mock(IPrimeNumberChecker.class);
		primeNumberGenerator = Mockito.mock(IPrimeNumberGenerator.class);
		service.setPrimeNumberChecker(primeNumberChecker);
		service.setPrimeNumberGenerator(primeNumberGenerator);
	}

	
	@Test 
	public void testCheckPrimeNoLimit() {
		//No max number is set (which takes -1), all number will be checked
		verifyNumberChecked(service.checkPrime(-10), -10);
		verifyNumberChecked(service.checkPrime(-1), -1);
		verifyNumberChecked(service.checkPrime(0), 0);
		verifyNumberChecked(service.checkPrime(1), 1);
		verifyNumberChecked(service.checkPrime(10), 10);
	}
	
	@Test 
	public void testCheckPrimeWithLimit() {
		Mockito.reset(primeNumberChecker);
		final String oldValue = System.getProperty(AbstractPrimeNumberService.MAX_NUMBER_PROPERTY_NAME);
		
		//change the value of the max allowed number property
		System.setProperty(AbstractPrimeNumberService.MAX_NUMBER_PROPERTY_NAME, "5");
		
		// read the new max number in
		readNewProperty();
		
		//numbers smaller than the max number (which is 5) will be checked
		verifyNumberChecked(service.checkPrime(-10), -10);
		verifyNumberChecked(service.checkPrime(-1), -1);
		verifyNumberChecked(service.checkPrime(0), 0);
		verifyNumberChecked(service.checkPrime(1), 1);
		
		//numbers greater than the max number (5) will NOT be checked
		verifyNumberNotCheckedBecauseOfLimit(service.checkPrime(10), 10);

		//restore the property value
		TestUtils.restoreSystemProperty(AbstractPrimeNumberService.MAX_NUMBER_PROPERTY_NAME, oldValue);
	}

	@Test
	public void testGeneratePrimesBoMaxUpperBound() {
		verifyNumberGenerated(service.generatePrimes(-10, -1), -10, -1);
		verifyNumberGenerated(service.generatePrimes(1, 10), 1, 10);
	}
	
	@Test
	public void testGeneratePrimesWithMaxUpperBound() {
		final String oldValue = System.getProperty(AbstractPrimeNumberService.MAX_NUMBER_PROPERTY_NAME);

		//change the value of the max upper bound property
		System.setProperty(AbstractPrimeNumberService.MAX_UPPER_BOUND_PROPERTY_NAME, "5");

		readNewProperty();

		//upper bound smaller than the max upper bound, not be generated
		verifyNumberGenerated(service.generatePrimes(1, 4), 1, 4);
		
		//upper bound greater than the max upper bound, not be generated
		verifyNumberNotGeneratedBecauseOfUpperBound(service.generatePrimes(1, 10), 1, 10);
		
		//restore the property value
		TestUtils.restoreSystemProperty(AbstractPrimeNumberService.MAX_UPPER_BOUND_PROPERTY_NAME, oldValue);
	}
	
	private void readNewProperty() {
		service = new PrimeNumberService();
		service.setPrimeNumberChecker(primeNumberChecker);
		service.setPrimeNumberGenerator(primeNumberGenerator);
		Mockito.reset(primeNumberChecker);
		Mockito.reset(primeNumberGenerator);
	}

	private void verifyNumberNotCheckedBecauseOfLimit(PrimeNumberCheckResult result, long number) {
		assertNull(result.getResult());
		assertTrue(result.getErrorMessage() != null && !"".equals(result.getErrorMessage()));
		Mockito.verify(primeNumberChecker, Mockito.times(0)).isPrime(number);
	}
	
	private void verifyNumberChecked(PrimeNumberCheckResult result, long number) {
		assertNotNull(result.getResult());
		assertTrue(result.getErrorMessage() != null && "".equals(result.getErrorMessage()));
		Mockito.verify(primeNumberChecker, Mockito.times(1)).isPrime(number);
	}

	private void verifyNumberGenerated(PrimeNumberGenerateResult result, long lower, long upper) {
		assertNotNull(result.getResult());
		assertTrue(result.getErrorMessage() != null && "".equals(result.getErrorMessage()));
		Mockito.verify(primeNumberGenerator, Mockito.times(1)).generatePrimes(lower, upper);
	}

	private void verifyNumberNotGeneratedBecauseOfUpperBound(PrimeNumberGenerateResult result, long lower, long upper) {
		assertNull(result.getResult());
		assertTrue(result.getErrorMessage() != null && !"".equals(result.getErrorMessage()));
		Mockito.verify(primeNumberGenerator, Mockito.times(0)).generatePrimes(lower, upper);
	}
}
