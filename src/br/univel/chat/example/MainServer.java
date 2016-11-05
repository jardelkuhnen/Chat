package br.univel.chat.example;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServer {

	private ExecutorService pool = Executors.newFixedThreadPool(5);

	public static void main(final String[] args) throws Exception {
		new MainServer().initServer();
	}

	public void initServer() throws Exception {
		final ServerSocket server = new ServerSocket(1000);
		System.out.println("Server iniciado, aguardando conexão");
		Socket connection = null;
		while ((connection = server.accept()) != null) {
			System.out.println(String.format("Connected client: %s", connection.getInetAddress().getHostAddress()));
			pool.submit(new MessageServer(connection));
		}
		server.close();
	}
}
