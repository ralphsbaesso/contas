$(document).ready(
		
		function() {

			
			function salvar() {
				
				var transferencias = new Array();
				
				var linhas = $("#tableTransacao tbody tr");
				
				for(var i = 0; i < linhas.length; i++){
					
					//objetos
					var transferencia = new Object();
					var transacaoPrincipal = new Object();
					var transacaoSecundaria = new Object();
					var contaPrincipal = new Object();
					var contaSecundaria = new Object();
					var subitem = new Object();
					
					subitem.descricao = linhas.eq(i).find("td [name = txtSubitem]").val();
					contaPrincipal.nome = linhas.eq(i).find("td [name = txtContaPrincipal]").val();
					transacaoPrincipal.dataTransacao = linhas.eq(i).find("td [name = txtData]").val();
					transacaoPrincipal.valor = linhas.eq(i).find("td [name = txtValor]").val();
					transacaoPrincipal.descricao = linhas.eq(i).find("td [name = txtDescricao]").val();
					transacaoPrincipal.titulo = linhas.eq(i).find("td [name = txtTitulo]").val();
					transacaoPrincipal.detalhamento = linhas.eq(i).find("td [name = txtDetalhamento]").val();
					contaSecundaria.nome = linhas.eq(i).find("td [name = txtContaSecundaria]").val();
					
					transacaoPrincipal.conta = contaPrincipal;
					transacaoPrincipal.subitem = subitem;
					
					transacaoSecundaria.conta = contaSecundaria;

					transferencia.transacaoPrincipal = transacaoPrincipal;
					transferencia.transacaoSecundaria = transacaoSecundaria;
					
					transferencias.push(transferencia);
				}
				
				var listaTransferencia = new Object();
				
				listaTransferencia.transferencias = transferencias;				

				var json = JSON.stringify(listaTransferencia);

				$.ajax(
						{
							url : "ListaTransferencia",
							type : 'post',
							dataType : 'json',
							data : {
								operacao : "salvar",
								transferencia : json

							},

							beforeSend : function() {
								$("#sucesso").html(
										"<p align='center'>Carregando...</p>");
							}
						}).done(
						function(msg) {

							try {
								var json = JSON.parse(msg);

							} catch (err) {
								var json = msg;
							}

							var especialidade = json.entidade;
							var especialidades = json.entidades;
							var mensagens = json.mensagens;
							var semafaro = json.semafaro;

							var mensagem = "";

							// verificar respostas
							if (semafaro == 'VERDE') {

								$('#divBody').html("");
								$('#divBody').append(
										makeMensagemSucesso("Especialidade "
												+ especialidade.descricao
												+ " salvo com sucesso!"));

							} else if (semafaro == 'VERMELHO') {

								if (mensagens != null && mensagens.length > 0) {

									for (var i = 0; i < mensagens.length; i++) {
										mensagem += mensagens[i];
										mensagem += "</br>";
									}
								} else {
									mensagem = "Erro!"
								}

								modalAdvertencia(mensagem);
							}
						}).fail(function(jqXHR, textStatus, msg) {
					alert(msg + " Erro grave!!!");
				});
			}

			
			// configurações 
			
			$(".dataTransacao").mask("99/99/9999");
		}); // ready jQuery