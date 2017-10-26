package ua.khpi.oop.pavlova06.util.sortAlgorithms;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.pavlova06.NewContainerOfStrings;

public class ShakerSort {

	public static Array<String> sort(Array<String> sortableArray) {
		int left = 1;
		int right = sortableArray.size() - 1;

		while (left <= right) {
			for (int i = right; i >= left; i--)
				if (sortableArray.get(i - 1).length() > sortableArray.get(i).length())
					changePlacesArray(sortableArray, i);

			left++;
			for (int i = left; i <= right; i++)
				if (sortableArray.get(i - 1).length() > sortableArray.get(i).length())
					changePlacesArray(sortableArray, i);

			right--;

		}
		return sortableArray;
	}

	public static NewContainerOfStrings sort(NewContainerOfStrings sortableArray) {
		int left = 1;
		int right = sortableArray.size() - 1;

		while (left <= right) {
			for (int i = right; i >= left; i--)
				if (sortableArray.get(i - 1).length() > sortableArray.get(i).length())
					changePlacesContainer(sortableArray, i);

			left++;
			for (int i = left; i <= right; i++)
				if (sortableArray.get(i - 1).length() > sortableArray.get(i).length())
					changePlacesContainer(sortableArray, i);

			right--;

		}
		return sortableArray;
	}

	private static void changePlacesArray(Array<String> sortableArray, int position) {
		StringBuilder temp = new StringBuilder();
		temp.append(sortableArray.get(position - 1));
		sortableArray.remove(position - 1);
		sortableArray.add(position - 1, sortableArray.get(position));
		sortableArray.remove(position);
		sortableArray.add(position, temp.toString());
	}

	private static void changePlacesContainer(NewContainerOfStrings sortableArray, int position) {
		StringBuilder temp = new StringBuilder();
		temp.append(sortableArray.get(position - 1));
		sortableArray.remove(position - 1);
		sortableArray.add(position - 1, sortableArray.get(position));
		sortableArray.remove(position);
		sortableArray.add(position, temp.toString());
	}
}
