var retorno;

class Transferencia{

	salvar(transferencia) {
		
		var json = JSON.stringify(transferencia);
		
		$.ajax(
				{
					url : "Transferencia",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "salvar",
						transferencia : json

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
	
	alterar(transferencia) {
		
		var json = JSON.stringify(transferencia);

		$.ajax(
				{
					url : "Transferencia",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "alterar",
						transferencia : json

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

	excluir(transferencia) {		

		$.ajax(
				{
					url : "Transferencia",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "excluir",
						transferencia : json

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
			
	listar(transferencia) {		
		
		var json = JSON.stringify(transferencia);
		
		$.ajax(
				{
					url : "Transferencia",
					type : 'post',
					dataType : 'json',
					data : {
						operacao : "listar",
						transferencia : json
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