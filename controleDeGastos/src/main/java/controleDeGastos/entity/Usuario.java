package controleDeGastos.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import database.EntityInterface;

public class Usuario implements EntityInterface {
	
	private final String NOME_ENTIDADE = "usuario";
	
	private final String CAMPO_CHAVE = "id";
	
	private Long id;
	
	private String nome;
	
	private String sobreNome;
	
	private String email;
	
	private String foto;
	
	private String senha;
	
	public Usuario() {
		super();
	}

	public Usuario(Long id, String nome, String sobreNome, String email, String foto, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.email = email;
		this.foto = foto;
		this.senha = senha;
	}
	
	@Override
	public String getNomeEntidade() {
		return NOME_ENTIDADE;
	}

	@Override
	public String getNomeDosCampos() {
		StringBuilder campos = new StringBuilder();
		campos.append("id").append(",");
		campos.append("nome").append(",");
		campos.append("sobreNome").append(",");
		campos.append("email").append(",");
		campos.append("foto").append(",");
		campos.append("senha");
		
		return campos.toString();
	}

	@Override
	public String getCampoChave() {
		return CAMPO_CHAVE;
	}

	@Override
	public String[] getValores() {
		String[] valores = new String[] {
			this.id == null? "0" : this.id.toString(),
			this.nome == null? "" : this.nome,
			this.sobreNome == null? "" : this.sobreNome,
			this.email == null? "" : this.email,
			this.foto == null? "" : this.foto,
			this.senha == null? "" : this.senha
		};
		
		return valores;
	}

	@Override
	public String[] getIdValor() {
		String[] valores = new String[] {
			this.id == null? "" : this.id.toString()
		};
		
		return valores;
	}
	
	@Override
	public List<?> toList(ResultSet rs) throws Exception {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();

		while(rs.next()) {
			Usuario user = new Usuario();      
			user.setId(Long.parseLong(rs.getString("id")));
			user.setNome(rs.getString("nome"));
			user.setSobreNome(rs.getString("sobreNome"));
			user.setEmail(rs.getString("email"));
			user.setFoto(rs.getString("foto"));
			user.setSenha(rs.getString("senha"));

			listaUsuario.add(user);
		}
		
		return listaUsuario.isEmpty()? null : listaUsuario;
	}
	
	public String getJson() throws IOException {
		StringBuilder build = new StringBuilder();
		build.append("{");
		build.append("\"").append("id").append("\":").append(this.id).append(",");
		build.append("\"").append("nome").append("\":\"").append(this.nome).append("\",");
		build.append("\"").append("sobreNome").append("\":\"").append(this.sobreNome).append("\",");
		build.append("\"").append("email").append("\":\"").append(this.email).append("\",");
		build.append("\"").append("foto").append("\":\"").append(getImagem(this.foto)).append("\"");
		build.append("}");
		
		return build.toString();
	}
	
	private String getImagem(String foto) throws IOException {
		String fotoNova = "";
		
		if(StringUtils.isNotBlank(foto)) {
			byte [] fotoBytes = Files.readAllBytes(Paths.get(this.foto));
			fotoNova = new String(fotoBytes);
		}
		
		return fotoNova;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", sobreNome=" + sobreNome + ", email=" + email + ", foto=" + foto + 
				", senha=" + senha + "]";
	}
	
}
