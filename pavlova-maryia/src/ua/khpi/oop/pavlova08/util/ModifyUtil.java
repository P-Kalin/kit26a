package ua.khpi.oop.pavlova08.util;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;
import ua.khpi.oop.pavlova08.HotelGuest;

/**
 * Class <b>ModifyUtil</b> contains methods for better modifying of elements in
 * a specific container. It has a method to get a specific element to modify via
 * creating an object from a string. It also has a method that returns an
 * element to the containerOfStrings (but at rhe end of the array).
 * 
 * @author pavlova-mv
 *
 */
public class ModifyUtil {

	/**
	 * Method <b>getObject</b> perfoms getting of a specific element by its index.
	 * Also it removes the original view of an element and creates an object typed
	 * <i>HotelGuest</i> from a string.
	 * 
	 * @param index
	 *            is an index of a specific element
	 * @param containerOfStrings
	 *            is a container
	 * @return element that will be modified
	 */
	public static HotelGuest getObject(int index, NewContainerOfStrings containerOfStrings) {
		String element = containerOfStrings.get(index);
		containerOfStrings.remove(index);
		HotelGuest toModify = HotelGuest.toObject(element);
		return toModify;
	}

	/**
	 * Method <b>returnToContainer</b> performs adding the modified element to the
	 * container
	 * 
	 * @param modified
	 *            is a modified element
	 * @param containerOfStrings
	 *            is a container
	 * @return updated container
	 */
	public static NewContainerOfStrings returnToContainer(HotelGuest modified,
			NewContainerOfStrings containerOfStrings) {
		String toAdd = modified.toString();
		containerOfStrings.add(toAdd);
		return containerOfStrings;
	}
}
