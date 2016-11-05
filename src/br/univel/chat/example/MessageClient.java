package br.univel.chat.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

public class MessageClient implements Callable<Object> {

	private final String message;
	private final String host;
	private final Integer port;

	public MessageClient(String message, String host, Integer port) {
		super();
		this.message = message;
		this.host = host;
		this.port = port;
	}

	@Override
	public Object call() {
		BufferedReader reader = null;
		InputStream input = null;
		PrintWriter writer = null;
		OutputStream outputStream = null;
		Socket connection = null;
		try {
			connection = new Socket(this.host, this.port);
			System.out.println(String.format("Client connected to server: %s:%d", this.host, this.port));

			// OutputStream é utilizado para enviar mensagem ao servidor, tudo
			// que é escrito no output vai para o server
			outputStream = connection.getOutputStream();
			writer = new PrintWriter(outputStream, true);
			writer.println(this.message);
			System.out.println(String.format("Client sent message: %s", message));

			// InputStream é a entrada de dados, ou seja, o que o server
			// escrever vem parar no InputStream
			input = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input));
			System.out.println(
					String.format("Message received from server: %s", connection.getInetAddress().getHostAddress()));
			String line = reader.readLine();
			System.out.println(String.format("Message content: %s", line));
			System.out.println("Client finished");
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
				if (input != null)
					input.close();
				if (writer != null)
					writer.close();
				if (outputStream != null)
					outputStream.close();
				if (connection != null)
					connection.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
