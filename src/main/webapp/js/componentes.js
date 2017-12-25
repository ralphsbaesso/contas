/**
*
*/

function makeDivPy5(parametro){
	return "<div class='py-5'>" + parametro + "</div>";
}

function makeDivContainer(parametro){
	return "<div class='container'>" + parametro + "</div>";
}

function makeDivColMd8(parametro){
	return "<div class='col-md-8'>" + parametro + "</div>";
}

function makeDivAlertSucesso(parametro){
	return "<div class='alert alert-success h-25 mx-auto role='alert'>" + parametro + "</div>";
}

function makeMensagemSucesso(parametro){
	return "<div class='py-5'><div class='container'><div class='col-md-8'><div class='alert alert-success h-25 mx-auto role='alert'>" + parametro + "</div></div></div></div>";
}

function makeMensagemAdvertencia(parametro){
	return "<div class='py-5'><div class='container'><div class='col-md-8'><div class='alert alert-danger h-25 mx-auto role='alert'>" + parametro + "</div></div></div></div>";
}

function makeTable(parametro){
	return "<div class='py-5'><div class='container'><div class='row'><div class='col-md-12'>" +
          "<h1 class='display-4'>Lista</h1>" +
          "<table class='table'>" +
          parametro +
          "</table></div></div></div></div>";
}

function makeTableTHead(parametro){
	return "<thead>" + parametro + "</thead>";
}

function makeTableTBody(parametro){
	return "<tbody>" + parametro + "</tbody>";
}

function makeTableTFoot(parametro, qtdade){
	 return "<tfoot><tr><td colspan='" + qtdade + "'><label class='form-control bg-light'>" + parametro + "</label> </td></tr></tfoot>";
}

function makeTableTR(parametro){
	return "<tr id='tr'>" + parametro + "</tr>";
}

function makeTableTD(parametro){
	return "<td>" + parametro + "</td>";
}

function makeTableTDDoublo(parametro1, parametro2){
	return "<td colspan='2' class='form-inline'>" + parametro1 + " " + parametro2 +  "</td>";
}

function htmlButtonUpdate(id){
	
	if(typeof id === undefined)
		id = '';

	return "<button id='" + id + "' class='btn btn-outline-warning form-control buttonAlterar' name='operacao'>Alterar</button>";
}

function htmlButtonDelete(id){
	
	if(typeof id === undefined)
		id = '';

	return "<button id='" + id + "' class='btn btn-outline-danger form-control buttonExcluir' name='operacao'>Excluir</button>";
}

