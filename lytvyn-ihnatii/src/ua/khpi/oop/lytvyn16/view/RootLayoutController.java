package ua.khpi.oop.lytvyn16.view;

import java.io.File;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import ua.khpi.oop.lytvyn16.Main;
import ua.khpi.oop.lytvyn16.model.Client;
import ua.khpi.oop.lytvyn16.util.ClientGenerator;
import ua.khpi.oop.lytvyn16.util.ClientUtil;
import ua.khpi.oop.lytvyn16.util.CountTask;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 */
public class RootLayoutController {

	// Reference to the main application
	private Main main;

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMain(Main main) {
		this.main = main;
	}

	/**
	 * Opens an about dialog.
	 */
	@FXML
	private void handleAbout() {
		final Alert alert = Message.message(AlertType.INFORMATION, null,
		        "Про програму", "Автор: Литвин І.І. КІТ-26 НТУ\"ХПІ\"", "");
		alert.showAndWait();
	}

	/**
	 * 
	 */
	@FXML
	private void handleClean() {
		main.getClientData().clear();
		main.count = 0;
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}

	@FXML
	private void handleFirstTask() {
		CountTask.countAvrAge(main.getClientData(), true);
	}

	/**
	 * Called when the user clicks the generate button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleGenerate() {
		int count = 0;
		final TextInputDialog dialog = new TextInputDialog("...");
		dialog.setTitle("Генерування клієнтів");
		// dialog.setHeaderText("");
		dialog.setContentText("Будь ласка введіть кількість клієнтів:");

		// Traditional way to get the response value.
		final Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			final String temp = result.get();
			try {
				count = Integer.parseInt(temp);
			} catch (final NumberFormatException exception) {
				final Alert alert = Message.message(AlertType.ERROR, null,
				        "Генерація", "Помилка!",
				        "Введено невірно кількість клієнтів!");
				alert.showAndWait();
			}
		}
		for (int i = 0; i < count; i++) {
			final Client tempClient = ClientGenerator.generate(
			        main.getClientData().size());
			main.getClientData().add(tempClient);
			main.count++;
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleGenerateClient() {
		final Client tempClient = ClientGenerator.generate(
		        main.getClientData().size());
		main.getClientData().add(tempClient);
		main.count++;
	}

	/**
	 * Creates an empty address book.
	 */
	@FXML
	private void handleNew() {
		main.getClientData().clear();
		main.setClientFilePath(null);
		main.count = 0;
	}

	/**
	 * Opens a FileChooser to let the user select an address book to load.
	 */
	@FXML
	private void handleOpen() {
		final FileChooser fileChooser = new FileChooser();

		// Set extension filter
		final FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
		        "XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		final File file = fileChooser.showOpenDialog(main.getPrimaryStage());

		if (file != null) {
			main.loadClientDataFromFile(file);
		}
	}

	@FXML
	private void handleParallelProcessing() {
		if (main.getClientData().isEmpty()) {
			final Alert alert = Message.message(AlertType.ERROR, null,
			        "Помилка", "Контейнер порожній!", "");
			alert.showAndWait();
		} else {
			int timeout = 0;
			final TextInputDialog dialog = new TextInputDialog("...");
			dialog.setTitle("Паралельна обробка");
			// dialog.setHeaderText("");
			dialog.setContentText("Будь ласка введіть тайм-аут у ms");

			// Traditional way to get the response value.
			final Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				final String temp = result.get();
				try {
					timeout = Integer.parseInt(temp);
				} catch (final NumberFormatException exception) {
					final Alert alert = Message.message(AlertType.ERROR, null,
					        "Помилка!", "Введено невірно тайм-аут!", "");
					alert.showAndWait();
				}
			}
			final Thread first = new Thread(new Runnable() {
				@Override
				public void run() {
					CountTask.countMinAge(main.getClientData(), false);
				}
			});

			final Thread second = new Thread(new Runnable() {
				@Override
				public void run() {
					CountTask.countMaxAge(main.getClientData(), false);
				}
			});

			final Thread third = new Thread(new Runnable() {
				@Override
				public void run() {
					CountTask.countAvrAge(main.getClientData(), false);
				}
			});
			long end = 0;
			final long start = System.currentTimeMillis();
			try {
				first.start();
				first.join(timeout);
				second.start();
				second.join(timeout);
				third.start();
				third.join(timeout);
				end = System.currentTimeMillis();
			} catch (final InterruptedException exception) {
				exception.printStackTrace();
			}
			final Alert message = Message.message(AlertType.INFORMATION, null,
			        "Результат",
			        "Час паралельного виконання: " + (end - start) + "ms",
			        "");
			message.showAndWait();
		}
	}

	/**
	 * Saves the file to the person file that is currently open. If there is no
	 * open file, the "save as" dialog is shown.
	 */
	@FXML
	private void handleSave() {
		final File clientFile = main.getClientFilePath();
		if (clientFile != null) {
			main.saveClientDataToFile(clientFile);
		} else {
			handleSaveAs();
		}
	}

	/**
	 * Opens a FileChooser to let the user select a file to save to.
	 */
	@FXML
	private void handleSaveAs() {
		final FileChooser fileChooser = new FileChooser();

		// Set extension filter
		final FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
		        "XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(main.getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			main.saveClientDataToFile(file);
		}
	}

	@SuppressWarnings("unused")
	private void handleSearch() {
		if (main.getClientData().isEmpty()) {
			final Alert alert = Message.message(AlertType.ERROR, null,
			        "Помилка", "Контейнер порожній!", "");
			alert.showAndWait();
		} else {
			ClientUtil.search(main.getClientData());
		}
	}

	@FXML
	private void handleSecondTask() {
		CountTask.countMinAge(main.getClientData(), true);
	}

	@FXML
	private void handleSequentialProcessing() {
		if (main.getClientData().isEmpty()) {
			final Alert alert = Message.message(AlertType.ERROR, null,
			        "Помилка", "Контейнер порожній!", "");
			alert.showAndWait();
		} else {
			final long start = System.currentTimeMillis();
			CountTask.countMinAge(main.getClientData(), false);
			CountTask.countMaxAge(main.getClientData(), false);
			CountTask.countAvrAge(main.getClientData(), false);
			final long end = System.currentTimeMillis();
			final Alert message = Message.message(AlertType.INFORMATION, null,
			        "Результат",
			        "Час послідовного виконання: " + (end - start) + "ms",
			        "");
			message.showAndWait();
		}
	}

	@FXML
	private void handleThirdTask() {
		CountTask.countMaxAge(main.getClientData(), true);
	}

}
