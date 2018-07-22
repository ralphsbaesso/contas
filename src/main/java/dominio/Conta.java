package dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enuns.ELeitorArquivo;

@Entity
@Table(name = "contas")
public class Conta extends Entidade{
	
	private String nome;
	private Correntista correntista = new Correntista();
	private List<Transacao> transacoes = new ArrayList();
	private ELeitorArquivo arquivo;
	
	@OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
	public List<Transacao> getTransacoes() {
		return transacoes;
	}
	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@ManyToOne
	@JoinColumn(name = "correntista_id")
	public Correntista getCorrentista() {
		return correntista;
	}
	public void setCorrentista(Correntista correntista) {
		this.correntista = correntista;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "arquivo")
	public ELeitorArquivo getArquivo() {
		return arquivo;
	}
	public void setArquivo(ELeitorArquivo arquivo) {
		this.arquivo = arquivo;
	}

}
