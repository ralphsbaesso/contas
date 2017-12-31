package negocio.mapadenegocio;

import java.util.ArrayList;
import java.util.List;

import negocio.IStrategy;

public abstract class AbstractMapaNegocio implements IMapaDeNegocio {
	
	protected List<IStrategy> estrategias;
}
