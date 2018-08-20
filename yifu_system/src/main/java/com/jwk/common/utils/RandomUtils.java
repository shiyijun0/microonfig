package com.jwk.common.utils;

import java.util.UUID;

public class RandomUtils {
	
	public static String getId() {
		return UUID.randomUUID().toString();
	}

	public static String generate(int length, boolean isNumber) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		if (isNumber) {
			uuid = uuid.replace("a", "1").replace("b", "2").replace("c", "3")
					.replace("d", "4").replace("e", "5").replace("f", "6");
		}
		if (uuid.length() < length) {
			uuid += generate(length - uuid.length(), isNumber);
		} else {
			uuid = uuid.substring(0, length);
		}
		return uuid;
	}

	public static String fromTime16() {
		return new Moment().format("yyyyMMdd") + generate(16, true);
	}
	
	public static String fromTime8() {
		return new Moment().format("yyyyMMdd") + generate(8, true);
	}

	public static String fromTime24() {
		return new Moment().format("yyyyMMddHHmmss") + generate(10, true);
	}

	public static String getBillno32() {
		return generate(32, false);
	}
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
}