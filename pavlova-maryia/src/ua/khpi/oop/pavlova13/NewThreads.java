package ua.khpi.oop.pavlova13;

class ThreadOne implements Runnable {

	@Override
	public void run() {
		System.out.println("New Thread");
	}

}