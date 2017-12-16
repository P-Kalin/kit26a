/**
 * 
 */
package ua.khpi.oop.lytvyn16.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;

/**
 * 
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class Message {

	public static Alert message(AlertType type, Window window, String title,
	        String headerText,
	        String contentText) {
		final Alert alert = new Alert(type);
		if (window != null) {
			alert.initOwner(window);
		}
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		return alert;
	}
}
