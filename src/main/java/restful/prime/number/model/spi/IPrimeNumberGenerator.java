package restful.prime.number.model.spi;

import java.util.List;

public interface IPrimeNumberGenerator {

    /**
     * Generates the prime numbers with in the given range (inclusive). It expects both bounds are greater
     * than 0 and the upper bound is greater the lower bound. 
     * <p/>
     * For negative and 0, it returns false.
     * 
     * @param lowerBound	the lower bound of the range.
     * @param lowerBound	the upper bound of the range.
     * @return       		a List of all the prime numbers in the range; an empty if there is no prime numbers
     * 						in the range or the bounds do not meet the pre-conditions.
     */
	List<Long> generatePrimes(long lowerBound, long upperBound);
	
	default public boolean validateBounds(long lowerBound, long upperBound) {
		return lowerBound >= 0 && upperBound >= 0 && upperBound > lowerBound; 
	}
}
