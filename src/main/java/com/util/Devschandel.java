package com.util;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class Devschandel extends IoHandlerAdapter {

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// Check if the 'fake' session secured message notification has been received
		session.write("OK");
		// IoBuffer ioBuffer = (IoBuffer) message;
		System.out.println("GTTS  Service is fail, " + message.toString());
	}

	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.out.println("server端闲置连接：会话 " + session.getId() + " 被触发 " + session.getIdleCount(status) + " 次");
	}

	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
		System.out.println("client closed");
	}

	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionOpened(session);
		System.out.println("进来了:" + session.getRemoteAddress());
	}
}
