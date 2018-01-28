package processosarquivos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import dominio.Conta;
import dominio.Transacao;
import dominio.Transferencia;

public class ArquivoSantanderCC implements IProcessadorArquivos {

	@Override
	public List<Transferencia> processaArquivo(Conta conta) {
		List<Transferencia> transferencias = new ArrayList();

		Path path = Paths.get("arquivos/arquivo.csv");

		String linha;

		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);) {
			while ((linha = reader.readLine()) != null) {

				String[] campos = linha.split(";");

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

				// conta
				tp.setConta(conta);

				t.getTransacoes().add(tp);
				transferencias.add(t);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}

		return transferencias;
	}

}
