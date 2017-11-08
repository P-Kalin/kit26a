package ua.khpi.oop.pavlova08.util.forFiles;

import java.util.ArrayList;

import ua.khpi.oop.pavlova03.TextUtil;

public class NewPath {

	private static final String SEPARATOR = "/";

	public static String doLongerPath(String path, String newDirectory) {
		path += SEPARATOR;
		path += newDirectory;
		return path;
	}

	public static String doShorterPath(String path) {
		ArrayList<String> inLines = (ArrayList<String>) TextUtil.extractElementsFromText(path, SEPARATOR);
		inLines.remove(inLines.size() - 1);

		String newPath = new String();
		for (String part : inLines) {
			if (path.length() > 0)
				newPath += SEPARATOR;
			newPath += part;
		}

		return newPath;
	}
}
