package ua.khpi.oop.pavlova08.util.forFiles;

import java.io.File;
import java.io.IOException;

import ua.khpi.oop.pavlova06.util.ChoiceUtil;
import ua.khpi.oop.pavlova08.util.InPutUtil;

public class DirectorySearch {

	public static String fileSearch() throws IOException {
		int choice = 0;

		String filename = "E:/";
		String ifDefault = "Beanarchieve.xml";
		FileParser parser = new FileParser(filename);
		parser.print();
		while (choice <= 2) {
			System.out.println("1. Выбор следующей директории.\n2. Шаг назад.\n");
			choice = ChoiceUtil.chooseFirstOrSecond(choice);
			if (choice == 1) {
				System.out.println("Введите название директории.");
				String newPart = InPutUtil.inputString();
				File path = new File(filename);
				if (path.isFile())
					return filename;

				filename = NewPath.doLongerPath(filename, newPart);
				parser = new FileParser(filename);
				parser.print();
			} else if (choice == 2) {
				filename = NewPath.doShorterPath(filename);
				parser = new FileParser(filename);
				parser.print();
			} else if (choice > 2)
				break;

		}

		return ifDefault;
	}
}
