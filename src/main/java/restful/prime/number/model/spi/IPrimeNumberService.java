package restful.prime.number.model.spi;

import restful.prime.number.response.PrimeNumberCheckResult;
import restful.prime.number.response.PrimeNumberGenerateResult;

public interface IPrimeNumberService {
    public PrimeNumberCheckResult checkPrime(long number);
    public PrimeNumberGenerateResult generatePrimes(long lower, long upper);
}
