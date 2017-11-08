package ua.khpi.oop.pavlova08;

import java.io.IOException;
import java.util.Scanner;

import ua.khpi.oop.pavlova08.util.forFiles.FileParser;

public class Main {
	public Scanner scanner = new Scanner(System.in);

	public static void main(final String[] args) throws IOException {
		FileParser parser = new FileParser("E:/");
		parser.print();
	}
}
