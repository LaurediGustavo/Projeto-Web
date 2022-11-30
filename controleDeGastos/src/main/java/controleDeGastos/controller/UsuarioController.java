package controleDeGastos.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import controleDeGastos.entity.Usuario;
import controleDeGastos.excption.ConstraintExcption;
import controleDeGastos.model.LoginModel;
import controleDeGastos.model.UsuarioModel;

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UsuarioModel usuarioModel;
	private LoginModel loginModel;

	@Override
	public void init() throws ServletException {
		usuarioModel = new UsuarioModel();
		loginModel = new LoginModel();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("id");
			Long idUsuario = StringUtils.isBlank(id)? 0L : Long.parseLong(id);
			String nome = req.getParameter("nome");
			String sobreNome = req.getParameter("sobrenome");
			String email = req.getParameter("email");
			String senha = req.getParameter("senha");
			String foto = req.getParameter("foto");
			
			Usuario usuario = usuarioModel.create(idUsuario, nome, sobreNome, email, senha, foto);
			
			usuarioModel.persistir(usuario);
			
			if(idUsuario.longValue() == 0) {				
				loginModel.login(req.getSession(), email, senha);
				usuarioModel.enviarEmail(email);
			}

			resp.getWriter().print("true");
		} 
		catch (ConstraintExcption e) {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().print(e.getMensagensConstraint());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Long idUsuario = (Long) req.getSession().getAttribute("user");
			Usuario usuario = usuarioModel.consultarPorId(idUsuario);
			resp.getWriter().print(usuario.getJson());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
