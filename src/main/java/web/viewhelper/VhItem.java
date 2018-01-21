package web.viewhelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import adaptergson.EmptyStringToDouble;
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

		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Calendar.class, new StringToCalendar())
				.registerTypeAdapter(double.class, new EmptyStringToDouble())
				.registerTypeAdapter(Double.class, new EmptyStringToDouble())
				.create();
		
		this.item = new Item();
		
		String jsonItem = request.getParameter("item");
		
		if(jsonItem != null) {
			
			try {
				this.item = gson.fromJson(jsonItem, Item.class);			
			}catch (Exception e) {
				this.item = null;
				e.printStackTrace();
			}
		}

		operacao = request.getParameter("operacao").toLowerCase();

		if (operacao.equals(EOperacao.SALVAR.getValor())) {
			
		}else if( operacao.equals(EOperacao.ALTERAR.getValor())) {
			
		}else if(operacao.equals(EOperacao.EXCLUIR.getValor())) {
		
			
		}

		return this.item;

	}

	@Override
	public void setView(ITransportador transportador, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		TransportadorWeb transpotadorWeb = new TransportadorWeb();

		transpotadorWeb.recebeObjetoMensagem(transportador);

		if (operacao.equals("salvar")) {

			out.print(transpotadorWeb.enviarObjetoWeb());
			return;

		} else if (operacao.equals("excluir")) {

			out.print(transpotadorWeb.enviarObjetoWeb());
			return;
			
		} else if (operacao.equals("alterar")) {

			out.print(transpotadorWeb.enviarObjetoWeb());
			return;
			
		} else if (operacao.equals("listar")) {
			
			List<Item> itens = (List) transpotadorWeb.getEntidades();
			
			for(Item item : itens) {
				Correntista correntista = item.getCorrentista();
				correntista.setContas(null);
				correntista.setItens(null);
				for (Subitem sub : item.getSubitens()) {
					sub.setItem(null);
				} 
			}

			out.print(transpotadorWeb.enviarObjetoWeb());
			return;
		}

		out.println("operacao: " + operacao);
		// rd.forward(request, response);
	}
}
