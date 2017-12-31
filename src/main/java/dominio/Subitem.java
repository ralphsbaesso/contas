package dominio;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subitens")
public class Subitem extends Entidade{
	
	private String descricao;
	private String detalhamento;
	private String nivelImportancia;
	private String tipoConta;
	private boolean isPrestacao;
	private Item item = new Item();
	
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
	public String getNivelImportancia() {
		return nivelImportancia;
	}
	public void setNivelImportancia(String nivelImportancia) {
		this.nivelImportancia = nivelImportancia;
	}
	public String getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	public boolean isPrestacao() {
		return isPrestacao;
	}
	public void setPrestacao(boolean isPrestacao) {
		this.isPrestacao = isPrestacao;
	}
	@ManyToOne
	@JoinColumn(name = "item_id")
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}

}
