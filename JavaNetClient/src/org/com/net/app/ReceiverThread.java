package org.com.net.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiverThread implements Runnable {

	@Override
	public void run() {
		System.out.println("Start Receiver Processor");
		 ServerSocket ss;
         try {
             ss = new ServerSocket(60010);

             Socket s = ss.accept();

             BufferedReader in = new BufferedReader(
                     new InputStreamReader(s.getInputStream()));
             String line = null;
             while ((line = in.readLine()) != null) {
            	 System.out.println("Receiving Data << "+line);
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
	}

}
