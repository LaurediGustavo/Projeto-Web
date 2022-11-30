<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="Stylesheet" type="text/css" href="css/estilo.css">
	<link rel="Stylesheet" type="text/css" href="css/home.css">
	
	<script type="text/javascript">
  
	  	$(document).ready(function() {
	  		validarLogin();
	  		chamarGastos();
	  		chamarGanhos();
	  		novoGasto();
	  		novoGanho();
	  		chamarPainelDeControle();
	  		chamarDadosUsuario();
	  		
			$("#btnSair").click(function() {
				$.ajax({
					url: "logout",
					type: "GET",
					success: function( data ) {
						window.location.href = 'login.jsp';
					}
				});
			});
			
			$("#btnGravarGasto").click(function() {
				var frmData = $("#idFormGasto").serialize();
				$.ajax({
					url: "gasto",
					data: frmData,
					type: "POST",
					success: function( data ) {
						if(data == "true") {
							$("#valor").val("");
							$("#descricao").val("");
							$("#btnFechar").click();
							
							chamarGastos();
							chamarPainelDeControle()
						}
						else {
							alert(data);							
						}
					}
				});
			});
			
			$("#btnGravarGanho").click(function() {
				var frmData = $("#idFormGasto").serialize();
				$.ajax({
					url: "ganho",
					data: frmData,
					type: "POST",
					success: function( data ) {
						if(data == "true") {
							$("#valor").val("");
							$("#descricao").val("");
							$("#btnFechar").click();
							
							chamarGanhos();
							chamarPainelDeControle()
						}
						else {
							alert(data);							
						}
					}
				});
			});
		});
  
	  	function validarLogin() {
	  		$.ajax({
				url: "login",
				type: "GET",
				success: function( data ) {
					if(data == "false") {
						window.location.href = 'login.jsp';
					}
				}
			});
	  	}
	  	
	  	function chamarGastos() {
	  		$.ajax({
				url: "gasto",
				type: "GET",
			  	contentType: "application/json;charset=utf-8",
		        dataType: "json",
				success: function(data) {
					$('#panelGastos').html('');
					$('#panelGastos').html(getHtml(data.gasto));
				}
			});
	  	}
	  	
	  	function chamarGanhos() {
	  		$.ajax({
				url: "ganho",
				type: "GET",
			  	contentType: "application/json;charset=utf-8",
		        dataType: "json",
				success: function(data) {
					$('#panelGanhos').html('');
					$('#panelGanhos').html(getHtml(data.ganho));
				}
			});
	  	}
	  	
	  	function chamarPainelDeControle() {
	  		$.ajax({
				url: "home",
				type: "GET",
			  	contentType: "application/json;charset=utf-8",
		        dataType: "json",
				success: function(data) {					
					$('#panelControle').html('');
					
					var html = '';
					html += '<div class="divValores">';
					html += '<p>Valor Total: R$ ' + data.valorTotal + '</p>';
					html += '</div><br>';
					html += '<div class="divValores">';
	                html += '<span class="valorGanho">Valor Ganho no Mês: R$ ' + data.valorGanhoNoMes + '</span>';
	            	html += '<span class="valorGasto">Valor Gasto no Mês: R$ ' + data.valorGastoNoMes + '</span>';
	            	html += '</div>';
					
					$('#panelControle').html(html);
					
				}
			});
		}
	  	
	  	function getHtml(value) {
	  		var html = '';
			
		 	$.each(value, function (key, item) {
		 		html += '<div class="lista">';
                html += '<span class="listaNome">R$ ' + item.valor + '</span>';
                html += '<span class="listaData">' + item.data + '</span>';
                html += '</div >';						
	        });
		 	
		 	return html;
		}
	  	
	  	function novoGasto() {
	  		$("#btnGasto").click(function() {
	  			$("#tituloModal").text('Novo Gasto');
	  			$("#btnGravarGanho").css('display', 'none');
	  			$("#btnGravarGasto").css('display', 'block');
	  		});
	  	}
	  	
		function novoGanho() {
			$("#btnGanho").click(function() {
				$("#tituloModal").text('Novo Ganho');
				$("#btnGravarGasto").css('display', 'none');
				$("#btnGravarGanho").css('display', 'block');
			});
	  	}
		
		function chamarDadosUsuario() {
			$.ajax({
				url: "usuario",
				type: "GET",
				success: function(data) {
					var usuario = JSON.parse(data);
					$("#imgPreview").attr("src", usuario.foto);
					$("#nomeUsuario").text(usuario.nome + " " + usuario.sobreNome);
				}
			});
		}
	  	
	</script>
	
</head>
<body>
	<div class="vh-100 gradient-custom">
		<div class="container text-white">
  			<div class="row">
				<div class="col col-lg-3 bg-dark mt-1 rounded alturaDiv">
					<div class="text-center">
						<p>Ganhos</p>
					</div>
					<div class="alturaDivGanho" id="panelGanhos">
						
					</div>
				    <div class="form-group text-center">
					    <button id="btnGanho" data-toggle="modal" data-target="#gastoModal" data-whatever="@mdo" class="btn btn-outline-light btn-lg px-2" type="button">Cadastrar</button>				    
				    </div>
				</div>
				<div class="col bg-dark rounded mr-1 ml-1 mt-1 alturaDiv">
					<div class="text-center painelDeControleUsuario">
						<img id="imgPreview" alt="Carregar Imagem" src="">
						<p id="nomeUsuario"></p>
					</div>
					<div class="painelDeControleDados text-center" id="panelControle">
						
					</div>
					<div class="form-group text-center">
					    <a href="perfil.jsp" id="btnPerfil" class="btn btn-outline-light btn-lg px-2" type="button">Editar Perfil</a>
					    <button id="btnSair" class="btn btn-outline-light btn-lg px-2" type="button">Log Out</button>				    
				    </div>
				</div>
				<div class="col col-lg-3 bg-dark mt-1 rounded alturaDiv">
					<div class="text-center">
						<p>Gastos</p>
					</div>
					<div class="alturaDivGanho" id="panelGastos">
						
					</div>
					<div class="form-group text-center">
					    <button id="btnGasto" data-toggle="modal" data-target="#gastoModal" data-whatever="@mdo" class="btn btn-outline-light btn-lg px-2" type="button">Cadastrar</button>				    
				    </div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="gastoModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 id="tituloModal" class="modal-title" id="exampleModalLabel"></h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="idFormGasto">
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">Valor:</label>
							<input type="number" class="form-control" step="0.01" id="valor" name="valor" min="0.01">
						</div>
						<div class="form-group">
							<label for="message-text" class="col-form-label">Descrição:</label>
							<textarea class="form-control" maxlength="100" name="descricao" id="descricao"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="btnFechar" class="btn btn-secondary"
						data-dismiss="modal">Fechar</button>
					<button type="button" id="btnGravarGasto" class="btn btn-primary">Gravar</button>
					<button type="button" id="btnGravarGanho" class="btn btn-primary">Gravar</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>