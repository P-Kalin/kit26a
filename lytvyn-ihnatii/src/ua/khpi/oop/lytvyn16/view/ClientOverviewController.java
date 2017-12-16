package ua.khpi.oop.lytvyn16.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ua.khpi.oop.lytvyn16.Main;
import ua.khpi.oop.lytvyn16.model.Client;
import ua.khpi.oop.lytvyn16.util.DateUtil;

/**
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class ClientOverviewController {
	@FXML
	private TableView<Client> clientTable;
	@FXML
	private TableColumn<Client, String> regNumColumn;
	@FXML
	private TableColumn<Client, String> nameColumn;

	@FXML
	private Label genderLabel;
	@FXML
	private Label regNumLabel;
	@FXML
	private Label regDateLabel;
	@FXML
	private Label nameLabel;
	@FXML
	private Label heightLabel;
	@FXML
	private Label eyesLabel;
	@FXML
	private Label birthdayLabel;
	@FXML
	private Label hobbyLabel;
	@FXML
	private Label requirementsLabel;

	// Reference to the main application.
	private Main main;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public ClientOverviewController() {
	}

	/**
	 * @return the main
	 */
	public Main getMain() {
		return main;
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param main
	 */
	public void setMain(Main main) {
		this.main = main;

		// Add observable list data to the table
		clientTable.setItems(main.getClientData());
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteClient() {
		final int selectedIndex = clientTable.getSelectionModel()
		        .getSelectedIndex();
		if (selectedIndex >= 0) {
			clientTable.getItems().remove(selectedIndex);
			main.count--;
		} else {
			// Nothing selected.
			final Alert alert = Message.message(AlertType.WARNING,
			        main.getPrimaryStage(),
			        "No Selection", "No Client Selected",
			        "Please select a client in the table.");
			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void handleEditClient() {
		final Client selectedClient = clientTable.getSelectionModel()
		        .getSelectedItem();
		if (selectedClient != null) {
			final boolean okClicked = main.showClientEditDialog(selectedClient);
			if (okClicked) {
				showClientDetails(selectedClient);
			}

		} else {
			// Nothing selected.
			final Alert alert = Message.message(AlertType.WARNING,
			        main.getPrimaryStage(),
			        "No Selection", "No Client Selected",
			        "Please select a client in the table.");
			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleNewClient() {
		final Client tempClient = new Client(main.getClientData().size());
		final boolean okClicked = main.showClientEditDialog(tempClient);
		if (okClicked) {
			main.getClientData().add(tempClient);
			main.count++;
		}
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		regNumColumn.setCellValueFactory(
		        cellData -> cellData.getValue().regNum().asString());
		nameColumn.setCellValueFactory(
		        cellData -> cellData.getValue().name());
		// Clear person details.
		showClientDetails(null);

		// Listen for selection changes and show the person details when
		// changed.
		clientTable.getSelectionModel().selectedItemProperty().addListener(
		        (observable, oldValue,
		                newValue) -> showClientDetails(newValue));
	}

	/**
	 * Fills all text fields to show details about the client. If the specified
	 * person is null, all text fields are cleared.
	 * 
	 * @param person
	 *            the person or null
	 */
	private void showClientDetails(Client client) {
		if (client != null) {
			// Fill the labels with info from the person object.
			genderLabel.setText(client.getGender());
			regNumLabel.setText(Integer.toString(client.getRegNum()));
			regDateLabel.setText(DateUtil.format(client.getBirthday()));
			nameLabel.setText(client.getName());
			heightLabel.setText(Integer.toString(client.getHeight()));
			eyesLabel.setText(client.getEyes());
			birthdayLabel.setText(DateUtil.format(client.getBirthday()));
			hobbyLabel.setText(client.getHobby());
			requirementsLabel.setText(client.getRequirements());
		} else {
			// Client is null, remove all the text.
			genderLabel.setText("");
			regNumLabel.setText("");
			regDateLabel.setText("");
			nameLabel.setText("");
			heightLabel.setText("");
			eyesLabel.setText("");
			birthdayLabel.setText("");
			hobbyLabel.setText("");
			requirementsLabel.setText("");
		}
	}
}
