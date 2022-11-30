package controleDeGastos.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controleDeGastos.model.LoginModel;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private LoginModel loginModel;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.loginModel = new LoginModel();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			loginModel.fecharSessao(req.getSession());
			resp.getWriter().print("login.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
