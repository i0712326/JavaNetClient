package com.net.pkg;

import java.util.ArrayList;
import java.util.List;

import org.com.app.core.entity.Member;
import org.com.app.core.service.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SwitchingCore {
	public void start() throws InterruptedException{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MemberService service = (MemberService) context.getBean("memberService");
		List<Member> list = service.getMember();
		List<Thread> threads = new ArrayList<Thread>();
		synchronized(list){
			for(Member member : list){
				int port = Integer.parseInt(member.getPort());
				ReceiverThread01 receiver = new ReceiverThread01(port);
				System.out.println("Server Receiver Thread at port -"+port);
				Thread thread = new Thread(receiver, "Server Receiver Thread at port -"+port);
				thread.start();
				threads.add(thread);
				Thread.sleep(1000);
			}
		}
		for(Thread t : threads) t.join();
		System.out.println("Main Thread End");
	}
}
