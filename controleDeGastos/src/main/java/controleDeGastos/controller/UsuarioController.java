package controleDeGastos.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controleDeGastos.entity.Usuario;
import controleDeGastos.excption.ConstraintExcption;
import controleDeGastos.model.UsuarioModel;

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UsuarioModel usuarioModel;

	@Override
	public void init() throws ServletException {
		usuarioModel = new UsuarioModel();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String nome = req.getParameter("nome");
			String sobreNome = req.getParameter("sobrenome");
			String email = req.getParameter("email");
			String senha = req.getParameter("senha");
			String foto = req.getParameter("foto");
			
			Usuario usuario = usuarioModel.create(nome, sobreNome, email, senha, foto);
			
			usuarioModel.insert(usuario);
		} 
		catch (ConstraintExcption e) {
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().print(e.getMensagensConstraint());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
