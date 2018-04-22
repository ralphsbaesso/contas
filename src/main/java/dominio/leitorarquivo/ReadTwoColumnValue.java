package dominio.leitorarquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;

import dominio.Conta;
import dominio.ListaTransferencia;
import dominio.Transacao;
import dominio.Transferencia;

public class ReadTwoColumnValue extends AbstractCarregarArquivo {
	
	public ReadTwoColumnValue(Conta conta) {
		super(conta);
	}

	@Override
	public ListaTransferencia read(BufferedReader reader) throws Exception {
		
		String linha;

		try {
			while ((linha = reader.readLine()) != null) {

				String[] campos = linha.split(";");
				
				if(campos.length == 0)
					continue;

				if (campos[0].trim().equals("Data")) {
					// Primeira linha
					continue;
				}

				Transferencia t = new Transferencia();
				Transacao tp = new Transacao();

				try {
					tp.setDataTransacao(campos[0]);
				} catch (ParseException e) {
					System.err.println("Erro em converter a data!");
					continue;
				}

				tp.setDescricao(campos[1].trim());
				tp.setDocto(campos[2].trim());

				Double valor;
				try {
					if (!campos[4].equals("") && !campos[4].equals(" ")) {

						valor = Double.valueOf(campos[4].replace(",", "."));
					} else {
						valor = Double.valueOf(campos[5].replace(",", "."));
					}
					tp.setValor(valor);

				} catch (NumberFormatException e) {
					System.err.println("Erro em converter o valor!");
					tp.setValor(Double.MIN_VALUE);
				}

				tp.setConta(conta);

				t.getTransacoes().add(tp);
				this.lista.getTransferencias().add(t);
			}
			reader.close();

		} catch (NoSuchFileException e) {
			e.printStackTrace();
			throw new Exception("Nenhum arquivo encontrado");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Erro");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro");
		}

		return lista;
	}

}
