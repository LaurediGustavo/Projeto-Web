package controleDeGastos.model;

import java.util.List;

import javax.servlet.http.HttpSession;

import controleDeGastos.entity.Usuario;
import controleDeGastos.excption.ConstraintExcption;
import database.BuildQuery;

public class LoginModel {
	
	private BuildQuery query;

	public LoginModel() {
		super();
		this.query = new BuildQuery();
	}
	
	public void login(HttpSession session, String email, String senha) throws Exception, ConstraintExcption {
		Usuario usuario = consultarUsuario(email, senha);
		
		if(usuario != null) {
			 iniciarSessao(session, usuario);
		}
		else {
			ConstraintExcption exception = new ConstraintExcption();
			exception.adicionarConstraint("E-mail ou senha inválido!");
			throw exception; 
		}
	}
	
	public Usuario consultarUsuario(String email, String senha) throws Exception {
		String condicao = "email = '".concat(email).concat("'");
		condicao += " and senha = '".concat(senha).concat("'");
		List<?> user = query.select(new Usuario(), condicao);
		
		return user == null? null : (Usuario) user.get(0);
	}
	
	public void iniciarSessao(HttpSession session, Usuario usuario) {
		session.setAttribute("user", usuario.getId());
		session.setMaxInactiveInterval(200);
	}
	
	public void fecharSessao(HttpSession session) {
		session.invalidate();
	}
	
	public boolean sessaoAberta(HttpSession session) {
		boolean isValid = true;
		
		Long attributeSession = (Long) session.getAttribute("user");
		
		if(attributeSession == null || attributeSession.longValue() == 0) {
			isValid = false;
		}
		
		return isValid;
	}
}
