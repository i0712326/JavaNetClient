package com.pkg.rsc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.jpos.iso.ISOMsg;

public class ApplicationQueue {
	private static final int SIZE = 500;
	private static BlockingQueue<ISOMsg> queue = new ArrayBlockingQueue<ISOMsg>(SIZE);
	
	public static BlockingQueue<ISOMsg> getQueue(){
		return queue;
	}
}
