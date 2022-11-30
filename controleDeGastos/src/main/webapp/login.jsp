<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="Stylesheet" type="text/css" href="css/estilo.css">
	
	<script type="text/javascript">
  
	  	$(document).ready(function() {
			$("#btnEnviar").click(function() {
				var frmData = $("#idForm").serialize();
				$.ajax({
					url: "login",
					data: frmData,
					type: "POST",
					success: function( data ) {
						if(data == 'true') {
							window.location.href = 'home.jsp';
						}
						else {
							$("#loginInvalido").html(data);							
						}
					}
				});
			});
		});
  
	</script>
</head>
<body>
	<section class="vh-100 gradient-custom">
		<div class="container py-5 h-100">
			<div class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12 col-md-8 col-lg-6 col-xl-5">
					<div class="card bg-dark text-white" style="border-radius: 1rem;">
						<div class="card-body text-center">
							<div class="mb-md-5 mt-md-4 pb-5">

								<h2 class="fw-bold mb-2 text-uppercase">Login</h2>
								<p id="loginInvalido" class="text-white-50 mb-5"></p>
								
								<form id="idForm">
									<div class="form-outline form-white mb-4">
										<input type="email" name="email" id="typeEmailX" class="form-control form-control-lg" />
										<label class="form-label" for="typeEmailX">Email</label>
									</div>
	
									<div class="form-outline form-white mb-4">
										<input type="password" name="senha" id="typePasswordX" class="form-control form-control-lg" />
										<label class="form-label" for="typePasswordX">Password</label>
									</div>
	
									<button id="btnEnviar" class="btn btn-outline-light btn-lg px-5" type="button">Login</button>								
								</form>
							</div>
							<div>
								<p class="mb-0"> Não possui conta? 
									<a href="cadastro.jsp" class="text-white-50 fw-bold">Cadastre-se</a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>