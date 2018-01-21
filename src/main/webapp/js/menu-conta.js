$(document).ready(function() {
	
	carregarContaPrimaria();
	carregarTransferencias();
	carregarContas();
	carregarItensComSubitens();
	
	// OPERAÇÃO
	$("body").on('click', '#operacao', function() {

		var operacao = $(this).val().toLowerCase();

		if (operacao == 'salvar') {
			salvar();
		} else if (operacao == 'alterar') {
			alterar();
		} else if (operacao == 'excluir') {
			excluir();
		} else if (operacao == 'listar') {
			listar();
		} else if (operacao == 'limparcampos') {

			limparCampos();
		} else if (operacao == 'buttonadicionarlinha') {

			var linhas = $('#tableTransacao tbody tr').clone();
			var indice = linhas.length -1;
			var novaLinha = linhas[indice];
			
			$('#tableTransacao tbody ').append(novaLinha);
			
			$(".dataTransacao").mask("99/99/9999");

		} else if (operacao == 'buttonremoverlinha') {

			var linha = $(this).parent().parent();
			
			if(linha.parent().find('tr').length == 1){
				return;
			}
			
			linha.slideUp('fast', function() {
				linha.remove();
			});
		} else if(operacao == "buttonadicionaritem"){
			modalNovoItem();
		} else if(operacao == "buttonadicionarsubitem"){
			modalNovoSubitem();
		} else if(operacao == "salvaritem"){
			salvarItem();
		} else if(operacao == "salvarsubitem"){
			salvarSubitem();
		}
	});
	
	function carregarContas(){
		
		var objConta = new Conta();
		var objJson = objConta.listar();
		
		var contas = objJson.entidades;
		var conta = objJson.entidade;
		var mensagens = objJson.mensagens;
		var semafaro = objJson.semafaro;

		
		if(semafaro == "VERDE"){
			
			contas.forEach(function(ent){
				
				$("#selectContaPrimaria").append("<option value ='" + ent.id + "'>" + ent.nome + "</option>");
				$("#tableTransacao tr #selectContaSecundaria").append("<option value ='" + ent.id + "'>" + ent.nome + "</option>");
			});
			
		}else if(semafaro == "VERMELHO"){
			
			alert(mensagens);
			
		}else{
			alert('Erro desconhecido!');
		}
	}
	
	function carregarContaPrimaria(){
		
		var contas = localStorage.getItem("contas");
		
		if(contas != "null" && contas != "[object Object]"){
			
			contas = JSON.parse(contas);
			
			$("#labelContaPrimaria").text(contas[0].nome).val(contas[0].id);
			
			localStorage.removeItem("contas");
		}
		
	}
	
	function carregarTransferencias(){
		
		var transferencias = localStorage.getItem("transferencias");
		
		if(transferencias != "null" && transferencias != null && transferencias != "[object Object]"){
			
			transferencias = JSON.parse(transferencias);
			var tableTransacao = $("#tableTransacao");
			$("#tableTransacao tbody").html("");
			
			for(var i = 0; i < transferencias.length; i++){
				var trans = transferencias[i];
				
				var contaNome;
				try{
					contaNome = trans.transacaoSecundaria.conta.nome
				}catch(err){
					contaNome = "Desconhecido";
				}
				
				var tr = $("<tr/>")
						.append("<td>" + formatarData(trans.transacaoPrincipal.dataCadastro) + "</td>")
						.append("<td>" + trans.transacaoPrincipal.valor + "</td>")
						.append("<td>" + trans.transacaoPrincipal.descricao + "</td>")
						.append("<td>" + trans.transacaoPrincipal.titulo + "</td>")
						.append("<td>" + trans.transacaoPrincipal.detalhamento + "</td>")
						.append("<td>" + trans.transacaoPrincipal.subitem.item.descricao + "</td>")
						.append("<td>" + trans.transacaoPrincipal.subitem.descricao + "</td>")
						.append("<td>" + trans.transacaoPrincipal.qtdeItem + "</td>")
						.append("<td>" + contaNome + "</td>")
						;
				tableTransacao.append(tr);
			}
			
			localStorage.removeItem("transferencias");
		}
		
	}
	
	function carregarItensComSubitens(){
		
		var objItem = new Item();
		var objJson = objItem.listar();
		
		var itens = objJson.entidades;
		var item = objJson.entidade;
		var mensagens = objJson.mensagens;
		var semafaro = objJson.semafaro;
		
		if(semafaro == "VERDE"){
			
			// limpar selects
			$("#tableTransacao tr #selectItem").html("<option value =''></option>");
			$("#tableTransacao tr #selectSubitem").html("<option value =''></option>");
			
			itens.forEach(function(ent){
				
				var jsonSuitem = JSON.stringify(ent.subitens);
				
				$("#tableTransacao tr #selectItem").append("<option name='" + jsonSuitem + "' value ='" + ent.id + "'>" + ent.descricao + "</option>");
			});
			
		}else if(semafaro == "VERMELHO"){
			
			alert(mensagens);
			
		}else{
			alert('Erro desconhecido!');
		}
		
	}
	
	function salvar(){
			
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
				
				subitem.id = linhas.eq(i).find("td [name = txtSubitemId]").val();
				contaPrincipal.id = $("#labelContaPrimaria").val();
				transacaoPrincipal.dataTransacao = linhas.eq(i).find("td [name = txtData]").val();
				transacaoPrincipal.valor = linhas.eq(i).find("td [name = txtValor]").val();
				transacaoPrincipal.descricao = linhas.eq(i).find("td [name = txtDescricao]").val();
				transacaoPrincipal.titulo = linhas.eq(i).find("td [name = txtTitulo]").val();
				transacaoPrincipal.detalhamento = linhas.eq(i).find("td [name = txtDetalhamento]").val();
				transacaoPrincipal.qtdeItem = linhas.eq(i).find("td [name = txtQtdeItem]").val();
				contaSecundaria.id = linhas.eq(i).find("td [name = txtContaSegundariaId]").val();
				
				transacaoPrincipal.conta = contaPrincipal;
				transacaoPrincipal.subitem = subitem;
				
				transacaoSecundaria.conta = contaSecundaria;

				transferencia.transacaoPrincipal = transacaoPrincipal;
				transferencia.transacaoSecundaria = transacaoSecundaria;
				
				transferencias.push(transferencia);
			}
			
			var listaTransferencia = new Object();
			
			listaTransferencia.transferencias = transferencias;	
			
			var objListaTransferencia = new ListaTransferencia();
			var objJson = objListaTransferencia.salvar(listaTransferencia);
			
			if(objJson.semafaro == 'VERDE'){
				alert("transações salvas com sucesso");
			}else{
				alert("algo de errado\n " + objJson.mensagens);
			}
			
	}
	
	function listar(){
		
		var conta = new Object();
		var transacao = new Object();
		var transferencia = new object();
		
		conta.id = $("#selectContaPrimaria").val();
		transacacao.conta = conta;
		transferencia.transacaoPrincipal = transacao;
		
		var objTransferencia = new Transferencia();
		var objJson = objTransferencia.listar(transferencia);
		
		if(objJson.semafaro == 'VERDE'){
			//alert("transações salvas com sucesso");
		}else{
			alert("algo de errado\n " + objJson.mensagens);
		}
		
	}
	
	// montar modal novo item
	function modalNovoItem(){
		
		$("#divBody").prepend(
		"<div class='modal fade' id='modalNovoItem' tabindex='-1' role='dialog' aria-labelledby='myModalLabel'>" +
		  "<div class='modal-dialog' role='document'>" +
		    "<div class='modal-content'>" +
		      "<div class='modal-header'>" +
		        "<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" +
		        "<h4 class='modal-title' id='myModalLabel'>Novo Item</h4>" +
		      "</div>" +
		      "<div class='modal-body'>" +
		      "<ul class='list-group'>" +
				"<li class='list-group-item form-inline'> <label class='form-control bg-secondary text-white' name='lblDescricao'>Descrição</label>" +
				"<input class='form-control' type='text' name='txtDescricao' value=''> </li>" +
				"<li class='list-group-item form-inline'> <label class='form-control bg-secondary text-white' name='lblDetalhamento'>Detalhamento</label>" +
				"<input class='form-control' type='text' name='txtDetalhamento' value=''> </li>" +
				"<li class='list-group-item form-inline'>" +
				"<button class='btn btn-outline-success form-control' name='operacao' id='operacao' value='salvarItem' style='width:100%'>Salvar</button>" +
				"</li>" +
				"</ul>" +
		      "</div>" +
		      "<div class='modal-footer'>" +
		        "<button type='button' class='btn btn-default' data-dismiss='modal'>Fechar</button>" +
		      "</div>" +
		    "</div>" +
		  "</div>" +
		"</div> " );
		
		// abrir modal
		 $('#modalNovoItem').modal('show');
		 $('.modal-backdrop').css({
			   'background-color' : '#FFE4C4',
			   'opacity' : 0.4
		 });
		 // fechando o modal
		 $('#modalNovoItem').on('hide.bs.modal', function(){
			 $('#modalNovoItem .modal-body').html("");
			 $('#divAdvertencia').html('');
			 // limpando as mensagens
		 });
	}
	
	// montar modal novo Subitem
	function modalNovoSubitem(){
		
		$("#divBody").prepend(
		"<div class='modal fade' id='modalNovoSubitem' tabindex='-1' role='dialog' aria-labelledby='myModalLabel'>" +
		  "<div class='modal-dialog' role='document'>" +
		    "<div class='modal-content'>" +
		      "<div class='modal-header'>" +
		        "<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" +
		        "<h4 class='modal-title' id='myModalLabel'>Novo Subitem</h4>" +
		      "</div>" +
		      "<div class='modal-body'>" +
		      "<ul class='list-group'>" +
				"<li class='list-group-item form-inline'> <label class='form-control bg-secondary text-white' name='lblDescricao'>Descrição</label>" +
				"<input class='form-control' type='text' name='txtDescricao' value=''> </li>" +
				"<li class='list-group-item form-inline'> <label class='form-control bg-secondary text-white' name='lblDetalhamento'>Detalhamento</label>" +
				"<input class='form-control' type='text' name='txtDetalhamento' value=''> </li>" +
				"<li class='list-group-item form-inline'> <label class='form-control bg-secondary text-white' name='lblSelectItem'>Item</label>" +
				"<select class='form-control' id='selectItem' name='txtItemId'></select> </li>" +
				"<li class='list-group-item form-inline'>" +
				"<button class='btn btn-outline-success form-control' name='operacao' id='operacao' value='salvarSubitem' style='width:100%'>Salvar</button>" +
				"</li>" +
				"</ul>" +
		      "</div>" +
		      "<div class='modal-footer'>" +
		        "<button type='button' class='btn btn-default' data-dismiss='modal'>Fechar</button>" +
		      "</div>" +
		    "</div>" +
		  "</div>" +
		"</div> " );
		
		// carregar itens
		var options = $("#tableTransacao tr #selectItem option");
		var select = $("#divBody #selectItem");
		select.html(options.clone());
		
		// abrir modal
		 $('#modalNovoSubitem').modal('show');
		 $('.modal-backdrop').css({
			   'background-color' : '#FFE4C4',
			   'opacity' : 0.4
		 });
		 // fechando o modal
		 $('#modalNovoSubitem').on('hide.bs.modal', function(){
			 $('#modalNovoSubitem .modal-body').html("");
			 $('#divAdvertencia').html('');
			 // limpando as mensagens
		 });
	}
	
	function salvarItem(){
		
		//modalNovoItem
		var item = new Object();
		item.descricao = $("#modalNovoItem [name = txtDescricao").val();
		item.detalhamento = $("#modalNovoItem [name = txtDetalhamento").val();
		
		var objItem = new Item();
		var objJson = objItem.salvar(item);
		
		if(objJson.semafaro == "VERDE"){
			alert("Item salvo com sucesso!");
			$('#modalNovoItem').modal('hide');
			carregarItensComSubitens();
		}
		
	}
	
	function salvarSubitem(){
		
		//modalNovoSubitem
		var subitem = new Object();
		subitem.descricao = $("#modalNovoSubitem [name = txtDescricao").val();
		subitem.detalhamento = $("#modalNvoSubitem [name = txtDetalhamento").val();
		subitem.isPretacao = false;
		var item = new Object();
		item.id = $("#modalNovoSubitem [name = txtItemId").val();
		subitem.item = item;
		
		var objSubitem = new Subitem();
		var objJson = objSubitem.salvar(subitem);
		
		if(objJson.semafaro == "VERDE"){
			alert("Subitem salvo com sucesso!");
			$('#modalNovoSubitem').modal('hide');
			carregarItensComSubitens();
		}
		
	}
	
	function atualizarSubitem(elemento){
		
		var linha = $(elemento).parent().parent();
		linha.find("#selectSubitem").html("<option value =''></option>");
		
		var id = $(elemento).val()
		
		if(id == ""){
			linha.find("#selectSubitem").append("<option value =''></option>");
		}else{
			
			var subitens = JSON.parse($(elemento).find("[value = " + id + "]").attr('name'));
			
			subitens.forEach(function(subitem){
				linha.find("#selectSubitem").append("<option value ='" + subitem.id + "'>" + subitem.descricao + "</option>");
			});
		}
	}
	
	// atualizar select do Subitem depois de escolher o Item
	$("body").on('change', '#selectItem', function() {
		
		atualizarSubitem(this);
	});
	
	// CONFIGURAÇÕES
	$(".dataTransacao").mask("99/99/9999");

});