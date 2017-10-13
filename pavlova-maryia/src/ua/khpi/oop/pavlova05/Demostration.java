package ua.khpi.oop.pavlova05;

public class Demostration {
	public static void demonstration() {
		int i;
		ContainerOfStrings containerOfStrings = new ContainerOfStrings();
		containerOfStrings.add("First");
		containerOfStrings.add("Second");
		containerOfStrings.add("Third");
		for (i = 0; i < containerOfStrings.size(); i++)
			System.out.println(containerOfStrings.get(i));

		boolean found = containerOfStrings.contains("Second");
		System.out.println(found);
		ContainerOfStrings temp = new ContainerOfStrings();
		temp.add("First");
		temp.add("Second");
		temp.add("Third");
		found = containerOfStrings.containsAll(temp);
		System.out.println(found);

		boolean ifDel = containerOfStrings.remove("Second");
		System.out.println(ifDel);
		containerOfStrings.clear();
	}
}
