package restful.prime.number.model.impl;

import org.apache.log4j.Logger;

import restful.prime.number.model.spi.IPrimeNumberChecker;
import restful.prime.number.model.spi.IPrimeNumberGenerator;
import restful.prime.number.model.spi.IPrimeNumberService;

public abstract class AbstractPrimeNumberService implements IPrimeNumberService {

	private static Logger logger = Logger.getLogger(AbstractPrimeNumberService.class.getName());

	public static final String MAX_NUMBER_PROPERTY_NAME = "restful.prime.max.number";
	public static final String MAX_UPPER_BOUND_PROPERTY_NAME = "restful.prime.max.upper.bound";
	public static final String MAX_TASK_TIMEOUT_PROPERTY_NAME = "restful.prime.max.task.timeout";
	
	public static final String ERROR_MESSAGE_NUMBER_TOO_BIG = "The supplied number to check is larger than the max number allowed (%s)";
	public static final String ERROR_MESSAGE_UPPER_BOUND_TOO_BIG = "The supplied upper bound is larger than the upper bound allowed (%s)";
	
	
	// MAX_NUMBER = -1 means no limit allowed to check if it is prime
	protected final long MAX_NUMBER;

	// MAX_UPPER_BOUND = -1 means no upper limit
	protected final long MAX_UPPER_BOUND;
	
	protected IPrimeNumberChecker primeNumberChecker;
	protected IPrimeNumberGenerator primeNumberGenerator;
	
	public AbstractPrimeNumberService() {
		MAX_NUMBER = getConfiguredValue(MAX_NUMBER_PROPERTY_NAME);
		MAX_UPPER_BOUND = getConfiguredValue(MAX_UPPER_BOUND_PROPERTY_NAME);
		
		logger.info(String.format("Restful Prime Nunmber Service: max number allowed: %s", MAX_NUMBER));
		logger.info(String.format("Restful Prime Nunmber Service: max upper bound allowed: %s", MAX_UPPER_BOUND));
	}

	public long getConfiguredValue(final String property) {
		try {
			return Long.parseLong(System.getProperty(property, "-1"));
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public IPrimeNumberChecker getPrimeNumberChecker() {
		return primeNumberChecker;
	}

	public void setPrimeNumberChecker(IPrimeNumberChecker primeNumberChecker) {
		this.primeNumberChecker = primeNumberChecker;
	}

	public IPrimeNumberGenerator getPrimeNumberGenerator() {
		return primeNumberGenerator;
	}

	public void setPrimeNumberGenerator(IPrimeNumberGenerator primeNumberGenerator) {
		this.primeNumberGenerator = primeNumberGenerator;
	}
}
