package br.com.ricardozandonai;

import javax.swing.*;

public class buscaDir {

	public String buscaDir(String base) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        f.showSaveDialog(null);
		return f.getSelectedFile().getAbsolutePath();
	}

}
