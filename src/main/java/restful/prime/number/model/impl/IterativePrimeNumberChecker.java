package restful.prime.number.model.impl;

import restful.prime.number.model.spi.IPrimeNumberChecker;

public class IterativePrimeNumberChecker implements IPrimeNumberChecker {

	public boolean isPrime(final long number) {
		if (number <= 0) { 
			return false;
		}
		
		int m = (int) Math.sqrt(number) + 1;
		for (int i = 2; i < m; ++i) {
			if (number % i == 0) {
				return false;
			}
		}
		
		return true;
	}
}
