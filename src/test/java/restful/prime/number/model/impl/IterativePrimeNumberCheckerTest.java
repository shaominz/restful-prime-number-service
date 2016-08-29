package restful.prime.number.model.impl;

import org.junit.BeforeClass;

import restful.prime.number.model.impl.IterativePrimeNumberChecker;
import restful.prime.number.model.spi.IPrimeNumberChecker;

public class IterativePrimeNumberCheckerTest extends AbstractPrimeNumberCheckerTest {

	private static IPrimeNumberChecker primeNumberChecker;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		primeNumberChecker = new IterativePrimeNumberChecker();
	}

	@Override
	public IPrimeNumberChecker getPrimeNumberChecker() {
		return primeNumberChecker;
	}
}
