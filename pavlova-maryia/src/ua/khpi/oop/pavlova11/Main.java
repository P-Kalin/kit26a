package ua.khpi.oop.pavlova11;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import ua.khpi.oop.pavlova08.util.InPutUtil;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Демонстрация обработки информации регулярными выражениями\n");
		String infoCustomer = inputGuest();
		System.out.println("Введенная информация валидна: " + " " + CheckFields.generalTest(infoCustomer) + "\n");

		String infoFile = deserializeString("SerialCont");
		System.out.println("Считанная информация валидна: " + " " + CheckFields.generalTest(infoFile) + "\n");
		// one.setGuestNameSurname("Ncdkj sdn sdcjksc");
		// one.setGuestMotherland("Fmd dvk dk");

		/**
		 * info += one.getGuestNameSurname() + "|" + one.getGuestDateOfBirth() + "|" +
		 * one.getGuestMotherland() + "|" + one.getGuestPassport() + "|" +
		 * one.getDateOfArrival() + "|" + one.getDateOfEviction() + "|" +
		 * one.getRoomNum() + "|" + one.getRoomClass() + "|" + one.getRoomPlaces() + "|"
		 * + one.getReasonOfArrival();
		 * 
		 * System.out.println("Введенная информация валидна: " + " " +
		 * CheckFields.generalTest(info)); System.out.println(info);
		 */
	}

	public static String inputGuest() {
		System.out.println("Введите Ф.И.О.");
		String guestNameSurname = InPutUtil.inputString();
		System.out.println("Введите дату рождения");
		String guestBith = InPutUtil.inputString();
		System.out.println("Введите странуу и город");
		String guestMotherland = InPutUtil.inputString();
		System.out.println("Введите номер паспорта");
		String guestPassport = InPutUtil.inputString();
		System.out.println("Введите дату заселения");
		String dateOfArrival = InPutUtil.inputString();
		System.out.println("Введите дату выселения");
		String dateOfEviction = InPutUtil.inputString();
		System.out.println("Введите номер комнаты");
		String roomNum = InPutUtil.inputString();
		System.out.println("Введите класс номера");
		String roomClass = InPutUtil.inputString();
		System.out.println("Введите количество мест в номере");
		String roomPlaces = InPutUtil.inputString();
		System.out.println("Введите причину заселения");
		String reasonOfArrival = InPutUtil.inputString();

		String info = new String();
		info += guestNameSurname + "|" + guestBith + "|" + guestMotherland + "|" + guestPassport + "|" + dateOfArrival
				+ "|" + dateOfEviction + "|" + roomNum + "|" + roomClass + "|" + roomPlaces + "|" + reasonOfArrival;
		return info;
	}

	public static String deserializeString(String toOpen) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(toOpen), StandardCharsets.UTF_8);
		String info = new String();
		info += lines.get(0);
		return info;
	}
}
