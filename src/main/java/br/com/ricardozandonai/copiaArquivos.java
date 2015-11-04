package br.com.ricardozandonai;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;

import br.com.ricardozandonai.utils.FileUtils;

/**
 * Utilities to get information about directorys of instalation.
 * 
 * It also allows to call callback functions.
 * 
 * @author Ricardo Zandonai
 */
public class copiaArquivos {

	public static void main(String[] args) throws URISyntaxException {
		try {
			wildflydemo("c:\\wilddemo\\", true, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean wildflydemo(String dirInstall, boolean installWF, boolean isUpdate)
			throws IOException, URISyntaxException {
		boolean itsOK = false;
		if (installWF) {
			File dirInst = new File(dirInstall);
			if (!dirInst.exists()) {
				dirInst.mkdirs();
			}
			URL url = copiaArquivos.class.getResource("/arquivos");
			if (url == null) {
				// error - missing folder
			} else {
				File dir = null;
				dir = new File(url.toURI());

				for (File nextFile : dir.listFiles()) {
					if (nextFile.getName().startsWith("wildfly-10.0.0.CR4.zip")) {
						Files.copy(Paths.get(nextFile.getPath()), Paths.get(dirInstall + "wildfly-10.0.0.CR4.zip"),
								StandardCopyOption.REPLACE_EXISTING);	
						FileUtils.unZipIt(dirInstall + "wildfly-10.0.0.CR4.zip", dirInstall);
					}
				}
			}
		}
		return itsOK;

	}
}
