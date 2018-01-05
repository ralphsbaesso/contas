var retorno;

class Item{

	salvar(item) {
		
		var json = JSON.stringify(item);
		
		$.ajax(
				{
					url : "Item",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "salvar",
						item : json

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
	
	alterar(item) {
		
		var json = JSON.stringify(item);

		$.ajax(
				{
					url : "Item",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "alterar",
						item : json

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

	excluir(item) {		

		$.ajax(
				{
					url : "Item",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "excluir",
						item : json

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
			
	listar(item) {		
		
		var json = JSON.stringify(item);
		
		$.ajax(
				{
					url : "Item",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "listar",
						item : json
					},
					async: false,
					beforeSend : function() {
						// carregendo ...
					}
				}).done(function(msg) {

					retorno = msg;
					
				}).fail(function(jqXHR, textStatus, msg) {
					retorno = msg;
		});
		
		return retorno;		
	}
}