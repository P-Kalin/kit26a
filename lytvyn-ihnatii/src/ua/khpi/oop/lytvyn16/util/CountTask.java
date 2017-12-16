package ua.khpi.oop.lytvyn16.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ua.khpi.oop.lytvyn16.model.Client;
import ua.khpi.oop.lytvyn16.view.Message;

/**
 * 
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class CountTask {
	/**
	 * обчислення середнього значення
	 */
	public synchronized static void countAvrAge(
	        ObservableList<Client> observableList,
	        boolean output) {
		if (observableList.isEmpty()) {
			final Alert alert = Message.message(AlertType.ERROR, null,
			        "Помилка", "Контейнер порожній!", "");
			alert.showAndWait();
		} else {
			final ArrayList<Integer> result = new ArrayList<>();
			final Date today = new Date();
			final SimpleDateFormat ft = new SimpleDateFormat("yyyy");
			String temp = ft.format(today);
			final int current = Integer.parseInt(temp);

			for (final Client client : observableList) {
				temp = client.getBirthday().toString();
				final int year = Integer
				        .parseInt(temp.substring(0, temp.indexOf("-")));
				result.add(current - year);
			}

			long sum = 0;

			for (final Integer integer : result) {
				sum += integer;
			}
			if (output) {
				final Alert message = Message.message(AlertType.INFORMATION,
				        null,
				        "Результат",
				        "Середній вік серед клієнтів: " + sum / result.size(),
				        "");
				message.showAndWait();
			}

		}

	}

	/**
	 * пошук максимуму
	 * 
	 * @param observableList
	 * @return
	 */

	public synchronized static void countMaxAge(
	        ObservableList<Client> observableList,
	        boolean output) {
		if (observableList.isEmpty()) {
			final Alert alert = Message.message(AlertType.ERROR, null,
			        "Помилка", "Контейнер порожній!", "");
			alert.showAndWait();
		} else {
			final Date today = new Date();
			final SimpleDateFormat ft = new SimpleDateFormat("yyyy");
			String temp = ft.format(today);
			final int current = Integer.parseInt(temp);

			final String first = observableList.get(0).getBirthday().toString();
			int result = Integer
			        .parseInt(first.substring(0, first.indexOf("-")));

			for (final Client client : observableList) {
				temp = client.getBirthday().toString();
				final int year = Integer
				        .parseInt(temp.substring(0, temp.indexOf("-")));
				if (result > year) {
					result = year;
				}
			}
			if (output) {
				final Alert message = Message.message(AlertType.INFORMATION,
				        null,
				        "Результат",
				        "Максимальний вік серед клієнтів: "
				                + (current - result),
				        "");
				message.showAndWait();
			}

		}

	}

	/**
	 * пошук мінімуму;
	 * 
	 * @param observableList
	 * @return
	 */

	public synchronized static void countMinAge(
	        ObservableList<Client> observableList,
	        boolean output) {
		if (observableList.isEmpty()) {
			final Alert alert = Message.message(AlertType.ERROR, null,
			        "Помилка", "Контейнер порожній!", "");
			alert.showAndWait();
		} else {
			final Date today = new Date();
			final SimpleDateFormat ft = new SimpleDateFormat("yyyy");
			String temp = ft.format(today);
			final int current = Integer.parseInt(temp);

			final String first = observableList.get(0).getBirthday().toString();
			int result = Integer
			        .parseInt(first.substring(0, first.indexOf("-")));

			for (final Client client : observableList) {
				temp = client.getBirthday().toString();
				final int year = Integer
				        .parseInt(temp.substring(0, temp.indexOf("-")));
				if (result < year) {
					result = year;
				}
			}
			if (output) {
				final Alert message = Message.message(AlertType.INFORMATION,
				        null,
				        "Результат",
				        "Мінімальний вік серед клієнтів: " + (current - result),
				        "");
				message.showAndWait();
			}

		}

	}

}
