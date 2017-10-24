package ua.khpi.oop.malokhvii02;

import java.util.ArrayList;

import ua.khpi.oop.malokhvii02.data.DataContainer;
import ua.khpi.oop.malokhvii02.data.NumberEqualityContainer;

/**
 * Призначений для демонстрації таблиці вихідних результатів.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
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
     * Призначений для оголошення точки входу у програму.
     *
     * @param args
     *            Аргументи командного рядку
     */
    public static void main(final String[] args) {
        final ArrayList<NumberEqualityContainer> numberEqualityContainers = new ArrayList<NumberEqualityContainer>();
        final ArrayList<Long> inputNumbers = PresentateAsTable
                .prepareInputNumbers();

        for (final Long number : inputNumbers) {
            final NumberEqualityContainer item = new NumberEqualityContainer();
            item.setNumber(number);
            numberEqualityContainers.add(item);
        }

        for (final DataContainer dataContainer : numberEqualityContainers) {
            dataContainer.computeData();
        }

        final String tableRowTemplate = "| %-15s | %-12d | %-12d | %-8s |%n";
        System.out.format(
                "+-----------------+--------------+--------------+--------"
                        + "--+%n");
        System.out.format(
                "| Number          | Left Part    | Right Part   | Equality"
                        + " |%n");
        System.out
                .format("+-----------------+--------------+-------------------"
                        + "------+%n");

        for (final NumberEqualityContainer dataContainer : numberEqualityContainers) {
            System.out.printf(tableRowTemplate, dataContainer.getNumber(),
                    dataContainer.getLeftNumberSide(),
                    dataContainer.getRightNumberSide(),
                    dataContainer.isEquals() ? "True" : "False");
        }

        System.out.format(
                "+-----------------+--------------+-----------------------"
                        + "--+%n");
    }

    /**
     * Призначений для генерації вхідних даних у вигляді масиву чисел.
     *
     * @return Масив псевдовипадкових чисел
     */
    public static ArrayList<Long> prepareInputNumbers() {
        final ArrayList<Long> numbers = new ArrayList<Long>();
        int i;
        for (i = 0; i < PresentateAsTable.AMOUNT_OF_NUMBERS; i++) {
            numbers.add((long) (Math.random()
                    * ((PresentateAsTable.MAX_RANDOM_NUMBER
                            - PresentateAsTable.MIN_RANDOM_NUMBER) + 1))
                    + PresentateAsTable.MIN_RANDOM_NUMBER);
        }

        return numbers;
    }

    /**
     * Оголошен приватним для заборони створення класу.
     */
    private PresentateAsTable() {

    }

}
