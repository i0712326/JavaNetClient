package org.com.net.app;


public class Main {
	private static final int port = 1123;
	private static final String host = "10.0.31.39";
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Start Main Thread");
		
		SenderThread01 sender = new SenderThread01(host,port);
		KeepAliveThread keepAlive = new KeepAliveThread();
		Thread t2 = new Thread(sender,"Client Sender Thread");
		Thread t3 = new Thread(keepAlive, "Keep Alive Thread");
		
		t2.start();
		t3.start();
		
		t2.join();
		t3.join();
		
		System.out.println("Main Thread End");
	}

}
