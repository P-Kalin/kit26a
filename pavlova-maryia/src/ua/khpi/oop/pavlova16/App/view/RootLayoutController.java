package ua.khpi.oop.pavlova16.App.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import ua.khpi.oop.pavlova16.App.MainApp;

public class RootLayoutController {

	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleNew() {
		mainApp.getEventData().clear();
		mainApp.setEventsFilePath(null);
	}

	@FXML
	private void handleOpen() {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

		if (file != null) {
			mainApp.loadEventsDataFromFile(file);
		}
	}

	@FXML
	private void handleSave() {
		File personFile = mainApp.getEventsFilePath();
		if (personFile != null) {
			mainApp.saveEventsDataToFile(personFile);
		} else {
			handleSaveAs();
		}
	}

	@FXML
	private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {

			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			mainApp.saveEventsDataToFile(file);
		}
	}

	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("EventApp");
		alert.setHeaderText("About");
		alert.setContentText("Author: Pavlova Mariia");

		alert.showAndWait();
	}

	@FXML
	private void handleExit() {
		System.exit(0);
	}
}
