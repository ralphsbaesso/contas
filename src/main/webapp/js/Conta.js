var retorno;

class Conta{

	salvar(conta) {
		
		var json = JSON.stringify(conta);
		
		$.ajax(
				{
					url : "Conta",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "salvar",
						conta : json

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
	
	alterar(conta) {
		
		var json = JSON.stringify(conta);

		$.ajax(
				{
					url : "Conta",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "alterar",
						conta : json

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

	excluir(conta) {		

		$.ajax(
				{
					url : "Conta",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "excluir",
						conta : json

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
			
	listar(conta) {		
		
		var json = JSON.stringify(conta);
		
		$.ajax(
				{
					url : "Conta",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "listar",
						conta : json
					},
					async: false,
					beforeSend : function() {
						$("#sucesso").html(
								"<p align='center'>Carregando...</p>");
					}
				}).done(function(msg) {

					retorno = msg;
					
				}).fail(function(jqXHR, textStatus, msg) {
					retorno = msg;
		});
		
		return retorno;		
	}
}