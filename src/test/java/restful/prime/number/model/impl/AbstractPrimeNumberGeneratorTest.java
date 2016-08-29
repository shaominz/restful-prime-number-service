package restful.prime.number.model.impl;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import restful.prime.number.model.spi.IPrimeNumberGenerator;

public abstract class AbstractPrimeNumberGeneratorTest {

	public abstract IPrimeNumberGenerator getPrimeNumberGenerator();
	
	@Test
	public void test() {
        List<Long> primeNumberList = getPrimeNumberGenerator().generatePrimes(1, 10000);
        assertTrue(TestNumbers.PRIMES_SMALLER_THAN_10000.containsAll(primeNumberList));
        assertTrue(primeNumberList.containsAll(TestNumbers.PRIMES_SMALLER_THAN_10000));
	}

	@Test
	public void testNegativeLowerBound() {
        List<Long> primeNumberList = getPrimeNumberGenerator().generatePrimes(-1, 10000);
		assertEquals(Collections.EMPTY_LIST, primeNumberList);
	}

	@Test
	public void testNegativeUpperBound() {
        List<Long> primeNumberList = getPrimeNumberGenerator().generatePrimes(1, -10000);
		assertEquals(Collections.EMPTY_LIST, primeNumberList);
	}

	@Test
	public void testNegativeBounds() {
        List<Long> primeNumberList = getPrimeNumberGenerator().generatePrimes(-1, -10000);
		assertEquals(Collections.EMPTY_LIST, primeNumberList);
	}

	@Test
	public void testReversedBounds() {
        List<Long> primeNumberList = getPrimeNumberGenerator().generatePrimes(100, 10);
		assertEquals(Collections.EMPTY_LIST, primeNumberList);
	}
}
