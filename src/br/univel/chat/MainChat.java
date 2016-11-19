package br.univel.chat;

import java.io.IOException;

import javax.swing.JOptionPane;

import br.univel.chat.hosts.UsuarioManager;
import br.univel.chat.hosts.dto.Usuario;
import br.univel.chat.server.ChatServer;
import br.univel.chat.view.DisplayController;

public class MainChat {

	public static void main(String[] args) {

		try {

			Usuario usuario = new Usuario();
			usuario.setIp("192.168.101.25");
			usuario.setNome("Jardel");
			usuario.setPorta(1000);

			new ChatServer(1000).iniciarServidor();

			DisplayController.getInstancia();

			new UsuarioManager("192.168.101.10", 5000, usuario).iniciar();
			
			System.out.println("Aplicação iniciada");

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Não foi possível iniciar o aplicativo");
			System.exit(1);
		}

	}
}
