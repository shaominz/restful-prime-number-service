package restful.prime.number.response;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class PrimeNumberCheckResultTest {

	private PrimeNumberCheckResult result;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		result = new PrimeNumberCheckResult(2L, true, "");
		assertNotNull(result.getResultId());
		assertEquals(36, result.getResultId().length());
		assertEquals(2L, result.getNumber().longValue());
		assertTrue(result.getResult());
	}

	@Test
	public void testFalse() {
		result = new PrimeNumberCheckResult(4L, false, "error");
		assertNotNull(result.getResultId());
		assertEquals(36, result.getResultId().length());
		assertEquals(4L, result.getNumber().longValue());
		assertFalse(result.getResult());
	}

}
