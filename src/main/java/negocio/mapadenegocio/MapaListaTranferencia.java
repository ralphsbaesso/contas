package negocio.mapadenegocio;

import java.util.ArrayList;
import java.util.List;

import negocio.IStrategy;
import negocio.RegraAlterarEntidade;
import negocio.RegraExcluirEntidade;
import negocio.RegraListarEntidade;
import negocio.RegraSalvarEntidade;
import negocio.SalvarListaTransferencia;
import negocio.VerificarCamposTransacoes;
import negocio.VerificarContaSegundariaDaListaTransferencia;

public class MapaListaTranferencia extends AbstractMapaNegocio{

	@Override
	public List<IStrategy> estrategiasSalvar() {
		this.estrategias = new ArrayList();
		this.estrategias.add(new VerificarContaSegundariaDaListaTransferencia());
		this.estrategias.add(new VerificarCamposTransacoes());
		this.estrategias.add(new SalvarListaTransferencia());
		
		return this.estrategias;
	}

	@Override
	public List<IStrategy> estrategiasAlterar() {
		this.estrategias = new ArrayList();
		this.estrategias.add(new RegraAlterarEntidade());
		
		return this.estrategias;
	}

	@Override
	public List<IStrategy> estrategiasExcluir() {
		this.estrategias = new ArrayList();
		this.estrategias.add(new RegraExcluirEntidade());
		
		return this.estrategias;
	}

	@Override
	public List<IStrategy> estrategiasListar() {
		this.estrategias = new ArrayList();
		this.estrategias.add(new RegraListarEntidade());
		
		return this.estrategias;
	}

}
