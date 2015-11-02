package br.com.ricardozandonai.utils;

import java.util.prefs.Preferences;

import br.com.ricardozandonai.variables;

public class WindowsRegUtils {
	Preferences prefs = Preferences.userNodeForPackage(this.getClass());
	
	public Preferences getprefs(){
		return prefs;
		
	}
	public static String BuscaInstalacao() {
		WindowsRegUtils wru = new WindowsRegUtils();
		Preferences prefs = Preferences.userNodeForPackage(wru.getprefs().getClass());
		return prefs.get(variables.getDIRKEY(), null);
		}
	
	public static String BuscaExiste() {
		WindowsRegUtils wru = new WindowsRegUtils();
		Preferences prefs = Preferences.userNodeForPackage(wru.getprefs().getClass());
		return prefs.get(variables.getINSTEXISTS(), null);
		}
}
