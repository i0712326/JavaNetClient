package org.com.net.app;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SenderThread implements Runnable {
	@Override
	public void run() {
		 Socket s = null;
		 try {
			 s = new Socket("10.0.31.39", 60010);
             BufferedWriter out = new BufferedWriter(
                     new OutputStreamWriter(s.getOutputStream()));
             String data = "Hello World !";
             while (true) {
            	 System.out.println("Sending Data to Server >> "+data);
                 out.write(data);
                 out.newLine();
                 out.flush();
                 Thread.sleep(10000);
             }

         } catch (UnknownHostException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
		 
	}

}
