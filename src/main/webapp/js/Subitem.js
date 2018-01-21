var retorno;

class Subitem{

	salvar(subitem) {
		
		var json = JSON.stringify(subitem);
		
		$.ajax(
				{
					url : "Subitem",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "salvar",
						subitem : json

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
	
	alterar(subitem) {
		
		var json = JSON.stringify(subitem);

		$.ajax(
				{
					url : "Subitem",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "alterar",
						subitem : json

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

	excluir(subitem) {		

		$.ajax(
				{
					url : "Subitem",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "excluir",
						subitem : json

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
			
	listar(subitem) {		
		
		var json = JSON.stringify(subitem);
		
		$.ajax(
				{
					url : "Subitem",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "listar",
						subitem : json
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