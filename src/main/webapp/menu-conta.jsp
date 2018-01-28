<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://v40.pingendo.com/assets/bootstrap/bootstrap-4.0.0-beta.1.css" type="text/css"> 
  <link rel="stylesheet" href="css/modelo.css" type="text/css">
  <link rel="stylesheet" href="js/jQuery/jquery.datetimepicker.css">
  </head>
<title>Contas</title>

<%
	String contas = (String)request.getAttribute("contas");
	String transferencias = (String)request.getAttribute("transferencias");
	String transacoes = (String)request.getAttribute("transacoes");
%>
	
<script type="text/javascript">
	
	localStorage.setItem("contas", JSON.stringify(<%=contas %>));
	localStorage.setItem("transferencias", JSON.stringify(<%=transferencias %>));
	localStorage.setItem("transacoes", JSON.stringify(<%=transacoes %>));
</script>
<%
	
%>
</head>
<body>

<nav class="navbar navbar-expand-md bg-primary navbar-dark">
    <div class="container">
      <a class="navbar-brand" href="#">Controle de Contas</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbar2SupportedContent" aria-controls="navbar2SupportedContent" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
      <div class="collapse navbar-collapse text-center justify-content-end" id="navbar2SupportedContent">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="#" id='linkCadastrar'><i class="fa d-inline fa-lg fa-pencil"></i> Cadastrar</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" id='linkPesquisar'><i class="fa d-inline fa-lg fa-search"></i> Pesquisar</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" id='linkListar'><i class="fa d-inline fa-lg fa-server"></i> Listar</a>
          </li>
        </ul>
        <a class="btn navbar-btn btn-primary ml-2 text-white"><i class="fa d-inline fa-lg fa-user-circle-o"></i> Entrar</a>
      </div>
    </div>
  </nav>

<div id="divBody">

  <div class="py-5" align="center">
          <div class="card" style="width:90%">
            <div class="card-header ">
            	<div class="form-inline">
            		<label class="" style="width:150px"><strong>Transações</strong></label>
            		<a href="menu-principal.html">Menu-principal</a>
            	</div>
            	<div class="form-inline" align="left">
	            	<label class="" style="width:150px"><strong>Conta: </strong></label>
		            <label class="" style="width:150px" id="labelContaPrimaria" name="txtContaPrimaria" value=""></label>
				</div>
            </div>
            <div class="card-body" style="overflow:auto">
              <table class="table" id="tableTransacao">
                <thead>
                  <tr id="linha">>
                    <th>Data</th>
                    <th>Valor</th>
                    <th>Descrição</th>
                    <th>Título</th>
                    <th>Detalhamento</th>
                    <th>Item</th>
                    <th>Subitem</th>
                    <th>Quantidade</th>
                    <th>Conta</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>
                      <input class="form-control dataTransacao" type="text" name="txtData"> </td>
                    <td>
                      <input class="form-control" type="text" name="txtValor"> </td>
                    <td>
                      <textarea class="form-control" type="text" name="txtDescricao"></textarea> </td>
                    <td>
                      <textarea class="form-control" type="text" name="txtTitulo"></textarea> </td>
                    <td>
                      <textarea class="form-control" type="text" name="txtDetalhamento"></textarea> </td>
                    <td>
                     	<select class="form-control" id="selectItem" name="txtItemId">
							<option value=""></option>
						</select> </td>
                    <td>
                      <select class="form-control" id="selectSubitem" name="txtSubitemId">
							<option value=""></option>
						</select> </td>
					<td>
						 <input class="form-control" type="text" name="txtQtdeItem"> </td>
                    <td>
                      <select class="form-control" id="selectContaSecundaria" name="txtContaSegundariaId">
							<option value=""></option>
						</select> </td>
                    <td>
                      <button class="form-control" id="operacao" value="buttonRemoverLinha"> Remover </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div class="card-footer">
            	<button class="btn btn-outline-success" id="operacao" value="buttonAdicionarLinha" style="width:20%"> Adicionar nova linha</button>
            	<button class="btn btn-outline-success" id="operacao" value="buttonAdicionarItem" style="width:20%"> Adicionar novo item</button>
            	<button class="btn btn-outline-success" id="operacao" value="buttonAdicionarSubitem" style="width:20%"> Adicionar novo subitem</button>
             	<button class="btn btn-outline-success" id="operacao" value="salvar" style="width:20%"> Salvar </button>
             	<button class="btn btn-outline-alert"  id="operacao" value="csv" style="width:20%">Processar Arquivo</button>
            </div>
          </div>
  </div>
</div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12"> </div>
      </div>
    </div>
  </div>

  <script src="js/cdnjs.cloudflare.comajaxlibsjquery3.2.1jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
  <script src="js/cdnjs.cloudflare.com-ajax-libs-jquery.mask-1.14.13-jquery.mask.min.js"></script>
  <script src="js/componentes.js"></script>
  <script src="js/util.js"></script>
  <script src="js/ListaTransferencia.js"></script>
  <script src="js/Transferencia.js"></script>
  <script src="js/Conta.js"></script>
  <script src="js/Item.js"></script>
  <script src="js/Subitem.js"></script>
  <script src="js/menu-conta.js"></script>
  <script src="js/jQuery/build/jquery.datetimepicker.full.js"></script>

</body>
</html>