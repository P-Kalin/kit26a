package ua.khpi.oop.lytvyn16.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.khpi.oop.lytvyn16.model.Client;
import ua.khpi.oop.lytvyn16.util.ClientUtil;
import ua.khpi.oop.lytvyn16.util.DateUtil;

/**
 * Dialog to edit details of a client.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class ClientEditDialogController {
	@FXML
	private TextField genderField;
	@FXML
	private TextField regNumField;
	@FXML
	private TextField regDateField;
	@FXML
	private TextField nameField;
	@FXML
	private TextField heightField;
	@FXML
	private TextField eyesField;
	@FXML
	private TextField birthdayField;
	@FXML
	private TextField hobbyField;
	@FXML
	private TextField requirementsField;

	private Stage dialogStage;
	private Client client;
	private boolean okClicked = false;

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the person to be edited in the dialog.
	 * 
	 * @param person
	 */
	public void setPerson(Client client) {
		this.client = client;

		genderField.setText(client.getGender());
		regNumField.setText(Integer.toString(client.getRegNum()));
		regDateField.setText(DateUtil.format(client.getRegDate()));
		regDateField.setPromptText("dd.mm.yyyy");
		nameField.setText(client.getName());
		heightField.setText(Integer.toString(client.getHeight()));
		eyesField.setText(client.getEyes());
		birthdayField.setText(DateUtil.format(client.getBirthday()));
		hobbyField.setText(client.getHobby());
		birthdayField.setPromptText("dd.mm.yyyy");
		requirementsField.setText(client.getRequirements());
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			client.setGender(genderField.getText());
			client.setRegNum(Integer.parseInt(regNumField.getText()));
			client.setRegDate(DateUtil.parse(regDateField.getText()));
			client.setName(nameField.getText());
			client.setHeight(Integer.parseInt(heightField.getText()));
			client.setEyes(eyesField.getText());
			client.setBirthday(DateUtil.parse(birthdayField.getText()));
			client.setHobby(hobbyField.getText());
			client.setRequirements(requirementsField.getText());

			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (genderField.getText() == null
		        || genderField.getText().length() == 0
		        || !ClientUtil.check(ClientUtil.REGEX.gender,
		                genderField.getText())) {
			errorMessage += "No valid gender!\n";
		}
		if (regNumField.getText() == null
		        || regNumField.getText().length() == 0) {
			errorMessage += "No valid regNum!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(regNumField.getText());
			} catch (final NumberFormatException e) {
				errorMessage += "No valid regNum (must be an integer)!\n";
			}
		}
		if (regDateField.getText() == null
		        || regDateField.getText().length() == 0) {
			errorMessage += "No valid regDate!\n";
		} else {
			if (!DateUtil.validDate(regDateField.getText())) {
				errorMessage += "No valid regDate. Use the format dd.mm.yyyy!\n";
			}
		}
		if (nameField.getText() == null
		        || nameField.getText().length() == 0
		        || !ClientUtil.check(ClientUtil.REGEX.name,
		                nameField.getText())) {
			errorMessage += "No valid name!\n";
		}
		if (eyesField.getText() == null
		        || eyesField.getText().length() == 0
		        || !ClientUtil.check(ClientUtil.REGEX.string,
		                eyesField.getText())) {
			errorMessage += "No valid eyes!\n";
		}
		if (heightField.getText() == null
		        || heightField.getText().length() == 0
		        || !ClientUtil.check(ClientUtil.REGEX.height,
		                heightField.getText())) {
			errorMessage += "No valid height!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(heightField.getText());
			} catch (final NumberFormatException e) {
				errorMessage += "No valid height (must be an integer)!\n";
			}
		}
		if (hobbyField.getText() == null
		        || hobbyField.getText().length() == 0
		        || !ClientUtil.check(ClientUtil.REGEX.string,
		                hobbyField.getText())) {
			errorMessage += "No valid hobby!\n";
		}
		if (birthdayField.getText() == null
		        || birthdayField.getText().length() == 0) {
			errorMessage += "No valid birthday!\n";
		} else {
			if (!DateUtil.validDate(birthdayField.getText())) {
				errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
			}
		}
		if (requirementsField.getText() == null
		        || requirementsField.getText().length() == 0
		        || !ClientUtil.check(ClientUtil.REGEX.string,
		                requirementsField.getText())) {
			errorMessage += "No valid requirements!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			final Alert alert = Message.message(AlertType.ERROR, dialogStage,
			        "Invalid Fields", "Please correct invalid fields",
			        errorMessage);
			alert.showAndWait();

			return false;
		}
	}
}
