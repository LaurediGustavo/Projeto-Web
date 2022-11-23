package controleDeGastos.entity;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.EntityInterface;

public class Gasto implements EntityInterface {
	
	private final String NOME_ENTIDADE = "gasto";

	private final String CAMPO_CHAVE = "id";
	
	private final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	private Long id;
	
	private BigDecimal valor;
	
	private Date dataDoGasto;
	
	private String descricao;
	
	private Long idUsuario;

	public Gasto() {
		super();
	}

	public Gasto(Long id, BigDecimal valor, Date dataDoGasto, String descricao, Long idUsuario) {
		super();
		this.id = id;
		this.valor = valor;
		this.dataDoGasto = dataDoGasto;
		this.idUsuario = idUsuario;
		this.descricao = descricao;
	}
	
	@Override
	public String getNomeEntidade() {
		return NOME_ENTIDADE;
	}

	@Override
	public String getNomeDosCampos() {
		StringBuilder campos = new StringBuilder();
		campos.append("id").append(",");
		campos.append("valor").append(",");
		campos.append("dataDoGasto").append(",");
		campos.append("descricao").append(",");
		campos.append("idUsuario");
		
		return campos.toString();
	}

	@Override
	public String getCampoChave() {
		return CAMPO_CHAVE;
	}

	@Override
	public String[] getValores() {
		DateFormat df = new SimpleDateFormat(DATE_PATTERN);
		
		String[] valores = new String[] {
			this.id == null? "0" : id.toString(),
			this.valor == null? "" : valor.toString(),
			this.dataDoGasto == null? "" : df.format(dataDoGasto),
			this.descricao == null? "" : descricao,
			this.idUsuario == null? "" : this.idUsuario.toString(),
		};
		
		return valores;
	}

	@Override
	public String[] getIdValor() {
		String[] valores = new String[] {
				this.id == null? "0" : id.toString()
		};
		
		return valores;
	}

	@Override
	public List<?> toList(ResultSet rs) throws Exception {
		DateFormat df = new SimpleDateFormat(DATE_PATTERN);
		List<Gasto> listaGasto = new ArrayList<Gasto>();

		while(rs.next()) {
			Gasto gasto = new Gasto();      
			gasto.setId(Long.parseLong(rs.getString("id")));
			gasto.setValor(new BigDecimal(rs.getString("valor")));
			gasto.setDataDoGasto(df.parse(rs.getString("dataDoGasto")));
			gasto.setDescricao(rs.getString("descricao"));
			gasto.setIdUsuario(Long.parseLong(rs.getString("idUsuario")));
			
			listaGasto.add(gasto);
		}
		
		return listaGasto.isEmpty()? null : listaGasto;
	}	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataDoGasto() {
		return dataDoGasto;
	}

	public void setDataDoGasto(Date dataDoGasto) {
		this.dataDoGasto = dataDoGasto;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Gasto [NOME_ENTIDADE=" + NOME_ENTIDADE + ", id=" + id + ", valor=" + valor + ", dataDoGasto="
				+ dataDoGasto + ", descricao=" + descricao + ", idUsuario=" + idUsuario + "]";
	}
	
}
