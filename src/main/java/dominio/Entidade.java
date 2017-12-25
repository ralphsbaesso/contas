package dominio;

import java.util.Calendar;

public class Entidade {
	
	private Calendar dataCadastro = Calendar.getInstance();
	private int id;
	
	public Calendar getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
