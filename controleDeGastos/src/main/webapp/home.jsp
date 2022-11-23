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
</head>
<body>
	<div class="vh-100 gradient-custom">
		<div class="container text-white">
  			<div class="row">
				<div class="col col-lg-3 bg-dark mt-1 rounded">
					<div class="text-center">
						<p>Ganhos</p>
					</div>
					<div class="alturaDivGanho">
						
					</div>
				    <div class="form-group text-center">
					    <button id="btnGanho" class="btn btn-outline-light btn-lg px-2" type="button">Cadastrar</button>				    
				    </div>
				</div>
				<div class="col bg-dark rounded mr-1 ml-1 mt-1 alturaDiv">
					<div class="text-center">
						<p>Ganhos</p>
					</div>
					<div class="alturaDivGanho">
						
					</div>
					<div class="form-group text-center">
					    <button id="btnPerfil" class="btn btn-outline-light btn-lg px-2" type="button">Editar Perfil</button>				    
				    </div>
				</div>
				<div class="col col-lg-3 bg-dark mt-1 rounded">
					<div class="text-center">
						<p>Gasto</p>
					</div>
					<div class="alturaDivGanho">
						
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
					<h5 class="modal-title" id="exampleModalLabel">Novo Gasto</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">Valor:</label>
							<input type="number" class="form-control" step="0.01" name="valor" min="0.01">
						</div>
						<div class="form-group">
							<label for="message-text" class="col-form-label">Descrição:</label>
							<textarea class="form-control" name="descricao" id="message-text"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fechar</button>
					<button type="button" class="btn btn-primary">Gravar</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>