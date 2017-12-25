package dominio;

import java.util.ArrayList;
import java.util.List;

public class Item {
	
	private String nome;
	private String descricao;
	private List<Subitem> subitens = new ArrayList();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Subitem> getSubitens() {
		return subitens;
	}
	public void setSubitens(List<Subitem> subitens) {
		this.subitens = subitens;
	}
	
}
