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

import controleDeGastos.entity.Ganho;
import controleDeGastos.excption.ConstraintExcption;
import controleDeGastos.model.GanhoModel;

@WebServlet("/ganho")
public class GanhoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private GanhoModel ganhoModel;
	
	@Override
	public void init() throws ServletException {
		super.init();
		ganhoModel = new GanhoModel();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String valorString = req.getParameter("valor");
			BigDecimal valor = new BigDecimal(StringUtils.isBlank(valorString)? "0" : valorString);
			String descricao = req.getParameter("descricao");
			Long idUsuario = (Long) req. getSession().getAttribute("user");
			
			Ganho ganho = ganhoModel.create(0L, valor, descricao, idUsuario);
			ganhoModel.insert(ganho);
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
			
			List<Ganho> ganhos = ganhoModel.consultarPorUsuarioId(idUsuario);
			resp.getWriter().print(ganhoModel.listToJson(ganhos));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
