<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Cadastro</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="Stylesheet" type="text/css" href="css/estilo.css">
	
	<script type="text/javascript">
		
	  	$(document).ready(function() {
	  		validarLogin();
	  		preencherCampos();
	  		
	  		$("#inputFile").change(function (event) {
				var file = event.target.files[0];
				var reader = new FileReader();
				reader.readAsDataURL( file );
				reader.onload = function () {
					var imgBase64 = reader.result;
					$("#foto").val(imgBase64);
				};
				reader.onerror = function ( error ) {
					console.log('Error: ', error);
				};
			});
	  		
			$("#btnEnviar").click(function() {
				var frmData = $("#idForm").serialize();
				$.ajax({
					url: "usuario",
					data: frmData,
					type: "POST",
					success: function(data) {
						if(data == "true") {
							window.location.href = 'home.jsp';							
						}
						else {
							alert(data);
						}
					}
				});
			});
		});
	  	
	  	function preencherCampos() {
	  		$.ajax({
				url: "usuario",
				type: "GET",
				success: function(data) {
					var usuario = JSON.parse(data);
					
					$("#id").val(usuario.id);
					$("#nome").val(usuario.nome);
					$("#sobrenome").val(usuario.sobreNome);
					$("#email").val(usuario.email);
				}
			});
	  	}
	  	
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
  
	</script>
</head>
<body>
	<div class="vh-100 gradient-custom">
		<div class="container py-5 h-100">
			<div class="card bg-dark text-white pt-3 pl-5 pr-5" style="border-radius: 1rem;">
				<form id="idForm">
				    <div class="form-group">
			      		<label for="email">Nome:</label>
				      	<input type="text" class="form-control" maxlength="100" id="nome" placeholder="Digite seu nome" name="nome" required>
				    </div>
   				    <div class="form-group">
			      		<label for="email">Sobrenome:</label>
				      	<input type="text" class="form-control" maxlength="100" id="sobrenome" placeholder="Digite seu Sobrenome" name="sobrenome" required>
				    </div>
   				    <div class="form-group">
			      		<label for="email">Email:</label>
				      	<input type="email" readonly="true" maxlength="100" class="form-control" id="email" placeholder="Digite seu email" name="email" required>
				    </div>
				    <div class="form-group">
			      		<label for="email">Senha:</label>
				      	<input type="password" class="form-control" maxlength="100" id="senha" placeholder="Digite sua Senha" name="senha" required>
				    </div>
				    <input type="hidden" id="foto" name="foto" value="">
				    <input type="hidden" id="id" name="id" value="">
			   	</form>
				<div class="form-group mt-3">
					<label for="foto">Foto:</label><br />
					<input id="inputFile" type="file" class="file">
				</div>
			    <div class="form-group text-center mt-5">
				    <button id="btnEnviar" class="btn btn-outline-light btn-lg px-5" type="button">Atualizar</button>
				    <a href="home.jsp" id="btnVoltar" class="btn btn-outline-light btn-lg px-5" type="button">Voltar</a>				    
			    </div>
			</div>
		</div>
	</div>
</body>
</html>