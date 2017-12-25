package dominio;

import java.util.Calendar;

public class Transacao extends Entidade {
	
	private Calendar dataTransacao;
	private Double valor = 0D;
	private String descricao;
	private String detalhamento;
	private String titulo;	
	
	private Conta contaPrimaria = new Conta();
	private Conta contaSecundaria = new Conta();
	
	public Calendar getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(Calendar dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDetalhamento() {
		return detalhamento;
	}
	public void setDetalhamento(String detalhamento) {
		this.detalhamento = detalhamento;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Conta getContaPrimaria() {
		return contaPrimaria;
	}
	public void setContaPrimaria(Conta contaPrimaria) {
		this.contaPrimaria = contaPrimaria;
	}
	public Conta getContaSecundaria() {
		return contaSecundaria;
	}
	public void setContaSecundaria(Conta contaSecundaria) {
		this.contaSecundaria = contaSecundaria;
	}

}
