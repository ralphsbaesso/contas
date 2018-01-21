package dominio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private int qtdeItem = 0;
	
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
	
	@OneToOne(mappedBy = "transacaoPrincipal")
	public Transferencia getTransferencia() {
		return transferencia;
	}
	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}
	
	public int getQtdeItem() {
		return qtdeItem;
	}
	public void setQtdeItem(int qtdeItem) {
		this.qtdeItem = qtdeItem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((dataTransacao == null) ? 0 : dataTransacao.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((detalhamento == null) ? 0 : detalhamento.hashCode());
		result = prime * result + qtdeItem;
		result = prime * result + ((subitem == null) ? 0 : subitem.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((transferencia == null) ? 0 : transferencia.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (dataTransacao == null) {
			if (other.dataTransacao != null)
				return false;
		} else if (!dataTransacao.equals(other.dataTransacao))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (detalhamento == null) {
			if (other.detalhamento != null)
				return false;
		} else if (!detalhamento.equals(other.detalhamento))
			return false;
		if (qtdeItem != other.qtdeItem)
			return false;
		if (subitem == null) {
			if (other.subitem != null)
				return false;
		} else if (!subitem.equals(other.subitem))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (transferencia == null) {
			if (other.transferencia != null)
				return false;
		} else if (!transferencia.equals(other.transferencia))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
}
