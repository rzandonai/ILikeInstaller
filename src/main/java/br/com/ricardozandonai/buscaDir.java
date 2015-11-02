package br.com.ricardozandonai;

import java.io.File;

import javax.swing.*;

import br.com.ricardozandonai.utils.WindowsRegUtils;
/**
 * Utilities to get information about directorys of instalation.
 * 
 * It also allows to call callback functions.
 * 
 * @author Ricardo Zandonai
 */
public class buscaDir {

	/**
	 * Call a java file chooser to chose what dir you whant installl
	 * 
	 * @param String directory base
	 * @out string dir for file choose 
	 */
	
	public String buscaDir(String base) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		System.out.println(base);
        JFileChooser f = new JFileChooser(base);
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        f.showSaveDialog(null);
        
		return (f.getSelectedFile() != null ?f.getSelectedFile().getAbsolutePath():null);
	}
	
	public String ultima(){

		String dir = WindowsRegUtils.BuscaInstalacao();
		if (dir == null){
			dir = variables.getDIRKEY();
		}
		return dir;
	}
	

}
