package org.com.net.app;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.com.app.core.jpos.MsgUtil;
import org.com.app.core.jpos.PackagerFactory;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;

import com.pkg.rsc.QueueProcessor;

public class SenderThread01 implements Runnable {
	private static ISOPackager packager = PackagerFactory.getPackager();
	private Logger logger = Logger.getLogger(getClass());
	private String host;
	private int port;
	public SenderThread01(String host, int port){
		this.port = port;
		this.host = host;
	}
	@Override
	public void run() {
		Socket s;
		QueueProcessor queueProcessor;
		try {
			queueProcessor = new QueueProcessor();
			s = new Socket(host, port);
			ReceiverThread01 receiver = new ReceiverThread01(port);
			Thread t = new Thread(receiver,"Server Receiver Thread");
			t.start();
			OutputStream dos = new DataOutputStream(s.getOutputStream());
			while (true) {
				ISOMsg isoMsg = queueProcessor.fetch();
				isoMsg.setPackager(packager);
				logger.debug(">> Fetching Data from queue");
				logger.debug("Sender data to server >>");
				MsgUtil.printLogger(isoMsg);
				byte[] data = MsgUtil.appendHeader(isoMsg.pack());
				dos.write(data);
				dos.flush();
			}

       } catch (UnknownHostException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (InterruptedException e) {
           e.printStackTrace();
       } catch (ISOException e) {
		e.printStackTrace();
       }
	}

}
