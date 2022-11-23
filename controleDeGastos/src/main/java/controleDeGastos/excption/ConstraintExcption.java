package controleDeGastos.excption;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ConstraintExcption extends Exception {

	private static final long serialVersionUID = 1L;

	private final String QUEBRA_LINHA = "\n";
	
	private List<String> mensagensConstraint;
	
	public ConstraintExcption() {
		super();
		this.mensagensConstraint = new ArrayList<String>();
	}
	
	public void adicionarConstraint(String constraint) {
		if(StringUtils.isNotBlank(constraint)) {
			this.mensagensConstraint.add(constraint);
		}
	}
	
	public String getMensagensConstraint() {
		String mensagens = "";
	
		for (String msg : mensagensConstraint) {
			mensagens += msg.concat(QUEBRA_LINHA);
		}
		
		return mensagens;
	}

	public boolean exiteMensagemConstraint() {
		boolean existe = false;
		
		if(this.mensagensConstraint != null && !this.mensagensConstraint.isEmpty()) {
			existe = true;
		}
		
		return existe;
	}
	
}
