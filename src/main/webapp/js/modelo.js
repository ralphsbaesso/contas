$(document).ready(function() {
	
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

			var novaLinha = $('#novaLinha tr').clone();
			novaLinha.hide();
			$('#tableTransacao tbody ').append(novaLinha);
			novaLinha.slideDown(0.5);

		} else if (operacao == 'buttonremoverlinha') {

			var linha = $(this).parent().parent();
			linha.slideUp('fast', function() {
				linha.remove();
			});
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
				
				$("#tableTransacao tr #selectConta").append("<option value ='" + ent.id + "'>" + ent.nome + "</option>");
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
	
	// atualizar select do Subitem depois de escolher o Item
	$("body").on('change', '#selectItem', function() {
		
		var linha = $(this).parent().parent();
		linha.find("#selectSubitem").html("");
		
		var id = $(this).val()
		
		if(id == ""){
			linha.find("#selectSubitem").append("<option value =''></option>");
		}else{
			
			var subitens = JSON.parse($(this).find("[value = " + id + "]").attr('name'));
			
			subitens.forEach(function(subitem){
				linha.find("#selectSubitem").append("<option value ='" + subitem.id + "'>" + subitem.descricao + "</option>");
			});
		}
		
		//alert();
	});

});