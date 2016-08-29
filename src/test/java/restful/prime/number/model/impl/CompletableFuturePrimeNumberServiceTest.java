package restful.prime.number.model.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import restful.prime.number.model.spi.IPrimeNumberChecker;
import restful.prime.number.model.spi.IPrimeNumberGenerator;
import restful.prime.number.utils.TimeoutCompletableFutureExecutor;

@RunWith(MockitoJUnitRunner.class)
public class CompletableFuturePrimeNumberServiceTest {

	private static CompletableFuturePrimeNumberService service;
	@Mock private static TimeoutCompletableFutureExecutor timeoutCompleteFutureExecutor;
	@Mock private static IPrimeNumberChecker primeNumberChecker;
	@Mock private static IPrimeNumberGenerator primeNumberGenerator;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new CompletableFuturePrimeNumberService();
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service.setTimeoutCompleteFutureExecutor(timeoutCompleteFutureExecutor);
		service.setPrimeNumberChecker(primeNumberChecker);
		service.setPrimeNumberGenerator(primeNumberGenerator);
	}

	@Test
	public void testGetTaskTimeout() {
		String property = AbstractPrimeNumberService.MAX_TASK_TIMEOUT_PROPERTY_NAME;
		
		assertEquals(-1L, service.getConfiguredValue(property));
		assertEquals(CompletableFuturePrimeNumberService.DEFAULT_MAX_TASK_TIMEOUT, service.getTaskTimeout());
		
		final String oldValue = System.getProperty(property);
		
		System.setProperty(property, "100");
		service = new CompletableFuturePrimeNumberService();
		assertEquals(100L, service.getConfiguredValue(property));
		assertEquals(100L, service.getTaskTimeout());
		
		System.setProperty(property, "abcd");
		service = new CompletableFuturePrimeNumberService();
		assertEquals(-1L, service.getConfiguredValue(property));
		assertEquals(CompletableFuturePrimeNumberService.DEFAULT_MAX_TASK_TIMEOUT, service.getTaskTimeout());
		
		TestUtils.restoreSystemProperty(property, oldValue);
		
	}
	
	
}
