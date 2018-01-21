package web.viewhelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import adaptergson.EmptyStringToDouble;
import adaptergson.EmptyStringToInteger;
import adaptergson.FactoryGson;
import adaptergson.StringToCalendar;
import controle.ITransportador;
import dominio.Entidade;
import dominio.ListaTransferencia;
import dominio.Transacao;
import dominio.Transferencia;
import enuns.EOperacao;
import web.TransportadorWeb;

public class VhListaTransferencia extends AbstractVH {
	
	private ListaTransferencia listaTransferencia;

	@Override
	public Entidade getEntidade(HttpServletRequest request) {

		Gson gson = FactoryGson.getGson();
		
		this.listaTransferencia = new ListaTransferencia();
		
		String jsonListaTransferencia = request.getParameter("listaTransferencia");
		
		try {
			this.listaTransferencia = gson.fromJson(jsonListaTransferencia, ListaTransferencia.class);			
		}catch (Exception e) {
			this.listaTransferencia = null;
			e.printStackTrace();
		}

		operacao = request.getParameter("operacao").toLowerCase();

		if (operacao.equals(EOperacao.SALVAR.getValor())) {
			
		}else if( operacao.equals(EOperacao.ALTERAR.getValor())) {
			
		}else if(operacao.equals(EOperacao.EXCLUIR.getValor())) {
		
			
		}

		return this.listaTransferencia;

	}

	@Override
	public void setView(ITransportador transportador, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		TransportadorWeb transpotadorWeb = new TransportadorWeb();

		transpotadorWeb.recebeObjetoMensagem(transportador);

		if (operacao.equals("salvar")) {
			
			ListaTransferencia lista = (ListaTransferencia) transportador.getEntidade();
			
			for(Transferencia transferencia : lista.getTransferencias()) {
				transferencia.getTransacaoPrincipal().setTransferencia(null);
				transferencia.getTransacaoPrincipal().setConta(null);
				transferencia.getTransacaoPrincipal().setSubitem(null);
				
				Transacao ts = transferencia.getTransacaoSecundaria();
				if(ts != null) {
					
					ts.setTransferencia(null);
					ts.setConta(null);
					ts.setSubitem(null);
				}
			}

			out.print(transpotadorWeb.enviarObjetoWeb());
			return;

		} else if (operacao.equals("excluir")) {

			out.print(transpotadorWeb.enviarObjetoWeb());
			return;
			
		} else if (operacao.equals("alterar")) {

			out.print(transpotadorWeb.enviarObjetoWeb());
			return;
			
		} else if (operacao.equals("listar")) {
			
//			for (Transferencia transferencia : transportador.getEntidade()) {
//				
//			}

			out.print(transpotadorWeb.enviarObjetoWeb());
			return;
		}

		out.println("operacao: " + operacao);
		// rd.forward(request, response);
	}
}
