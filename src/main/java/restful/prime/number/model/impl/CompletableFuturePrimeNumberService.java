package restful.prime.number.model.impl;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;

import restful.prime.number.response.PrimeNumberCheckResult;
import restful.prime.number.response.PrimeNumberGenerateResult;
import restful.prime.number.utils.TimeoutCompletableFutureExecutor;

public class CompletableFuturePrimeNumberService extends AbstractPrimeNumberService {

	private static Logger logger = Logger.getLogger(CompletableFuturePrimeNumberService.class.getName());

	//default to 1 hour
	static final long DEFAULT_MAX_TASK_TIMEOUT = 1 * 60 * 60 * 100;
	
	// MAX_TASK_WAITING_TIME = -1 means no timeout, in seconds
	protected final long MAX_TASK_TIMEOUT;
	

	private TimeoutCompletableFutureExecutor timeoutCompleteFutureExecutor;
	
	public CompletableFuturePrimeNumberService() {
		super();

		MAX_TASK_TIMEOUT = getConfiguredValue(MAX_TASK_TIMEOUT_PROPERTY_NAME);
		logger.info(String.format("Restful Prime Nunmber Service: max task timeout allowed: %s", MAX_TASK_TIMEOUT));
	}

	@Override
	public PrimeNumberCheckResult checkPrime(long number) {
		if (MAX_NUMBER > 0 && number > MAX_NUMBER) { 
			return new PrimeNumberCheckResult(number, null, String.format(ERROR_MESSAGE_NUMBER_TOO_BIG, MAX_NUMBER));
		} else {
			CompletableFuture<Boolean> taskFuture = CompletableFuture.supplyAsync( ( ) -> {
				    return primeNumberChecker.isPrime(number);
			});
			
			Pair<Boolean, String> resultPair = timeoutCompleteFutureExecutor.startTask(taskFuture, 
					Duration.ofSeconds(getTaskTimeout()), 
					null);
			return new PrimeNumberCheckResult(number, resultPair.getLeft(), resultPair.getRight());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PrimeNumberGenerateResult generatePrimes(long lower, long upper) {
		if (MAX_UPPER_BOUND > 0 && upper > MAX_UPPER_BOUND) { 
	        return new PrimeNumberGenerateResult(lower, upper, null, String.format(ERROR_MESSAGE_UPPER_BOUND_TOO_BIG, MAX_UPPER_BOUND));
		} else {
			CompletableFuture<List<Long>> taskFuture = CompletableFuture.supplyAsync( ( ) -> {
				return primeNumberGenerator.generatePrimes(lower, upper);
			});
		
			Pair<List<Long>, String> resultPair = timeoutCompleteFutureExecutor.startTask(taskFuture, 
					Duration.ofSeconds(getTaskTimeout()), 
					(List<Long>)Collections.EMPTY_LIST);
	        return new PrimeNumberGenerateResult(lower, upper, resultPair.getLeft(), resultPair.getRight());
		}
	}

	long getTaskTimeout() {
		return (MAX_TASK_TIMEOUT > 0) ? MAX_TASK_TIMEOUT : DEFAULT_MAX_TASK_TIMEOUT;
	}
	
	public TimeoutCompletableFutureExecutor getTimeoutCompleteFutureExecutor() {
		return timeoutCompleteFutureExecutor;
	}

	public void setTimeoutCompleteFutureExecutor(TimeoutCompletableFutureExecutor timeoutCompleteFutureExecutor) {
		this.timeoutCompleteFutureExecutor = timeoutCompleteFutureExecutor;
	}

}
