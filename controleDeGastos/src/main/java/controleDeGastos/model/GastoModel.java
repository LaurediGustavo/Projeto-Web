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
	
	public Gasto create(Long id, BigDecimal valor, String descricao, Long idUsuario) { 
		Gasto gasto = new Gasto();
		gasto.setId(id);
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
	
	@SuppressWarnings("unchecked")
	public List<Gasto> consultarPorUsuarioId(Long id) throws Exception {
		String condicao = "idUsuario = ".concat(id.toString());
		List<?> gasto = query.select(new Gasto(), condicao);
		
		return gasto == null? null : (List<Gasto>) gasto;
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
	
	public String listToJson(List<Gasto> gastos) {		
		StringBuilder build = new StringBuilder();
		
		if(gastos != null && !gastos.isEmpty()) {			
			build.append("{");
			build.append("\"").append("gasto").append("\": [");
			
			int controle = 0;
			for (Gasto gasto : gastos) {
				controle++;
				
				build.append(gasto.getJson());
				build.append(gastos.size() == controle? "" : ",");	
			}
			
			build.append("]}");	
		}
		
		return build.toString();
	}
	
	public BigDecimal valorGastoNoMes(Long id) throws Exception {
		BigDecimal valor = BigDecimal.ZERO;
		
		List<Gasto> gastos = consultarGastosMensais(id);
		if(gastos != null && !gastos.isEmpty()) {
			valor = gastos.stream()
		        	.map(gasto -> gasto.getValor())
		        	.reduce(BigDecimal.ZERO, BigDecimal::add); 
		}
		
		return valor;
	}
	
	@SuppressWarnings("unchecked")
	private List<Gasto> consultarGastosMensais(Long id) throws Exception {
		String condicao = "year(dataDoGasto) = year(now()) and month(dataDoGasto) = month(now()) and "
				+ "idUsuario = ".concat(id.toString());
		
		List<?> gastos = query.select(new Gasto(), condicao);
		return gastos == null? null : (List<Gasto>) gastos;
	}
	
	public BigDecimal valorTotal(Long id) throws Exception {
		BigDecimal valor = BigDecimal.ZERO;
		
		List<Gasto> gastos = consultarTodosGastos(id);
		if(gastos != null && !gastos.isEmpty()) {
			valor = gastos.stream()
		        	.map(gasto -> gasto.getValor())
		        	.reduce(BigDecimal.ZERO, BigDecimal::add); 
		}
		
		return valor;
	}
	
	@SuppressWarnings("unchecked")
	private List<Gasto> consultarTodosGastos(Long id) throws Exception {
		String condicao =  "idUsuario = ".concat(id.toString());
		List<?> gastos = query.select(new Gasto(), condicao);
		return gastos == null? null : (List<Gasto>) gastos;
	}
	
}
