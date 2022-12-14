package controleDeGastos.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

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
			String valorString = req.getParameter("valor");
			BigDecimal valor = new BigDecimal(StringUtils.isBlank(valorString)? "0" : valorString);
			String descricao = req.getParameter("descricao");
			Long idUsuario = (Long) req. getSession().getAttribute("user");
			
			Gasto gasto = gastoModel.create(0L, valor, descricao, idUsuario);
			gastoModel.insert(gasto);
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
			Long idUsuario = (Long) req. getSession().getAttribute("user");
			
			List<Gasto> gastos = gastoModel.consultarPorUsuarioId(idUsuario);
			resp.getWriter().print(gastoModel.listToJson(gastos));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
