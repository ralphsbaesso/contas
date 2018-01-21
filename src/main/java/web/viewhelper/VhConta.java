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
				.registerTypeAdapter(double.class, new EmptyStringToDouble())
				.registerTypeAdapter(Double.class, new EmptyStringToDouble())
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
		}else {
			
			int id;
			try {
				id = Integer.valueOf(request.getParameter("txtContaId"));
			}catch(NumberFormatException e) {
				e.printStackTrace();
				id = 0;
			}
			
			conta.setId(id);
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
		Gson json = new Gson();

		transpotadorWeb.recebeObjetoMensagem(transportador);

		if (operacao.equals("salvar")) {

		} else if (operacao.equals("excluir")) {
			
		} else if (operacao.equals("alterar")) {
			
		} else if (operacao.equals("listar")) {
			
			List<Conta> contas = (List) transpotadorWeb.getEntidades();
			
			for(Conta conta : contas) {
				conta.getCorrentista().setContas(null);
				conta.getCorrentista().setItens(null);
				conta.setTransacoes(null);
			}
			
		}
		
		String destino = request.getParameter("destino");
		
		if(destino != null) {
			
			request.setAttribute("contas",json.toJson(transportador.getEntidades()));
			RequestDispatcher rd = request.getRequestDispatcher(destino);
			rd.forward(request, response);
			return;
		}
		
		out.print(transpotadorWeb.enviarObjetoWeb());

	}
}
