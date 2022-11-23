package controleDeGastos.uteis;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class ArquivoUteis {
	
	public static String getCaminhoImagem(String foto) {
		String caminho = "";
		
		try {
			if(StringUtils.isNotBlank(foto)) {
				String filename = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss-SSS", Locale.ENGLISH).format(new Date());
				filename += ".base64";
				
				caminho = "C:/Users/LaurediRigoni/Pictures/ControleDeGastos/" + filename;
				
				byte[] bytes = foto.getBytes(); 
				
				Files.write(Paths.get(caminho), bytes);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return caminho;
	}
	
}
