package com.jwk.common.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <pre>
 * id = timestamp+increment+instanceId
 * +-----------+--------------+------------+
 * | 52...21   | 20...4       | 3...0      |
 * +-----------+--------------+------------+
 * | timestamp | increment    | instanceId |
 * +-----------+--------------+------------+
 * </pre>
 * 
 * 
 */
public class LongID {
	private static final long maxIncrement = 131071;
	private static final int instanceNO = 15;

	private static final LongID[] INSTANCES = new LongID[instanceNO + 1];

	private final AtomicLong INC = new AtomicLong();
	private int instanceId = 0;
	private final long time = 1400000000000L;

	static {
		for (int i = 0; i <= instanceNO; i++) {
			LongID instance = new LongID();
			instance.instanceId = i;
			INSTANCES[i] = instance;
		}
	}

	private LongID() {
	}

	public long get() {
		return (((System.currentTimeMillis() - time) >> 9) << 21) + ((INC.incrementAndGet() & maxIncrement) << 4)
				+ instanceId;
	}

	public static LongID id(int instanceId) {
		if (instanceId < 0 || instanceId > instanceNO)
			return null;
		return INSTANCES[instanceId];
	}

	public static LongID generateInstanceZeroID() {
		return INSTANCES[0];
	}

	public static void main(String[] args) {
		long id = 0;
		Set<Long> set = new HashSet<Long>();
		for (int i = 0; i < 1000 * 100; i++) {
			id = LongID.id(i & 1).get();
			if (!set.add(id))
				System.out.println("!!!!!! duplication found:" + id);
		}
	}

}
