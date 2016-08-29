package restful.prime.number.model.impl;

import java.util.List;

import restful.prime.number.response.PrimeNumberCheckResult;
import restful.prime.number.response.PrimeNumberGenerateResult;

public class PrimeNumberService extends AbstractPrimeNumberService {

	@Override
	public PrimeNumberCheckResult checkPrime(long number) {
		if (MAX_NUMBER > 0 && number > MAX_NUMBER) { 
			return new PrimeNumberCheckResult(number, null, String.format(ERROR_MESSAGE_NUMBER_TOO_BIG, MAX_NUMBER));
		} else {
			Boolean result = primeNumberChecker.isPrime(number);
			return new PrimeNumberCheckResult(number, result, "");
		}
	}

	@Override
	public PrimeNumberGenerateResult generatePrimes(long lower, long upper) {
		if (MAX_UPPER_BOUND > 0 && upper > MAX_UPPER_BOUND) { 
	        return new PrimeNumberGenerateResult(lower, upper, null, String.format(ERROR_MESSAGE_UPPER_BOUND_TOO_BIG, MAX_UPPER_BOUND));
		} else {
			final List<Long> result = primeNumberGenerator.generatePrimes(lower, upper);
	        return new PrimeNumberGenerateResult(lower, upper, result, "");
		}
	}
	
}
