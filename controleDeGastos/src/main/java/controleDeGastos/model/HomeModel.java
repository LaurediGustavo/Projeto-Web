package controleDeGastos.model;

import java.math.BigDecimal;

public class HomeModel {
	
	private GastoModel gastoModel;
	private GanhoModel ganhoModel;
	
	public HomeModel() {
		gastoModel = new GastoModel();
		ganhoModel = new GanhoModel();
	}
	
	public String getValores(Long id) throws Exception {
		BigDecimal valorGastoNoMes = gastoModel.valorGastoNoMes(id);
		BigDecimal valorGanhoNoMes = calcularValorGanho(id, valorGastoNoMes);
		BigDecimal valorTotal = calcularValorTotal(id);
		
		return getJson(valorGastoNoMes, valorGanhoNoMes, valorTotal);
	}

	private String getJson(BigDecimal valorGastoNoMes, BigDecimal valorGanhoNoMes, BigDecimal valorTotal) {
		StringBuilder build = new StringBuilder();
		build.append("{");
		build.append("\"valorGastoNoMes\":\"").append(valorGastoNoMes).append("\",");
		build.append("\"valorGanhoNoMes\":\"").append(valorGanhoNoMes).append("\",");
		build.append("\"valorTotal\":\"").append(valorTotal).append("\"");
		build.append("}");
		
		return build.toString();
	}

	private BigDecimal calcularValorGanho(Long id, BigDecimal valorGastoNoMes) throws Exception {
		BigDecimal valorGanhoNoMes = ganhoModel.valorGanhoNoMes(id);
		
		valorGanhoNoMes = valorGanhoNoMes.subtract(valorGastoNoMes);
		return valorGanhoNoMes;
	}
	
	private BigDecimal calcularValorTotal(Long id) throws Exception {
		BigDecimal totalGanho = ganhoModel.valorTotal(id);
		BigDecimal totalGasto = gastoModel.valorTotal(id);
		
		return totalGanho.subtract(totalGasto);
	}
	
	
}
