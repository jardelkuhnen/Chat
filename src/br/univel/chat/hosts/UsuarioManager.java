package br.univel.chat.hosts;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import br.univel.chat.hosts.dto.Usuario;
import br.univel.chat.view.DisplayController;

public class UsuarioManager implements Runnable {

	private final String ipServidor;
	private final Integer portaServidor;
	private final Usuario usuarioLocal;

	public UsuarioManager(final String ipServidor, final Integer portaServidor,
			final Usuario usuarioLocal) {
		this.ipServidor = ipServidor;
		this.usuarioLocal = usuarioLocal;
		this.portaServidor = portaServidor;
	}

	public void iniciar() {
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this,
				0, 3, TimeUnit.SECONDS);
	}

	@Override
	public void run() {

		Socket conexao = null;
		OutputStream output = null;
		ObjectOutputStream objOutput = null;
		InputStream input = null;
		ObjectInputStream objInput = null;

		try {

			conexao = new Socket(ipServidor, portaServidor);

			// Escrevendo informações para o server
			output = conexao.getOutputStream();
			objOutput = new ObjectOutputStream(output);
			objOutput.writeObject(usuarioLocal);
			objOutput.flush();

			// Lendo informalções do server
			input = conexao.getInputStream();
			objInput = new ObjectInputStream(input);

			ArrayList<Usuario> usuarios = (ArrayList<Usuario>) objInput
					.readObject();

			DisplayController.getInstancia().atualizarUsuarios(usuarios);

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Falha na comunicação com o servidor");
			e.printStackTrace();
		} finally {
			try {
				if (objOutput != null) {
					objOutput.close();
				}
				if (output != null) {
					output.close();
				}
				if (objInput != null) {
					objInput.close();
				}
				if (input != null) {
					input.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
