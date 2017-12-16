package ua.khpi.oop.lytvyn16;

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
import ua.khpi.oop.lytvyn16.model.Bureau;
import ua.khpi.oop.lytvyn16.model.Client;
import ua.khpi.oop.lytvyn16.view.ClientEditDialogController;
import ua.khpi.oop.lytvyn16.view.ClientOverviewController;
import ua.khpi.oop.lytvyn16.view.Message;
import ua.khpi.oop.lytvyn16.view.RootLayoutController;

/**
 * Відповідає за запуск роботи застосунку.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	private Stage primaryStage;
	private BorderPane rootLayout;

	/**
	 * Список клієнтів
	 */
	private ObservableList<Client> clientData = FXCollections
	        .observableArrayList();

	public int count = 0;

	public Main() {
	}

	/**
	 * @return the clientData
	 */
	public ObservableList<Client> getClientData() {
		return clientData;
	}

	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 * 
	 * @return
	 */
	public File getClientFilePath() {
		final Preferences prefs = Preferences.userNodeForPackage(Main.class);
		final String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Initializes the root layout and tries to load the last opened person
	 * file.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
			        Main.class.getClassLoader().getResource(
			                "\\ua\\khpi\\oop\\lytvyn16\\view\\RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			final Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			final RootLayoutController controller = loader.getController();
			controller.setMain(this);

			primaryStage.show();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		// Try to load last opened person file.
		final File file = getClientFilePath();
		if (file != null) {
			loadClientDataFromFile(file);
		}
	}

	/**
	 * Loads person data from the specified file. The current person data will
	 * be replaced.
	 * 
	 * @param file
	 */
	public void loadClientDataFromFile(File file) {
		try {
			final JAXBContext context = JAXBContext
			        .newInstance(Bureau.class);
			final Unmarshaller um = context.createUnmarshaller();

			// Reading XML from the file and unmarshalling.
			final Bureau bureau = (Bureau) um.unmarshal(file);

			clientData.clear();
			clientData.addAll(bureau.getClients());
			count = bureau.getClients().size();

			// Save the file path to the registry.
			setClientFilePath(file);

		} catch (final Exception e) { // catches ANY exception
			final Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not load data");
			alert.setContentText(
			        "Could not load data from file:\n" + file.getPath());

			alert.showAndWait();
		}
	}

	/**
	 * Saves the current person data to the specified file.
	 * 
	 * @param file
	 */
	public void saveClientDataToFile(File file) {
		try {
			final JAXBContext context = JAXBContext
			        .newInstance(Bureau.class);
			final Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			final Bureau wrapper = new Bureau();
			wrapper.setClients(clientData);

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);

			// Save the file path to the registry.
			setClientFilePath(file);
		} catch (final Exception e) { // catches ANY exception
			final Alert alert = Message.message(AlertType.ERROR, null, "Error",
			        "Could not save data",
			        "Could not save data to file:\n" + file.getPath());
			alert.showAndWait();
		}
	}

	/**
	 * @param clientData
	 *            the clientData to set
	 */
	public void setClientData(ObservableList<Client> clientData) {
		this.clientData = clientData;
	}

	/**
	 * Sets the file path of the currently loaded file. The path is persisted in
	 * the OS specific registry.
	 * 
	 * @param file
	 *            the file or null to remove the path
	 */
	public void setClientFilePath(File file) {
		final Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());

			// Update the stage title.
			primaryStage.setTitle("AddressApp - " + file.getName());
		} else {
			prefs.remove("filePath");

			// Update the stage title.
			primaryStage.setTitle("AddressApp");
		}
	}

	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 * 
	 * @param person
	 *            the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showClientEditDialog(Client client) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
			        Main.class.getClassLoader().getResource(
			                "\\ua\\khpi\\oop\\lytvyn16\\view\\ClientEditDialog.fxml"));
			final AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			final Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Client");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			final Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			final ClientEditDialogController controller = loader
			        .getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(client);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (final IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Shows the client overview inside the root layout.
	 */
	public void showClientOverview() {
		try {
			// Load person overview.
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getClassLoader().getResource(
			        "\\ua\\khpi\\oop\\lytvyn16\\view\\ClientOverview.fxml"));
			final AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			// Give the controller access to the main app.
			final ClientOverviewController controller = loader.getController();
			controller.setMain(this);

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Bureau of acquaintances");

		// Set the application icon.
		this.primaryStage.getIcons()
		        .add(new Image(
		                "\\ua\\khpi\\oop\\lytvyn16\\resources\\images\\ico_32.png"));

		initRootLayout();
		showClientOverview();
	}
}
