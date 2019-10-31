package com.util;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SessionHandler {

	private static IoAcceptor acceptor = null;
	private static String IP;
	private static int PORT;

	@Value("${netsocket.ip}")
	public void setIP(String ip) {
		SessionHandler.IP = ip;
	}

	@Value("${netsocket.port}")
	public void setPort(Integer port) {
		SessionHandler.PORT = port;
	}

	private SessionHandler() {
	}

	public static IoAcceptor getAcceptor() {

		if (acceptor == null) {
			acceptor = new NioSocketAcceptor();
			acceptor.setHandler(new Devschandel());
			acceptor.getSessionConfig().setReadBufferSize(2048);
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
			try {
//				acceptor.bind(new InetSocketAddress(ip, Integer.parseInt(port)));
				acceptor.bind(new InetSocketAddress(IP, PORT));
				// System.out.println("GTTS Service is running, listening on port:" + PORT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// System.out.println("GTTS Service is fail, " + e.getMessage());
			}
		}
		return acceptor;
	}
}
