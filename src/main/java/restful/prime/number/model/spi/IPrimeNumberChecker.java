package restful.prime.number.model.spi;

public interface IPrimeNumberChecker {
	
    /**
     * Check whether the given number is a prime number or not
     * <p/>
     * For numbers smaller or equal to 0, it returns false.
     * 
     * @param number                the number to be checked.
     * @return      true if the number is prime, false otherwise.
     */
	boolean isPrime(final long number);
}
