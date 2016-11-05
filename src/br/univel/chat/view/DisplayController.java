package br.univel.chat.view;

import java.io.IOException;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

import br.univel.chat.cliente.ChatCliente;
import br.univel.chat.dto.Mensagem;

public class DisplayController {

	private static final DisplayController instancia = new DisplayController();
	private Display display = new Display();

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
					"Esperado implementa��o MensageLIstModel para LIst, por�m foi encontrado outra, favor trocar implementa��o");
		}

	}

	public void enviarMensagem() {

		final String destinatario = this.display.getTxtDestinatario().getText();
		final String textMensagem = this.display.getTxtMensagem().getText();
		final Mensagem mensagem = new Mensagem();
		mensagem.setDestinatario(destinatario);
		mensagem.setMensagem(textMensagem);
		mensagem.setRemetente("Jardel");

		try {
			new ChatCliente().enviar(mensagem);
		
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane
					.showMessageDialog(
							this.display,
							"Ocorreu erro ao enviar menssagem. Favor verificar endere�o digitado, deve estar no formato ip:port",
							"Erro no envio", JOptionPane.ERROR_MESSAGE);
		}

	}

}
