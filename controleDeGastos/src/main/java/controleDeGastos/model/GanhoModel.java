package controleDeGastos.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import controleDeGastos.entity.Ganho;
import controleDeGastos.excption.ConstraintExcption;
import database.BuildQuery;

public class GanhoModel {

	private BuildQuery query;

	public GanhoModel() {
		super();
		this.query = new BuildQuery();
	}
	
	public Ganho create(Long id, BigDecimal valor, String descricao, Long idUsuario) { 
		Ganho ganho = new Ganho();
		ganho.setId(id);
		ganho.setValor(valor);
		ganho.setDescricao(descricao);
		ganho.setDataDoGanho(new Date());
		ganho.setIdUsuario(idUsuario);
		
		return ganho;
	}
	
	public void insert(Ganho ganho) throws ConstraintExcption { 
		validarDados(ganho);
		query.insert(ganho);
	}
	
	@SuppressWarnings("unchecked")
	public List<Ganho> consultarPorUsuarioId(Long id) throws Exception {
		String condicao = "idUsuario = ".concat(id.toString());
		List<?> ganho = query.select(new Ganho(), condicao);
		
		return ganho == null? null : (List<Ganho>) ganho;
	}
	
	private void validarDados(Ganho ganho) throws ConstraintExcption {
		ConstraintExcption exception = new ConstraintExcption();
		
		BigDecimal valor = ganho.getValor();
		if(valor == null || BigDecimal.ZERO.equals(valor)) { 
			exception.adicionarConstraint("O campo VALOR é obrigatório.");
		}
		
		String descricao = ganho.getDescricao();
		if(StringUtils.isBlank(descricao)) { 
			exception.adicionarConstraint("O campo DESCRIÇÃO é obrigatório.");
		}
		
		Date dataDoGasto = ganho.getDataDoGanho();
		if(dataDoGasto == null)  {
			exception.adicionarConstraint("O campo DATA DO GASTO é obrigatório.");
		}
		
		if(exception.exiteMensagemConstraint()) {
			throw exception;
		}
	}
	
	public String listToJson(List<Ganho> ganhos) {		
		StringBuilder build = new StringBuilder();
		
		if(ganhos != null && !ganhos.isEmpty()) {		
			build.append("{");
			build.append("\"").append("ganho").append("\": [");
			
			int controle = 0;
			for (Ganho ganho : ganhos) {
				controle++;
				
				build.append(ganho.getJson());
				build.append(ganhos.size() == controle? "" : ",");	
			}
			
			build.append("]}");	
		}
		
		return build.toString();
	}
	
	public BigDecimal valorGanhoNoMes(Long id) throws Exception {
		BigDecimal valor = BigDecimal.ZERO;
		
		List<Ganho> ganhos = consultarGanhosMensais(id);
		if(ganhos != null && !ganhos.isEmpty()) {
			valor = ganhos.stream()
		        	.map(gasto -> gasto.getValor())
		        	.reduce(BigDecimal.ZERO, BigDecimal::add); 
		}
		
		return valor;
	}
	
	@SuppressWarnings("unchecked")
	private List<Ganho> consultarGanhosMensais(Long id) throws Exception {
		String condicao = "year(dataDoGanho) = year(now()) and month(dataDoGanho) = month(now()) and "
				+ "idUsuario = ".concat(id.toString());
		
		List<?> ganhos = query.select(new Ganho(), condicao);
		return ganhos == null? null : (List<Ganho>) ganhos;
	}
	
	public BigDecimal valorTotal(Long id) throws Exception {
		BigDecimal valor = BigDecimal.ZERO;
		
		List<Ganho> ganhos = consultarTodosGanhos(id);
		if(ganhos != null && !ganhos.isEmpty()) {
			valor = ganhos.stream()
		        	.map(gasto -> gasto.getValor())
		        	.reduce(BigDecimal.ZERO, BigDecimal::add); 
		}
		
		return valor;
	}
	
	@SuppressWarnings("unchecked")
	private List<Ganho> consultarTodosGanhos(Long id) throws Exception {
		String condicao =  "idUsuario = ".concat(id.toString());
		List<?> ganhos = query.select(new Ganho(), condicao);
		return ganhos == null? null : (List<Ganho>) ganhos;
	}
	
}
