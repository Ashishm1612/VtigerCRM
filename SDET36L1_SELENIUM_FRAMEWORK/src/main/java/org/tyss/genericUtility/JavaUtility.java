package org.tyss.genericUtility;

import java.util.Random;

/**
 * This class contains java reusable methods 
 * @author Ashish
 *
 */
public class JavaUtility {
/**
 * This method is used to generate the Random Number
 */
	
	public int getRandomNumber() {
		return new Random().nextInt(1000);
	}
	/**
	 * This method is used to convert string to long(timeout)
	 * @param timeouts
	 * @return
	 */
	public Long convertStringToLong(String timeouts) {
		return Long.parseLong(timeouts);
	}
	
}
