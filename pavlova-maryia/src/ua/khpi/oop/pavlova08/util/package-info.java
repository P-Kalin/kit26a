/**
 * Package contains util classes for work with customer and container of
 * strings.</br>
 * Class <b>ChoiceUtil</b> contains methods for dialogue window with a customer.
 * It helps to get customer's choice by the index, and the this choice is used
 * in <b><i>CommandParser</b></i> to do one of many proposed commands. Number is
 * choosed using the <b><i>InPutUtil</i></b>. Methods:</br>
 * ~ <b>listOfCommands</b> gives to customer a list of possible actions with a
 * container.</br>
 * </br>
 * <b>InPutUtil</b> contains methods for iformation input by customer. It has
 * method for modifying element from the container. It works via
 * <b><i>ModifyUtil</i></b>. Also it fas a method for filling object's fields.
 * It works via <b><i>InputUtil</i></b>. Methods: </br>
 * ~ <b>inputGuest</b> performs the filling of an object's fields with input
 * information written by customer.</br>
 * ~ <b>askToModifyHotelGuest</b> returns an element from the container, that
 * will be modified. </br>
 * </br>
 * Class <b>ModifyUtil</b> contains methods for better modifying of elements in
 * a specific container. It has a method to get a specific element to modify via
 * creating an object from a string. It also has a method that returns an
 * element to the containerOfStrings (but at rhe end of the array).</br>
 * ~ <b>getObject</b> perfoms getting of a specific element by its index. Also
 * it removes the original view of an element and creates an object typed
 * <i>HotelGuest</i> from a string.</br>
 * ~ <b>returnToContainer</b> performs adding the modified element to the
 * container.
 * 
 * @author pavlova-mv
 */
package ua.khpi.oop.pavlova08.util;
