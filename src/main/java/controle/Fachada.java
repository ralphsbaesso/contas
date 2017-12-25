package controle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Idao;
import dominio.Entidade;
import negocio.IStrategy;
import negocio.mapadenegocio.IMapaDeNegocio;

public class Fachada implements IFachada {
	
	//atributos
	private Map<String, IMapaDeNegocio> mapaEstrategias = new HashMap();
	private Map<String, Idao> mapaDao = new HashMap();
	private TransportadorFachada transportador;
	private List<IStrategy> estrategias;
	
	//construtor
	/**
	 * construtor
	 * Carrega todos os mapas de estratégias
	 */
	public Fachada(){
		
		// Carregar mapa de estratégias
		this.mapaEstrategias.put(null,null);
		
		// Carregar mapa de DAO
		this.mapaDao.put(null,null);
	}

	@Override
	public ITransportador salvar(Entidade entidade) {
		
		String nomeEntidade = entidade.getClass().getName();
		this.transportador = new TransportadorFachada();
		Idao dao = this.mapaDao.get(nomeEntidade);
		this.estrategias = this.mapaEstrategias.get(nomeEntidade).estrategiasSalvar();
		
		this.transportador.setEntidade(entidade);
		this.transportador.mapaObjetos().put("dao", dao);
		
		this.executarEstrategias(this.transportador);
		return this.transportador;
		
	}

	@Override
	public ATransportador alterar(Entidade entidade) {
		
		String nomeEntidade = entidade.getClass().getName();
		this.transportador = new TransportadorFachada();
		Idao dao = this.mapaDao.get(nomeEntidade);
		this.estrategias = this.mapaEstrategias.get(nomeEntidade).estrategiasAlterar();
		
		this.transportador.setEntidade(entidade);
		this.transportador.mapaObjetos().put("dao", dao);
		
		this.executarEstrategias(this.transportador);
		return this.transportador;
		
	}

	@Override
	public ATransportador excluir(Entidade entidade) {

		String nomeEntidade = entidade.getClass().getName();
		this.transportador = new TransportadorFachada();
		this.estrategias = this.mapaEstrategias.get(nomeEntidade).estrategiasExcluir();
		Idao dao = this.mapaDao.get(nomeEntidade);
		
		this.transportador.setEntidade(entidade);
		this.transportador.mapaObjetos().put("dao", dao);
		
		this.executarEstrategias(this.transportador);
		return this.transportador;
		
	}

	@Override
	public ATransportador listar(Entidade entidade) {

		String nomeEntidade = entidade.getClass().getName();
		this.transportador = new TransportadorFachada();
		this.estrategias = this.mapaEstrategias.get(nomeEntidade).estrategiasListar();
		Idao dao = this.mapaDao.get(nomeEntidade);
		
		this.transportador.setEntidade(entidade);
		this.transportador.mapaObjetos().put("dao", dao);
		
		this.executarEstrategias(this.transportador);
		return this.transportador;
		
	}
	
	private void executarEstrategias(ITransportador transportador) {
		
		for(IStrategy st: this.estrategias){
			
			if(!st.processar(transportador)) {
				return;
			}
		}
	}
}
