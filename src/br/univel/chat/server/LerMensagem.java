package br.univel.chat.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import br.univel.chat.dto.Mensagem;

public class LerMensagem implements Runnable {

	private final Socket socket;

	public LerMensagem(final Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try (final InputStream input = socket.getInputStream();

		final ObjectInputStream objInput = new ObjectInputStream(input)) {

			final Mensagem mensagem = (Mensagem) objInput.readObject();

			System.out.println("Menssagem recebida de: ".concat(mensagem
					.getRemetente()));

			new MensagemRecebida(mensagem).exibirMensagem();
		} catch (final IOException | ClassNotFoundException e) {
			throw new RuntimeException();
		} finally {

			try {
				this.socket.close();

			} catch (final IOException e) {
				throw new RuntimeException(e);
			}

		}

	}
}
