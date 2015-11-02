package br.com.ricardozandonai;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import netscape.javascript.JSObject;

/**
 * Utilities to connect Java objects as javascript objects in a WebEngine.
 * 
 * It also allows to call callback functions.
 * 
 * @author lipido
 */
public class Java2JavascriptUtils {
	
	private static Map<WebEngine, Map<String, Object>> 
		backendObjects = new HashMap<>();
	private static Set<WebEngine> 
		webEnginesWithAlertChangeListener =	new HashSet<>();
	
	private static boolean changing = false;
	
	/**
	 * Registers a backend Java object as a Javascript variable.
	 * The real connection to the webEngine comes when the javascript performs
	 * an special "alert" message by invoking 
	 * "alert('__CONNECT__BACKEND__varname')" where varname is the javascript
	 * variable we want to make available.
	 * 
	 * The call to this function has to be performed before the engine loads the
	 * first page (where the alert call should take place).
	 * 
	 * @param webEngine The webEngine to register the new variable.
	 * @param varname The name of the variable in javascript.
	 * @param backend The Java backend object.
	 */
	public static void connectBackendObject(
			final WebEngine webEngine,
			final String varname, 
			final Object backend) {
		
		registerBackendObject(webEngine, varname, backend);
		

		if (!webEnginesWithAlertChangeListener.contains(webEngine)) {
			if (webEngine.getOnAlert() == null) {
				webEngine.setOnAlert(new AlertEventHandlerWrapper(webEngine,
						null));
			}
			
			webEngine.onAlertProperty().addListener(
				new ChangeListener<EventHandler<WebEvent<String>>>() {

					@Override
					public void changed(
							ObservableValue
							<? extends EventHandler<WebEvent<String>>> arg0,										
							EventHandler<WebEvent<String>> previous,
							final EventHandler<WebEvent<String>> newHandler) {

						if (!changing) { // avoid recursive calls
							changing = true;
							webEngine.setOnAlert(
								new AlertEventHandlerWrapper(
										webEngine, 
										newHandler));
							changing = false;
						}
					}
			});
		}
		webEnginesWithAlertChangeListener.add(webEngine);
	}

	private static void registerBackendObject(final WebEngine webEngine,
			final String varname, final Object backend) {
		Map<String, Object> webEngineBridges = backendObjects.get(webEngine);
		if (webEngineBridges == null){
			webEngineBridges = new HashMap<>();
			backendObjects.put(webEngine, webEngineBridges);
			
		}
		webEngineBridges.put(varname, backend);
		
	}

	private static void connectToWebEngine(WebEngine engine, String varname) {
		if (	backendObjects.containsKey(engine) &&
				backendObjects.get(engine).containsKey(varname)) {
			
			JSObject window = (JSObject) engine.executeScript("window");
			window.setMember(varname, backendObjects.get(engine).get(varname));
		}
	}
	
	public static void call(Object callback, Object argument) {	
			// it is not a json object, so let the 
			// API to create the javascript object
			((JSObject)callback).eval("this("+argument.toString()+")");
	}
	
	private final static class AlertEventHandlerWrapper 
		implements EventHandler<WebEvent<String>> {
		
		private static final 
			String CONNECT_BACKEND_MAGIC_WORD = "__CONNECT__BACKEND__";
		private final EventHandler<WebEvent<String>> wrappedHandler;
		private WebEngine engine;

		private AlertEventHandlerWrapper(
				WebEngine engine, 
				EventHandler<WebEvent<String>> wrappedHandler) {
			
			this.engine = engine;
			this.wrappedHandler = wrappedHandler;
		}

		@Override
		public void handle(WebEvent<String> arg0) {
			if (arg0.getData().contains(CONNECT_BACKEND_MAGIC_WORD)) {
				String varname = arg0.getData().substring(
						CONNECT_BACKEND_MAGIC_WORD.length());
				
				connectToWebEngine(engine, varname);
			}
			else if (wrappedHandler != null) wrappedHandler.handle(arg0);
		}
	}
}