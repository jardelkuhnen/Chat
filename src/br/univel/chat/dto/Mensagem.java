package br.univel.chat.dto;

import java.io.Serializable;

public class Mensagem implements Serializable {

	/**
	 * Precisa ser a mesma versão do cliente e servidor
	 */
	private static final long serialVersionUID = 1L;

	private String remetente;
	private String destinatario;
	private String mensagem;

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
