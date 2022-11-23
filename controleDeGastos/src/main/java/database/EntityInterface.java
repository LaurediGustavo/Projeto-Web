package database;

import java.sql.ResultSet;
import java.util.List;

public interface EntityInterface {

	public String getNomeEntidade();
	
	public String getNomeDosCampos(); 
	
	public String getCampoChave();
	
	public String[] getValores();
	
	public String[] getIdValor();
	
	public List<?> toList(ResultSet rs) throws Exception;
	
}
