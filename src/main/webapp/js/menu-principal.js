$(document).ready(function() {
	
	carregarContas();
	
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
				
				$(".selectContaPrimaria").append("<option value ='" + ent.id + "'>" + ent.nome + "</option>");
			});
			
		}else if(semafaro == "VERMELHO"){
			
			alert(mensagens);
			
		}else{
			alert('Erro desconhecido!');
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
//	$(".dataTransacao").mask("99/99/9999");

});