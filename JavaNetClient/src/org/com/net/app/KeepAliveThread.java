package org.com.net.app;

import org.apache.log4j.Logger;
import org.com.app.core.jpos.MessageDefinition;
import org.com.app.core.jpos.MsgUtil;
import org.com.app.core.jpos.PackagerFactory;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;

import com.pkg.rsc.QueueProcessor;

public class KeepAliveThread implements Runnable {
	private Logger logger = Logger.getLogger(getClass());
	private static ISOPackager p = PackagerFactory.getPackager();
	@Override
	public void run() {
		QueueProcessor queueProcessor = new QueueProcessor();
		try {
			while (true) {
				queueProcessor.put(echoMsg());
				Thread.sleep(30000);
			}
		} catch (InterruptedException e) {
			logger.debug("Excepiton Thrown while try to put echo message in the queue",e);
		}
	}
	
	private ISOMsg echoMsg(){
		ISOMsg isoMsg = new ISOMsg();
		isoMsg.setPackager(p);
		try {
			isoMsg.setMTI("0800");
			String de11 = MessageDefinition.buildField11();
			isoMsg.set(7,MessageDefinition.buildField07());
			isoMsg.set(11, de11);
			isoMsg.set(37, MessageDefinition.buildField37(de11));
			isoMsg.set(70, "301");
			logger.debug("Put data to queue");
			MsgUtil.printLogger(isoMsg);
			return isoMsg;
		} catch (ISOException e) {
			logger.debug("Could Create Echo ISO Message thrown exception",e);
			return null;
		}
	}
}
