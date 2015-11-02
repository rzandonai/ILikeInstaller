package br.com.ricardozandonai;

public class variables{
	private static String programName = "ILikeInstaller ";
	private static final String KEY = "br.com.ricardozandonai";
	private static String PRODKEY = KEY+"_88D4116FBFA1B_";
	private static final String INSTEXISTS = PRODKEY+"complete";
	private static final String DIRKEY = PRODKEY+"dirloc";
	private String installDirWin = System.getenv("ProgramFiles")+"/"+programName;

	
	public static String getPRODKEY() {
		return PRODKEY;
	}

	public String getProgramName() {
		return programName;
	}
	public static String getDIRKEY() {
		return DIRKEY;
	}
	public static String getINSTEXISTS() {
		return INSTEXISTS;
	}
	
	public String getInstallDirWin() {
		return installDirWin;
	}
	public void setInstallDirWin(String installDirWin) {
		this.installDirWin = installDirWin;
	}
}
