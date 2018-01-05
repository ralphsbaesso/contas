package controle;

import dao.Idao;

public class TransportadorFachada extends ATransportador {

	private Idao dao;

	public Idao getDao() {
		return dao;
	}

	public void setDao(Idao dao) {
		this.dao = dao;
	}

}
