package br.univel.chat.view;

import java.util.List;
import java.util.Objects;

import javax.swing.AbstractListModel;

import br.univel.chat.dto.Mensagem;

public class MensagemListModel extends AbstractListModel<String> {

	private final List<Mensagem> mensagens;

	public MensagemListModel(final List<Mensagem> mensagens) {

		Objects.requireNonNull(mensagens, "Mensagem não pode ser null");

		this.mensagens = mensagens;
	}

	@Override
	public String getElementAt(int posicao) {

		final Mensagem msg = this.mensagens.get(posicao);
		final String mensagem = String.format("%s disse: %s",
				msg.getRemetente(), msg.getMensagem());
		return mensagem;

	}

	@Override
	public int getSize() {
		return this.mensagens.size();
	}

	public void adicionarMensagem(final Mensagem mensagem) {
		this.mensagens.add(mensagem);
		fireIntervalAdded(this, mensagens.size() - 1, mensagens.size());
	}
}
