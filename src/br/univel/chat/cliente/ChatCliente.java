package br.univel.chat.cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import br.univel.chat.dto.Mensagem;

public class ChatCliente {

	public void enviar(final Mensagem mensagem) throws UnknownHostException,
			IOException {

		final String destinatario = mensagem.getDestinatario();

		final String[] array = destinatario.split(":");

		final String ip = array[0];
		final Integer port = Integer.parseInt(array[1]);

		// Colocando as instancias dentro dos () no try, faz o java fechar os
		// objetos automaticamente
		try (final Socket socket = new Socket(ip, port);
				final OutputStream output = socket.getOutputStream();
				final ObjectOutputStream objOut = new ObjectOutputStream(output)) {

			objOut.writeObject(mensagem);

		} catch (final IOException e) {
			throw e;
		}

	}
}
