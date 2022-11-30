package controleDeGastos.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import controleDeGastos.entity.Usuario;
import controleDeGastos.excption.ConstraintExcption;
import controleDeGastos.uteis.ArquivoUteis;
import database.BuildQuery;
import mail.SendMail;

public class UsuarioModel {
	
	private BuildQuery query;

	public UsuarioModel() {
		super();
		query = new BuildQuery();
	}
	
	public Usuario create(Long id, String nome, String sobreNome, String email, String senha, String foto) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setNome(nome);
		usuario.setSobreNome(sobreNome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuario.setFoto(ArquivoUteis.getCaminhoImagem(foto));
		
		return usuario;
	}
	
	public void persistir(Usuario usuario) throws ConstraintExcption, Exception {
		if(usuario.getId() == null || usuario.getId().longValue() == 0) {
			insert(usuario);
		}
		else {
			update(usuario);
		}
	}
	
	public void insert(Usuario usuario) throws Exception, ConstraintExcption {
		validarDados(usuario);
		query.insert(usuario);
	}
	
	public void update(Usuario usuario) throws ConstraintExcption, Exception {
		juntarDados(usuario);
		validarDados(usuario);
		query.update(usuario);
	}

	private void juntarDados(Usuario usuario) throws Exception {
		Usuario usuarioAntigo = consultarPorId(usuario.getId());
		
		String foto = usuario.getFoto();
		if(StringUtils.isBlank(foto)) {
			usuario.setFoto(usuarioAntigo.getFoto());
		}
		
		String senha = usuario.getSenha();
		if(StringUtils.isBlank(senha)) {
			usuario.setSenha(usuarioAntigo.getSenha());
		}
		
		usuario.setEmail(usuarioAntigo.getEmail());
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
		
		if(usuario.getId().longValue() == 0) {			
			String email = usuario.getEmail();
			if(StringUtils.isBlank(email)) {
				exception.adicionarConstraint("O campo E-MAIL é obrigatório.");
			}
			else {			
				if(emailInvalido(usuario)) {
					exception.adicionarConstraint("O E-MAIL é inválido.");
				}
			}
		}
		
		String senha = usuario.getSenha();
		if(StringUtils.isBlank(senha)) {
			exception.adicionarConstraint("O campo SENHA é obrigatório.");
		}
		else {
			if(senha.length() < 8) {
				exception.adicionarConstraint("O campo SENHA deve ser maior ou igual a 8.");
			}
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
	
	public Usuario consultarPorId(Long id) throws Exception {
		String condicao = "id = ".concat(id.toString());
		List<?> user = query.select(new Usuario(), condicao);
		
		Usuario usuario = null;
		if(user != null && !user.isEmpty()) {
			usuario = (Usuario) user.get(0);
		}
		
		return usuario;
	}
	
	public void enviarEmail(String email) {
		SendMail sendMail = new SendMail("smtp.gmail.com", "465", "trabalho.projeto.web@gmail.com", "watwmrqyniuahctt", "SSL");
		sendMail.send("trabalho.projeto.web@gmail.com", email, "Cadastro no Controle de Gastos", "Obrigado por realizar o cadastro em nosso site!");
	}
	
}
