package org.com.net.app;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.com.app.core.jpos.MsgUtil;
import org.com.app.core.jpos.PackagerFactory;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;

public class ReceiverThread01 implements Runnable {
	private static final int BUFFSIZE = 4;
	private static ISOPackager packager = PackagerFactory.getPackager();
	private Logger logger = Logger.getLogger(getClass());
	private int port;
	public ReceiverThread01(int port){
		this.port = port;
	}
	@Override
	public void run() {
		ServerSocket ss;
        try {
            ss = new ServerSocket(port);
            Socket s = ss.accept();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            byte[] buffer = new byte[BUFFSIZE];
            while(dis.read(buffer)!=0){
				int len = Integer.parseInt(new String(buffer));
				if (len > 0) {
					byte[] data = new byte[len];
					dis.read(data);
					ISOMsg isoMsg = new ISOMsg();
					isoMsg.setPackager(packager);
					isoMsg.unpack(data);
					logger.debug("Receiving data from client <<");
					MsgUtil.printLogger(isoMsg);
				}
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ISOException e) {
			e.printStackTrace();
		}
	}

}
