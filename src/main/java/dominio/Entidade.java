package dominio;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@MappedSuperclass
public class Entidade {
	
	private Calendar dataCadastro = Calendar.getInstance();
	private int id;
	private Map<String, Object> filtros = new HashMap<String, Object>();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	public Calendar getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Transient
	public Map<String, Object> getFiltros() {
		return filtros;
	}
}
