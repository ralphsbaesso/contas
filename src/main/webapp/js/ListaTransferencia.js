var retorno;

class ListaTransferencia{

	salvar(listaTransferencia) {
		
		var json = JSON.stringify(listaTransferencia);
		
		$.ajax(
				{
					url : "ListaTransferencia",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "salvar",
						listaTransferencia : json

					},
					async: false,
					beforeSend : function() {
						// carregando ...
					}
				}).done(
				function(msg) {
					retorno = msg;
					
				}).fail(function(jqXHR, textStatus, msg) {
					retorno = msg;
		});
		
		return retorno;
	}
	
	alterar(listaTransferencia) {
		
		var json = JSON.stringify(listaTransferencia);

		$.ajax(
				{
					url : "ListaTransferencia",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "alterar",
						listaTransferencia : json

					},
					async: false,

					beforeSend : function() {
						// carregando
					}
				}).done(
				function(msg) {
					retorno = msg;
					
				}).fail(function(jqXHR, textStatus, msg) {
					retorno = msg
		});
		
		return retorno;
	}

	excluir(listaTransferencia) {		

		$.ajax(
				{
					url : "ListaTransferencia",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "excluir",
						listaTransferencia : json

					},
					async: false,
					beforeSend : function() {
						// carregando ... 
					}
				}).done(
				function(msg) {
					retorno = msg;
					
				}).fail(function(jqXHR, textStatus, msg) {
					retorno = msg;
		});
		
		return retorno;
	}
			
	listar(listaTransferencia) {		
		
		var json = JSON.stringify(listaTransferencia);
		
		$.ajax(
				{
					url : "ListaTransferencia",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "listar",
						listaTransferencia : json
					},
					async: false,
					beforeSend : function() {
						// carregando ...
					}
				}).done(function(msg) {

					retorno = msg;
					
				}).fail(function(jqXHR, textStatus, msg) {
					retorno = msg;
		});
		
		return retorno;		
	}
}