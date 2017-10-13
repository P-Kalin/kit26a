package ua.khpi.oop.malokhvii02;

import java.util.ArrayList;

import ua.khpi.oop.malokhvii02.data.DataContainer;
import ua.khpi.oop.malokhvii02.data.NumberEqualityContainer;

/**
 * Призначений для демонстрації таблиці вихідних результатів.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 */
public final class PresentateAsTable {

    /**
     * Кількість вхідних чисел.
     */
    public static final short AMOUNT_OF_NUMBERS = 10;
    /**
     * Верхня границя випадкових чисел.
     */
    public static final long MAX_RANDOM_NUMBER = 222229L;
    /**
     * Нижня границя випадкових чисел.
     */
    public static final long MIN_RANDOM_NUMBER = 222221L;

    /**
     * Оголошен приватним для заборони створення класу.
     */
    private PresentateAsTable() {

    }

    /**
     * Призначений для оголошення точки входу у програму.
     *
     * @param args
     *            Аргументи командного рядку
     */
    public static void main(String[] args) {
        ArrayList<NumberEqualityContainer> numberEqualityContainers = new ArrayList<NumberEqualityContainer>();
        ArrayList<Long> inputNumbers = PresentateAsTable.prepareInputNumbers();

        for (Long number : inputNumbers) {
            NumberEqualityContainer item = new NumberEqualityContainer();
            item.setNumber(number);
            numberEqualityContainers.add(item);
        }

        for (DataContainer dataContainer : numberEqualityContainers) {
            dataContainer.computeData();
        }

        String tableRowTemplate = "| %-15s | %-12d | %-12d | %-8s |%n";
        System.out.format(
                "+-----------------+--------------+--------------+----------+%n");
        System.out.format(
                "| Number          | Left Part    | Right Part   | Equality |%n");
        System.out.format(
                "+-----------------+--------------+-------------------------+%n");

        for (NumberEqualityContainer dataContainer : numberEqualityContainers) {
            System.out.printf(tableRowTemplate, dataContainer.getNumber(),
                    dataContainer.getLeftNumberSide(),
                    dataContainer.getRightNumberSide(),
                    dataContainer.isEquals() ? "True" : "False");
        }

        System.out.format(
                "+-----------------+--------------+-------------------------+%n");
    }

    /**
     * Призначений для генерації вхідних даних у вигляді масиву чисел.
     *
     * @return Масив псевдовипадкових чисел
     */
    public static ArrayList<Long> prepareInputNumbers() {
        ArrayList<Long> numbers = new ArrayList<Long>();
        int i;
        for (i = 0; i < AMOUNT_OF_NUMBERS; i++) {
            numbers.add((long) (Math.random()
                    * ((MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER) + 1))
                    + MIN_RANDOM_NUMBER);
        }

        return numbers;
    }

}
