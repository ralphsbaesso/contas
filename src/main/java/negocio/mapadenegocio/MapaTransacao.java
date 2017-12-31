package negocio.mapadenegocio;

import java.util.ArrayList;
import java.util.List;

import negocio.IStrategy;

public class MapaTransacao extends AbstractMapaNegocio{

	@Override
	public List<IStrategy> estrategiasSalvar() {
		this.estrategias = new ArrayList();
		this.estrategias.add(null);
		
		return this.estrategias;
	}

	@Override
	public List<IStrategy> estrategiasAlterar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IStrategy> estrategiasExcluir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IStrategy> estrategiasListar() {
		// TODO Auto-generated method stub
		return null;
	}

}
