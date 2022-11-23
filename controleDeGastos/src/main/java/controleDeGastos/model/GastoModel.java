package controleDeGastos.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import controleDeGastos.entity.Gasto;
import controleDeGastos.excption.ConstraintExcption;
import database.BuildQuery;

public class GastoModel {

	private BuildQuery query;

	public GastoModel() {
		super();
		this.query = new BuildQuery();
	}
	
	public Gasto create(BigDecimal valor, String descricao, Long idUsuario) { 
		Gasto gasto = new Gasto();
		gasto.setValor(valor);
		gasto.setDescricao(descricao);
		gasto.setDataDoGasto(new Date());
		gasto.setIdUsuario(idUsuario);
		
		return gasto;
	}
	
	public void insert(Gasto gasto) throws ConstraintExcption { 
		validarDados(gasto);
		query.insert(gasto);
	}
	
	public Gasto consultarPorId(Long id) throws Exception {
		String condicao = "id = ".concat(id.toString());
		List<?> gasto = query.select(new Gasto(), condicao);
		
		return gasto == null? null : (Gasto) gasto.get(0);
	}
	
	private void validarDados(Gasto gasto) throws ConstraintExcption {
		ConstraintExcption exception = new ConstraintExcption();
		
		BigDecimal valor = gasto.getValor();
		if(valor == null || BigDecimal.ZERO.equals(valor)) { 
			exception.adicionarConstraint("O campo VALOR é obrigatório.");
		}
		
		String descricao = gasto.getDescricao();
		if(StringUtils.isBlank(descricao)) { 
			exception.adicionarConstraint("O campo DESCRIÇÃO é obrigatório.");
		}
		
		Date dataDoGasto = gasto.getDataDoGasto();
		if(dataDoGasto == null)  {
			exception.adicionarConstraint("O campo DATA DO GASTO é obrigatório.");
		}
		
		if(exception.exiteMensagemConstraint()) {
			throw exception;
		}
	}
	
}
