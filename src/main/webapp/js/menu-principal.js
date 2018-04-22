$(document).ready(function() {
	
	carregarContas();
	
	// OPERAÇÃO
	$("body").on('click', '#operacao', function() {

		var operacao = $(this).val();

		if (operacao == 'novasTransacoes') {
			novasTransacoes();
		} else if (operacao == 'listarTransacoes') {
			listarTransacoes();
		} else if (operacao == 'salvarArquivo') {
			salvarArquivo();
		}
	});
	
	function novasTransacoes(){
		
		var conta = new Object();
		conta.id = $("#selectContaPrimaria").val();
		
		var requisicao = new Object();
		requisicao.operacao = "LISTAR";
		requisicao.destino = "menu-conta.jsp";
		
		requisicaoPost(conta, requisicao, "Conta");
	}
	
	function listarTransacoes(){
		
		var conta = {};
		conta.id = $("#selectContaPrimaria").val();
		
		var transacao = {};
		transacao.conta = conta;
		
		var requisicao = new Object();
		requisicao.operacao = "LISTAR";
		requisicao.destino = "menu-conta.jsp";
		
		requisicaoPost(transacao, requisicao, "Transacao");
	}
	
	function salvarArquivo(){
		
		var conta = new Object();
		conta.id = $("#selectContaPrimaria").val();
		
		var transacao = new Object();
		transacao.conta = conta;
		
		var transferencia = new Object();
		transferencia.transacoes = [transacao];
		
		var listaTransferencia = new Object();
		listaTransferencia.transferencias  = [transferencia];
		//listaTransferencia.caminhoArquivo = $("#file");
		
		var requisicao = new Object();
		requisicao.operacao = "SALVAR";
		
		var objJson = requisicaoAjaxWithFile(listaTransferencia, requisicao, "ListaTransferencia", document.getElementById('file').files[0]);
		
		var semafaro = objJson.semafaro;
		var mensagens = objJson.mensagens;
		var mensagem ="";
		
		if(semafaro == "VERDE"){
			alert("arquivo salvo");
		}else if(semafaro == "VERMELHO"){
			
			if(mensagens != null && mensagens.length > 0){
   			 
	   			 for(var i = 0; i < mensagens.length; i++){
	   				 mensagem += mensagens[i];
	   				 mensagem += "\n";
   			 }
	   		 }else{
	   			 mensagem = "Erro!"
	   		 }
			
			alert(mensagem);
			
		}else{
			alert("erro desconheciado!");
		}
	}
	
	function carregarContas(){
		
		var servlet = "Conta";
		var conta = new Object();
		var requisicao = new Object();
		
		requisicao.operacao = "LISTAR";

		var objJson = requisicaoAjax(conta, requisicao, servlet);		
		
		var contas = objJson.entidades;
		var conta = objJson.entidade;
		var mensagens = objJson.mensagens;
		var semafaro = objJson.semafaro;
		
		if(semafaro == "VERDE"){
			
			contas.forEach(function(ent){
				
				$("#selectContaPrimaria").append("<option value ='" + ent.id + "'>" + ent.nome + "</option>");
			});
			
		}else if(semafaro == "VERMELHO"){
			
			alert(mensagens);
			
		}else{
			alert('Erro desconhecido!');
		}
	}
	
});