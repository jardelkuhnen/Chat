package br.univel.chat;

import java.io.IOException;

import javax.swing.JOptionPane;

import br.univel.chat.server.ChatServer;
import br.univel.chat.view.DisplayController;

public class MainChat {

	public static void main(String[] args) {

		try {
			new ChatServer(2500).iniciarServidor();

			DisplayController.getInstancia();

			System.out.println("Aplica��o iniciada");

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"N�o foi poss�vel iniciar o aplicativo");
			System.exit(1);
		}

	}

}
