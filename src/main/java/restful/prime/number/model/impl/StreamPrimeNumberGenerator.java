package restful.prime.number.model.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import restful.prime.number.model.spi.IPrimeNumberChecker;
import restful.prime.number.model.spi.IPrimeNumberGenerator;

public class StreamPrimeNumberGenerator implements IPrimeNumberGenerator {

	private IPrimeNumberChecker primeNumberChecker = new StreamPrimeNumberChecker();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> generatePrimes(long lowerBound, long upperBound) {
        return (!validateBounds(lowerBound, upperBound)) ?
        		(List<Long>)Collections.EMPTY_LIST :
        		LongStream
                .rangeClosed(lowerBound, upperBound)
                .filter(primeNumberChecker::isPrime)
                .boxed()
                .collect(Collectors.toList());
	}
}
