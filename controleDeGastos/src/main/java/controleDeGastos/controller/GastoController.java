package controleDeGastos.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controleDeGastos.entity.Gasto;
import controleDeGastos.excption.ConstraintExcption;
import controleDeGastos.model.GastoModel;

@WebServlet("/gasto")
public class GastoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private GastoModel gastoModel;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.gastoModel = new GastoModel();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BigDecimal valor = new BigDecimal(req.getParameter("valor"));
			String descricao = req.getParameter("descricao");
			Long idUsuario = (Long) req. getSession().getAttribute("user");
			
			Gasto gasto = gastoModel.create(valor, descricao, idUsuario);
			gastoModel.insert(gasto);
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
			Long idUsuario = (Long) req. getSession().getAttribute("id");
			
			Gasto gasto = gastoModel.consultarPorId(idUsuario);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
