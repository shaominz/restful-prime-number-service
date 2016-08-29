package restful.prime.number.utils;

import java.time.Duration;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;

public class TimeoutCompletableFutureExecutor {
	
	static final String STATUS_SUCCESSFUL = "successful";
	static final String STATUS_CANCELLATION_EXCEPTION = "Task was cancelled";
	static final String STATUS_COMPLETION_EXCEPTION = "Task not completed";
	static final String STATUS_EXCEPTION = "Exception happened";
	
	private static Logger logger = Logger.getLogger(TimeoutCompletableFutureExecutor.class.getName());

	private static final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor();

	public <T> Pair<T, String> startTask(CompletableFuture<T> taskFuture, final Duration timeout, T valueWhenException) {
		try {
			return taskFuture
		    .applyToEither(getTimeoutTask(taskFuture, timeout, valueWhenException), res -> Pair.of(res, STATUS_SUCCESSFUL))
		    .join();
		} catch (CancellationException e) {
			logger.error(e, e);
			return Pair.of(valueWhenException, STATUS_CANCELLATION_EXCEPTION);
		} catch (CompletionException e) {
			logger.error(e, e);
			if (e.getCause() instanceof CancellationException) {
				return Pair.of(valueWhenException, STATUS_CANCELLATION_EXCEPTION);
			} else {
				return Pair.of(valueWhenException, STATUS_COMPLETION_EXCEPTION);
			}
		} catch (Exception e) {
			logger.error(e, e);
			return Pair.of(valueWhenException, STATUS_EXCEPTION);
		}
	}
	
	private <T> CompletableFuture<T> getTimeoutTask(CompletableFuture<T> taskFuture, final Duration delay, T valueWhenTimeout) {
		final CompletableFuture<T> timoutFuture = new CompletableFuture<T>();
		EXECUTOR.schedule(() -> {
			taskFuture.cancel(true);
			timoutFuture.complete(valueWhenTimeout);
		}, delay.toMillis(), TimeUnit.MILLISECONDS);
		return timoutFuture;
	}

}
