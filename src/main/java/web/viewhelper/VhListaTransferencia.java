package web.viewhelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import adaptergson.FactoryGson;
import controle.ITransportador;
import dominio.Entidade;
import dominio.ListaTransferencia;
import dominio.Transacao;
import dominio.Transferencia;
import enuns.EOperacao;
import processosarquivos.ArquivoSantanderCC;
import processosarquivos.IProcessadorArquivos;
import web.TransportadorWeb;

public class VhListaTransferencia extends AbstractVH {
	
	private ListaTransferencia listaTransferencia;

	@Override
	public Entidade getEntidade(HttpServletRequest request) {

		Gson gson = FactoryGson.getGson();
		
		this.listaTransferencia = new ListaTransferencia();
		
		String jsonListaTransferencia = request.getParameter("entidade");
		
		try {
			this.listaTransferencia = gson.fromJson(jsonListaTransferencia, ListaTransferencia.class);			
		}catch (Exception e) {
			this.listaTransferencia = null;
			e.printStackTrace();
		}

		operacao = request.getParameter("operacao").toLowerCase();

		if (operacao.equals(EOperacao.SALVAR.getValor())) {
			
			if(!this.listaTransferencia.getFiltros().isEmpty()) {
				
				String opcao = this.listaTransferencia.getFiltros().get("arquivo").toString();
				
				IProcessadorArquivos pa = new ArquivoSantanderCC();
				this.listaTransferencia.getTransferencias().addAll(pa.processaArquivo(null));
			}
			
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
				
				transferencia.setTransacoes(null);
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
