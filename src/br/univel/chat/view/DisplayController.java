package br.univel.chat.view;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

import br.univel.chat.cliente.ChatCliente;
import br.univel.chat.dto.Mensagem;
import br.univel.chat.hosts.dto.Usuario;

public class DisplayController {

	private static final DisplayController instancia = new DisplayController();
	private Display display = new Display();
	private Collection<Usuario> destinatarios;

	private DisplayController() {
		display.setVisible(true);
	}

	public static DisplayController getInstancia() {
		return instancia;
	}

	public void adicionarMensagem(final Mensagem mensagem) {

		final JList<String> lstMensagem = this.display.getLstMensagem();
		final ListModel<String> model = lstMensagem.getModel();

		if (model instanceof MensagemListModel) {
			final MensagemListModel lista = (MensagemListModel) model;
			lista.adicionarMensagem(mensagem);

		} else {
			throw new IllegalStateException(
					"Esperado implementação MensageLIstModel para LIst, porém foi encontrado outra, favor trocar implementação");
		}

	}

	public void enviarMensagem() {

		final String nome = this.display.getDestinatario();
		final Usuario usuario = getrDestinatario(nome);
		final String textMensagem = this.display.getTxtMensagem().getText();
		final Mensagem mensagem = new Mensagem();
		mensagem.setDestinatario(usuario.getIp().concat(":")
				.concat(usuario.getPorta().toString()));
		mensagem.setMensagem(textMensagem);
		mensagem.setRemetente("Jardel");

		try {
			new ChatCliente().enviar(mensagem);

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane
					.showMessageDialog(
							this.display,
							"Ocorreu erro ao enviar menssagem. Favor verificar endereço digitado, deve estar no formato ip:port",
							"Erro no envio", JOptionPane.ERROR_MESSAGE);
		}

	}

	private Usuario getrDestinatario(final String nome) {

		Usuario destino = this.destinatarios.stream()
				.filter(usuario -> usuario.getNome().equalsIgnoreCase(nome))
				.findAny().orElse(null);

		if (destino == null) {
			throw new IllegalStateException("Destinatário não disponível");
		}
		return destino;
	}

	public void atualizarUsuarios(final Collection<Usuario> usuarios) {

		System.out.println("Usuarios recebidos no servidor");
		this.destinatarios = usuarios;

		List<String> destinatarios = usuarios.stream().map(Usuario::getNome)
				.collect(Collectors.toList());
		this.display.atualizarDestiantarios(destinatarios);
	}

}
