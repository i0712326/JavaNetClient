package com.net.pkg;

import java.io.DataOutputStream;
import java.io.IOException;
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
		this.host = host;
		this.port = port;
	}
	@Override
	public void run() {
		Socket s;
		QueueProcessor queueProcessor;
		try {
			s = new Socket(host, port);
			queueProcessor = new QueueProcessor();
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			while (true) {
				ISOMsg isoMsg = queueProcessor.fetch();
				isoMsg.setPackager(packager);
				isoMsg.setMTI("0810");
				isoMsg.set(39,"00");
				logger.debug(">> Fetching Data from Queue");
				MsgUtil.printLogger(isoMsg);
				byte[] data = MsgUtil.appendHeader(isoMsg.pack());
				logger.debug(">> Write Data to Client");
				dos.write(data);
				dos.flush();
				logger.debug("Sending Data Complete to Client");
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
