package web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import adaptergson.FactoryGson;
import conexao.ControleConexao;
import controle.Fachada;
import controle.IFachada;
import controle.ITransportador;
import dominio.Entidade;
import web.command.CommandAlterar;
import web.command.CommandExcluir;
import web.command.CommandListar;
import web.command.CommandSalvar;
import web.command.ICommand;
import web.viewhelper.IViewHelper;
import web.viewhelper.Requisicao;
import web.viewhelper.VhConta;
import web.viewhelper.VhItem;
import web.viewhelper.VhListaTransferencia;
import web.viewhelper.VhSubitem;
import web.viewhelper.VhTransacao;
import web.viewhelper.VhTransferencia;

@MultipartConfig
public class Servlet extends HttpServlet {
	
	// atributos
	private Map<String, ICommand> commands;
	private Map<String, IViewHelper> vhs;
	private ITransportador mensagem;
	private Requisicao requisicao = new Requisicao();
	
	// construtor
	public Servlet(){
		commands = new HashMap();
		commands.put("salvar", new CommandSalvar());
		commands.put("alterar", new CommandAlterar());
		commands.put("excluir", new CommandExcluir());
		commands.put("listar", new CommandListar());
		
		vhs = new HashMap();
		vhs.put("/contas/ListaTransferencia", new VhListaTransferencia());
		vhs.put("/contas/Conta", new VhConta());
		vhs.put("/contas/Item", new VhItem());
		vhs.put("/contas/Subitem", new VhSubitem());
		vhs.put("/contas/Transferencia", new VhTransferencia());
		vhs.put("/contas/Transacao", new VhTransacao());
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		new ControleConexao();
	}

	public void service(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {// request.getPart("file")
		
		Gson gson = FactoryGson.getGson();//request.getParameter("txtArquivo")
		
		// Obt?m a uri que invocou esta servlet (O que foi definido no methdo do form html)
		String uri = request.getRequestURI();
		
		// Obt?m um viewhelper indexado pela uri que invocou esta servlet
		IViewHelper vh = vhs.get(uri);
		
		// O viewhelper retorna a entidade especifica para a tela que chamou
		// esta servlet
		Entidade entidade = vh.getEntidade(request);
		
		String jsonRequisicao = request.getParameter("requisicao");
		requisicao = gson.fromJson(jsonRequisicao, Requisicao.class);
		
		ICommand cmd = commands.get(requisicao.getOperacao().getValor());
		if(cmd != null){
			this.mensagem = cmd.executar(entidade);
		}else{
			//System.out.println("apenas controle visual");
		}
		
		vh.setView(this.mensagem, request, response);
		
	}
}
