package dominio;

public class Conta extends Entidade{
	
	private String nome;
	private Correntista correntista = new Correntista();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Correntista getCorrentista() {
		return correntista;
	}
	public void setCorrentista(Correntista correntista) {
		this.correntista = correntista;
	}

}
