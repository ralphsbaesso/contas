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
import dominio.Entidade;
import dominio.Subitem;
import enuns.EOperacao;
import web.TransportadorWeb;

public class VhSubitem extends AbstractVH {
	
	private Subitem subitem;

	@Override
	public Entidade getEntidade(HttpServletRequest request) {

		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Calendar.class, new StringToCalendar())
				.registerTypeAdapter(double.class, new EmptyStringToDouble())
				.registerTypeAdapter(Double.class, new EmptyStringToDouble())
				.create();
		
		this.subitem = new Subitem();
		
		String jsonSubitem = request.getParameter("subitem");
		
		if(jsonSubitem != null) {
			
			try {
				this.subitem = gson.fromJson(jsonSubitem, Subitem.class);			
			}catch (Exception e) {
				this.subitem = null;
				e.printStackTrace();
			}
		}

		operacao = request.getParameter("operacao").toLowerCase();

		if (operacao.equals(EOperacao.SALVAR.getValor())) {
			
		}else if( operacao.equals(EOperacao.ALTERAR.getValor())) {
			
		}else if(operacao.equals(EOperacao.EXCLUIR.getValor())) {
		
			
		}

		return this.subitem;

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
			
			List<Subitem> itens = (List) transpotadorWeb.getEntidades();
			
			for(Subitem subitem : itens) {
				subitem.getItem().setCorrentista(null);
			}

			out.print(transpotadorWeb.enviarObjetoWeb());
			return;
		}

		out.println("operacao: " + operacao);
		// rd.forward(request, response);
	}
}
