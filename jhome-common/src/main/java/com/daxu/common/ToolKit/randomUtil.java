package com.daxu.common.ToolKit;

import java.util.Random;

public class randomUtil {
	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static final String randomString(int length) {
		char[] numbersAndLetters;
		if (length < 1) {
			return null;
		}
		Random randGen = new Random();
		numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
				+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	public static final String randomInt(int length) {
		char[] numbersAndLetters;
		if (length < 1) {
			return null;
		}
		Random randGen = new Random();
		numbersAndLetters = ("0123456789").toCharArray();
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
		}
		return new String(randBuffer);
	}
}
