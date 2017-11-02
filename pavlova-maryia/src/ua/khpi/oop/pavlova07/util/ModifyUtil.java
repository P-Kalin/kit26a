package ua.khpi.oop.pavlova07.util;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;
import ua.khpi.oop.pavlova07.HotelGuest;

public class ModifyUtil {

	public static HotelGuest getObject(int index, NewContainerOfStrings containerOfStrings) {
		String element = containerOfStrings.get(index);
		containerOfStrings.remove(index);
		HotelGuest toModify = HotelGuest.toObject(element);
		return toModify;
	}

	public static NewContainerOfStrings returnToContainer(HotelGuest modified,
			NewContainerOfStrings containerOfStrings) {
		String toAdd = modified.toString();
		containerOfStrings.add(toAdd);
		return containerOfStrings;
	}
}
