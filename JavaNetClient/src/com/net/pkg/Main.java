package com.net.pkg;

import com.net.pkg.core05.SwitchingCore05;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Start Main Thread");
		SwitchingCore05 app = new SwitchingCore05();
		app.start();
		System.out.println("Main Thread End.");
	}
}
