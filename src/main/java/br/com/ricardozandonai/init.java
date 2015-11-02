package br.com.ricardozandonai;

import static br.com.ricardozandonai.Java2JavascriptUtils.connectBackendObject;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.*;

@SuppressWarnings("restriction")
public class init extends Application {
	private final String INITPAGE = "/index.html";

	public static void main(String[] args) {
		System.setProperty("prism.lcdtext", "false"); // enhance fonts
		launch(args);
	}

	private void Init(Stage stage) {
		WebView browser = new WebView();
		browser.getEngine().load(getClass().getResource(INITPAGE).toExternalForm());
		browser.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

        Scene scene =new Scene(browser);
        stage.setScene(scene);

        connectBackendObject(
        		browser.getEngine(),
				"buscaDir", new buscaDir());
 
		
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
