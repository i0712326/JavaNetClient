package com.net.pkg;

public class Main {
	//private static final int port = 1123;
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Start Main Thread");
		/*ReceiverThread01 receiver = new ReceiverThread01(port);
		
		Thread t1 = new Thread(receiver,"Server Receiver Thread");
		t1.start();
		
		t1.join();*/
		SwitchingCore app = new SwitchingCore();
		app.start();
		System.out.println("Main Thread End.");
	}

}
