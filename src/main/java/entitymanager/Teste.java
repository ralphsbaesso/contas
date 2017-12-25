package entitymanager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity(name="tb_teste")
public class Teste {

	@Column
	private String nome;
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
