package dao;

import java.util.List;

import dominio.Entidade;

public interface Idao {
	
	public boolean salvar(Entidade entidade);
	public boolean alterar(Entidade entidade);
	public boolean excluir(Entidade entidade);
	public List listar(Entidade entidade);
}
