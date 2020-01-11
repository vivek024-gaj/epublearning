/**
 * 
 */
package tk.vky.epublearning;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 *         10-Jan-2020
 */
public class PasswordEncoder {

	/**
	 * 
	 */
	public PasswordEncoder() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // Strength set as 12
		String encodedPassword = encoder.encode("pass");
		System.out.println("password encoder : " + encodedPassword);
	}

}
