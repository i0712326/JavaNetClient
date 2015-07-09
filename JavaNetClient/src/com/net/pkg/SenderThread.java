package com.net.pkg;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.pkg.rsc.QueueProcessor01;

public class SenderThread implements Runnable {

	@Override
	public void run() {
		Socket s;
		 try {
            s = new Socket("10.0.31.8", 60010);
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(s.getOutputStream()));
            QueueProcessor01 qp = new QueueProcessor01();
            //String line = "Salut tout les mond !";
            while (true) {
            	String line = qp.fetch();
            	if(line != null){
            		line = line + " Client !!!!";
					System.out.println("Send data to client >>" + line);
					out.write(line);
					out.newLine();
					out.flush();
            	}
                Thread.sleep(3000);
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
