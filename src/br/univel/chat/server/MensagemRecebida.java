package br.univel.chat.server;

import br.univel.chat.dto.Mensagem;
import br.univel.chat.view.DisplayController;

public class MensagemRecebida {

	private final Mensagem mensagem;

	public MensagemRecebida(final Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public void exibirMensagem() {

		DisplayController.getInstancia().adicionarMensagem(this.mensagem);

	}
}
