package ua.khpi.oop.pavlova16.App.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.khpi.oop.pavlova16.App.model.Event;
import ua.khpi.oop.pavlova16.App.util.DateUtil;;

public class EventEditDialogController {
	@FXML
	private TextField guestField;
	@FXML
	private TextField dateField;
	@FXML
	private TextField roomField;
	@FXML
	private TextField numberOfPlacesField;
	@FXML
	private TextField motherlandField;

	private Stage dialogStage;
	private Event event;
	private boolean okClicked = false;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setEvent(Event event) {
		this.event = event;

		guestField.setText(event.getDescription());
		dateField.setText(DateUtil.format(event.getDate()));
		roomField.setText(Integer.toString(event.getTimeOfSpending()));
		numberOfPlacesField.setText(Integer.toString(event.getCountOfParticipant()));
		motherlandField.setText(event.getMotherland());

	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {
			event.setDescription(guestField.getText());
			event.setDate(DateUtil.parse(dateField.getText()));
			event.setTimeOfSpending(Integer.parseInt(roomField.getText()));
			event.setCountOfParticipant(Integer.parseInt(numberOfPlacesField.getText()));
			event.setMotherland(motherlandField.getText());
			okClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (guestField.getText() == null || guestField.getText().length() == 0) {
			errorMessage += "No valid event name!\n";
		}
		if (dateField.getText() == null || dateField.getText().length() == 0) {
			errorMessage += "No valid date!\n";
		} else {
			if (!DateUtil.validDate(dateField.getText())) {
				errorMessage += "No valid date. Use the format dd.mm.yyyy!\n";
			}
		}

		if (roomField.getText() == null || roomField.getText().length() == 0) {
			errorMessage += "No valid time of spending!\n";
		}
		if (numberOfPlacesField.getText() == null || numberOfPlacesField.getText().length() == 0) {
			errorMessage += "No valid count of participant!\n";
		}

		if (motherlandField.getText() == null || motherlandField.getText().length() == 0) {
			errorMessage += "No valid city!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Показываем сообщение об ошибке.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

}
