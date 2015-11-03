package br.com.ricardozandonai.utils;
/**
 * Utilities to get information about directorys of instalation.
 * 
 * It also allows to call callback functions.
 * 
 * @author Ricardo Zandonai
 */
public class Utils {
	public static boolean isUpdateInstalation(){
		String wRegBusEx = WindowsRegUtils.BuscaExiste();
		if (wRegBusEx != null && !wRegBusEx.trim().isEmpty()){
			return wRegBusEx.trim().equalsIgnoreCase("true");
		}
		return false;
	}
}
