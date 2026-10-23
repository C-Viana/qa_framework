package common;

import java.io.File;
import java.net.URISyntaxException;

public class FilesSupport {
	/**
	 * Cria os diretórios informados através de um caminho definido em uma string.
	 * Se os diretórios já existirem, nenhuma ação será realizada. <br>
	 * Exemplo: <br>
	 * path = "D:/PDI/template-automacao-web/uploads" <br>
	 * 
	 * @param path
	 */
	public static void createFolders(String path) {
		File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
	}

	public static <T> String getParentFolder(Class<T> className) throws URISyntaxException {
		File jarOrBinDir = new File(className.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
		return jarOrBinDir.getParentFile().getPath();
	}
}
