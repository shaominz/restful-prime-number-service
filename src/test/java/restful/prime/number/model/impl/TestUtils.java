package restful.prime.number.model.impl;

public class TestUtils {
	public static void restoreSystemProperty(final String property, final String oldValue) {
		if (oldValue != null) {
			System.setProperty(property, oldValue);
		} else {
			System.clearProperty(property);
		}
		
	}
	

}
