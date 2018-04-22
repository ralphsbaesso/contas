package web.viewhelper;

import enuns.EOperacao;

public class Requisicao {
	
	private EOperacao operacao;
	private String destino;
	

	public EOperacao getOperacao() {
		return operacao;
	}

	public void setOperacao(EOperacao operacao) {
		this.operacao = operacao;
	}
	
	public void setOperacao(String operacao) {
		
		operacao = operacao.toLowerCase();
		
		if(operacao.equals(EOperacao.SALVAR.getValor()))
			this.operacao = EOperacao.SALVAR;
		else if(operacao.equals(EOperacao.ALTERAR.getValor()))
			this.operacao = EOperacao.ALTERAR;
		else if(operacao.equals(EOperacao.EXCLUIR.getValor()))
			this.operacao = EOperacao.EXCLUIR;
		else if(operacao.equals(EOperacao.LISTAR.getValor()))
			this.operacao = EOperacao.LISTAR;
		else
			this.operacao = null;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

}
