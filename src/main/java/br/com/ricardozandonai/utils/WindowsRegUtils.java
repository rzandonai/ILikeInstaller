package br.com.ricardozandonai.utils;

import java.util.prefs.Preferences;

import br.com.ricardozandonai.variables;

/**
 * Utilities to get information about directorys of instalation.
 * 
 * It also allows to call callback functions.
 * 
 * @author Ricardo Zandonai
 */
public class WindowsRegUtils {
	private static Preferences prefs = Preferences.userNodeForPackage(WindowsRegUtils.class);

	public static String BuscaDirInstalacao() {
		return prefs.get(variables.getDIRKEY(), null);
	}

	public static void SetaDirInstalacao(String dir) {
		prefs.put(variables.getDIRKEY(), dir);
	}

	public static String BuscaExiste() {
		return prefs.get(variables.getINSTEXISTS(), null);
	}
	public static void SetaExiste() {
		prefs.put(variables.getINSTEXISTS(), "true");
	}
}
