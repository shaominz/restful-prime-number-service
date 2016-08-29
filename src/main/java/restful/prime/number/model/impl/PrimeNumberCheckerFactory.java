package restful.prime.number.model.impl;

import java.util.Arrays;

import restful.prime.number.model.spi.IPrimeNumberChecker;
import restful.prime.number.model.spi.IPrimeNumberCheckerFactory;

public class PrimeNumberCheckerFactory implements IPrimeNumberCheckerFactory {
	
	public static final String CHECKING_STRATEGY_PROPERTY_NAME = "restful.prime.number.checking.strategy";
	
	public enum CheckingStrategy {STREAM, ITERATIVE};
	
	public IPrimeNumberChecker createPrimeNumberChecker() {
		if (getCheckingStrategy() == CheckingStrategy.STREAM) {
			return new StreamPrimeNumberChecker();
		} else {
			return new IterativePrimeNumberChecker();
		}
	}
	
	CheckingStrategy getCheckingStrategy() {
		final String checkingStrategy = System.getProperty(CHECKING_STRATEGY_PROPERTY_NAME, "STREAM");
		return Arrays.stream(CheckingStrategy.values())
			   .filter(s -> s.toString().equals(checkingStrategy))
			   .findFirst().orElse(CheckingStrategy.STREAM);
	}
}
