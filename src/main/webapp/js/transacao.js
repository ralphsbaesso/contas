$(document).ready(function(){
	
	// OPERAÇÃO
	$("body").on('click', '#operacao', function(){
		
		var operacao = $(this).val().toLowerCase();
		if(operacao == 'salvar'){
			salvar();
		}else if(operacao == 'alterar'){
			alterar();
		}else if(operacao == 'excluir'){
			excluir();
		}else if(operacao == 'listar'){
			listar();
		}else if(operacao == 'limparcampos'){
			
			limparCampos();			
		}else if(operacao == 'buttonadicionarlinha'){
			alert('ok');
		}
	});
	
});