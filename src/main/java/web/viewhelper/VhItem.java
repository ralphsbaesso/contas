package web.viewhelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import adaptergson.EmptyStringToDouble;
import adaptergson.FactoryGson;
import adaptergson.StringToCalendar;
import controle.ITransportador;
import dominio.Correntista;
import dominio.Entidade;
import dominio.Item;
import dominio.Subitem;
import enuns.EOperacao;
import web.TransportadorWeb;

public class VhItem extends AbstractVH {
	
	private Item item;

	@Override
	public Entidade getEntidade(HttpServletRequest request) {

		this.item = new Item();
		Gson gson = FactoryGson.getGson();
		
		String jsonRequisicao = request.getParameter("requisicao");
		requisicao = gson.fromJson(jsonRequisicao, Requisicao.class);

		String jsonItem = request.getParameter("entidade");
		
		if(jsonItem != null) {
			
			try {
				this.item = gson.fromJson(jsonItem, Item.class);			
			}catch (Exception e) {
				this.item = null;
				e.printStackTrace();
			}
		}

		if (requisicao.getOperacao().equals(EOperacao.SALVAR)) {
			
		}else if( requisicao.getOperacao().equals(EOperacao.ALTERAR)) {
			
		}else if(requisicao.getOperacao().equals(EOperacao.EXCLUIR)) {
		
		}else if(requisicao.getOperacao().equals(EOperacao.LISTAR)) {
			
		}

		return this.item;

	}

	@Override
	public void setView(ITransportador transportador, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		TransportadorWeb transpotadorWeb = new TransportadorWeb();
		Gson json = new Gson();

		transpotadorWeb.recebeObjetoMensagem(transportador);

		if (requisicao.getOperacao().equals(EOperacao.SALVAR)) {

		} else if (requisicao.getOperacao().equals(EOperacao.ALTERAR)) {
			
		} else if (requisicao.getOperacao().equals(EOperacao.EXCLUIR)) {
			
		} else if (requisicao.getOperacao().equals(EOperacao.LISTAR)) {
			
			List<Item> itens = (List) transpotadorWeb.getEntidades();
			
			for(Item item : itens) {
				Correntista correntista = item.getCorrentista();
				correntista.setContas(null);
				correntista.setItens(null);
				for (Subitem sub : item.getSubitens()) {
					sub.setItem(null);
				} 
			}
		}

		if(this.requisicao.getDestino() != null) {
			
			request.setAttribute("contas",json.toJson(transportador.getEntidades()));
			RequestDispatcher rd = request.getRequestDispatcher(this.requisicao.getDestino());
			rd.forward(request, response);
			return;
		}
		
		out.print(transpotadorWeb.enviarObjetoWeb());
	}
}
