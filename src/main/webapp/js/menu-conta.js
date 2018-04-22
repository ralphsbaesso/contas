$(document).ready(function() {
	
	// variaveis globais
	var selectPrincipal = $("<select>");
	var selectConta = $("<select>").addClass("form-control").attr('name','txtContaSegundariaId');
	
	carregarItensComSubitens();
	carregarContaPrincipal();
	carregarContas();
	carregarTransacaoes();
	
	
	// OPERAÇÃO
	$("body").on('click', '#operacao', function() {

		var operacao = $(this).val();

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
		} else if (operacao == 'buttonAdicionarLinha') {

			var linhas = $('#tableTransacao tbody tr').clone();
			var indice = linhas.length -1;
			var novaLinha = linhas[indice];
			
			$('#tableTransacao tbody ').append(novaLinha);
			
			$(".dataTransacao").datetimepicker({
								    format: 'd/m/Y',                
								    mask:'99/99/9999',
								    timepicker:false
								})
								.css({
									"width":"110px"
								});

		} else if (operacao == 'buttonremoverlinha') {

			var linha = $(this).parent().parent();
			
			if(linha.parent().find('tr').length == 1){
				return;
			}
			
			linha.slideUp('fast', function() {
				linha.remove();
			});
		} else if(operacao == "buttonAdicionarItem"){
			modalNovoItem();
		} else if(operacao == "buttonAdicionarSubitem"){
			modalNovoSubitem();
		} else if(operacao == "salvarItem"){
			salvarItem();
		} else if(operacao == "salvarSubitem"){
			salvarSubitem();
		}

	});
	
	function carregarContas(){
		
		var requisicao = new Object();
		requisicao.operacao = "LISTAR";
		
		var objJson = requisicaoAjax(new Object(), requisicao, "Conta");
		
		var contas = objJson.entidades;
		var conta = objJson.entidade;
		var mensagens = objJson.mensagens;
		var semafaro = objJson.semafaro;

		
		if(semafaro == "VERDE"){
			
			$("#selectContaPrimaria").append("<option>");
			$("#tableTransacao tr #selectContaSecundaria").append("<option>");
			selectConta.append("<option>");
			
			contas.forEach(function(ent){
				
				$("#selectContaPrimaria").append("<option value ='" + ent.id + "'>" + ent.nome + "</option>");
				$("#tableTransacao tr #selectContaSecundaria").append("<option value ='" + ent.id + "'>" + ent.nome + "</option>");
				selectConta.append("<option  value ='" + ent.id + "'>" + ent.nome + "</option>");
			});
			
		}else if(semafaro == "VERMELHO"){
			
			alert(mensagens);
			
		}else{
			alert('Erro desconhecido!');
		}
	}
	
	function carregarContaPrincipal(){
		
		var conta = localStorage.getItem("conta");
		
		if(conta != "null" && conta != "[object Object]"){
			
			conta = JSON.parse(conta);
			
			$("#labelContaPrimaria").text(conta.nome).val(conta.id);
		}
		
		return conta;
	}
	
	function carregarTransacaoes(){
		
		var transacoes = localStorage.getItem("transacoes");
		
		if(transacoes != "null" && transacoes != null && transacoes != "[object Object]"){
			
			transacoes = JSON.parse(transacoes);
			var tableTransacao = $("#tableTransacao");
			$("#tableTransacao tbody").html("");
			
			for(var i = 0; i < transacoes.length; i++){
				
				var trans = transacoes[i];
				var transSecundaria = {id:0};
				
				var divContaSecundaria;
				try{
					divContaSecundaria = $("<div>").prepend(htmlSelected(selectConta, trans.transferencia.transacoes[0].conta.id));
					transSecundaria.id = trans.transferencia.transacoes[0].id;
				}catch(err){
					divContaSecundaria = $("<div>").prepend(htmlSelected(selectConta, 0));
				}
				
				var subitem = trans.subitem;
				if(typeof subitem === "undefined"){
					subitem = {id:0};
					subitem.item = {id:0};
				}
				
				var transferencia = trans.transferencia;
				
				var divSelectItem = $("<div>").prepend(htmlSelected(selectPrincipal, subitem.item.id));
				
				var idSubitem = subitem.id;
				
				var divSelectSubitem = $("<div>");
				
				var selectSubitem = $("<select class='form-control' name='txtSubitemId' id='selectSubitem'></select>");
				
				if(idSubitem > 0){
					
					
					var subitens = JSON.parse(divSelectItem.find("[value = " + subitem.item.id + "]").attr('name'));
					
					subitens.forEach(function(subitem){
						selectSubitem.append("<option value ='" + subitem.id + "'>" + subitem.descricao + "</option>");
					});
					
					divSelectSubitem.append(htmlSelected(selectSubitem, idSubitem));
				}
				
				divSelectSubitem.append(selectSubitem);
				
				
				var tr = $("<tr/>")
				.attr('id', transferencia.id)
				.append("<td> " + (i + 1) + " <label name='txtTransacaoId' style='display:none'>" + trans.id + "</label></td>")
				.append("<td><label name='txtData'>" + formatarData(trans.dataTransacao) + "</label></td>")
				.append("<td><label name='txtValor'>" + trans.valor + "</label></td>")
				.append("<td><textarea class='form-control' name='txtDescricao'>" + trans.descricao + "</textarea></td>")
				.append("<td><input type='text' class='form-control' name='txtTitulo' value='" + trans.titulo + "'></td>")
				.append("<td><input type='text' class='form-control' name='txtDetalhamento' value='" + trans.detalhamento + "'></td>")
				.append("<td>" + divSelectItem.html() + "</td>")
				.append("<td>" + divSelectSubitem.html() + "</td>")
				.append("<td> <input class='form-control' type='text' name='txtQtdeItem' value='" + trans.qtdeItem + "'></td>")
				.append("<td>" + divContaSecundaria.html() + "<input type='hidden' name='txtTransacaoSecundariaId' value='" + transSecundaria.id + "'></td>")
				;
				tableTransacao.append(tr);
				
				
			}
			
			localStorage.removeItem("transacoes");
		}
		
	}
	
	function carregarItensComSubitens(){
		
		var requisicao = new Object();
		requisicao.operacao = "LISTAR";
		
		var objJson = requisicaoAjax(new Object(), requisicao, "Item");
		
		var itens = objJson.entidades;
		var item = objJson.entidade;
		var mensagens = objJson.mensagens;
		var semafaro = objJson.semafaro;
		
		if(semafaro == "VERDE"){
			
			selectPrincipal = $("<select>").addClass("form-control").attr("id", "selectItem");
			
			// limpar selects
			$("#tableTransacao tr #selectItem").html("<option value =''></option>");
			$("#tableTransacao tr #selectSubitem").html("<option value =''></option>");
			selectPrincipal.html("<option value =''></option>");
			
			itens.forEach(function(ent){
				
				var jsonSuitem = JSON.stringify(ent.subitens);
				
				$("#tableTransacao tr #selectItem").append("<option name='" + jsonSuitem + "' value ='" + ent.id + "'>" + ent.descricao + "</option>");
				selectPrincipal.append("<option name='" + jsonSuitem + "' value ='" + ent.id + "'>" + ent.descricao + "</option>");
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
				var transacoes = new Array();
				var transacao = new Object();
				var contaPrincipal = new Object();
				var contaSecundaria = new Object();
				var subitem = new Object();
				
				//transacao principal
				subitem.id = linhas.eq(i).find("td [name = txtSubitemId]").val();
				contaPrincipal.id = $("#labelContaPrimaria").val();
				transacao.dataTransacao = linhas.eq(i).find("td [name = txtData]").text();
				transacao.valor = linhas.eq(i).find("td [name = txtValor]").text();
				transacao.descricao = linhas.eq(i).find("td [name = txtDescricao]").val();
				transacao.titulo = linhas.eq(i).find("td [name = txtTitulo]").val();
				transacao.detalhamento = linhas.eq(i).find("td [name = txtDetalhamento]").val();
				transacao.qtdeItem = linhas.eq(i).find("td [name = txtQtdeItem]").val();
				
				var idTransferencia = linhas.eq(i).attr('id');
				if(typeof idTransferencia === "undefined"){
					transferencia.id = 0;
				}else{
					transferencia.id = idTransferencia;
				}
				
				var idTransacao = linhas.eq(i).find("td [name=txtTransacaoId]").text();
				if(typeof idTransacao === "undefined"){
					transacao.id = 0;
				}else{
					transacao.id = idTransacao;
				}				
				
				transacao.conta = contaPrincipal;
				transacao.subitem = subitem;
				
				transacoes.push(transacao)
				
				//transacao secundaria
				transacao = new Object();
				transacao.id = linhas.eq(i).find("td [name = txtTransacaoSecundariaId]").val();
				
				contaSecundaria.id = linhas.eq(i).find("td [name = txtContaSegundariaId]").val();
				
				transacao.conta = contaSecundaria;
				
				transacoes.push(transacao)

				transferencia.transacoes = transacoes;
				
				transferencias.push(transferencia);
			}
			
			var listaTransferencia = new Object();
			
			listaTransferencia.transferencias = transferencias;	
		
			var requisicao = {operacao : 'SALVAR'};
			
			var objJson = requisicaoAjax(listaTransferencia, requisicao, "ListaTransferencia");
			
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
	
	//modal para atualizar uma célula
	function corrigirCelula(celula){
		
		$("#divBody").prepend(
				"<div class='modal fade' id='modalNovoSubitem' tabindex='-1' role='dialog' aria-labelledby='myModalLabel'>" +
				  "<div class='modal-dialog' role='document'>" +
				    "<div class='modal-content'>" +
				      "<div class='modal-header'>" +
				        "<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" +
				        "<h4 class='modal-title' id='myModalLabel'>Novo Subitem</h4>" +
				      "</div>" +
				      "<div class='modal-body'>" +
				      	celula.text() +
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
		
		var requisicao = {operacao : "SALVAR"};
		var objJson = requisicaoAjax(item, requisicao, "Item");
		
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
		
		var requisicao = {operacao : "SALVAR"};
		var objJson = requisicaoAjax(subitem, requisicao, "Subitem");
		
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
	
	// corrigir celula aqui.
	
	// CONFIGURAÇÕES
	
	
	$.datetimepicker.setLocale('pt-BR');
	
	$('.dataTransacao')
		.datetimepicker({
		    format: 'd/m/Y',                
		    mask:'99/99/9999',
		    timepicker:false
		})
		.css({
			"width":"110px"
		});
	
});// jquery