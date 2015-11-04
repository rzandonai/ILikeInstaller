package br.com.ricardozandonai.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;

public class FileUtils {

	List<String> fileList;

	/**
	 * Unzip it
	 * 
	 * @param zipFile
	 *            input zip file
	 * @param output
	 *            zip file output folder
	 */
	public static void unZipIt(String zip, String outputFolder) {

		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zip);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				File entryDestination = new File(outputFolder, entry.getName());
				if (entry.getName().startsWith("."));
				if (entry.isDirectory())
					entryDestination.mkdirs();
				else {
					entryDestination.getParentFile().mkdirs();
					InputStream in = zipFile.getInputStream(entry);
					OutputStream out = new FileOutputStream(entryDestination);
					IOUtils.copy(in, out);
					IOUtils.closeQuietly(in);
					out.close();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				zipFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
