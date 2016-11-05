package br.univel.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer implements Runnable {

	private ServerSocket server;
	private final ExecutorService pool = Executors.newFixedThreadPool(10);
	private Integer porta;

	public ChatServer(final Integer porta) {
		this.porta = porta;
	}

	public void iniciarServidor() throws IOException {

		new Thread(this).start();
		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {

				try {
					ChatServer.this.pool.shutdownNow();
					ChatServer.this.server.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}

			}

		});
	}

	@Override
	public void run() {
		try {
			this.server = new ServerSocket(porta);

			while (!server.isClosed()) {
				final Socket socket = server.accept();
				pool.submit(new LerMensagem(socket));
			}
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}
