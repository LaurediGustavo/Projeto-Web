package controleDeGastos.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import controleDeGastos.entity.Usuario;
import controleDeGastos.excption.ConstraintExcption;
import controleDeGastos.uteis.ArquivoUteis;
import database.BuildQuery;

public class UsuarioModel {
	
	private BuildQuery query;

	public UsuarioModel() {
		super();
		query = new BuildQuery();
	}
	
	public Usuario create(String nome, String sobreNome, String email, String senha, String foto) {
		Usuario usuario = new Usuario();
		usuario.setId(0L);
		usuario.setNome(nome);
		usuario.setSobreNome(sobreNome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuario.setFoto(ArquivoUteis.getCaminhoImagem(foto));
		
		return usuario;
	}
	
	public void insert(Usuario usuario) throws Exception, ConstraintExcption {
		validarDados(usuario);
		query.insert(usuario);
	}

	private void validarDados(Usuario usuario) throws Exception, ConstraintExcption {
		ConstraintExcption exception = new ConstraintExcption();
		
		String nome = usuario.getNome();
		if(StringUtils.isBlank(nome)) {
			exception.adicionarConstraint("O campo NOME é obrigatório.");
		}
		
		String sobreNome = usuario.getSobreNome();
		if(StringUtils.isBlank(sobreNome)) {
			exception.adicionarConstraint("O campo SOBRENOME é obrigatório.");
		}
		
		String email = usuario.getEmail();
		if(StringUtils.isBlank(email)) {
			exception.adicionarConstraint("O campo E-MAIL é obrigatório.");
		}
		else {			
			if(emailInvalido(usuario)) {
				exception.adicionarConstraint("O E-MAIL é inválido.");
			}
		}
		
		String senha = usuario.getSenha();
		if(StringUtils.isBlank(senha)) {
			exception.adicionarConstraint("O campo SENHA é obrigatória.");
		}
		
		if(exception.exiteMensagemConstraint()) {
			throw exception;
		}
		
	}
	
	private boolean emailInvalido(Usuario usuario) throws Exception {
		boolean isInvalid = false; 
	
		String condicao = "email = '".concat(usuario.getEmail()).concat("'");
		List<?> user = query.select(usuario, condicao);
		
		if(user != null && !user.isEmpty()) {
			isInvalid = true;
		}
		
		return isInvalid;
	}
	
}
