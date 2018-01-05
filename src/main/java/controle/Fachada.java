package controle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityTransaction;

import conexao.ControleConexao;
import dao.DaoConta;
import dao.DaoItem;
import dao.DaoListaTransferencia;
import dao.Idao;
import dominio.Conta;
import dominio.Entidade;
import dominio.Item;
import dominio.ListaTransferencia;
import negocio.IStrategy;
import negocio.mapadenegocio.IMapaDeNegocio;
import negocio.mapadenegocio.MapaConta;
import negocio.mapadenegocio.MapaItem;
import negocio.mapadenegocio.MapaListaTranferencia;

public class Fachada implements IFachada {

	// atributos
	private Map<String, IMapaDeNegocio> mapaEstrategias = new HashMap();
	private Map<String, Idao> mapaDao = new HashMap();
	private TransportadorFachada transportador;
	private List<IStrategy> estrategias;

	// construtor
	/**
	 * construtor Carrega todos os mapas de estratégias
	 */
	public Fachada() {

		// Carregar mapa de estratégias
		this.mapaEstrategias.put(Conta.class.getName(), new MapaConta());
		this.mapaEstrategias.put(ListaTransferencia.class.getName(), new MapaListaTranferencia());
		this.mapaEstrategias.put(Item.class.getName(), new MapaItem());

		// Carregar mapa de DAO
		this.mapaDao.put(Conta.class.getName(), new DaoConta());
		this.mapaDao.put(ListaTransferencia.class.getName(), new DaoListaTransferencia());
		this.mapaDao.put(Item.class.getName(), new DaoItem());
	}

	@Override
	public ITransportador salvar(Entidade entidade) {

		String nomeEntidade = entidade.getClass().getName();
		this.transportador = new TransportadorFachada();
		Idao dao = this.mapaDao.get(nomeEntidade);
		this.estrategias = this.mapaEstrategias.get(nomeEntidade).estrategiasSalvar();

		this.transportador.setEntidade(entidade);
		this.transportador.setDao(dao);

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
		this.transportador.setDao(dao);

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
		this.transportador.setDao(dao);

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
		this.transportador.setDao(dao);

		this.executarEstrategias(this.transportador);
		return this.transportador;

	}

	private void executarEstrategias(ITransportador transportador) {

		// abre conexao
		EntityTransaction transaction =  ControleConexao.getTransaction();
		transaction.begin();
		try {
			for (IStrategy st : this.estrategias) {

				if (!st.processar(transportador)) {
					transaction.rollback();
					return;
				}
			}
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			
		} finally {
			ControleConexao.finishTransaction();
		}

	}
}
