package controleDeGastos.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controleDeGastos.excption.ConstraintExcption;
import controleDeGastos.model.LoginModel;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private LoginModel loginModel;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.loginModel = new LoginModel();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email = req.getParameter("email");
			String senha = req.getParameter("senha");
			
			loginModel.login(req.getSession(), email, senha);
			resp.sendRedirect("http://localhost:8080/controleDeGastos/home.jsp");
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
