package restful.prime.number.model.impl;

import java.util.Arrays;

import restful.prime.number.model.spi.IPrimeNumberGenerator;
import restful.prime.number.model.spi.IPrimeNumberGeneratorFactory;

public class PrimeNumberGeneratorFactory implements IPrimeNumberGeneratorFactory {

	public static final String GENERATING_STRATEGY_PROPERTY_NAME = "restful.prime.number.generating.strategy";
	
	public enum GeneratingStrategy {STREAM, PARALLEL_STREAM};
	
	public  IPrimeNumberGenerator createPrimeNumberGenerator() {
		if (getGeneratingStrategy() == GeneratingStrategy.STREAM) {
			return new StreamPrimeNumberGenerator();
		} else {
			return new ParallelStreamPrimesNumberGenerator();
		}
	}
	
	GeneratingStrategy getGeneratingStrategy() {
		final String checkingStrategy = System.getProperty(GENERATING_STRATEGY_PROPERTY_NAME, "STREAM");
		return Arrays.stream(GeneratingStrategy.values())
			   .filter(s -> s.toString().equals(checkingStrategy))
			   .findFirst().orElse(GeneratingStrategy.STREAM);
	}
}
