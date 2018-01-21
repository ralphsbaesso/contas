package web.viewhelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import adaptergson.FactoryGson;
import controle.ITransportador;
import dominio.Conta;
import dominio.Entidade;
import dominio.Item;
import dominio.Subitem;
import dominio.Transacao;
import dominio.Transferencia;
import enuns.EOperacao;
import web.TransportadorWeb;

public class VhTransferencia extends AbstractVH {

	private Transferencia transferencia;

	@Override
	public Entidade getEntidade(HttpServletRequest request) {

		Gson gson = FactoryGson.getGson();

		this.transferencia = new Transferencia();

		String jsonTransferencia = request.getParameter("transferencia");
		
		if(jsonTransferencia != null) {
			
			try {
				this.transferencia = gson.fromJson(jsonTransferencia, Transferencia.class);
			} catch (Exception e) {
				this.transferencia = null;
				e.printStackTrace();
			}
		}else {
			
			int id;
			try {
				id = Integer.valueOf(request.getParameter("txtContaId"));
			}catch(Exception e) {
				id = 0;
			}
			this.transferencia.getTransacaoPrincipal().getConta().setId(id);
		}

		operacao = request.getParameter("operacao").toLowerCase();

		if (operacao.equals(EOperacao.SALVAR.getValor())) {

		} else if (operacao.equals(EOperacao.ALTERAR.getValor())) {

		} else if (operacao.equals(EOperacao.EXCLUIR.getValor())) {

		}

		return this.transferencia;

	}

	@Override
	public void setView(ITransportador transportador, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		Gson json = new Gson();
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

			List<Transferencia> transferencias = (List) transportador.getEntidades();

			for (Transferencia t : transferencias) {

				Transacao tp = t.getTransacaoPrincipal();
				Transacao ts = t.getTransacaoSecundaria();

				tp.setTransferencia(null);

				Conta cp = tp.getConta();
				cp.setTransacoes(null);
				cp.setCorrentista(null);

				Subitem subitem = tp.getSubitem();
				Item item = subitem.getItem();

				item.setCorrentista(null);
				item.setSubitens(null);

				if (ts != null) {

					ts.setTransferencia(null);
					ts.setSubitem(null);

					Conta cs = ts.getConta();
					
					if(cs != null) {
						cs.setTransacoes(null);
						cs.setCorrentista(null);
					}
				}
			}
		}

		String destino = request.getParameter("destino");
		
		if(destino != null) {
			
			request.setAttribute("transferencias",json.toJson(transportador.getEntidades()));
			RequestDispatcher rd = request.getRequestDispatcher(destino);
			rd.forward(request, response);
			return;
		}
		
		out.print(transpotadorWeb.enviarObjetoWeb());
		return;
	}
}
