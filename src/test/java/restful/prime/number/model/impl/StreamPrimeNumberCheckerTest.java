package restful.prime.number.model.impl;

import org.junit.BeforeClass;

import restful.prime.number.model.impl.StreamPrimeNumberChecker;
import restful.prime.number.model.spi.IPrimeNumberChecker;

public class StreamPrimeNumberCheckerTest extends AbstractPrimeNumberCheckerTest {

	private static IPrimeNumberChecker primeNumberChecker;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		primeNumberChecker = new StreamPrimeNumberChecker();
	}

	@Override
	public IPrimeNumberChecker getPrimeNumberChecker() {
		return primeNumberChecker;
	}
}
