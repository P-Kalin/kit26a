package ua.khpi.oop.pavlova13;

import java.util.Random;

public class Main // Класс с методом main()
{
	static FirstThread firstThread; // Побочный поток
	static SecondThread secondThread;
	private static Random random = new Random();

	public static void main(String[] args) {
		// <HotelGuest> list = createDefaultList();
		firstThread = new FirstThread(); // Создание потока
		secondThread = new SecondThread();

		System.out.println("Demonstartion of threads");
		firstThread.start(); // Запуск потока
		secondThread.start();

		int rand = Integer.valueOf(random.nextInt(5));
		for (int i = 0; i < rand; i++) {
			try {
				Thread.sleep(1000); // Приостанавливает поток на 1 секунду
			} catch (InterruptedException e) {

			}
		}
		firstThread.finish();
		secondThread.finish();
		try {
			Thread.sleep(1000); // Приостанавливает поток на 1 секунду
		} catch (InterruptedException e) {
		}
		System.out.println("\n\n\n Demonstration is over");

	}

}