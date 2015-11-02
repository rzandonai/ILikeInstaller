package br.com.ricardozandonai;

import static br.com.ricardozandonai.utils.Java2JavascriptUtils.connectBackendObject;

import br.com.ricardozandonai.utils.Utils;
import br.com.ricardozandonai.utils.WindowsRegUtils;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;


@SuppressWarnings("restriction")
public class init extends Application {
	private final String INITPAGE = "/index.html";
	private final String UPDATETPAGE = "/index.html";

	public static void main(String[] args) {
		System.setProperty("prism.lcdtext", "false"); // enhance fonts
		launch(args);
	}

	private void Init(Stage stage) {
		boolean atualiza = Utils.isUpdateInstalation();
		

		
		WebView browser = new WebView();
		if(atualiza){
			browser.getEngine().load(getClass().getResource(UPDATETPAGE).toExternalForm());
		}else {
			browser.getEngine().load(getClass().getResource(INITPAGE).toExternalForm());
		}
		
		browser.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

        Scene scene =new Scene(browser);
        stage.setScene(scene);

   
        
        connectBackendObject(
        		browser.getEngine(),
				"buscaDir", new buscaDir());
        
        
        connectBackendObject(
        		browser.getEngine(),
				"salvavariaveis", new salvaVariaveis());
 
		
        connectBackendObject(
        		browser.getEngine(),
				"copiaarquivos", new copiaArquivos());
        
		stage.setTitle("Installer test");
		stage.show();

		browser.getEngine().setOnAlert(new EventHandler<WebEvent<String>>(){
			@Override
			public void handle(WebEvent<String> arg0) {
				System.err.println("erros: " + arg0.getData());
			}
		});

	}

	@Override
	public void start(Stage stage) {

		Init(stage);
	}
}
