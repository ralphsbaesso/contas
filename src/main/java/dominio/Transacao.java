package dominio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transacoes")
public class Transacao extends Entidade {
	
	private Calendar dataTransacao = Calendar.getInstance();
	private Double valor = 0D;
	private String descricao;
	private String detalhamento;
	private String titulo;
	private Subitem subitem = new Subitem();
	private Conta conta = new Conta();
	private Transferencia transferencia;
	
	@OneToOne
	@JoinColumn(name = "subitem_id")
	public Subitem getSubitem() {
		return subitem;
	}
	public void setSubitem(Subitem subitem) {
		this.subitem = subitem;
	}
	
	@ManyToOne
	@JoinColumn(name = "conta_id")
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_transacao")
	public Calendar getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(Calendar dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao.setTime(dataTransacao);
	}
	public void setDataTransacao(String dataTransacao) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		this.dataTransacao.setTime(sdf.parse(dataTransacao));
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
	
	@OneToOne
	public Transferencia getTransferencia() {
		return transferencia;
	}
	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}
}
