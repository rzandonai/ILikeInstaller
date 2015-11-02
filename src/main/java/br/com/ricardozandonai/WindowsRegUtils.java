package br.com.ricardozandonai;

import java.util.prefs.Preferences;

public class WindowsRegUtils {

	public static final String REALKEY = "br.com.ricardozandonai";

	public static void main(String[] args) {
		new WindowsRegUtils().doit();
	}

	public void doit() {
		Preferences prefs = Preferences.userNodeForPackage(this.getClass());
		prefs.put(REALKEY, "bar");
		System.out.println(prefs);
		System.out.println(prefs.get(REALKEY, "HKLM houston we have a problem"));
	}
	
	/* TODO:
	 * Adicionar codigo do produto que vai ser instalador
	 * 
	 * Adicionar o caminho de instalação 
	 * 
	 * 
	 */
}
