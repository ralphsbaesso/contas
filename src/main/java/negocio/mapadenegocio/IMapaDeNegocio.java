package negocio.mapadenegocio;

import java.util.List;

import negocio.IStrategy;

public interface IMapaDeNegocio {
	
	public List<IStrategy> estrategiasSalvar();
	public List<IStrategy> estrategiasAlterar();
	public List<IStrategy> estrategiasExcluir();
	public List<IStrategy> estrategiasListar();

}
