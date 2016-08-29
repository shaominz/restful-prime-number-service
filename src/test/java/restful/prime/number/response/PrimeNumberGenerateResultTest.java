package restful.prime.number.response;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

public class PrimeNumberGenerateResultTest {

	private PrimeNumberGenerateResult result;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		result = new PrimeNumberGenerateResult(1L, 10L, Arrays.asList(new Long[] {2L, 3L, 5L, 7L}), "");
		assertNotNull(result.getResultId());
		assertEquals(36, result.getResultId().length());
		assertEquals(1L, result.getLower().longValue());
		assertEquals(10L, result.getUpper().longValue());
		assertNotNull(result.getResult());
		assertEquals(4, result.getResult().size());
		assertEquals("", result.getErrorMessage());
	}

	@Test
	public void testFalse() {
		result = new PrimeNumberGenerateResult(10L, 1L, Arrays.asList(new Long[] {}), "error");
		assertNotNull(result.getResultId());
		assertEquals(36, result.getResultId().length());
		assertEquals(10L, result.getLower().longValue());
		assertEquals(1L, result.getUpper().longValue());
		assertNotNull(result.getResult());
		assertEquals(0, result.getResult().size());
		assertEquals("error", result.getErrorMessage());
	}
	
}
