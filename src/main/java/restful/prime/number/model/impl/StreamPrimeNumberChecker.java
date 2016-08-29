package restful.prime.number.model.impl;

import java.util.stream.LongStream;

import restful.prime.number.model.spi.IPrimeNumberChecker;

public class StreamPrimeNumberChecker implements IPrimeNumberChecker {

	public boolean isPrime(final long number) {
		return number > 1 && 
				LongStream.rangeClosed(2, (int)Math.sqrt(number))
				.allMatch(n -> number % n != 0);
				
	}

}
