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

import adaptergson.EmptyStringToNumber;
import adaptergson.StringToCalendar;
import controle.ITransportador;
import dominio.Conta;
import dominio.Entidade;
import enuns.EOperacao;
import web.TransportadorWeb;

public class VhConta extends AbstractVH {
	
	private Conta conta;

	@Override
	public Entidade getEntidade(HttpServletRequest request) {

		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Calendar.class, new StringToCalendar())
				.registerTypeAdapter(double.class, new EmptyStringToNumber())
				.registerTypeAdapter(Double.class, new EmptyStringToNumber())
				.create();
		
		this.conta = new Conta();
		
		String jsonConta = request.getParameter("conta");
		
		if(jsonConta != null) {
			
			try {
				this.conta = gson.fromJson(jsonConta, Conta.class);			
			}catch (Exception e) {
				this.conta = null;
				e.printStackTrace();
			}
		}

		operacao = request.getParameter("operacao").toLowerCase();

		if (operacao.equals(EOperacao.SALVAR.getValor())) {
			
		}else if( operacao.equals(EOperacao.ALTERAR.getValor())) {
			
		}else if(operacao.equals(EOperacao.EXCLUIR.getValor())) {
		
			
		}

		return this.conta;

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
			
			List<Conta> contas = (List) transpotadorWeb.getEntidades();
			
			for(Conta conta : contas) {
				conta.getCorrentista().setContas(null);
				conta.getCorrentista().setItens(null);
			}
			
			

			out.print(transpotadorWeb.enviarObjetoWeb());
			return;
		}

		out.println("operacao: " + operacao);
		// rd.forward(request, response);
	}
}
