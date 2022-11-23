package database;

import java.util.List;

public class BuildQuery {

	private DBQuery query;
	
	public BuildQuery() {
		super();
		query = new DBQuery();
	}
	
	public void insert(EntityInterface entityInterface) {
		montarTabela(entityInterface);
		query.insert(entityInterface.getValores());
	}
	
	public void update(EntityInterface entityInterface) {
		montarTabela(entityInterface);
		query.update(entityInterface.getValores());
	}
	
	public void detele(EntityInterface entityInterface) {
		montarTabela(entityInterface);
		query.setKeyFieldIndex(0);
		query.delete(entityInterface.getIdValor());
	}
	
	public List<?> select(EntityInterface entityInterface, String condicao) throws Exception {
		montarTabela(entityInterface);
		return entityInterface.toList(query.select(condicao));
	}
	
	private void montarTabela(EntityInterface entityInterface) {
		query.setTableName(entityInterface.getNomeEntidade());
		query.setFieldsName(entityInterface.getNomeDosCampos());
		query.setFieldKey(entityInterface.getCampoChave());
	}
	
}
