package restful.prime.number.model.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import restful.prime.number.model.spi.IPrimeNumberChecker;

public abstract class AbstractPrimeNumberCheckerTest {

	public abstract IPrimeNumberChecker getPrimeNumberChecker();
	
	@Test
	public void testPrimes() {
		TestNumbers
		.PRIMES_SMALLER_THAN_10000
		.stream()
		.forEach(n -> assertTrue(String.format("%s is a prime number", n), getPrimeNumberChecker().isPrime(n)));
	}

	@Test
	public void testNonPrimes() {
		TestNumbers
		.NON_PRIMES_SMALLER_THAN_100
		.stream()
		.forEach(n -> assertFalse(String.format("%s is a prime number", n), getPrimeNumberChecker().isPrime(n)));
	}

	@Test
	public void testNetiveNumber() {
		assertFalse("-10 is not a prime number", getPrimeNumberChecker().isPrime(-10));
	}
}
