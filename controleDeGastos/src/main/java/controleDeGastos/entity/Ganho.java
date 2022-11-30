package controleDeGastos.entity;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.EntityInterface;

public class Ganho implements EntityInterface {
	
	private final String NOME_ENTIDADE = "ganho";

	private final String CAMPO_CHAVE = "id";
	
	private final String DATE_PATTERN = "yyyy-MM-dd";
	
	private final String DATE_PATTERN_DD_MM_YYYY = "dd/MM/yyyy";
	
	private Long id;
	
	private BigDecimal valor;
	
	private Date dataDoGanho;
	
	private String descricao;
	
	private Long idUsuario;

	public Ganho() {
		super();
	}

	public Ganho(Long id, BigDecimal valor, Date dataDoGanho, String descricao, Long idUsuario) {
		super();
		this.id = id;
		this.valor = valor;
		this.dataDoGanho = dataDoGanho;
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
		campos.append("dataDoGanho").append(",");
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
			this.dataDoGanho == null? "" : df.format(dataDoGanho),
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
		List<Ganho> listaGanho = new ArrayList<Ganho>();

		while(rs.next()) {
			Ganho ganho = new Ganho();      
			ganho.setId(Long.parseLong(rs.getString("id")));
			ganho.setValor(new BigDecimal(rs.getString("valor")));
			ganho.setDataDoGanho(df.parse(rs.getString("dataDoGanho")));
			ganho.setDescricao(rs.getString("descricao"));
			ganho.setIdUsuario(Long.parseLong(rs.getString("idUsuario")));
			
			listaGanho.add(ganho);
		}
		
		return listaGanho.isEmpty()? null : listaGanho;
	}	
	
	public String getJson() {
		DateFormat df = new SimpleDateFormat(DATE_PATTERN_DD_MM_YYYY);
		
		StringBuilder build = new StringBuilder();
		build.append("{");
		build.append("\"").append("id").append("\":").append(this.id).append(",");
		build.append("\"").append("valor").append("\":\"").append(this.valor.toString()).append("\",");
		build.append("\"").append("data").append("\":\"").append(df.format(dataDoGanho)).append("\",");
		build.append("\"").append("descricao").append("\":\"").append(this.descricao).append("\",");
		build.append("\"").append("idUsuario").append("\":").append(this.idUsuario);
		build.append("}");
		
		return build.toString();
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

	public Date getDataDoGanho() {
		return dataDoGanho;
	}

	public void setDataDoGanho(Date dataDoGanho) {
		this.dataDoGanho = dataDoGanho;
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
		return "Ganho [NOME_ENTIDADE=" + NOME_ENTIDADE + ", id=" + id + ", valor=" + valor + ", dataDoGanho="
				+ dataDoGanho + ", descricao=" + descricao + ", idUsuario=" + idUsuario + "]";
	}
	
}