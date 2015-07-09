package com.pkg.rsc;

import java.util.concurrent.BlockingQueue;

public class QueueProcessor01 {
	public void put(String item) throws InterruptedException{
		BlockingQueue<String> queue = ApplicationQueue01.getQueue();
		queue.put(item);
	}
	public String fetch() throws InterruptedException{
		BlockingQueue<String> queue = ApplicationQueue01.getQueue();
		return queue.take();
	}
}
