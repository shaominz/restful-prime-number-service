package restful.prime.number.utils;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import restful.prime.number.utils.TimeoutCompletableFutureExecutor;

public class TimeoutCompleteFutureExecutorTest {

	private static final String VALUE_CALAULATED = "value calculated (takes a long time)";
	private static final String VALUE_WITH_EXCEPTION = "Exception happened";
	
	private static Logger logger = Logger.getLogger(TimeoutCompleteFutureExecutorTest.class.getName());

	private static TimeoutCompletableFutureExecutor executor;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		executor = new TimeoutCompletableFutureExecutor();
	}

	@Test
	public void testHappyPath() {
		CompletableFuture<String> taskFuture = createFutureTask(1, 1000);
		Pair<String, String> resultPair = executor.startTask(taskFuture, Duration.ofMillis(2000), VALUE_WITH_EXCEPTION);
		assertEquals(Pair.of(VALUE_CALAULATED, TimeoutCompletableFutureExecutor.STATUS_SUCCESSFUL), resultPair);
	}

	@Test
	public void testCancellationException() {
		CompletableFuture<String> taskFuture = createFutureTask(3, 1000);
		Pair<String, String> resultPair = executor.startTask(taskFuture, Duration.ofMillis(1000), VALUE_WITH_EXCEPTION);
		assertEquals(Pair.of(VALUE_WITH_EXCEPTION, TimeoutCompletableFutureExecutor.STATUS_CANCELLATION_EXCEPTION), resultPair);
	}

	@Test
	public void testCompletionException() {
		CompletableFuture<String> taskFuture = createFutureTask(3, 1000);
		completeExceptionally(taskFuture, new CompletionException("", new RuntimeException()));
		Pair<String, String> resultPair = executor.startTask(taskFuture, Duration.ofMillis(5000), VALUE_WITH_EXCEPTION);
		assertEquals(Pair.of(VALUE_WITH_EXCEPTION, TimeoutCompletableFutureExecutor.STATUS_COMPLETION_EXCEPTION), resultPair);
	}

	@Test
	public void testRuntimeException() {
		CompletableFuture<String> taskFuture = createFutureTask(3, 1000);
		completeExceptionally(taskFuture, new RuntimeException());
		Pair<String, String> resultPair = executor.startTask(taskFuture, Duration.ofMillis(5000), VALUE_WITH_EXCEPTION);
		assertEquals(Pair.of(VALUE_WITH_EXCEPTION, TimeoutCompletableFutureExecutor.STATUS_COMPLETION_EXCEPTION), resultPair);
	}

	@Test
	public void testException() {
		CompletableFuture<String> taskFuture = createFutureTask(3, 1000);
		completeExceptionally(taskFuture, new Exception());
		Pair<String, String> resultPair = executor.startTask(taskFuture, Duration.ofMillis(5000), VALUE_WITH_EXCEPTION);
		assertEquals(Pair.of(VALUE_WITH_EXCEPTION, TimeoutCompletableFutureExecutor.STATUS_COMPLETION_EXCEPTION), resultPair);
	}

	private void completeExceptionally(CompletableFuture<String> taskFuture, Exception ex) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				taskFuture.completeExceptionally(ex);
			}
		}).start();
	}
	
	private CompletableFuture<String> createFutureTask(int n, int sleepMills) {
		CompletableFuture<String> taskFuture = CompletableFuture.supplyAsync( ( ) -> {
			try {
				int i = 0;
				while(i < n) {
					logger.debug("doing hard work now");
					Thread.sleep(sleepMills);
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		    return VALUE_CALAULATED;
		} );
		
		return taskFuture;
	}
	
}
