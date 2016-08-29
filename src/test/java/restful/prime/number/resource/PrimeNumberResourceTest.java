package restful.prime.number.resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import restful.prime.number.model.spi.IPrimeNumberService;

public class PrimeNumberResourceTest {

	private static PrimeNumberResource primeNumberResource;
	private static IPrimeNumberService primeNumberService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		primeNumberResource = new PrimeNumberResource();
		primeNumberService = Mockito.mock(IPrimeNumberService.class);
		primeNumberResource.setPrimeNumberService(primeNumberService);
	}

	@Test
	public void testCheckPrime() {
		primeNumberResource.checkPrime(2);
		Mockito.verify(primeNumberService, Mockito.times(1)).checkPrime(2);
	}

	@Test
	public void testGeneratePrimes() {
		primeNumberResource.generatePrimes(1, 10);
		Mockito.verify(primeNumberService, Mockito.times(1)).generatePrimes(1, 10);
	}

}
