package ua.khpi.oop.pavlova16.application;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.khpi.oop.pavlova16.application.container.Guest;
import ua.khpi.oop.pavlova16.application.container.ListOfGuests;
import ua.khpi.oop.pavlova16.application.controllers.EditDialogController;
import ua.khpi.oop.pavlova16.application.controllers.OverviewController;
import ua.khpi.oop.pavlova16.application.controllers.RootLayoutController;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Guest> guestData = FXCollections.observableArrayList();

	public Main() {
		guestData.add(new Guest());
	}

	public ObservableList<Guest> getGuestData() {
		return guestData;
	}

	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("List of Hotel Guests");

		this.primaryStage.getIcons().add(new Image("file: ua/khpi/oop/pavlova16/util/book.png"));

		initRootLayout();
		showGuestOverview();
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("FXMLUtil/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File file = getGuestsFilePath();
		if (file != null) {
			loadGuestsDataFromFile(file);
		}
	}

	public void showGuestOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("FXMLUtil/Overview.fxml"));
			AnchorPane eventOverview = (AnchorPane) loader.load();
			rootLayout.setCenter(eventOverview);

			OverviewController controller = loader.getController();
			controller.setMain(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public boolean showEditDialog(Guest guest) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("FXMLUtil/EditDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("Edit Guest");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);

		EditDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setGuest(guest);

		dialogStage.showAndWait();

		return controller.isOkClicked();

	}

	public File getGuestsFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	public void setGuestsFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());
			primaryStage.setTitle("List of Hotel Guests - " + file.getName());
		} else {
			prefs.remove("filePath");
			primaryStage.setTitle("List of Hotel Guests");
		}
	}

	@SuppressWarnings("unchecked")
	public void loadGuestsDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(ListOfGuests.class);
			Unmarshaller um = context.createUnmarshaller();

			ListOfGuests wrapper = (ListOfGuests) um.unmarshal(file);

			guestData.clear();
			guestData.addAll(wrapper.getGuests());

			// Сохраняем путь к файлу в реестре.
			setGuestsFilePath(file);

		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not load data");
			alert.setContentText("Could not load data from file:\n" + file.getPath());

			alert.showAndWait();
		}
	}

	public void saveGuestsDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(ListOfGuests.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Обёртываем наши данные об адресатах.
			ListOfGuests wrapper = new ListOfGuests();
			wrapper.setGuests(guestData);

			// Маршаллируем и сохраняем XML в файл.
			m.marshal(wrapper, file);

			// Сохраняем путь к файлу в реестре.
			setGuestsFilePath(file);
		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n" + file.getPath());

			alert.showAndWait();
		}
	}

}
