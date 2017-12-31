package controle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import conexao.ControleConexao;
import dao.Idao;
import dominio.Entidade;
import dominio.ListaTransferencia;
import dominio.Transacao;
import negocio.IStrategy;
import negocio.mapadenegocio.IMapaDeNegocio;
import negocio.mapadenegocio.MapaTransacao;
import negocio.mapadenegocio.MapaListaTranferencia;

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
		this.mapaEstrategias.put(Transacao.class.getName(), new MapaTransacao());
		this.mapaEstrategias.put(ListaTransferencia.class.getName(), new MapaListaTranferencia());
	}

	@Override
	public ITransportador salvar(Entidade entidade) {
		
		
		String nomeEntidade = entidade.getClass().getName();
		this.transportador = new TransportadorFachada();
		this.estrategias = this.mapaEstrategias.get(nomeEntidade).estrategiasSalvar();
		
		this.transportador.setEntidade(entidade);
		
		// abre conexao
		ControleConexao.entityManager.getTransaction().begin();
		try {
			
			this.executarEstrategias(this.transportador);
		}catch (Exception e) {
			e.printStackTrace();
			ControleConexao.entityManager.getTransaction().rollback();
			// TODO: handle exception
		}finally {
			ControleConexao.entityManager.close();
		}
		return this.transportador;
		
	}

	@Override
	public ATransportador alterar(Entidade entidade) {
		
		String nomeEntidade = entidade.getClass().getName();
		this.transportador = new TransportadorFachada();
		Idao dao = this.mapaDao.get(nomeEntidade);
		this.estrategias = this.mapaEstrategias.get(nomeEntidade).estrategiasAlterar();
		
		this.transportador.setEntidade(entidade);
//		this.transportador.mapaObjetos().put("dao", dao);
		
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
//		this.transportador.mapaObjetos().put("dao", dao);
		
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
//		this.transportador.mapaObjetos().put("dao", dao);
		
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
