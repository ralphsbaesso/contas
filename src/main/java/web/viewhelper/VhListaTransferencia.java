package web.viewhelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import adaptergson.FactoryGson;
import controle.ITransportador;
import dominio.Entidade;
import dominio.ListaTransferencia;
import dominio.Transferencia;
import enuns.EOperacao;
import web.TransportadorWeb;

@MultipartConfig
public class VhListaTransferencia extends AbstractVH {

	private ListaTransferencia listaTransferencia;

	@Override
	public Entidade getEntidade(HttpServletRequest request) {

		this.listaTransferencia = new ListaTransferencia();
		Gson gson = FactoryGson.getGson();

		String jsonRequisicao = request.getParameter("requisicao");
		requisicao = gson.fromJson(jsonRequisicao, Requisicao.class); // request.getContentType() request.getParts()

		String jsonListaTransferencia = request.getParameter("entidade");

		try {
			this.listaTransferencia = gson.fromJson(jsonListaTransferencia, ListaTransferencia.class);
		} catch (Exception e) {
			this.listaTransferencia = null;
			e.printStackTrace();
		}

		if (requisicao.getOperacao().equals(EOperacao.SALVAR)) {
			

			try {
							
				if(!request.getParts().isEmpty()) {

					InputStream in = request.getPart("file").getInputStream();
					BufferedReader reader = new BufferedReader( new InputStreamReader(in, "UTF-8"));
					
					this.listaTransferencia.setReader(reader);
				}

			} catch (IOException | ServletException e) {
				e.printStackTrace();
			}

		} else if (requisicao.getOperacao().equals(EOperacao.ALTERAR)) {

		} else if (requisicao.getOperacao().equals(EOperacao.EXCLUIR)) {

		} else if (requisicao.getOperacao().equals(EOperacao.LISTAR)) {

		}

		return this.listaTransferencia;

	}

	@Override
	public void setView(ITransportador transportador, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		TransportadorWeb transpotadorWeb = new TransportadorWeb();
		Gson json = new Gson();

		transpotadorWeb.recebeObjetoMensagem(transportador);

		if (requisicao.getOperacao().equals(EOperacao.SALVAR)) {

			ListaTransferencia lista = (ListaTransferencia) transportador.getEntidade();

			for (Transferencia transferencia : lista.getTransferencias()) {

				transferencia.setTransacoes(null);
			}

		} else if (requisicao.getOperacao().equals(EOperacao.ALTERAR)) {

		} else if (requisicao.getOperacao().equals(EOperacao.EXCLUIR)) {

		} else if (requisicao.getOperacao().equals(EOperacao.LISTAR)) {

		}

		if (this.requisicao.getDestino() != null) {

			request.setAttribute("contas", json.toJson(transportador.getEntidades()));
			RequestDispatcher rd = request.getRequestDispatcher(this.requisicao.getDestino());
			rd.forward(request, response);
			return;
		}

		out.print(transpotadorWeb.enviarObjetoWeb());
	}
	
	private String getFile(Part filePart) {
		
		// Create path components to save the file
	    final String path = "C:\\Users\\ralph.baesso\\Desktop";
	    final String fileName = "novo";

	    OutputStream out = null;
	    InputStream filecontent = null;
//	    final PrintWriter writer = response.getWriter();

	    try {
	        out = new FileOutputStream(new File(path + File.separator + fileName));
	        filecontent = filePart.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        
	        if (out != null) {
	        	out.close();
	        }
	        if (filecontent != null) {
	        	filecontent.close();
	        }
	    } catch (FileNotFoundException fne) {
	    	fne.getLocalizedMessage();
//	        writer.println("You either did not specify a file to upload or are "
//	                + "trying to upload a file to a protected or nonexistent "
//	                + "location.");
//	        writer.println("<br/> ERROR: " + fne.getMessage());

//	        LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", 
//	                new Object[]{fne.getMessage()});
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
	    return path;
	}
	
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    System.out.println( "Part Header = " + partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
}
