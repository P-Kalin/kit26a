package ua.khpi.oop.pavlova16.App.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ua.khpi.oop.pavlova16.App.MainApp;
import ua.khpi.oop.pavlova16.App.model.Event;
import ua.khpi.oop.pavlova16.App.util.DateUtil;;

public class EventOverviewController {

	@FXML
	private TableView<Event> eventTable;
	@FXML
	private TableColumn<Event, String> descriptionColoumn;
	@FXML
	private Label eventLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label timeOfSpendingLabel;
	@FXML
	private Label countOfParticipantLabel;
	@FXML
	private Label placeLabel;

	private MainApp mainApp;

	public EventOverviewController() {
	}

	@FXML
	private void initialize() {

		descriptionColoumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

		showEventDetails(null);
		eventTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showEventDetails(newValue));

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		eventTable.setItems(mainApp.getEventData());
	}

	private void showEventDetails(Event event) {
		if (event != null) {
			eventLabel.setText(event.getDescription());
			dateLabel.setText(DateUtil.format(event.getDate()));
			timeOfSpendingLabel.setText(Integer.toString(event.getTimeOfSpending()));
			countOfParticipantLabel.setText(Integer.toString(event.getCountOfParticipant()));
			placeLabel.setText(event.getMotherland());
		} else {
			eventLabel.setText("");
			dateLabel.setText("");
			timeOfSpendingLabel.setText("");
			countOfParticipantLabel.setText("");
			placeLabel.setText("");
		}
	}

	@FXML
	private void handleDeleteEvent() {
		int selectedIndex = eventTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			eventTable.getItems().remove(selectedIndex);
		} else {
			// Ничего не выбрано.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Event Selected");
			alert.setContentText("Please select a event in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleNewEvent() throws IOException {
		Event tempEvent = new Event();
		boolean okClicked = mainApp.showEventEditDialog(tempEvent);
		if (okClicked) {
			mainApp.getEventData().add(tempEvent);
		}
	}

	/**
	 * Вызывается, когда пользователь кликает по кнопка Edit... Открывает диалоговое
	 * окно для изменения выбранного адресата.
	 * 
	 * @throws IOException
	 */
	@FXML
	private void handleEditEvent() throws IOException {
		Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
		if (selectedEvent != null) {
			boolean okClicked = mainApp.showEventEditDialog(selectedEvent);
			if (okClicked) {
				showEventDetails(selectedEvent);
			}

		} else {

			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}
}
