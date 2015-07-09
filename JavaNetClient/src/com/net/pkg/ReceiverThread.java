package com.net.pkg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.pkg.rsc.QueueProcessor01;

public class ReceiverThread implements Runnable {

	@Override
	public void run() {
		ServerSocket ss;
        try {
            ss = new ServerSocket(60010);

            Socket s = ss.accept();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
            QueueProcessor01 qp = new QueueProcessor01();
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println("Receiving data from client << "+line);
                qp.put(line);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
		
	}

}
