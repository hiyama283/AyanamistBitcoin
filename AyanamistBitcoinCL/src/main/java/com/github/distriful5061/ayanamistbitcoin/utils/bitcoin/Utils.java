/* 
 * Bitcoin cryptography library
 * Copyright (c) Project Nayuki
 * 
 * https://www.nayuki.io/page/bitcoin-cryptography-library
 * https://github.com/nayuki/Bitcoin-Cryptography-Library
 */

package com.github.distriful5061.ayanamistbitcoin.utils.bitcoin;

import java.nio.charset.StandardCharsets;


// Miscellaneous utilities for the test suites.
final class Utils {
	
	/*---- Static functions ----*/
	
	// Converts a hex string to a new array of bytes. The string length
	// must be even. Hexadecimal letters can be uppercase or lowercase.
	public static byte[] hexToBytes(String s) {
		if (s.length() % 2 != 0)
			throw new IllegalArgumentException();
		
		byte[] b = new byte[s.length() / 2];
		for (int i = 0; i < s.length(); i += 2) {
			if (s.charAt(i) == '+' || s.charAt(i) == '-')
				throw new IllegalArgumentException();
			b[i / 2] = (byte)Integer.parseInt(s.substring(i, i + 2), 16);
		}
		return b;
	}
	
	
	public static byte[] asciiToBytes(String s) {
		return s.getBytes(StandardCharsets.US_ASCII);
	}
	
	
	
	/*---- Miscellaneous ----*/
	
	private Utils() {}  // Not instantiable
	
}
