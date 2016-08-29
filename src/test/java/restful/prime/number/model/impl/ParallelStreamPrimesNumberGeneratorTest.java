package restful.prime.number.model.impl;

import org.junit.BeforeClass;

import restful.prime.number.model.spi.IPrimeNumberGenerator;

public class ParallelStreamPrimesNumberGeneratorTest extends AbstractPrimeNumberGeneratorTest {

	private static IPrimeNumberGenerator primeNumberGenerator;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		primeNumberGenerator = new StreamPrimeNumberGenerator();
	}

	@Override
	public IPrimeNumberGenerator getPrimeNumberGenerator() {
		return primeNumberGenerator;
	}
}
