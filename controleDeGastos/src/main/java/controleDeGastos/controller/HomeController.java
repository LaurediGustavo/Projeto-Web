package controleDeGastos.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controleDeGastos.model.HomeModel;

@WebServlet("/home")
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private HomeModel homeModel;
	
	@Override
	public void init() throws ServletException {
		super.init();
		homeModel = new HomeModel();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Long id = (Long) req.getSession().getAttribute("user");
			resp.getWriter().print(homeModel.getValores(id));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
