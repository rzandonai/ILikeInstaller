package br.com.ricardozandonai.utils;

public class Utils {

	
	
	public static boolean isUpdateInstalation(){
		String wRegBusEx = WindowsRegUtils.BuscaExiste();
		if (wRegBusEx != null && !wRegBusEx.trim().isEmpty()){
			return wRegBusEx.trim().equalsIgnoreCase("true");
		}
		return false;
	}
}
