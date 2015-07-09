package com.pkg.rsc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ApplicationQueue01 {
	private static final int SIZE = 500;
	private static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(SIZE);
	public static BlockingQueue<String> getQueue(){
		return queue;
	}
}
