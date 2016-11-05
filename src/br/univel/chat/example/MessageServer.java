package br.univel.chat.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageServer implements Runnable {

	private final Socket connection;

	public MessageServer(final Socket socket) {
		this.connection = socket;
	}

	@Override
	public void run() {
		// Input representa a entrada de dados do socket, as linhas abaixo
		// são utilizadas para fazer a leitura da mensagem enviada ao server
		try (final InputStream input = connection.getInputStream();
				final BufferedReader reader = new BufferedReader(new InputStreamReader(input));) {
			final String messageClient = reader.readLine();
			System.out
					.println(String.format("Message received from: %s", connection.getInetAddress().getHostAddress()));
			System.out.println(String.format("Message content: %s", messageClient));

			// Output é utilizado para escrever a mensagem que o cliente deve
			// receber
			final OutputStream outputStream = connection.getOutputStream();
			final PrintWriter writer = new PrintWriter(outputStream, true);
			writer.println(String.format("Server response message: %s", messageClient));

			System.out.println("Message process finished");
			writer.close();
			outputStream.close();
			connection.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
